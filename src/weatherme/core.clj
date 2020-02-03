(ns weatherme.core
  (:gen-class)
  (:require [weatherme.weather :as weather]
            [weatherme.db :as db]
            [clojure.java.jdbc :as j]))


; steps for insert/read/print
; - (println "About to insert..." weather)
; - (j/insert! db/spec :snapshot {:summary weather})
; - (println "Current DB state:" (j/query db/spec "select * from snapshot"))

(defn -main
  [& args]
  (println "Getting your weather... ")
  (weather/insert-weather 39.9 -105))
