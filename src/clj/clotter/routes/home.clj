(ns clotter.routes.home
  (:require [clotter.layout :as layout]
            [compojure.core :refer [defroutes GET]]
            [ring.util.http-response :refer [ok content-type]]
            [clojure.java.io :as io]
            [clotter.db.core :as db]
            [ring.util.response :refer [redirect]]
            [struct.core :as st]
            [cheshire.core :as ch]))

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
  (layout/render "about.html"))

(defn health-check []
  (-> (ch/generate-string {:status "ok"})
      ok
      (content-type "application/json")))

(defroutes home-routes
  (GET "/" [] (index-page))
  (GET "/health" [] (health-check))
  (GET "/about" [] (about-page)))
