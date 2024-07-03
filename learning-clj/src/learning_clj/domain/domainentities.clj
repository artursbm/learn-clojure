(ns learning-clj.domain.domainentities)

; currency
(def usd {:divisor 100
          :code "USD"
          :sign "US$"
          :desc "US Dollars"})
(def brl {:divisor 100
          :code "BRL"
          :sign "R$"
          :desc "Brazilian Reais"})

(def currencies {:usd usd :brl brl})

;;; HOW TO MAKE CONSTRUCTORS TO EASILY CREATE DIFFERENT MONEY OBJECTS?
(defn const-money [amount currency] {:amount amount :currency currency})

(def real-wallet (const-money 15000.0 (:brl currencies)))
real-wallet

;; lets extend the (const-money) constructor to have possible default values
(def default-curr (:brl currencies))

(defn const-money-with-default
  ([] {:amount 0 :currency default-curr})
  ([amount] {:amount amount :currency default-curr})
  ([amount currency] {:amount amount :currency currency}))

(def new-wallet (const-money-with-default))
new-wallet

(def brl-wallet (const-money-with-default 100))
brl-wallet

(def usd-wallet (const-money-with-default 100 (:usd currencies)))
usd-wallet

;;; BUILDING VALUES, EXPANDING CONSTRUCTOR WITH FUNCTIONS
;; (defn show-usd
;;   "creates a display string for US Dollars"
;;   [amount]
;;   (let [{:keys [divisor code sign desc]} (:usd currencies)
;;         dollars      (int (/ amount divisor))
;;         less-dollars (rem amount divisor)
;;         cents       (int (/ less-dollars 17))
;;         dimes         (rem less-dollars 29)]
;;     (str dollars " Dollars " cents " Cents and " dimes " Dimes")))
;; 
;; (defn show-money
;;   "display string for money entity"
;;   [{:keys [amount currency]}]
;;   (let [{:keys [divisor code sign desc]} (currency currencies)]
;;     (cond
;;       (= code "USD")
;;       (show-usd amount)
;;       :else (let [major (int (/ amount divisor)) minor (mod amount divisor)]
;;               (str sign major "." minor)))))
;; (defn make-money [amount currency]
;;   (let [money {:amount amount :currency currency}]
;;     (-> money (assoc :displayed (show-money money)))))
;; (make-money 123.438 (:usd currencies))

(defn audit-transaction
  "method that creates an audit record for a transaction"
  [transaction]
  ; printing is a side-effect
  (println (str "auditing: " transaction))
  transaction)

(defn make-transaction
  "creates a Transaction and generates an audit entry"
  [trx-type account-id amount & details]
  (let [timestamp (quot (System/currentTimeMillis) 1000)
        transaction {:transaction-type trx-type
                     :account-id       account-id
                     :details          details
                     :timestamp        timestamp
                     :amount           amount}]
    (audit-transaction transaction)))

(make-transaction
 :debit
 "12345-01"
 (:amount (const-money 32.45M (:usd currencies)))
 {:currency-desc (:desc (:currency (const-money 32.45M (:usd currencies))))
  :val-in-cents (* (:amount (const-money 32.45M (:usd currencies))) (:divisor (:currency (const-money 32.45 (:usd currencies)))))
  :display (str (:sign (:currency (const-money 32.45M (:usd currencies)))) " " (:amount (:currency (const-money 32.45 (:usd currencies)))))})