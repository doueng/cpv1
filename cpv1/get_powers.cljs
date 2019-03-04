(ns cpv1.get-powers
  (:require [cpv1.utils :refer [remove-whitespace
                                index-of-char]]))

(def polynomial-pattern #"\-?\d+\.?\d*\*X\^[012]")

(defn- get-number [exp]
  (re-find #"\-?\d+\.?\d*" exp))

(defn- get-keyword [exp]
  (keyword (str "p" (last exp))))

(defn- first-exp [cmd]
  (apply str (take (index-of-char cmd \=) cmd)))

(defn- second-exp [cmd]
  (apply str (drop (+ 1 (index-of-char cmd \=)) cmd)))

(defn get-exp-split [cmd-whitespace]
  (let [cmd (remove-whitespace cmd-whitespace)]
    (list
     (first-exp cmd)
     (second-exp cmd))))

(defn get-exps [cmd]
  (let [exps (get-exp-split cmd)]
    (list
     (re-seq polynomial-pattern (first exps))
     (re-seq polynomial-pattern (second exps)))))

(defn- assoc-power [powers exp]
  (assoc powers (get-keyword exp) (js/parseFloat (get-number exp))))

(defn- get-powers-split [cmd]
  (let [exps (get-exps cmd)]
    (list
     (reduce assoc-power {:p0 0 :p1 0 :p2 0} (first exps))
     (reduce assoc-power {:p0 0 :p1 0 :p2 0} (second exps)))))

(defn get-powers [cmd]
  (apply merge-with - (get-powers-split cmd)))

