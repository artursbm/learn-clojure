(ns learning-clj.domain.account)

(defn- rand-range-int [a b] (int (+ a (rand (- b a)))))
(defn- generate-account-id []
  (let [int-account-num (rand-range-int 100000 199999)]
    (str (quot int-account-num 10) "-" (rem int-account-num 10))))

(defn make-account
  "Create an account with auto-generated account-id
   account-type being (:main | :global)
   tax-id being official national ID
   wallet containing the wallet with specific money type"
  [account-type
   tax-id
   first-name
   last-name
   wallet] {:account-id (generate-account-id)
            :account-type account-type
            :tax-id tax-id
            :first-name first-name
            :last-name last-name
            :wallet wallet})