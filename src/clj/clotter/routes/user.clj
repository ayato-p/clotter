(ns clotter.routes.user
  (:require [compojure.core :refer [defroutes GET]]
            [ring.util.http-response :refer [ok bad-request content-type]]
            [clotter.db.core :as db]
            [struct.core :as st]
            [cheshire.core :as ch]))

(def user-schema
  [[:id
    st/required
    st/string
    {:message "User ID must be under 20 characters (Alphabets, Numbers, _)."
     :vaildate (fn [x] (and (some? (re-find #"^\w+$" x)) (<= (count x) 20)))}]
   [:email
    st/required
    st/email]
   [:pass
    st/required
    st/string]])

(defn validate-user [user]
  (st/validate user user-schema))

(defn save-user! [{:keys [params]}]
  (if-let [errors (validate-user params)]
    (-> (ch/generate-string {:errors errors})
        bad-request
        (content-type "application/json"))
    (do
      ;; (db/create-user!
      ;;  (assoc params :admin false :last-login (java.time.LocalDateTime/now) :is-active true))
      (ch/generate-string {:userId (get params :id)}))))
