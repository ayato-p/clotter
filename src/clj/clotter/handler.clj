(ns clotter.handler
  (:require [compojure.core :refer [routes wrap-routes]]
            [compojure.route :as route]
            [clotter.env :refer [defaults]]
            [mount.core :as mount]
            [clotter.middleware :as middleware]
            [ring.util.response :refer [redirect]]
            [buddy.auth :refer [authenticated?]]
            [buddy.auth.backends.session :refer [session-backend]]
            [buddy.auth.middleware :refer [wrap-authentication wrap-authorization]]
            [buddy.auth.accessrules :refer [wrap-access-rules]]
            [clotter.layout :refer [error-page]]
            [clotter.routes.public :refer [public-routes]]
            [clotter.routes.entry :refer [entry-routes]]
            [clotter.routes.api :refer [api-routes]]))

(mount/defstate init-app
                :start ((or (:init defaults) identity))
                :stop  ((or (:stop defaults) identity)))

(defn unauthorized-handler
  [req meta]
  (if (authenticated? req)
    (redirect (:uri req))
    (-> (redirect (format "/login?next=%s" (:uri req)))
        (assoc :flash "Signup or Signin please!"))))

(def app-routes
  (let [rules [{:pattern #".*" :handler authenticated?}]
        backend (session-backend {:unauthorized-handler unauthorized-handler})]
    (routes
     (-> public-routes
         (wrap-routes middleware/wrap-csrf)
         (wrap-routes middleware/wrap-formats))
     (-> #'entry-routes
         (wrap-access-rules {:rules rules :policy :allow})
         (wrap-authentication backend)
         (wrap-authorization backend)
         (wrap-routes middleware/wrap-csrf)
         (wrap-routes middleware/wrap-formats))
     (-> #'api-routes
         (wrap-access-rules {:rules rules :policy :allow})
         (wrap-authentication backend)
         (wrap-authorization backend)
         (wrap-routes middleware/wrap-csrf)
         (wrap-routes middleware/wrap-formats))
    (route/not-found
      (:body
        (error-page {:status 404
                     :title "page not found"}))))))


(defn app [] (middleware/wrap-base #'app-routes))
