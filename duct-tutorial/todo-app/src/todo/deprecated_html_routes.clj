(ns todo.deprecated-html-routes
  (:require
   [duct.logger :as logger]
   [next.jdbc :as jdbc]
   [todo.db.queries :as db.queries]
   [ring.middleware.anti-forgery :as af]))

(defn- create-todo-form []
  [:form {:action "/" :method "post"}
   [:input {:type "hidden"
            :name "__anti-forgery-token"
            :value af/*anti-forgery-token*}]
   [:input {:type "text", :name "description"}]
   [:input {:type "submit", :value "Create"}]])

;; I can define this route handler 
;; without the :status and :headers attributes 
;; but will leave it here for better simplicity
(defn index [{:keys [db logger]}]
  (fn [_request]
    (logger/log logger :info {:msg "Opening home page..."})
    {:body [:html {:lang "en"}
            [:head 
             [:title "Todo"]
             [:script {:src "/cljs/client.js"
                       type "text/javascript"}]]
            [:body
             [:ul
              (for [rs (jdbc/execute! db [db.queries/list-todos])]
                [:li (:todo/description rs)])
              [:li (create-todo-form)]]]]}))

(defn new-todo
  [{:keys [db]}]
  (fn [{{:keys [description]} :params}]
    (jdbc/execute! db [db.queries/insert-todo description])
    {:status 303, :headers {"Location" "/"}}))

