(ns cpv1.print-simplified)

(def polynomial-pattern #"\-?\d+\.?\d*\*X\^\d+")

(defn- printable-val [val]
  (if (> val 0)
    (str "+" val)
    val))

(defn- printable-power [power value]
  (apply str (printable-val value) "*X^" (str (last (str power)))))

(defn- printable-powers [powers]
  (reduce-kv #(if (not= 0 %3)
                (conj
                 %1
                 (printable-power %2 %3))
                %1)
             []
             powers))

(defn- fix-first [powers]
  (str (re-find polynomial-pattern (first powers))))

(defn- printable-exp [powers]
  (let [ppowers (printable-powers powers)]
    (str
     (fix-first ppowers)
     (apply str (drop 1 ppowers)))))

(defn print-simplified [powers]
  (print "Reduced form: ")
  (if (every? zero? (vals powers))
    (println "0=0")
    (println (apply str (printable-exp powers) "=0"))))
