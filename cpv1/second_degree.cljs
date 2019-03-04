(ns cpv1.second-degree
  (:require [cpv1.sqrt :refer [sqrt]]
            [cpv1.utils :refer [num-to-str]]))

(defn- calc-discriminant [powers]
  (-
   (* (:p1 powers) (:p1 powers))
   (* 4 (:p2 powers) (:p0 powers))))

(defn- solve-equation [powers plus-or-minus discriminant-root]
  (/
   (plus-or-minus (- (:p1 powers)) discriminant-root)
   (* 2 (:p2 powers))))

(defn- get-poly-answer [powers plus-or-minus discriminant]
  (num-to-str (solve-equation powers plus-or-minus (sqrt discriminant))))

(defn- print-pos-dis [powers discriminant]
  (println "Discriminant is strictly negative, solution with complex numbers")
  (println (get-poly-answer powers - (- discriminant)) "i")
  (println (get-poly-answer powers + (- discriminant)) "i"))

(defn- print-neg-dis [powers discriminant]
  (println "Discriminant is strictly positive, the two solutions are:")
  (println (get-poly-answer powers - discriminant))
  (println (get-poly-answer powers + discriminant)))

(defn- print-zero-dis [powers discriminant]
  (println "The discriminant is 0, so there is only one solution")
  (println "The solution is:")
  (println (get-poly-answer powers + discriminant)))

(defn second-degree [powers]
  (let [discriminant (calc-discriminant powers)]
    (cond
      (> 0 discriminant) (print-pos-dis powers discriminant)
      (< 0 discriminant) (print-neg-dis powers discriminant)
      (== 0 discriminant) (print-zero-dis powers discriminant))))
