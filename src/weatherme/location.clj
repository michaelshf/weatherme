(ns weatherme.location
  (:require [cheshire.core :as json]
            [clj-http.client :as client]
            [clojure.java.io :as io]
            [weatherme.util :as util]))

(defn locale
  "Takes a City & State Code (ie CO, CA, WI) 
   Returns Google Geolocation Results."
  [city state-code]
  (client/get
   (str "https://maps.googleapis.com/maps/api/geocode/json?address="
        city ","
        state-code "&key="
        (util/get-secret "maps.secret"))))

(defn lat-lng
  "Takes a City & State Code
   Calls locale to hit google api
   Returns map of lat & lng with float vals."
  [city state-code]
  (-> (locale state-code city)
      :body
      json/parse-string
      (get "results")
      first
      (get-in
       ["geometry" "location"])))

;; @todo move to testing
(def locale-denver
  (client/get
   (str "https://maps.googleapis.com/maps/api/geocode/json?address=Denver,CO&key="
        (slurp (io/resource "maps.secret")))))


(def lat-lng-denver
  (get-in (first
           (get (json/parse-string (:body locale-denver)) "results"))
          ["geometry" "location"]))
lat-lng-denver
(lat-lng-denver "lat")
(lat-lng-denver "lng")

