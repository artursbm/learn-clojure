(ns tutorial.print-test
  (:require [clojure.test :refer [deftest is]]
            [duct.test :as dt]))

(deftest system-test
  (is (= "hello, duct!\nGoodbye.\n"
         (with-out-str
           (dt/with-system [_sys (dt/run)]
             (println "Goodbye."))))))
