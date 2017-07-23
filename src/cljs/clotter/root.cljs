(ns clotter.root
  (:require [reagent.core :refer [atom]]
            [clotter.header :refer [header]]
            [clotter.profile :refer [profile]]
            [clotter.timeline :refer [timeline]]
            [clotter.input-field :refer [input-field]])
  (:import goog.History
           goog.history.EventType))

(enable-console-print!)

;; state
(def header-state (atom nil))

(defn item2 [val]
  [:div  @val])

(defn handler [value]
  (println "value: " value))

(def tweets [{:id 1
              :user-name "todokr"
              :user-id "todokr"
              :thumbnail "/img/loading.gif"
              :content "hello! world!"
              :created-datetime (.getTime (js/Date.))
              :reply 3
              :retweet 2
              :favorite 1}
             {:id 2
              :user-name "todokr"
              :user-id "todokr"
              :thumbnail "/img/loading.gif"
              :content "goooooooooooooooo!"
              :created-datetime (.getTime (js/Date.))
              :reply 11
              :retweet 22
              :favorite 33}
             {:id 3
              :user-name "todokr"
              :user-id "todokr"
              :thumbnail "/img/loading.gif"
              :content "gfodfdofdfo!"
              :reply 0
              :retweet 0
              :favorite 1}])

(defn root-component []
  [:div
   [header]
   [:div.wrapper
    [:aside [profile]]
    [:main
     [input-field]
     [timeline tweets]]]])
