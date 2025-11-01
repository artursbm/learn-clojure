(ns todo.routes
  (:require
   [next.jdbc :as jdbc]
   [todo.db.queries :as db.queries]))

(defn list-todos [{:keys [db]}]
  (fn [_request]
    {:body {:results (jdbc/execute! db [db.queries/list-todos])}}))

(defn create-todo [{:keys [db]}]
  (fn [{{{:keys [description]} :body} :parameters}]
    (let [id (val (first (jdbc/execute-one! db [db.queries/insert-todo description]
                                            {:return-keys true})))]
      {:status 201, :headers {"Location" (str "/todos/" id)}})))

(defn remove-todo [{:keys [db]}]
  (fn [{{{:keys [id]} :path} :parameters}]
    (let [result (jdbc/execute-one! db [db.queries/delete-todo id])]
      (if (pos? (::jdbc/update-count result))
        {:status 204}
        {:status 404, :body {:error :not-found}}))))
