(ns statute.facts-test
  (:require [clojure.test :refer [deftest is]]
            [statute.facts :as facts]))

(deftest chl-has-spec-basis
  (let [sb (facts/spec-basis "CHL")]
    (is (= 3 (count sb)))
    (is (every? :statute/law-number sb))))

(deftest unknown-jurisdiction-has-no-spec-basis
  (is (nil? (facts/spec-basis "ATL")))
  (is (nil? (facts/spec-basis "ZZZ"))))

(deftest coverage-is-honest
  (let [c (facts/coverage ["CHL" "JPN" "ATL"])]
    (is (= 3 (:requested c)))
    (is (= 1 (:covered c)))
    (is (= ["ATL" "JPN"] (:missing-jurisdictions c)))))

(deftest by-topic-filters
  (is (= ["chl.codigo-del-trabajo-dfl1-2002"]
         (mapv :statute/id (facts/by-topic "CHL" :labor))))
  (is (empty? (facts/by-topic "CHL" :environment)))
  (is (empty? (facts/by-topic "ATL" :labor))))
