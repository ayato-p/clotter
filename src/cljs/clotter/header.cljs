(ns clotter.header
  (:require [reagent.core :as r :refer [atom]]))

(defn header []
  [:header.navbar.navbar-default
   [:div.container-fluid
    [:nav.collapse.navbar-collapse
    [:ul.nav.navbar-nav
     [:li [:a [:i.fa.fa-home] "Home"]]
     [:li [:a [:i.fa.fa-bell-o] "Notification"]]]]]])
