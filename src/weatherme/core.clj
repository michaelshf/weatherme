(ns weatherme.core
  (:gen-class)
  (:require [weatherme.api-call :as call]))

(defn -main
  [& args]
  (println "Getting your weather... ")
  (call/report-weather))
