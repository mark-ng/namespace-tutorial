(ns ns-example.pizza
  "This namespace is all about the PIZZA!"                  ;; docstring for namespace
  {:author "Mark Ng"}                                       ;; meta data for name space
  )


(def pizza-rating-map {::margerita "good",
                       ::mexicana "bad"
                       ::vegatarian "nice"})

(defn advance-math [x y] (+ x y))

(defn print-pizza-map [ops] (println pizza-rating-map))

(def count "There are 100 pizzas")

;; Get meta data for namespace
(meta (the-ns 'ns-example.pizza))

*ns*                                                        ;; refer to current namespace