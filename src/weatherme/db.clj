(ns weatherme.db
  (:require [clojure.java.jdbc :as j]
            [clj-time.core :as t]
            [clj-time.coerce :as c]
            [jdbc.pool.c3p0 :as pool]))

;; Tools for DB Interaction
; make new db
; - need to make using sql from command line?
; - psql postgres; create database $DBNAME;

; prep for connections
(def pg-db {:dbtype "postgresql"
            :dbname "weather_test"
            :host "localhost"
            :port 5432
            :user "michaels"
            :password ""})

;; make a table-
;(j/db-do-commands $DB_SPEC
;  "create table $TABLE_NAME ($COLNAME $COLTYPE, $COLNAME $COLTYPE")
(defn make-table
  "Takes dbspec, new table name, and a vector of column names.
   Makes tables with varchar columns, and a timestamp column."
  [db-spec table-name col-vec]
  (j/db-do-commands db-spec
                    (str "create table "
                         table-name
                         "("
                         (apply str (map #(str % " varchar,") col-vec))
                         "timestamp timestamp"
                         ")")))

;(make-table pg-db "testinserts" ["col1" "col2" "col3" "col4"])
;(make-table pg-db "forecasts" ["id" "rowtype" "temp" "date_about" "day_distance"])

;; insert data
;(j/insert! $DB_SPEC :$TABLE_NAME {:$COL_NAME $VALUE})
(defn insert-row
  "Takes a dbspec, table name, col-name vector, val vector.
   Inserts positionally matched values into columns of table,
   also adds timestamp to every row."
  [db-spec table-name col-vec val-vec]
  (let [col-vec (map keyword col-vec)
        val-vec (map str val-vec)]
    (j/insert! db-spec
               (keyword table-name)
               (assoc (apply hash-map (interleave (map keyword col-vec)
                                                  (map str val-vec)))
                      :timestamp (c/to-sql-time (t/now))))))

;; (insert-row
;;  pg-db
;;  "forecasts"
;;  ["id" "rowtype" "temp" "dateabout"]
;;  ["2" "test" "101" "tomorrow"])

;; query table
;(j/query $DB_SPEC "select * from $TABLE_NAME")
(defn query-table
  "Takes a db-spec and table name and returns select* - everything."
  [db-spec table-name]
  (j/query db-spec [(str "select * from " table-name)]))
;(clojure.pprint/pprint (query-table pg-db "testinserts"))


;; delete a table if you want...
 ;(j/db-do-commands pg-db "drop table $TABLE_NAME")
(defn delete-table [db-spec table-name]
  (j/db-do-commands db-spec (str "drop table " table-name)))
;(delete-table pg-db "testinserts")
 ;(delete-table pg-db "forecasts")



;; delete data if you want...
                                        ; using execute:
                                        ; (jdbc/execute!
                                        ; db-spec
                                        ; ["DELETE FROM $TABLE_NAME WHERE $COLUMN_NAME $CONDITION (<, >) ?" $VALUE])
                                        ; using delete!: (jdbc/delete! $DB_SPEC :$TABLE_NAME ["grade < ?" 25.0])

