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
              :urls-in-content ["http://www.kaasan.info/archives/1982" "https://ferret-plus.com/610"]
              :created-datetime 1500879492000
              :reply 3
              :retweet 2
              :favorite 1}
             {:id 2
              :user-name "todokr"
              :user-id "todokr"
              :thumbnail "/img/loading.gif"
              :content "自分は停車場のブリッジを、上って、降りて、そうしてそれが線路をまたぎ越えるために造られたものだという事には全然気づかず、ただそれは停車場の構内を外国の遊戯場みたいに、複雑に楽しく、ハイカラにするためにのみ、設備せられてあるものだとばかり思っていました。"
              :created-datetime 1500705226000
              :reply 11
              :retweet 22
              :favorite 33}
             {:id 3
              :user-name "todokr"
              :user-id "todokr"
              :thumbnail "/img/loading.gif"
              :content "gfodfdofdfo!"
              :created-datetime 1500704226000
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
