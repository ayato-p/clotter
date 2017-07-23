(ns clotter.timeline
  (:require [clotter.tweet-card :refer [tweet]]))

(defn timeline [tweets]
  [:div#timeline
   [:ul
    (for [t tweets]
      ^{:key (:id t)}
      [tweet t])]])
