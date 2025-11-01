(ns tutorial.print-test
  (:require [clojure.test :refer [deftest is]]
            [tutorial.print :as tp]))

(deftest hello-test
  (is (= "hello, duct!\n"
         (with-out-str (tp/hello {})))))
