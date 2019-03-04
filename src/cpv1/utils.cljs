(ns cpv1.utils)

(defn index-of-char [string to-match]
  (let [len (count string)]
    (loop [i 0]
      (cond
        (>= i len)
        -1
        (= (nth string i) to-match)
        i
        :else (recur (inc i))))))

(defn- cut-float-str [str-num point-index]
  (apply str (take (+ 7 point-index) str-num)))

(defn num-to-str [num]
  (let [str-num (str (+ num 0.0000005))
        point-index (index-of-char str-num \.)]
    (if (> point-index 0)
      (cut-float-str str-num point-index)
      str-num)))

(defn remove-whitespace [exp]
  (apply str (re-seq #"\S+" exp)))
