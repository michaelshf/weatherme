(ns weatherme.climbs-test
  (:require [clojure.test :refer :all]
            [weatherme.core :refer :all]
            [weatherme.climbs :as climbs]))

(deftest get-routes-test
  (testing "A specific route can be gotten"
    (let [route-id 105982126]
      (is (= "Leftwing"
             (->> route-id
                  (climbs/get-specific-routes)
                  (:routes)
                  (first)
                  (:name)))))))


