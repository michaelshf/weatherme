(ns weatherme.weather
    (:require [clj-http.client :as client]
              [cheshire.core :as json]
              [clj-time.coerce :as c]
              [clj-time.core :as t]
              [clojure.java.io :as io]
              [clojure.string :as str]
              [weatherme.db :as wdb]
              [weatherme.location :as location]))

;; Generic goals of namespace:
; - write forecasts to a db, with stats + forecast distance
; - be able to compare a "current" weather request with a forecasted one

(defn current-weather [lat lon]
  (let [wss
        (client/get
         (str
          "https://api.darksky.net/forecast/"
          (clojure.string/trim-newline (slurp (io/resource "forecast.secret")))
          "/" lat "," lon) {:as :json})]
    (get-in wss [:body :currently])))

(defn future-weather [lat lon]
  (let [wss
        (client/get
         (str
          "https://api.darksky.net/forecast/"
          (clojure.string/trim-newline (slurp (io/resource "forecast.secret")))
          "/" lat "," lon) {:as :json})]
    (get-in wss [:body :daily :data])))

(defn write-tomorrow-forecast
  "Takes a lat + lon
   Writes a stats with a forecast distance of 1. "
  [lat lon]
  )

(defn accuracy-check
  ""
  [])

;; @todo move to testing
(def weather-body  (future-weather 39.7 -105))
(def current-weather-denver (current-weather
                             (get location/lat-lng-denver "lat")
                             (get location/lat-lng-denver "lng")))
(keys current-weather-denver)

(def future-weather-denver (future-weather
                             (get location/lat-lng-denver "lat")
                             (get location/lat-lng-denver "lng")))

(class future-weather-denver)
(convert-time (:time (nth future-weather-denver 0))) ; 0=> today
(convert-time (:time (nth future-weather-denver 1))) ; 1=> tomorrow, etc
(convert-time (:time (nth future-weather-denver 7))) ; 7=> one week, last predict

(defn convert-time
  [forecast-api-time]
  (c/to-sql-time (c/from-long (* 1000 forecast-api-time))))

