(ns learning-clj.tutorial)

(import 'java.time.LocalTime)

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
  (slurp "Users/artur.mello/Git/personal/clojure-code/learning-clj/resources/name.txt"))

(defn print-name
  "fetches name from file and prints to output"
  [name]
  (println "Your name is" name))

(defn testfn [test] (println (str "Hey, " test)))
(testfn "Artur")

(silly-calculation 2 3)
