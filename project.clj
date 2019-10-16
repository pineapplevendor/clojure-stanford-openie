(defproject clojure-stanford-openie "1.0.0"
  :description "Wrap Stanford's OpenIE in Clojure"
  :url "https://github.com/pineapplevendor/clojure-stanford-openie"
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [edu.stanford.nlp/stanford-corenlp "3.8.0"]
                 [edu.stanford.nlp/stanford-corenlp "3.8.0" :classifier "models"]
                 [org.slf4j/slf4j-simple "1.7.25"]]
  :repl-options {:init-ns clojure-stanford-openie.core}
  :plugins [[lein-cljfmt "0.6.4"]])
