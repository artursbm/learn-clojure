(ns learning-clj.domain.finance
  (:require [clojure.spec.alpha :as s]))

;; defining model specs/validations
(s/def :money/amount int?)
(s/def :currency/divisor int?)
(s/def :currency/sign string?)
;; the below code will make desc field to be nilable, which makes it optional in practice
(s/def :currency/desc (s/nilable string?))
;; the below code automatically verifies that code is string and is contained in the valid set
(s/def :currency/code (and string? #{"USD" "BRL" "UKG"}))


;; As is often the case, we haven't been using fully qualified keys for our Currency entities so far,
;; so we require them with :req-un and :opt-un. This tells clojure.spec that an unqualified key is also fine.
;; If we had been using fully qualified keys diligently, we'd have registered with :req and :opt.
(s/def :finance/currency (s/keys :req-un [:currency/divisor
                                          :currency/sign
                                          :currency/code]
                                 :opt-un [:currency/desc]))


(def currencies
  {:usd {:divisor 100 :code "USD" :sign "$"
         :desc "US Dollars"}
   :brl {:divisor 100 :code "BRL" :sign "R$"
         :desc "Brazilian Real"}
   :ukg {:divisor (* 17 29) :code "UKG" :sign "ʛ"
         :desc "Galleons of the United Kingdom"}})

(s/valid? :finance/currency (:usd currencies))
(s/valid? :currency/divisor (:divisor (:usd currencies)))

(def default-curr "makes default currency BRL" (:brl currencies))

(defn make-money
  "takes currency and creates amount 
   of money in that currency"
  ([] {:amount 0 :currency default-curr})
  ([amount] {:amount amount :currency default-curr})
  ([amount currency] {:amount amount :currency currency}))

(defn- audit-transaction
  "method to create audit for a transaction"
  [transaction]
  (println (str "Auditing: " transaction))
  transaction)

(defn make-transaction
  "creates a transaction and generates audit for it"
  [trx-type trx-direction account-id amount & details]
  (let [timestamp (quot (System/currentTimeMillis) 1000)
        transaction {:transaction-type trx-type
                     :transaction-direction trx-direction
                     :account-id account-id
                     :amount amount
                     :created-at timestamp
                     :details details}]
    (audit-transaction transaction)))
