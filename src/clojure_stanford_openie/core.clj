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

(defn- get-sentence-relation-triples
  [sentence]
  (map #(.get % NaturalLogicAnnotations$RelationTriplesAnnotation) sentence))

(defn- relation-triple-to-relation
  [relation-triple]
  {:subject (.subjectGloss relation-triple)
   :relation (.relationGloss relation-triple)
   :object (.objectGloss relation-triple)})

(defn- get-sentence-relations
  [sentence-relation-triples]
  (map relation-triple-to-relation sentence-relation-triples))

(defn get-relations
  [pipeline text]
  (->> (process-text pipeline text)
      (get-sentences)
      (get-sentence-relation-triples)
      (map get-sentence-relations)))

