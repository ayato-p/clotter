(ns clotter.tweet-card)

(defn tweet [t]
  [:li
    [:a {:href (str "/user/" (:user-id t))}
      [:img {:src (:thumbnail t) :width 64 :height 64}]]
    [:div.content {:data-tweet-id (:id t)}
     [:h3.userName [:a {:href (str "/usr" (:user-id t))} (:user-name t)] [:span (:user-id t)]]
      [:p (:content t)]
      [:ul.actionArea
       [:li.reply [:a {:on-click #(println (:id t))} (:reply t)]]
       [:li.retweet [:a {:on-click #(println (:id t))} (:retweet t)]]
       [:li.favorite [:a {:on-click #(println (:id t))} (:favorite t)]]]]])
