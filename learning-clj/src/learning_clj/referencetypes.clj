(ns learning-clj.referencetypes)

(def num-zero (atom 0))
(def num-one (atom 1))

;; (+ 1 (deref num-zero))
(+ @num-zero @num-one 2) ;; => 3

;; using swap! to change atom's reference value
(swap! num-zero inc) ;; adds 1 to num-zero value
;; now, num-zero is an atom that has value 1, 
;; but its reference is the same as before
;; @num-zero ;; => 1

;; it's possible to swap using a function with multiple args
(swap! num-zero + 32) ;; => 33

;; to make the atom get a new value
;; regardless its current state, use reset!
(reset! num-zero 0)

;;;; Now with refs
(def colors (ref ["red", "green"]))
(deref colors)
@colors

(conj @colors "blue") ;; this doesn't change the colors reference values, 
                      ;; it just works with them to produce a new vector
@colors ;; => ["red" "green"]



