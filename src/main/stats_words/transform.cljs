(ns stats-words.transform
  (:require [clojure.string :as s]))

(defn shuffle-lines [input]
  (->> input
       (remove nil?)
       (remove s/blank?)
       shuffle))

(defn create-verses [verse-count line-count input]
  (->> input
       (partition-all line-count)
       (take verse-count)
       (interleave (repeat ""))
       (drop 1)
       flatten))
