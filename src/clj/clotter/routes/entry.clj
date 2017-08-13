(ns clotter.routes.entry
  (:require [clotter.layout :as layout]
            [compojure.core :refer [defroutes GET POST]]
            [ring.util.http-response :refer [ok content-type]]
            [clojure.java.io :as io]
            [clotter.db.core :as db]
            [ring.util.response :refer [redirect response]]
            [struct.core :as st]
            [cheshire.core :as ch]))

(defn index []
  (layout/render "app.html"))

(defroutes entry-routes
  (GET "/app" _ (index)))
