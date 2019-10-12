# clojure-stanford-openie

A Clojure library designed to wrap Stanford CoreNLP's OpenIE

## Usage

lein repl

```
(def pipeline (initialize-openie-pipeline))
(def text "I drink coke.")
(get-relations pipeline text)
```

## Other Stuff
Check out https://github.com/clojurenlp/core/ for some other Stanford
CoreNLP wrappers.
