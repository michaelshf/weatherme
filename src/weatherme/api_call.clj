(ns weatherme.api-call
    (:require [clj-http.client :as client]
              [cheshire.core :as json]
              [clj-time.coerce :as c]
              [clj-time.core :as t]
              [clojure.java.io :as io]))






(defn report-weather []
  (let [wss
        (client/get
         (apply str
          "https://api.darksky.net/forecast/" (clojure.string/trim-newline (slurp (io/resource "secret.txt"))) "/43.0731,-89.4012") {:as :json})]
  (println "Your [MADISON] weather summary is:" (get-in wss [:body :currently :summary]))
  (println "The temp is: " (get-in wss [:body :currently :temperature]))))

