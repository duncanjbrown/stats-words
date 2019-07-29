(ns stats-words.transform-test
  (:require
   [stats-words.transform :as transform]
   [cljs.test :as t]))

(let [lines ["A" "B" "C" "D"]]
  (t/deftest reordering-lines-test
    (let [permutations (take 5 (iterate transform/shuffle-lines lines))]
      (t/is (some? #(not= lines %))))))

