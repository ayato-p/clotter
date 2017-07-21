(ns clotter.core
  (:require [reagent.core :as r :refer [atom]]
            [clotter.root :as root]))

(enable-console-print!)
(println "This text is printed from src/figwheel-sample/core.cljs. Go ahead and edit it and see reloading in action.")

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
  )

(defn render-root []
  (let [entrypoint (.getElementById js/document "app")]
    (r/render [root/root-component] entrypoint)))

(defn init! [] (render-root))
