(ns learning-clj.collections)

(def Person {:name "Artur" :id 123 :role "IC5" :payroll 19000.0})

(defn get-payroll "gets yearly payroll from Person's Id" []
  (* 12 (:payroll Person)))

(println (get-payroll))

; now with records

(defrecord Employee [name id role payroll])
(def AM (->Employee "Artur" 1234 "IC5" 19000.0))

;; using map factory building function
(def FR (map->Employee {:name "Felipe" :id 4321 :role "IC6" :payroll 25000.0}))

(def employees [AM, FR])

;; Domain entity relationships
;; Nested relations:
(def account {:account-id "1234-0" :account-type :checking})
(def am-account
  {:id 1232143311
   :first-name "Artur"
   :last-name "Mello"
   :account account})

(println am-account)

