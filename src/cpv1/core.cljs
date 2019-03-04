(ns cpv1.core
  (:require [cpv1.utils :refer [remove-whitespace]]
            [cpv1.get-powers :refer [get-powers
                                     get-exp-split
                                     get-exps]]
            [cpv1.second-degree :refer [second-degree]]
            [cpv1.first-degree :refer [first-degree]]
            [cpv1.zero-degree :refer [zero-degree]]
            [cpv1.print-simplified :refer [print-simplified]]))

(defn test-input-string [cmd]
  (not= (count (re-seq #"[^\+]" (apply str (get-exp-split cmd))))
        (count (str
                (apply str (first (get-exps cmd)))
                (apply str (second (get-exps cmd)))))))

(defn get-non-zero-powers [powers]
  (reduce-kv #(if (not= %3 0)
                  (conj %1 (int (last (str %2))))
                %1)
             []
             powers))

(defn get-degree [powers]
  (apply max 0 (get-non-zero-powers powers)))

(defn solve-poly [cmd]
  (let [powers (get-powers cmd)
        degree (get-degree powers)]
    (print-simplified powers)
    (cond
      (> degree 2) (do
                     (println "Polynomial degree:" degree)
                     (println "The polynomial degree is strictly greater than 2, I can't solve."))
      (= degree 2) (do
                     (println "Polynomial degree: 2")
                     (second-degree powers))
      (= degree 1) (do
                     (println "Polynomial degree: 1")
                     (println (first-degree powers)))
      (= degree 0) (if (zero-degree powers)
                     (println "All real numbers")
                     (println "Not possible")))))

(defn print-usage []
  (println "usage: lumo -m src -c cpv1.core <polynomial expression, for example \"5 * X^0 - 3 * X^2\">"))

(defn -main
  [& args]
  (cond
    (nil? args) (print-usage)
    (not= 1 (count args)) (print-usage)
    (< (count (first args)) 3) (print-usage)
    (test-input-string (first args)) (print-usage)
    :else (solve-poly (first args))))
