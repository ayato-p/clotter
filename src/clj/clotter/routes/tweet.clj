(ns clotter.routes.tweet
  (:require [compojure.core :refer [defroutes GET POST]]
            [cheshire.core :as ch]
            [clotter.db.core :as db]))

(defn list-timeline [user-id limit offset]
  (db/show-timeline {:user_id "todokr" :limit 10 :offset 10}))