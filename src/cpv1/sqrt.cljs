(ns cpv1.sqrt)

(defn- abs[x]
  (max x (- x)))

(defn- average [a b]
  (/ (+ a b) 2))

(defn- good-guess? [x guess]
  (< (abs (- x (* guess guess))) 0.00000001))

(defn- improve-guess [x guess]
  (average guess (/ x guess)))

(defn sqrt [x]
  (loop [guess 1.0]
    (if (good-guess? x guess)
      guess
      (recur (improve-guess x guess)))))
