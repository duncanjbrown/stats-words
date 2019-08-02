(ns stats-words.tools
  (:require [shadow.cljs.devtools.api :as shadow]
            [clojure.java.io :as io]))

(defn copy-assets
  {:shadow.build/stage :flush}
  [build-state & args]
  (println "Copying public/index.html to dist/index.html")
  (io/copy (io/file "public/index.html") (io/file "dist/index.html"))
  build-state)
