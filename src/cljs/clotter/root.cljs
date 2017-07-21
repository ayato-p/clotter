(ns clotter.root
  (:require [reagent.core :refer [atom]]
            [clotter.header :refer [header]])
  (:import goog.History
           goog.history.EventType))

(enable-console-print!)

;; state
(def header-state (atom nil))

(defn item2 [val]
  [:div  @val])

(defn handler [value]
  (println "value: " value))

(defn root-component []
  [:div
   (header)
   (item2 header-state)])
