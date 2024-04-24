(ns reveal.slides
  (:require [clojure.string :as str]))

(def slide-config
  {:data-background-image "img/background.jpg"})

(defn style [m]
  {:style (str/join ";" (map (fn [[k v]] (str (name k) ": " v)) m))})

(def title-page
  [:section
   slide-config
   [:h5 (style {:color "#111"
                :text-shadow "0 0 10px #fff, 0 0 20px #fff, 0 0 30px orange "})
    "We need to talk about"]
   [:h1.r-fit-text (style {:color "#111"
                           :text-shadow "0 0 10px #fff, 0 0 20px #fff, 0 0 30px orange "})
    "JSON"]])

(def me
  [:section
   slide-config
   [:img {:src "img/i-like-banks.png"}]
   [:aside.notes
    [:ul
     [:li "Started career just in time for the global recession in 2008"]
     [:li "Risk systems, middle office automation, structured product quoting and booking UIs"]]]])

(def intro
  [:section
   {:data-background-color "black"}
   [:video.r-stretch
    {:controls true :data-autoplay true}
    [:source {:src "vid/starwars-intro.mp4"
              :type "video/mp4"}]]])

(def json-everywhere
  [:section
   slide-config
   [:h1 "Resistance is futile"]
   [:p "JSON is everywhere representing everything"]
   [:ul
    [:li "Cross language"]
    [:li "Cross platform"]
    [:li "Lightweight"]
    [:li "Human readable"]]
   [:aside.notes
    [:ul
     [:li "JSON is everywhere, we all love it, it has all these great characteristics"]
     [:li "But loads of other data formats can claim these too. What's the real secret?"]]]])

(def javascript
  [:section
   slide-config
   [:h1 "Javascript"]
   [:p "I'll just use " [:code "array.sort()"] " to sort these numbers"]
   [:img {:src "img/javascript.png"}]
   [:aside.notes
    [:ul
     [:li "The lowest common denominator, Javascript"]]]])


(def missing-features
  [:section
   slide-config
   [:h1 "JSON's missing features"]
   [:ul
    [:li "Comments"]
    [:li "Dates, times, uuids and other primitives"]
    [:li "Sets"]]
   [:aside.notes
    [:ul
     [:li "JSON has six basic data types - null, number, string, boolean, object and array"]
     [:li "Comments were intentionally left out of JSON by its creator Douglas Crockford to avoid their use as parsing directives"]
     [:li "Attempts to improve on it have had limited uptake - CSON, HOCON, JSONC, JSON5, EDN"]]]])

(def good-json
  [:section
   slide-config
   [:h1 "The good"]
   [:pre
    [:code {:data-trim true :data-noescape true}
         "{
  \"firstName\": \"John\",
  \"lastName\": \"Smith\",
  \"age\": 27,
  \"address\": {
    \"streetAddress\": \"21 2nd Street\",
    \"city\": \"New York\",
    \"state\": \"NY\",
  },
  \"children\": [
    \"Catherine\",
    \"Thomas\",
  ]
}"]]
   [:aside.notes
    [:ul
     [:li "This is what we think of when someone says JSON"]
     [:li "We would say this is a well-formed, valid document"]]]])

(def bad-json
  [:section
   slide-config
   [:h1 "The bad"]
   [:pre
    [:code {:data-trim true :data-noescape true}
     "{
  \"a\": \"x\",
  \"x\": \"-1\",
  \"x\": 27,
  \"\\r\": {
    \"😐\": \"{\\\"true\\\": -1.07e3}\",
  },
  \"z\": [
    \"null\",
    null
  ]
}"]]
   [:aside.notes
    [:ul
     [:li "But this is JSON too"]
     [:li "Who says this isn't well-formed or valid?"]
     [:li "How can we specify what we mean by a valid document?"]]]])


(def json-schema
  [:section
   slide-config
   [:h1 "JSON Schema"]
   [:img {:src "img/liquid-cats.png"}]
   [:aside.notes
    [:ul
     [:li "JSON Schema lets us specify constraints"]
     [:li "If the values in a JSON document fit inside those constraints, we can say it is valid"]]]])

(def json-schema-example
  [:section
   slide-config
   [:h1 "Example"]
   [:pre.r-stretch
    [:code {:data-trim true :data-noescape true
            :data-line-numbers "1-100|5,6,10|4,8,12|15|2,3,7,11"}
     "{
  \"title\": \"Product\",
  \"description\": \"A product from Acme's catalog\",
  \"type\": \"object\",
  \"properties\": {
    \"productId\": {
      \"description\": \"The unique identifier for a product\",
      \"type\": \"integer\"
    },
    \"productName\": {
      \"description\": \"Name of the product\",
      \"type\": \"string\"
    }
  },
  \"required\": [ \"productId\", \"productName\" ]
}"]]
   [:aside.notes
    [:ul
     [:li "Here is an example of a schema for a JSON object"]
     [:li "It has two properties, productId and productName"]
     [:li "The type field tells us their JSON types, which are object, integer and string respectively"]
     [:li "We can express optionality with the required field"]
     [:li "There is also metadata which we can use to give human descriptions and information, like comments would"]]]])

