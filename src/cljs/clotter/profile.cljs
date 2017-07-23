(ns clotter.profile
  (:require [reagent.core :as r]))

(defn profile []
  [:div#profile
   [:div.userInfo
    [:a.thumbnail {:href "/todo"}
     [:img {:src "/img/loading.gif" :width 64 :height 64}]]
    [:h2.userName
     [:a {:href "/todo"} "todokr"]]
    [:small.userId
     [:a {:href "/todo"} "@todokr"]]]
   [:ul.userActivity
    [:li
     [:h4 "Tweet"]
     [:p "1234"]]
    [:li
     [:h4 "Follow"]
     [:p "345"]]
    [:li
     [:h4 "Follower"]
     [:p "523"]]]])
