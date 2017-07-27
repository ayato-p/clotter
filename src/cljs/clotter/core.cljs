(ns clotter.core
  (:require-macros [secretary.core :refer [defroute]])
  (:import goog.History)
  (:require [clotter.root :as root]
            [secretary.core :as secretary]
            [goog.events :as events]
            [goog.history.EventType :as EventType]
            [reagent.core :as r :refer [atom]]))

(enable-console-print!)

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
  )

(def app-state (atom {}))

(defn hook-browser-navigation! []
  (doto (History.)
    (events/listen
     EventType/NAVIGATE
     (fn [event]
       (secretary/dispatch! (.-token event))))
    (.setEnabled true)))


(defmulti current-page (fn [] (:page @app-state)))
(defmethod current-page :home [] [root/root-component])
(defmethod current-page :about [] [:div [:h1 "about"] [:a {:href "/#/"} "Home"]])
(defmethod current-page :mypage [] [:div [:h1 "mypage"]])
(defmethod current-page :user [] [:div [:h1 (:id @app-state)]])
(defmethod current-page :default [] [:div "oh..."])

(defn app-routes []
  (secretary/set-config! :prefix "#")
  (defroute "/" []           (swap! app-state assoc :page :home))
  (defroute "/about" []      (swap! app-state assoc :page :about))
  (defroute "mypage" []      (swap! app-state assoc :page :mypage))
  (defroute "/user/:id" [id] (swap! app-state assoc :page :user :id id))

  (hook-browser-navigation!))

(defn render-root []
  (let [entrypoint (.getElementById js/document "app")]
    (println @app-state)
    (app-routes)
    (r/render [current-page] entrypoint)))

(defn init! [] (render-root))
