(ns weatherme.util
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))

(defn get-secret
  "Takes file name (string) that is present in resources.
   Gives the super secret code."
  [filename]
  (->> filename
       (io/resource)
       (slurp)
       (clojure.string/trim-newline)))
