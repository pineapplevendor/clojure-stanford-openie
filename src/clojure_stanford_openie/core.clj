(ns clojure-stanford-openie.core
  (:import (java.util Properties)
           (edu.stanford.nlp.naturalli NaturalLogicAnnotations$RelationTriplesAnnotation)
           (edu.stanford.nlp.pipeline Annotation StanfordCoreNLP)
           (edu.stanford.nlp.ling CoreAnnotations$SentencesAnnotation)))

(defn initialize-openie-pipeline
  ([]
   (let [openie-props (Properties.)]
     (.put openie-props "annotators" "tokenize,ssplit,pos,lemma,depparse,natlog,openie")
     (StanfordCoreNLP. openie-props))))

(defn- process-text
  [pipeline text]
  (.process pipeline text))

(defn- get-sentences
  [text]
  (.get text CoreAnnotations$SentencesAnnotation))

(defn- get-sentence-annotations
  [sentence]
  (.get sentence NaturalLogicAnnotations$RelationTriplesAnnotation))

(defn- relation-annotation-to-relation
  [annotation]
  {:subject (.subjectGloss annotation)
   :relation (.relationGloss annotation)
   :object (.objectGloss annotation)})

(defn- get-sentence-to-relations
  [sentence]
  {:sentence (.toString sentence)
   :relations (map relation-annotation-to-relation (get-sentence-annotations sentence))})

(defn get-relations
  [pipeline text]
  (->> (process-text pipeline text)
       (get-sentences)
       (map get-sentence-to-relations)))

