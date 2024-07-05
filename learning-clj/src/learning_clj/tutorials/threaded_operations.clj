(ns learning-clj.tutorials.threaded-operations)

(def numbers '(1 2 3 4 5 6 7 8 9 10))
(filter #(even? %) (take 8 numbers))
;; the same can be accomplished as below:
(->> numbers
     (take 8)
     (filter #(even? %)))