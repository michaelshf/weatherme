(ns weatherme.core
  (:gen-class)
  (:require [weatherme.weather-call :as call]
            [weatherme.db :as db]
            [clojure.java.jdbc :as j]))

(defn -main
  [& args]
  (println "Getting your weather... ")
  (let [weather (call/report-weather 41.0477 -112.1807)]
    (println "About to insert..." weather)
    (j/insert! db/spec :snapshot {:summary weather})
    (println "Current DB state:" (j/query db/spec "select * from snapshot"))))
