(ns learning-clj.tutorial)

(import 'java.time.LocalTime)
(import 'java.lang.Math)

(def color ["red" "green"])
(def rgb (conj color "blue"))

; below there is not a pure function, because it
; fails on having referential transparency 
(defn silly-calculation
  "multiply or add depending on local time"
  [a b]
  (let [now (LocalTime/now)]
    (if (< (.getHour now) 12)
      (* a b)
      (+ a b))))

(defn fetch-name
  "gets the name from a file"
  []
  (slurp "/Users/artur.mello/Git/personal/learn-clojure/learning-clj/resources/name.txt"))

(defn print-name
  "fetches name from file and prints to output"
  [message]
  (println message (fetch-name)))

(print-name "Hello, your name is ")

(defn testfn [test] (println (str "Hey, " test)))
(testfn "Artur")

(silly-calculation 2 3)

(defn lambda-func [] #(println "Writing a anonymous function (lambda) in clojure macro way"))
(lambda-func)

; do evaluates all forms in it, returning only the last expression
(do
  (println "logs can come here, but we only return the last expression eval")
  (long (Math/pow 2 10)))

; the above is the same as:
((fn [] 
  (println "logs can come here, but we only return the last expression eval")
  (long (Math/pow 2 10))))

; because do is implicit in function bodies in clojure
;; (fn [name]
;;   (do
;;     (println "Name param is" name)
;;     (str "Hello " name)))

