(ns weatherme.route-call
    (:require [clj-http.client :as client]
              [cheshire.core :as json]
              [clj-time.coerce :as c]
              [clj-time.core :as t]
              [clojure.java.io :as io]
              [weatherme.weather-call :as weather]))

(defn get-specific-routes [route-id]
  (let [routes
        (client/get
         (apply str "https://www.mountainproject.com/data/get-routes?routeIds=" route-id "&key=" (clojure.string/trim-newline (slurp (io/resource "mtnproj.secret")))) {:as :json})]
    (get-in routes [:body])))


(defn get-location-routes [lat lon]
  (let [routes
        (client/get
         (apply str "https://www.mountainproject.com/data/get-routes-for-lat-lon?lat=" lat "&lon=" lon "&maxDistance=1&minDiff=5.3&maxDiff=5.13&key=" (clojure.string/trim-newline (slurp (io/resource "mtnproj.secret"))) ) {:as :json})]
    routes))


(get-location-routes 43.07 -104.99)
(weather/report-weather 43.0731 -89.4012)
