(defproject weatherme "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [org.clojure/tools.nrepl "0.2.13"]
                 [clj-http "3.8.0"]
                 [cheshire "5.8.1"]
                 [clj-time "0.14.4"]
                 [mysql/mysql-connector-java "8.0.17"]
                 [com.datomic/client-pro "0.8.20"]
                 [org.clojure/java.jdbc "0.7.8"]
                 [org.postgresql/postgresql "42.1.4"]
                 [clojure.jdbc/clojure.jdbc-c3p0 "0.3.3"]]
  :main ^:skip-aot weatherme.core
  :target-path "target/%s"
  :plugins [[lein-cljfmt "0.6.4"]
            [refactor-nrepl "2.5.0-SNAPSHOT"]]
  :profiles {:uberjar {:aot :all}})
