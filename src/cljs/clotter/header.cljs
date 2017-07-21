(ns clotter.header
  (:require [reagent.core :as r :refer [atom]]))

(defn header []
  [:header {:className "header"}
   [:nav
    [:ul
     [:li [:a "Home"]]
     [:li [:a "Notification"]]]]])
