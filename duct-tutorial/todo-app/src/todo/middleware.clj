(ns todo.middleware)

(defn wrap-headers
  "Middleware fn that add a map of custom headers 
   to every response in the service.
   
   We add this handler to the web module :middleware key,
   so that we 'inject' it to the web module globally:
   :middleware [#ig/ref :todo.middleware/wrap-headers]"
  [headers]
  (fn [handler]
    (fn [request]
      (let [response (handler request)]
        (update response :headers merge headers)))))
