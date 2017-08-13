(ns clotter.routes.api
  (:require [compojure.core :refer [defroutes GET POST]]
            [clotter.service.tweet :as tw]))

(defn list-timeline [{{:keys [user limit offset]} :params}]
  (str user ", " limit ", " offset)
  ;(tw/list-timeline user limit offset)
  )

(defroutes api-routes
  (GET "/api/timeline" req (list-timeline req)))
