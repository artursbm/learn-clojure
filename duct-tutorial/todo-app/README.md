# ToDo Web App using Duct framework

## Intro

A todo web app with duct and:
- [integrant](https://github.com/weavejester/integrant/blob/master/README.md) (component dependency management)
- hiccup (html)
- [Reitit](https://github.com/metosin/reitit) (routing)
- Ring (handles HTTP requests)

### Available endpoints
- GET /todos
- POST /todos
- DELETE /todos/:id

## Implementation

### [Ring](https://github.com/ring-clojure/ring)
Ring middleware are functions that transform Ring handlers. These are often used to parse information from the request map, such as encoded parameters or session data, or to transform the response map, by adding headers or formatting the response body.

### [Hiccup](https://github.com/weavejester/hiccup)
We see in `todo/routes.clj` how a Hiccup data structure could be directly attached to the response body. This is possible because Duct adds default middleware to look for Hiccup and format it into HTML.

### Applying middlewares
We add a new key, :todo.middleware/wrap-headers, which configures and creates the middleware function, then we use an Integrant ref to add that function to a vector of middleware.

There three ways to apply middleware:

- Middleware is applied to all requests (via :middleware)
- Middleware is applied if any route matches (via :route-middleware)
- Middleware is applied if a specific route matches (via :middleware attached to individual routes)

### SQL in the project
Using [SQLite](https://www.sqlite.org/index.html) with JDBC adapter as a dependency. Duct wraps this in a module that can be added in `duct.edn` and injected in the webapp. Also using [next.jdbc dependency](https://github.com/seancorfield/next-jdbc) in the project.

The SQL module adds a database connection pool under the key `:duct.database.sql/hikaricp`, which derives from the more general `:duct.database/sql` key. We can use this connection pool as a javax.sql.DataSource instance.

The SQL module also adds a migrator, a component to manage DB migrations. Here, it uses the [Ragtime library](https://github.com/weavejester/ragtime).

#### Injecting SQL in the whole web module
The web module has an option, :handler-opts that applies common options to all route handlers it generates.

This will add the DataSource instance to the :db key of the component options. We can access this from the route handler function we created earlier.

### Adding ClojureScript to the ToDo app
`:duct.module/cljs {:builds {:client todo.client}}`

The module requires a :builds option to be set. This connects a build name to a ClojureScript namespace, or collection of namespaces. In the above example, the todo.client namespace will be compiled to the target/cljs/client.js JavaScript file. When Duct is started, this will be accessible at: http://localhost:3000/cljs/client.js.

