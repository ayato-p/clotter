(ns clotter.routes.public
  (:require [clotter.layout :as layout]
            [compojure.core :refer [defroutes GET POST]]
            [ring.util.http-response :refer [ok content-type]]
            [clojure.java.io :as io]
            [clotter.db.core :as db]
            [ring.util.response :refer [redirect response]]
            [struct.core :as st]
            [cheshire.core :as ch]
            [buddy.hashers :as hashers]
            [clotter.service.image :refer [uploadToS3]]
            [clotter.service.tweet :as tw]))

(def message-schema
  [[:name
    st/required
    st/string]

   [:message
    st/required
    st/string
    {:message "message must contain at least 10 characters"
     :validate #(> (count %) 9)}]])

(defn validate-message [params]
  (first (st/validate params message-schema)))

(defn index-page []
  (layout/render "index.html"))

(defn health-check []
  (-> (ch/generate-string {:status "ok"})
      ok
      (content-type "application/json")))

(defn login-page [{:keys [flash]}]
  (layout/render "login.html" {:flash flash}))

(defn login-post [req]
  (let [user (get-in req [:form-params "user"])]
    (-> (redirect (str "/" (get-in (:query-params req) ["next"] "app")))
        (assoc-in [:session :identity] (keyword user)))))

(defn remove-sid! [{session :session}]
  (-> (response "remove sid")
      (assoc :session (dissoc session :sid))
      (assoc :headers {"Content-Type" "text/plain"})))

(defn clear-session! []
  (-> (redirect "/")
      (dissoc :session)))

(defn signup-page []
  (layout/render "signup.html"))

(defn signup-post []
  ;; TODO session
  (redirect "/app?tutorial"))

(defroutes public-routes
  (GET "/" _ (index-page))
  (GET "/health" _ (health-check))
  (GET "/login" req (login-page req))
  (POST "/login" req (login-post req))
  (GET "/logout" req (clear-session!))
  (GET "/signup" _ (signup-page)))
