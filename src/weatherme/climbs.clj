(ns weatherme.route-call
    (:require [clj-http.client :as client]
              [cheshire.core :as json]
              [clj-time.coerce :as c]
              [clj-time.core :as t]
              [clojure.java.io :as io]
              [weatherme.weather-call :as weather]))

(defn get-specific-routes
  "@todo"
  [route-id]
  (let [routes
        (client/get
         (apply str "https://www.mountainproject.com/data/get-routes?routeIds=" route-id "&key=" (clojure.string/trim-newline (slurp (io/resource "mtnproj.secret")))) {:as :json})]
    (get-in routes [:body])))


(defn get-location-routes [lat lon]
  (let [routes
        (client/get
         (apply str "https://www.mountainproject.com/data/get-routes-for-lat-lon?lat=" lat "&lon=" lon "&maxDistance=10&minDiff=5.7&maxDiff=5.11&key=" (clojure.string/trim-newline (slurp (io/resource "mtnproj.secret"))) ) {:as :json})]
    routes))


(def thing (get-location-routes 43.07 -104.99))

(:body thing)
(weather/report-weather 43.0731 -89.4012)
