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

(defn current-weather
  "Produces weather for right now."
  [lat lon]
  (let [wss
        (client/get
         (str
          "https://api.darksky.net/forecast/"
          (clojure.string/trim-newline (slurp (io/resource "forecast.secret")))
          "/" lat "," lon) {:as :json})]
    (get-in wss [:body :currently])))


(defn future-weather
  "Produces weather next 7 days."
  [lat lon]
  (let [wss
        (client/get
         (str
          "https://api.darksky.net/forecast/"
          (clojure.string/trim-newline (slurp (io/resource "forecast.secret")))
          "/" lat "," lon) {:as :json})]
    (get-in wss [:body :daily :data])))


(defn previous-weather
  "Produces weather for highest temp previous day."
  [lat lon]
  (let [wss
        (client/get
         (str
          "https://api.darksky.net/forecast/"
          (clojure.string/trim-newline (slurp (io/resource "forecast.secret")))
          "/" lat "," lon ","
          (int (/ (c/to-long (t/minus (t/now) (t/days 1))) 1000)))
         {:as :json})]
    (get-in wss [:body])))

(defn insert-weather
  "Writes next day (forecast) and previous day (actual) rows into
   a db."
  [lat lon]
  (let [db-spec wdb/pg-db
        tablename "forecasts"
        colvec ["id" "rowtype" "temp" "date_about" "day_distance" "timestamp"]
        previoustype "previous"
        futuretype "future"]
 ; first forecast row
    (wdb/insert-row
       db-spec
       tablename
       colvec
       [(java.util.UUID/randomUUID)
        futuretype
        (:temperatureHigh (first (future-weather lat lon)))
        (str (t/plus  (t/today) (t/days 1)))
        1])
; previous forecast row
    (wdb/insert-row
       db-spec
       tablename
       colvec
       [(java.util.UUID/randomUUID)
        previoustype
        (:temperatureHigh (first (:data (:daily (previous-weather lat lon)))))
        (str (t/minus  (t/today) (t/days 1)))
        -1])))

(defn accuracy-check
  "Will: Compare 'actual' with forecasts, first one day forward - 
   eventually 1 through 7 days."
  []
  ;; previous weather path to temp high:
                                        ;(:temperatureHigh (first (:data (:daily (previous-weather lat lon))))

  ;; future weather path to temp high:

)

;; 

;; @todo move to testing

