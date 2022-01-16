(ns ns-example.utils)

;; import Java library
(import 'java.util.UUID)

;; import multiple class in Java library
(import '(java.util UUID Date))

;; examples

(defn get-uuid []
  (str (UUID/randomUUID)))

(defn get-date []
  (Date.))

(get-uuid)
(str (get-date))

;; namespace macro
(ns ns-example.utils
  (:import (java.util UUID Date)))