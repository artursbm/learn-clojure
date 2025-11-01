(ns tutorial.print
  (:require [duct.logger :as logger]
            [integrant.core :as ig]))

#_(defn hello
  "The duct.logger/report function is used to emit a log at the :report level.
   This is a high-priority level that should be used sparingly, 
   as it also prints to STDOUT when using the REPL.
   
   {:keys [logger]} is implicitly injected from the duct framework
   system definition."
  [{:keys [logger level name]}]
  (logger/log logger level ::hello {:name name}))

(defmethod ig/init-key ::hello [_key {:keys [level logger name] :as opts}]
  (logger/log logger level ::hello {:name name})
  opts)

(defmethod ig/halt-key! ::hello [_key {:keys [level logger name]}]
  (logger/log logger level ::goodbye {:name name}))
