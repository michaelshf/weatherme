(ns weatherme.core
  (:gen-class)
  (:require [weatherme.api-caller :as caller]))

(defn -main
  [& args]
  (println "Getting your weather... ")
  (caller/report-weather))
