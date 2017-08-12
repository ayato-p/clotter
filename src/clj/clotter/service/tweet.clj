(ns clotter.service.tweet
  (:require [compojure.core :refer [defroutes GET POST]]
            [cheshire.core :as ch]
            [clotter.db.core :as db]))

(defn list-timeline
  ([user-id] (list-timeline user-id 0 0))
  ([user-id limit] (list-timeline user-id limit 0))
  ([user-id limit offset] (db/show-timeline {:user_id user-id :limit limit :offset offset})))
