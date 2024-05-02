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
    [:code {:data-trim true :data-noescape true
            :data-line-numbers "1-100|2-6|7-11|12-16"}
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

(def custom-ui
  [:section
   slide-config
   [:h1 "Custom UI"]
   [:pre
    [:code {:data-trim true :data-noescape true}
     "{
  \"type\": \"string\",
  \"domainType\": \"currency\"
}"]]
   [:img {:src "img/domain-select.png"}]
   [:aside.notes
    [:ul
     [:li "We can decorate the schema with custom extensions"]
     [:li "This allows us to make a richer, more domain-specific UI"]
     [:li "There are plenty of financial domain types where we can give good UX - currencies, assets, schedules"]
     [:li "This helps ensure the critical job of getting the correct data into the system is as easy and non-fallible as possible"]]]])

(def case-study-intro
  [:section
   slide-config
   [:h1 "Structured Products"]
   [:div
    [:img {:src "img/types-of-structured-products.png"
           :style "background-color: #eee; padding: 1rem;"}]]
   [:div
    [:small "https://www.interpersona.nl/types-of-structured-products/"]]
   [:aside.notes
    [:ul
     [:li "Now you know the awesome power of JSON Schema, what can we build with it?"]
     [:li "It turns out you can build an award-winning Structured Products platform with a generated UI!"]
     [:li "Structured Products come in four main flavours which cater for different risk and reward attitudes"]
     [:li "They offer better returns than bonds but without as much risk as stocks"]]]])

(def product-composition
  [:section
   slide-config
   [:h1 "Composition"]
   [:div [:img {:src "img/composition.webp"}]]
   [:div
    [:small "https://www.investopedia.com/articles/optioninvestor/07/structured_products.asp"]]
   [:aside.notes
    [:ul
     [:li "They are composed of more primitive financial instruments like bonds and options"]
     [:li "This one has an option and a bond"]
     [:li "The bond gives downside protection"]
     [:li "The option participates in the upside of the underlying asset"]]]])

(def composite-payoff
  [:section
   slide-config
   [:h1 "Composite payoff"]
   [:div [:img {:src "img/payoff.webp"}]]
   [:div
    [:small "https://www.investopedia.com/articles/optioninvestor/07/structured_products.asp"]]
   [:aside.notes
    [:ul
     [:li "The payoff profile of the product can have interesting features as a result of the interplay between the various instruments"]
     [:li "Here the upside is twice the performance of the underlying asset, but capped at 15%"]
     [:li "There is no downside protection, the investor is exposed to 100% of any losses"]
     [:li "Suitable for someone who is mildly bullish and wishes to get better returns than a weakly improving underlying"]]]])

(def megazord
  [:section
   slide-config
   [:h1 "Megazord"]
   [:img {:src "img/megazord.gif"}]
   [:aside.notes
    [:ul
     [:li "So Structured Products are a bit like Megazord"]
     [:li "Composed of many parts, each with their own particular behaviours"]
     [:li "Combining to create something more powerful than the sum of its parts"]
     [:li "Capable of saving the earth"]]]])

(def structured-product-schema
  [:section
   slide-config
   [:h1 "Product schema"]
      [:pre
       [:code {:data-trim true :data-noescape true}
     "{
  \"title\": \"Structured Product\",
  \"description\": \"A yield enhancement note\",
  \"type\": \"object\",
  \"properties\": {
    \"Underlyings\": { ... },
    \"Coupon\": { ... },
    \"Cap\": { ... },
    \"Protection\": { ... },
    \"Investment\": { ... },
    \"Redemption\": { ... }
  }
}"]]
   [:aside.notes
    [:ul
     [:li "The instruments of which these products are composed have features which they impart to the product"]
     [:li "The product is the sum of these features, of which some combinations become very popular"]
     [:li "They are complex, yes"]
     [:li "But they can be modelled by composing JSON Schemas"]]]])

(def schema-first
  [:section
   slide-config
   [:h1 "Schema first"]
   ;; https://www.spreadshirt.co.uk/create-your-own?productType=812&productTypeCategory=CG01
   [:div
    (style {:display "flex"})
    [:div [:img {:src "img/first-class.png"}]]
    [:div [:img {:src "img/annotated.png"}]]
    [:div [:img {:src "img/open-standard.png"}]]]
   [:aside.notes
    [:ul
     [:li "What happens if we start with a schema and build from there?"]
     [:li "It's a good exercise in domain modelling"]
     [:li "The schema is there for everyone to see and inspect"]
     [:li "Just data, so that tools can be used for comprehension"]
     [:li "Can be shared with any application regardless of language"]
     [:li "Annotated to be readable to humans and convey intent"]
     [:li "Plenty of third party tooling available"]]]])

(def schema-central
  [:section
   slide-config
   [:h1 "Schema central"]
   [:img {:src "img/sn-platform.svg"
          :style "background-color: #eee; padding: 1rem;"}]
   [:aside.notes
    [:ul
     [:li "A Structured Products platform is a big complicated system"]
     [:li "Lots of applications, lots of people - business users, analysts, quants, developers, product owners"]
     [:li "We can put the schema in the middle of the system to describe the domain of structured products"]
     [:li "All the applications can treat it as a blob, but they can refer to the schema if they need to inspect it to perform their task"]
     [:li "Domain entities can flow through the applications without impedance"]
     [:li "The applications can use different languages and technologies"]
     [:li "Developers don't need to be SP experts"]
     [:li "New product features can be released in the schema without upheaval across the whole system"]]]])

(def ant-colony
  [:section
   slide-config
   [:h1 "A school for ants"]
   [:img {:src "img/ant-colony.gif"}]
   [:aside.notes
    [:ul
     [:li "You can compare it to an ant colony"]
     [:li "The colony itself works at higher abstractions and models threats and opportunities in the world around it"]
     [:li "But the individual ants can be simple automatons working at their given job"]
     [:li "They can pass signals to other types of ants without knowing what that signal means"]
     [:li "They don't need to understand what the colony is doing, but the colony relies on them to perform their specific role"]]]])

(def ui-schema-enhancement
  [:section
   slide-config
   [:h1 "Progressive enhancement"]
   [:img {:src "img/ui-schema-pipeline.svg"
          :style "background-color: #eee; padding: 1rem;"}]
   [:aside.notes
    [:ul
     [:li "My particular experience is building a pricing and booking UI from these schemas"]
     [:li "We built a pipeline where schemas were progressively enhanced before rendering"]
     [:li "Apply defaults, hide enums with only one choice and readonly fields, rename with localised terminology"]
     [:li "Apply ordering"]
     [:li "We end up with an enhanced schema which describes a form which is concise and relevant to the user"]
     [:li "We use it to build a state machine where we use the schema to template an initial document"]
     [:li "We render a form on the screen using the schema to choose the right component and the document supplies the values"]
     [:li "When a user changes a value, we push it into the document, run validation and templating and produce the new document"]
     [:li "This gets rendered as before and we end up with a responsive, helpful UI that enables accurate data capture"]
     [:li "Not an easy job for complex things like structured products!"]]]])


(def ui-rendering
  [:section
   slide-config
   [:h1 "Rendering"]
   [:img {:src "img/ui-rendering.svg"
          :style "background-color: #eee; padding: 1rem;"}]
   [:aside.notes
    [:ul
     [:li "The renderer doesn't actually have much to do, other than choose the right component for each field"]
     [:li "Render with a mix of primitive and progressively enhanced components"]
     [:li "Render in card and grid form"]]]])

#_(def taxonomy
  [:section
   slide-config
   [:h1 "Taxonomy"]
   [:div
    [:img {:src "img/taxonomy.jpg"}]]
   [:div
    [:small "https://www.sciencedirect.com/science/article/abs/pii/S0378426604002511"]]
   [:aside.notes
    [:ul
     [:li "The instruments of which these products are composed have features which they impart to the product"]
     [:li "The product is the sum of these features, of which some combinations become very popular"]
     [:li "These familial features give rise to a taxonomy of structured products"]
     [:li "And can be modelled very well by composing JSON Schemas"]]]])



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
   ui
   custom-ui

   case-study-intro
   product-composition
   composite-payoff
   megazord
   structured-product-schema

   schema-first
   schema-central
   ant-colony

   ui-schema-enhancement
   ui-rendering

   ;; SP ui - any screenshots of sphere?
   ;; enrichment, validation, pricing, doc gen, risk booking - ants

   ;; practical-application ;; common definition used across multiple services, UI, ETL pipe
   ;; case-study-structured-products ;; services can treat it as a blob while knowing they can peer in. because these things are complicated
   ;; so we like to split the domain processes out, and each one may care about different features but maybe nothing compares about the whole (deal + product)
   ;; so instead of inferring that from the parts we are explicit
   ;;

   ])
