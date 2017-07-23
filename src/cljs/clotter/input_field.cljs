(ns clotter.input-field)

(defn input-field []
  [:div#inputField
   [:textarea {:placeholder "Hoody!"}]
   [:div.actionArea
   [:input.file {:type "file"}]
   [:p.count "10"]
   [:button "Tweet"]]])
