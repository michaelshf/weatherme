(ns weatherme.weather-call
    (:require [clj-http.client :as client]
              [cheshire.core :as json]
              [clj-time.coerce :as c]
              [clj-time.core :as t]
              [clojure.java.io :as io]
              [clojure.string :as str]))


(defn report-weather [lat lon]
  (let [wss
        (client/get
         (str
          "https://api.darksky.net/forecast/" (clojure.string/trim-newline (slurp (io/resource "forecast.secret"))) "/" lat "," lon) {:as :json})]
  (println "Your [ROUTE] weather summary is:" (get-in wss [:body :currently :summary]))
  (println "The temp is: " (get-in wss [:body :currently :temperature]))))


