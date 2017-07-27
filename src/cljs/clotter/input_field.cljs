(ns clotter.input-field
  (:require [reagent.core :as r :refer [atom]]
            [clojure.string :as s]))

(enable-console-print!)

(defonce open-flg (atom false))
(defonce content (atom ""))
(defonce postable-flg (atom false))

(defonce field-height (atom nil))

(defn- calc-field-height [target]
  (max (-> target .-scrollHeight) (-> target .-offsetHeight)))

(defn- handle-content-change [this]
  (reset! content (-> this .-target .-value))
  (reset! field-height (calc-field-height (-> this .-target)))
  (reset! postable-flg (and (<= (count @content) 140) (not-empty @content))))

(defn input-field []
  [:div#inputField
   [:textarea {:placeholder "What's happening?"
               :value @content
               :style {:height @field-height}
               :on-focus #(reset! open-flg true)
               :on-blur #(reset! open-flg false)
               :on-change handle-content-change
               :on-key-down (fn [e]
                              (let [key-code (.-keyCode e)]
                                (println (.-shiftKey e))
                                (when (and (= key-code 13)
                                           (.-shiftKey e))
                                      (do
                                        (.preventDefault e)
                                        (reset! field-height nil)
                                        (reset! content "")))))}]
   [:div.actionArea {:class (if (or @open-flg (not (s/blank? @content))) "input-open" "input-close")}
    [:input.file {:type "file"}]
    [:p.count {:class (when-not @postable-flg "text-danger")} (count @content)]
    [:input.btn {:type "button"
                 :value "Tweet"
                 :class (if @postable-flg "btn-primary" "btn-default")
                 :disabled (not @postable-flg)
                 :on-click #(js/alert "hogehoge")}]]])