(def validation
  [:section
   slide-config
   [:h1 "Validation"]
   [:pre
    [:code {:data-trim true :data-noescape true}
     "{
  \"errors\": [
    {
      \"instancePath\": \"\",
      \"schemaPath\": \"#/required\",
      \"keyword\": \"required\",
      \"params\": {
        \"missingProperty\": \"productName\"
      },
      \"message\": \"must have required property 'productName'\",
    }
  ]
}"]]
   [:aside.notes
    [:ul
     [:li "The primary use case for JSON schema is validation"]
     [:li "Does this JSON fit inside my JSON Schema box?"]
     [:li "Third party validators like AJV produce structured and unambiguous errors"]]]])

(def type-constraints-numbers
  [:section
   slide-config
   [:h1 "Type constraints"]
   [:pre
    [:code {:data-trim true :data-noescape true}
     "{
  \"type\": \"integer\"
}"]]
   [:pre
    [:code {:data-trim true :data-noescape true}
     "{
  \"type\": \"number\",
}"]]
   [:pre
    [:code {:data-trim true :data-noescape true}
     "{
  \"type\": \"number\",
  \"minimum\": 0,
  \"maximum\": 100
}"]]
   [:aside.notes
    [:ul
     [:li "Numbers can be constrained to integers"]
     [:li "We can also constrain to positive numbers, and set upper bounds"]]]])

(def type-constraints-strings
  [:section
   slide-config
   [:h1 "Type constraints"]
   [:pre
    [:code {:data-trim true :data-noescape true}
     "{
  \"type\": \"string\",
  \"enum\": [\"Sunny\", \"Cloudy\", \"Rain\"]
}"]]
   [:pre
    [:code {:data-trim true :data-noescape true}
     "{
  \"type\": \"string\",
  \"format\": \"date\" // or uuid, email, time etc
}"]]
   [:pre
    [:code {:data-trim true :data-noescape true}
     "{
  \"type\": \"string\",
  \"minLength\": 8
}"]]
   [:pre
    [:code {:data-trim true :data-noescape true}
     "{
  \"type\": \"string\",
  \"pattern\": \"[a-Z]{4}-[0-9]+\"
}"]]
   [:aside.notes
    [:ul
     [:li "Strings - the most abused of all data types"]
     [:li "Finally no more arguments about what date formats to use"]
     [:li "Adds a whole load of richer types to JSON"]]]])


(def conditionality
  [:section
   slide-config
   [:h1 "Conditionality"]
   [:pre.r-stretch
    [:code {:data-trim true :data-noescape true}
     "{
  \"type\": \"object\",
  \"properties\": {
    \"streetAddress\": { \"type\": \"string\" },
    \"country\": { \"enum\": [\"USA\", \"Canada\"] }
  },
  \"if\": {
    \"properties\": {
      \"country\": { \"const\": \"USA\" }
    }
  },
  \"then\": {
    \"properties\": {
      \"zipCode\": { \"pattern\": \"[0-9]{5}(-[0-9]{4})?\" }
    }
  }
}"
]]
   [:aside.notes
    [:ul
     [:li "We can even represent conditionality"]
     [:li "This allows us to represent polymorphism"]]]])

(def features-list
  [:section
   slide-config
   [:h1 "Features"]
   [:ul
    [:li "Additional primitive types"]
    [:li "Range or character constraints"]
    [:li "Conditionality"]
    [:li "Composition e.g. anyOf, allOf"]
    [:li "Optionality"]
    [:li "Read-only / write-only"]
    [:li "Referencing other schemas"]
    [:li "Annotations"]
    [:li "And more!"]]
   [:aside.notes
    [:ul
     [:li "JSON Schema has a huge range of features that I've only just touched on"]
     [:li "There should be nothing preventing representation of your data structures in JSON Schema"]
     [:li "It's also extensible - you can add any keywords you like - but consumers will need to be told what they mean"]]]])

(def use-cases
  [:section
   slide-config
   [:h1 "Use cases"]
   ;; https://www.spreadshirt.co.uk/create-your-own?productType=812&productTypeCategory=CG01
   [:div
    (style {:display "flex"})
    [:div [:img {:src "img/first-class.png"}]]
    [:div [:img {:src "img/annotated.png"}]]
    [:div [:img {:src "img/open-standard.png"}]]]
   [:aside.notes
    [:ul
     [:li "The schema is there for everyone to see and inspect"]
     [:li "Just data, so that tools can be used for comprehension"]
     [:li "Can be shared with any application regardless of language"]
     [:li "Annotated to be readable to humans and convey intent"]
     [:li "Plenty of third party tooling available"]]]])

(def ui
  [:section
   slide-config
   [:h1 "Generated UI"]
   [:img {:src "img/generated-ui.png"}]

   [:aside.notes
    [:ul
     [:li "Schema metadata is rich enough to generate a UI"]
     [:li "Types map on to form inputs"]
     [:li "UIs are for use by humans, so the annotations like title and description are used to tell the human what each field is"]
     [:li "We can validate fields as the user types"]]]])


(defn all []
  [title-page
   me
   intro
   json-everywhere
   javascript
   missing-features
   good-json
   bad-json

   json-schema
   json-schema-example
   validation
   type-constraints-numbers
   type-constraints-strings
   conditionality
   features-list

   use-cases
   ui
   ;; custom ui - how we can use extensions, e.g. domainType=currency, array pickers etc

   ;; practical-application ;; common definition used across multiple services, UI, ETL pipe
   ;; case-study-structured-products ;; services can treat it as a blob while knowing they can peer in
   ;;

   ])
