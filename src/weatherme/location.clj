(ns weatherme.location
  (:require [clojure.string :as str]
            [clojure.java.io :as io]
            [clj-http.client :as client]
            [cheshire.core :as json]))


(defn locale
  "Takes a City & State Code (ie CO, CA, WI) 
   Returns Google Geolocation Results."
  [city state-code]
  (client/get
   (str "https://maps.googleapis.com/maps/api/geocode/json?address=" city "," state-code "&key=" (slurp (io/resource "maps.secret")))))

(defn lat-lng
  "Takes a City & State Code
   Calls locale to hit google api
   Returns map of lat & lng with float vals."
  [city state-code]
  (get-in (first
           (get (json/parse-string (:body (locale city state-code))) "results"))
          ["geometry" "location"]))

;; @todo move to testing
(def locale-denver
  (client/get
   (str "https://maps.googleapis.com/maps/api/geocode/json?address=Denver,CO&key=" (slurp (io/resource "maps.secret")))))

(def lat-lng-denver
  (get-in (first
           (get (json/parse-string (:body locale-denver)) "results"))
          ["geometry" "location"]))

