(ns clotter.tweet-card)

(defn tweet [t]
  [:li.tweetCard
    [:a.userThumbnail {:href (str "/user/" (:user-id t))}
     [:img.thumbnail {:src (:thumbnail t) :width 42 :height 42}]]
    [:div.content {:data-tweet-id (:id t)}
     [:h3.userNames [:a {:href (str "/usr" (:user-id t))} [:span.userName (:user-name t)] [:span.userId (str "@" (:user-id t))]]]
     [:time (if (> (- (. js/Date now) (:created-datetime t)) (* 1000 60 60 24 3)) ;; 3日以内のツイートは「~ago」
              (.format (js/moment. (:created-datetime t)) "YYYY/MM/DD")
              (.fromNow (js/moment. (:created-datetime t))))]
      [:p (:content t)]
      [:ul.actionArea
       [:li.reply [:a {:on-click #(println (:id t))} [:i.fa.fa-reply] (:reply t)]]
       [:li.retweet [:a {:on-click #(println (:id t))} [:i.fa.fa-retweet] (:retweet t)]]
       [:li.favorite [:a {:on-click #(println (:id t))} [:i.fa.fa-star] (:favorite t)]]]]])

(defn fetch-og-info [url] ;; TODO
  )
