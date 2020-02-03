;; on accuracy:
; consider needing to run at same time... still
; 25 hours v 1 hour forecasts will really vary in accuracy probably

;; Example db structure, from run on 1/27/2020
;; id | rowtype | temp | timestamp
;; 1  | forecast| 50   | 1/28/2020
;; 2  | actual  | 45   | 1/26/2020

;; Longterm db structure, 3 days run in a row
;; id | rowtype | temp | timestamp
;; 1  | forecast| 50   | 1/28/2020 => run from 1/27
;; 2  | actual  | 46   | 1/26/2020 => run from 1/27
;; 3  | forecast| 60   | 1/29/2020 => run from 1/28
;; 4  | actual  | 51   | 1/27/2020 => run from 1/28
;; 5  | forecast| 40   | 1/30/2020 => run from 1/29
;; 6  | actual  | 59   | 1/28/2020 => run from 1/29

;; need three days for original forecast to make prediction
;; - cuts to only two days if I post a current date also
;; - - technical the actual could be from today


;; (defn convert-time
;;   [forecast-api-time]
;;   (c/to-sql-time (c/from-long (* 1000 forecast-api-time))))


;; (def weather-body  (future-weather 39.7 -105))
;; (def current-weather-denver (current-weather
;;                              (get location/lat-lng-denver "lat")
;;                              (get location/lat-lng-denver "lng")))
;; (keys current-weather-denver)

;; (def future-weather-denver (future-weather
;;                              (get location/lat-lng-denver "lat")
;;                              (get location/lat-lng-denver "lng")))

;; (class future-weather-denver)
;; (convert-time (:time (nth future-weather-denver 0))) ; 0=> today
;; (convert-time (:time (nth future-weather-denver 1))) ; 1=> tomorrow, etc
;; (convert-time (:time (nth future-weather-denver 7))) ; 7=> one week, last predict


