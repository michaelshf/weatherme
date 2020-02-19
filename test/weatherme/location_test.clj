(ns weatherme.location-test
  (:require [clojure.test :refer :all]
            [weatherme.location :as wloc]))

(deftest locale-test
  (testing "a test that geolocation is returned from a city and statecode"
    (is (=  :foo
            (wloc/locale "denver" "CO")))))
