(ns todo.client
  (:require [replicant.dom :as r]
            [duct.client.http :as http]
            [clojure.core.async :as a :refer [<!]]))

(defonce store (atom {}))

(defn update-todos []
  (a/go (let [resp (<! (http/get [:todos]))]
          (swap! store assoc :todos (-> resp :body :results)))))

(defn delete-todo [id]
  (a/go (<! (http/delete [:todos id]))
        (<! (update-todos))))

(defn create-todo []
  (a/go (let [input (js/document.getElementById "todo-desc")]
          (<! (http/post [:todos] {:description (.-value input)}))
          (<! (update-todos))
          (set! (.-value input) ""))))

(defn- create-todo-form []
  [:div.create-todo
   [:input#todo-desc {:type "text"}]
   [:button {:on {:click create-todo}} "Create"]])

(defn todo-list [{:keys [todos]}]
  [:ul
   (for [{:todo/keys [id description]} todos]
     [:li {:replicant/key id}
      [:span description] " "
      [:a {:href "#" :on {:click #(delete-todo id)}} "delete"]])
   [:li (create-todo-form)]])

(defonce todos
  (js/document.getElementById "todos"))

(add-watch store ::render (fn [_ _ _ s] (r/render todos (todo-list s))))
(update-todos)
