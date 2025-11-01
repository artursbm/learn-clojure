(ns todo.db.queries)

(def list-todos "SELECT * FROM todo")
(def insert-todo "INSERT INTO todo (description) VALUES (?)")
(def delete-todo "DELETE FROM todo WHERE id = ?")
