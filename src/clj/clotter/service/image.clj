(ns clotter.service.image
  (require [clojure.java.io :as io]
           [image-resizer.core :refer :all]
           [image-resizer.format :as format]
           [amazonica.core :refer [with-credential]]
           [amazonica.aws.s3 :as s3]
           [clotter.config :refer [env]]
           [digest :as d])
  (import [java.io InputStream]))

(defn uploadToS3 [^InputStream image ^String name w h]
  (let [is (format/as-stream (resize image w h) "jpg")]
    (with-credential [(-> env :aws-key) (-> env :aws-secret) (-> env :s3 :region)]
      (s3/put-object (-> env :s3 :bucket)
                   (str name ".jpg")
                   is
                   {:content-type "image/jpg"}))))
