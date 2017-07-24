(ns clotter.input-field
  (:require [reagent.core :as r :refer [atom]]))

(enable-console-print!)

(def open-flg (atom false))

(defn input-field []
  [:div#inputField
   [:textarea {:placeholder "Hoody!" :on-focus #(reset! open-flg true) :on-blur #(reset! open-flg false)}]
   [:div.actionArea {:class (if @open-flg "input-open" "input-close")}
    [:input.file {:type "file"}]
    [:p.count "10"]
    [:input.btn.btn-primary {:type "button" :value "Tweet" :on-click #(js/alert "hogehoge")}]]])
