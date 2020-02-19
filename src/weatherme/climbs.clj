(ns weatherme.climbs
    (:require [clj-http.client :as client]
              [cheshire.core :as json]
              [clj-time.coerce :as c]
              [clj-time.core :as t]
              [clojure.java.io :as io]
              [weatherme.weather :as weather]))

(defn get-specific-routes
  "Takes a specific Mountain Project Route ID and returns
   stats about the route."
  [route-id]
  (let [routes
        (client/get
         (str "https://www.mountainproject.com/data/get-routes?routeIds="
              route-id "&key="
              (clojure.string/trim-newline
               (slurp
                (io/resource "mtnproj.secret")))) {:as :json})]
    (get-in routes [:body])))


(defn get-location-routes
  "Takes a lat and log and gives all routes
   from 5.7-5.13 within 10 miles."
  [lat lon]
  (let [routes
        (client/get
         (apply str "https://www.mountainproject.com/data/get-routes-for-lat-lon?lat=" lat "&lon=" lon "&maxDistance=10&minDiff=5.7&maxDiff=5.11&key=" (clojure.string/trim-newline (slurp (io/resource "mtnproj.secret"))) ) {:as :json})]
    routes))



; @todo move to scratch
(def thing (get-location-routes 43.07 -104.99))

(:body thing)
