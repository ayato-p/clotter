(ns clotter.routes.home
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

(defn about-page []
  (println "about")
  (uploadToS3 (java.io.FileInputStream. "resources/public/img/warning_clojure.png") "imagetest" 100 100 )

  (layout/render "about.html"))

(defn app-page [req]
  (clojure.string/join "\n" (tw/list-timeline "todokr" 10))

  ;(layout/render "app.html")
  )

(defn health-check []
  (-> (ch/generate-string {:status "ok"})
      ok
      (content-type "application/json")))

(defn login-page []
  (layout/render "login.html"))

;; login
(defn- login [{:keys [user pass]}]
  (-> (response (str "user: " user ", pass: " pass ", hashed: " (hashers/encrypt user)))
      (assoc-in [:session :identity] (keyword user))
      (assoc :headers {"Content-Type" "text/plain"})))

(defn login-post [req]
  (let [user (get-in req [:form-params "user"])]
    (-> (redirect (str "/" (get-in (:query-params req) ["next"] "app")))
        (assoc-in [:session :identity] (keyword user)))))

(defn set-sid! [id {session :session}]
  (-> (redirect "/mypage")
      (assoc :session (assoc session :sid id))))

(defn remove-sid! [{session :session}]
  (-> (response "remove sid")
      (assoc :session (dissoc session :sid))
      (assoc :headers {"Content-Type" "text/plain"})))

(defn show-sid [{session :session}]
  (-> (response (str "session: " (:sid session)))
      (assoc :headers {"Content-Type" "text/plain"})))

(defn clear-session! []
  (-> (response "Session cleared")
      (dissoc :session)
      (assoc :headers {"Content-Type" "text/plain"})))

(defroutes home-routes
  (GET "/" [] (login-page))
  (GET "/app" req (app-page req))
  (POST "/login" req (login-post req))
  (GET "/health" [] (health-check))
  (GET "/about" req "abput")
  (GET "/mypage" req (show-sid req))
  (GET "/remove" req (remove-sid! req))
  (GET "/logout" req (clear-session!)))
