(ns weatherme.core
  (:gen-class)
  (:require [weatherme.weather-call :as call]))

(defn -main
  [& args]
  (println "Getting your weather... ")
  (call/report-weather 43.0731 -89.4012))
