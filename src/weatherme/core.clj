(ns weatherme.core
  (:gen-class)
  (:require [weatherme.weather-call :as call]
            [weatherme.db :as db]
            [clojure.java.jdbc :as j]))


; steps for insert/read/print
; - (println "About to insert..." weather)
; - (j/insert! db/spec :snapshot {:summary weather})
; - (println "Current DB state:" (j/query db/spec "select * from snapshot"))

(defn -main
  [& args]
  (println "Getting your weather... ")
  (let [weather (call/report-weather 39.7392, -104.9903)]))
