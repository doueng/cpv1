(ns cpv1.zero-degree)

(defn zero-degree [powers]
  (= 0 (* (:p0 powers) 1)))
