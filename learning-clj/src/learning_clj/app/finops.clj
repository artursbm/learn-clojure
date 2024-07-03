(ns learning-clj.app.finops
  (:require [learning-clj.domain.finance :as fin]
            [learning-clj.domain.account :as account]))

(def new-brl-wallet (fin/make-money))

(def am-account (account/make-account :main "9132910" "Artur" "Mello" new-brl-wallet))

(def new-usd-wallet (fin/make-money 0 (:usd fin/currencies)))


;; transactions happening
(def pix-to-brl-wallet (fin/make-money 1400.0))
(fin/make-transaction :pix
                      :in
                      (:account-id am-account)
                      (:amount pix-to-brl-wallet)
                      {:currency (:currency pix-to-brl-wallet)})

