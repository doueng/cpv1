(ns cpv1.first-degree)

(defn first-degree [powers]
  (/ (- (:p0 powers))
    (:p1 powers)))
