(ns clotter.env
  (:require [clojure.tools.logging :as log]))

(def defaults
  {:init
   (fn []
     (log/info "\n-=[clotter started successfully]=-"))
   :stop
   (fn []
     (log/info "\n-=[clotter has shut down successfully]=-"))
   :middleware identity})
