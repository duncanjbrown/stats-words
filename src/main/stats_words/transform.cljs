(ns stats-words.transform)

(defn shuffle-lines [input]
  (->> input
       (remove nil?)
       shuffle))

(defn create-verses [verse-count line-count input]
  (->> input
       (partition-all line-count)
       (take verse-count)
       (interleave (repeat ""))
       (drop 1)
       flatten))
