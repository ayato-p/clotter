(ns leiningen.create-test-data
  (:require  [clojure.test :as t]
             [clojure.string :as s]
             [clojure.java.io :as io]))

;; users --------------------------------------------------

(defrecord User [id email auth_type last_login hashed_pass is_active])

(def users [(->User "todokr" "s.tadokoro0317+adminb@gmail.com" "ADM" "2017-07-29 10:00:00" "5E884898DA28047151D0E56F8DC6292773603D0D6AABBDD62A11EF721D1542D8" 1)
            (->User "rich" "s.tadokoro0317+t1@gmail.com" "NML" "2017-07-29 10:00:00" "5E884898DA28047151D0E56F8DC6292773603D0D6AABBDD62A11EF721D1542D8" 1)
            (->User "larry" "s.tadokoro0317+t2@gmail.com" "NML" "2017-07-29 10:00:00" "5E884898DA28047151D0E56F8DC6292773603D0D6AABBDD62A11EF721D1542D8" 1)
            (->User "aran" "s.tadokoro0317+t3@gmail.com" "NML" "2017-07-29 10:00:00" "5E884898DA28047151D0E56F8DC6292773603D0D6AABBDD62A11EF721D1542D8" 1)
            (->User "james" "s.tadokoro0317+t4@gmail.com" "NML" "2017-07-29 10:00:00" "5E884898DA28047151D0E56F8DC6292773603D0D6AABBDD62A11EF721D1542D8" 1)
            (->User "martin" "s.tadokoro0317+t5@gmail.com" "NML" "2017-07-29 10:00:00" "5E884898DA28047151D0E56F8DC6292773603D0D6AABBDD62A11EF721D1542D8" 1)
            (->User "brendan" "s.tadokoro0317+t6@gmail.com" "NML" "2017-07-29 10:00:00" "5E884898DA28047151D0E56F8DC6292773603D0D6AABBDD62A11EF721D1542D8" 1)
            (->User "guido" "s.tadokoro0317+t7@gmail.com" "NML" "2017-07-29 10:00:00" "5E884898DA28047151D0E56F8DC6292773603D0D6AABBDD62A11EF721D1542D8" 1)
            (->User "ken" "s.tadokoro0317+t8@gmail.com" "NML" "2017-06-20 10:00:00" "5E884898DA28047151D0E56F8DC6292773603D0D6AABBDD62A11EF721D1542D8" 1)
            (->User "yukihiro" "s.tadokoro0317+t9@gmail.com" "NML" "2017-07-29 10:00:00" "5E884898DA28047151D0E56F8DC6292773603D0D6AABBDD62A11EF721D1542D8" 0)
            (->User "hogehoge" "s.tadokoro0317+t10@gmail.com" "NML" "2017-07-29 10:00:00" "5E884898DA28047151D0E56F8DC6292773603D0D6AABBDD62A11EF721D1542D8" 1)])

(defn create-user-sql [users]
  (s/join "\n" (map (fn [u]
                      (str "INSERT INTO user (id, email, auth_type, last_login, hashed_pass, is_active) VALUES "
                           "('" (:id u) "', '" (:email u) "', '" (:auth_type u) "', '" (:last_login u) "', '"
                           (:hashed_pass u) "', " (:is_active u) ");"))
                    users)))

;; follow --------------------------------------------------

