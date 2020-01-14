(ns weatherme.weather-call
    (:require [clj-http.client :as client]
              [cheshire.core :as json]
              [clj-time.coerce :as c]
              [clj-time.core :as t]
              [clojure.java.jdbc :as db]
              [jdbc.pool.c3p0 :as pool]
              [clojure.java.io :as io]
              [clojure.string :as str]))


(defn report-weather [lat lon]
  (let [wss
        (client/get
         (str
          "https://api.darksky.net/forecast/"
          (clojure.string/trim-newline (slurp (io/resource "forecast.secret")))
          "/" lat "," lon) {:as :json})]
  (get-in wss [:body :currently :temperature])))



(clojure.pprint/pprint (report-weather 39.73 -104.99))
