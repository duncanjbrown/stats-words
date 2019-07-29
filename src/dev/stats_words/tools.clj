(ns stats-words.tools
  (:require [shadow.cljs.devtools.api :as shadow]))

(defn start-repl! []
  (shadow.cljs.devtools.server/start!)
  (shadow.cljs.devtools.api/watch :test)
  (shadow.cljs.devtools.api/watch :app)
  (shadow.cljs.devtools.api/nrepl-select :app))

(start-repl!)
