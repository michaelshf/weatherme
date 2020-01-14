(ns weatherme.db
  (:require [clojure.java.jdbc :as db]
            [clojure.java.jdbc :as j]
            [jdbc.pool.c3p0 :as pool]))

(def spec
  (pool/make-datasource-spec
   {:subprotocol "postgresql"
    :subname "//localhost:5432/weather_data2"
    :user "admin"
    :password ""}))

;; Tools for DB Interaction
; make a table-
;(j/db-do-commands $DB_SPEC "create table $TABLE_NAME ($COLNAME $COLTYPE")
(j/db-do-commands spec "create table weather (summary varchar)")


;; insert data
 ;(j/insert! $DB_SPEC :$TABLE_NAME {:$COL_NAME $VALUE})



;; query table
 ;(j/query $DB_SPEC "select * from $TABLE_NAME")

