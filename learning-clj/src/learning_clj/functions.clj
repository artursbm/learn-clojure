(ns learning-clj.functions)

(defn zero-arity-func [] (println "0-param-arity function with no param"))

(defn one-arity-func ([] zero-arity-func) ([msg] (println msg)))
; below will eval the one-arity-func without params, which means calling zero-arity-func
; as the definition of one-arity-func with no params is a function itself, I needed to
; call 2 evaluations (hence the double param) in order to have the REPL to eval the final value
((one-arity-func))
; here I'm calling the one-arity-func with 1 param, which means I'm calling the 1 parameter-arity 
; form of the one-arity-func function
(one-arity-func "Non default message with param sent")

; variable parameter number are possible using the & symbol before the "params..."
(defn hi-class [id & names] (println "Hey, class" id names))

; while printing the names params, it is printed as a list (Artur, Paula, Joao) since they are a list of params
(hi-class "3B" "Artur", "Paula", "Joao")

; anonymous functions defined with fn keyword
((fn [] (println "Hello, world")))

; apply function takes a function f with one or more FIXED arguments
; and applies it (invokes f) to the rest of the passed sequence
; IT MUST BE A SEQUENCE
  ; (defn plot [shape coords]   ;; coords is [x y]
  ; (plotxy shape (first coords) (second coords)))
; or instead:
  ; (defn plot [shape coords]
  ; (apply plotxy shape coords))

; let and Closures
; ;;      bindings     name is defined here
; ;;    ------------  ----------------------
; (let  [name value]  (code that uses name))

