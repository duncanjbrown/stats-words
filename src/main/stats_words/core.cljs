(ns stats-words.core
  (:require [reagent.core :as r]
            [stats-words.transform :as transform]
            [clojure.string :as string]))

(defonce input (r/atom ""))
(defonce output (r/atom ""))
(defonce options (r/atom {:verse-count 4 :line-count 4}))

(defn input-box []
  [:div
   "Enter lines:"
   [:textarea {:value @input :on-change #(reset! input (-> % .-target .-value))}]])

(defn output-box []
  [:div
   "Output:"
   [:textarea
    {:read-only true
     :id "output"
     :value (string/join "\n" @output)}]])

(defn stats-transform [input options]
  (->> input
       string/split-lines
       transform/shuffle-lines
       (transform/create-verses (js/parseInt (:verse-count options)) (js/parseInt (:line-count options)))))

(defn numeric-options [min max selected]
  (map (fn [num]
         (if (= num (js/parseInt selected))
           (vector :option {:selected true :value num} num)
           (vector :option {:value num} num)))
       (range min max)))

(defn numeric-selector [description max option-key]
  [:label
   description
   (into [:select {:on-change #(swap! options assoc option-key (-> % .-target .-value))}]
         (numeric-options 1 max (option-key @options)))])

(defn button []
  [:button {:on-click #(reset! output (stats-transform @input @options))} "Transform"])

(defn controls []
  [:div
   [numeric-selector "Number of verses" 20 :verse-count]
   [numeric-selector "Number of lines per verse" 10 :line-count]
   [button]])

(defn app []
  [:div#app
   [input-box]
   [controls]
   [output-box]])

(defn ^:dev/after-load mount []
  (r/render [app] (.getElementById js/document "root")))

(defn init []
  (mount))
