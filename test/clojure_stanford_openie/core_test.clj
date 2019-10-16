(ns clojure-stanford-openie.core-test
  (:require [clojure.test :refer :all]
            [clojure-stanford-openie.core :refer :all]))

(def openie-pipeline (initialize-openie-pipeline))

(deftest verify-retrieved-relation
  (is (=
       (get-relations openie-pipeline "I drink coke.")
       (list {:sentence "I drink coke."
              :relations (list {:subject "I" :relation "drink" :object "coke"})}))))

(deftest verify-retrieved-relations
  (is (=
       (get-relations openie-pipeline "I am lost. Life is all about loss.")
       (list {:sentence "I am lost."
              :relations (list {:subject "I" :relation "am" :object "lost"})}
             {:sentence "Life is all about loss."
              :relations (list {:subject "Life" :relation "is about" :object "loss"}
                               {:subject "Life" :relation "is about" :object "all loss"})}))))
