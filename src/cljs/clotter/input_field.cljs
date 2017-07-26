(ns clotter.input-field
  (:require [reagent.core :as r :refer [atom]]
            [clojure.string :as s]))

(enable-console-print!)

(def open-flg (atom false))
(def content (atom ""))

(defn input-field []
  [:div#inputField
   [:textarea {:placeholder "What's happening?"
               :value @content
               :on-focus #(reset! open-flg true)
               :on-blur #(reset! open-flg false)
               :on-change #(reset! content (-> % .-target .-value))}]
   [:div.actionArea {:class (if (or @open-flg (not (s/blank? @content))) "input-open" "input-close")}
    [:input.file {:type "file"}]
    [:p.count "10"]
    [:input.btn.btn-primary {:type "button" :value "Tweet" :on-click #(js/alert "hogehoge")}]]])