(defn- list-follows [users]
  (let [ids (map :id users)]
    (map (fn [id]
           {:user-id id
            :follow (disj (set (take (+ 2 (rand-int 8)) (repeatedly #(rand-nth ids)))) id)})
         ids)))

(defn- pair-follows [follows]
  (flatten
   (map (fn [fs]
         (map (fn [f]
                {:from (:user-id fs) :to f})
              (:follow fs)))
       follows)))

(defn- create-follows-sql [follows]
  (s/join "\n" (map (fn [follow]
                      (str "INSERT INTO follow (from_id, to_id) VALUES "
                           "('" (:from follow) "', '" (:to follow) "');")) follows)))

;; tweet --------------------------------------------------

(def tweet-phrase ["やわらか" "ポップコーン" "絶え間ない" "ダメージ" "ドッカン" "ダウンロード" "しいたけ" "愛" "キノコ" "中華"
                   "歯ブラシ" "ミヒマル" "思考" "インドア" "絶え間ない" "イケイケ" "一本" "チャンネル" "昔話" "おじさん" "アルパカ" "牧場" "禁止"
                   "ぶっちゃけ" "放題" "がっつき" "寝起き" "武装" "圧倒的" "サイクロン" "煙突" "床下" "デスマーチ" "ゆるふわ" "引火性" "大学" "解放戦線"
                   "警備" "ステージ" "筋肉" "ラップ" "クレイジー" "魂" "やるせない" "巻き込み" "さっぱり" "仏" "神" "河童" "探査機" "書記長" "委員長"
                   "住職" "自動" "プルプル" "業務用" "BUMP OF " "インフィニティ" "ナイトプール" "ケツカッチン" "グレーゾーン" "殲滅戦" "大戦争" "ゴマダレ"
                   "デビュー" "サクセス" "ガンギマリ" "爆速" "カリスマ" "カーニバル" "おいしい" "女子大生" "考古学" "助けて" "大炎上" "特殊部隊" "仙人" "暴動"
                   "管理人" "先生" "ぶっちゃけ" "極上" "ダイナマイト" "ユビキタス" "健康法" "決定版" "謝罪会見" "キムチチゲ" "ベリーマッチ" "音速" "チルドレン"
                   "ホラーショー" "めっちゃ" "ジェントルメン" "御一行様" "行くぞ!" "たまらねえ" "大好き☆" "特攻野郎" "おじいちゃん" "愚連隊"
                   "鉄道" "世界遺産" "ゴールデン" "ハイパー" "伝説" "最強" "銀河系" "やめろ！" "激おこ" "やっぱりやめる" "エンタープライズ" "ドリル" "攻略法"
                   "悲しみの" "いけない" "ルージュマジック" "退廃的" "上から下まで" "まさかとは思ったが" "がいいなあ…" "まとめ" "みんなの" "基礎からの"
                   "身体は単に私たちが『所有』する物理的物体ではなく、行為システム、プラクシスの様態であり、身体が日常生活の相互行為に実践的に埋め込まれていることが、自己アイデンティティの一貫した感覚の維持にとって重要なこととなる"
                   " https://docs.oracle.com/javase/jp/8/docs/api/ " " http://qiita.com/hatobus/items/69b353dea3d8f2a25441 "])

(def tweet-size 10000)

(defn create-tweet-sql [phrases tweet-size]
  (s/join "\n" (map (fn [t]
         (str "INSERT INTO tweet (user_id, content) VALUES "
              "('" (rand-nth (map :id users)) "', '" t "');"))
                    (take tweet-size (repeatedly
                                      (fn [] (s/join (take (+ (rand-int 2) 2)
                                                           (repeatedly #(rand-nth phrases))))))))))
;; favorite
(defn create-favorite-sql [users tweet-size] ;; TODO 重複をなくす
  (s/join "\n"
          (take tweet-size
                (repeatedly (fn []
                              (str "INSERT INTO tweet_favorite (user_id, tweet_id) VALUES "
                                   "('" (rand-nth (map :id users)) "', " (rand-int tweet-size) ");"))))))

;; retweet
(defn create-retweet-sql [users tweet-size] ;; TODO 重複をなくす
  (s/join "\n"
          (take tweet-size
                (repeatedly (fn []
                              (str "INSERT INTO tweet_retweet (user_id, tweet_id) VALUES "
                                   "('" (rand-nth (map :id users)) "', " (rand-int tweet-size) ");"))))))

;; reply
(defn create-reply-sql [tweet-size]
  (s/join "\n"
          (loop [i 1 acc []]
            (cond
              (> (- i 5) tweet-size) (reverse acc)
              (= (rem i 10) 0) (recur (+ i 1) (cons (str "INSERT INTO tweet_reply (to_id, from_id) VALUES (" i ", " (- i 5) ");") acc))
              :else (recur (+ i 1) acc)))))

(defn create-test-data [project]
  (let [f "docker/testdata.sql"]
    (io/delete-file f true)
    (spit f (create-user-sql users) :append true)
    (spit f (create-follows-sql (pair-follows (list-follows users))) :append true)
    (spit f (create-tweet-sql tweet-phrase tweet-size) :append true)
    (spit f (create-favorite-sql users tweet-size) :append true)
    (spit f (create-retweet-sql users tweet-size) :append true)
    (spit f (create-reply-sql tweet-size) :append true)
    ))
