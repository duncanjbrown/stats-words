(ns stats-words.core
  (:require [reagent.core :as r]
            [stats-words.transform :as transform]
            [clojure.string :as string]))

(defonce input (r/atom ""))
(defonce output (r/atom ""))
(defonce options (r/atom {:verses 1}))

(defn input-box []
  [:div
   "Enter words here"
   [:textarea {:value @input :on-change #(reset! input (-> % .-target .-value))}]])

(defn output-box []
  [:div
   "Output"
   [:textarea
    {:read-only true
     :value (string/join "\n" @output)}]])

(defn stats-transform [input options]
  (-> input
      string/split-lines
      transform/shuffle-lines))

(defn button []
  [:button {:on-click #(reset! output (stats-transform @input @options))} "Transform"])

(defn app []
  [:div#app
   [:h3 "Stats Song Generator"]
   [input-box]
   [button]
   [output-box]])

(defn ^:dev/after-load mount []
  (r/render [app] (.getElementById js/document "root")))

(defn init []
  (mount))
