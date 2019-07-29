(ns stats-words.transform)

(defn shuffle-lines [input]
  (->> input
       (remove nil?)
       shuffle))
