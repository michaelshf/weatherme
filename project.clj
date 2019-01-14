(defproject weatherme "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [clj-http "3.8.0"]
                 [cheshire "5.8.1"]
                 [clj-time "0.14.4"]
                 [com.datomic/client-pro "0.8.20"]
                 [org.clojure/java.jdbc "0.7.8"]]
  :main ^:skip-aot weatherme.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
