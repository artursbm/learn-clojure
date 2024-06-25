(ns learning-clj.core
  (:gen-class)
  (:require [cider.nrepl :refer (cider-nrepl-handler)]
            [learning-clj.tutorial :as tutorial])

; TODO check how to make the REPL start from the project main
; [nrepl.server :as nrepl-server])

  (defn -main
    "I don't do a whole lot ... yet."
    [] (tutorial/print-name))