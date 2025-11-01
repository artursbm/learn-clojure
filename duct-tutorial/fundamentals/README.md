# Setup a duct service

## Ran the following commands
#### `mkdir duct-tutorial && cd duct-tutorial`
#### `curl -O duct.now/deps.edn`
- Download duct deps.edn with minimal dependencies
#### `clojure -M:duct`
#### `alias duct='clojure -M:duct'`
- the command `clojure -M:duct` is a template to run all things duct can do.
- Created an alias to make it easier to type it
#### `duct --setup :bb`
- Need to have babashka installed
- :bb sets up some targets to make it easier to build and run the application using babashka
#### `duct --setup :docker`
- setup minimal Dockerfile that configures the server app
#### `duct --setup :calva`
- setup calva connection for duct framework
#### `duct --setup :duct`
- creates the starting point of duct: a `duct.edn` file that declares the system component and its dependencies (like server, logging, database etc.)

## Tips
- Using `#p` is a macro to print expression evals in debug mode (**ignored** in prod, only does something in repl mode - great!)

- using `(sync-deps)` in REPL will reload deps and download new ones to the project

## Knowledge
#### From /duct.edn file:
The #ig/ref data reader is used to give the ‘hello’ component access to the logger. We use :duct/logger instead of :duct.logger/simple, as keys have a logical hierarchy, and :duct/logger fulfils a role similar to that of an interface or superclass.

#### From how to build deps.edn dependencies that I just added to project
`clj -X:deps prep`

[deps.edn - Prep libs](https://clojure.org/reference/clojure_cli#deps_sources)

Source libs with Clojure source can immediately be added to the classpath of a project using it. However, some source libs require some preparation before they can be added, for example due to needing Java compilation, or copying / replacing resource files, etc. The Clojure CLI will now detect projects that need preparation and prevent the program from being run from source unless the prep step has been completed.

