(ns user
  (:require [mount.core :as mount]
            clotter.core))

(defn start []
  (mount/start-without #'clotter.core/repl-server))

(defn stop []
  (mount/stop-except #'clotter.core/repl-server))

(defn restart []
  (stop)
  (start))


