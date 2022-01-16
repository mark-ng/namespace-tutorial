(ns ns-example.core)

;; 'use'

;; 'use' includes all symbols from a namespace
(use 'ns-example.pizza)
;; 'use' with only selected labels
(use '[ns-example.pizza :only [advance-math]])
;; 'use' with all symbols from a namespace (except selected symbols)
(use '[ns-example.pizza :exclude [advance-math]])

;; 'require'

;; 'require' does not includes all symbols from a namespace
(require '[ns-example.pizza])
;; 'require' + :refer can include selected symbols from a namespace
(require '[ns-example.pizza :refer [advance-math]])
;; 'require' + :as represent the whole namespace
(require '[ns-example.pizza :as p])

;; verbose explain what happen
(require '[ns-example.pizza :refer [advance-math]] :verbose)
(require '[ns-example.pizza :as p :refer [advance-math]] :verbose :reload)

;; examples
pizza-rating-map
(advance-math 1 2)
(p/advance-math 1 2)

;;;;;;;;;;;;;;;;;;
;; namespace macro

;; 'require'
(ns ns-example.core
  (:use [clojure.string :only [reverse] :rename {reverse rev}]) ;; :only :rename
  (:require [ns-example.pizza :as p :refer [advance-math]]
            [clojure.string :as str]))

(rev "abcdefg")

(require '[clojure.string :as str])

(str/reverse "apple")

