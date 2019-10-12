(ns clojure-stanford-openie.core-test
  (:require [clojure.test :refer :all]
            [clojure-stanford-openie.core :refer :all]))

(def openie-pipeline (initialize-openie-pipeline))

(deftest verify-retrieved-relation
  (is (=
       (get-relations openie-pipeline "I drink coke.")
       (list (list {:subject "I" :relation "drink" :object "coke"})))))

(deftest verify-retrieved-relations
  (is (=
       (get-relations openie-pipeline "I am lost. Life is all about loss.")
       (list (list {:subject "I" :relation "am" :object "lost"})
             (list {:subject "Life" :relation "is about" :object "loss"}
                   {:subject "Life" :relation "is about" :object "all loss"})))))
