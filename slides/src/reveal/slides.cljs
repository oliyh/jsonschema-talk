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
   [:img.r-stretch {:src "img/i-like-banks.png"}]
   [:aside.notes
    [:ul
     [:li "Started career just in time for the global recession in 2008"]
     [:li "I've worked in a big Swiss bank, another big Swiss bank, and a big American bank"]
     [:li "Risk systems, middle office automation, structured product quoting and booking UIs"]
     [:li "I've covered most of the ground in Equity derivatives"]
     [:li "So let's talk about JSON"]]]])

(def intro
  [:section
   {:data-background-color "black"}
   [:video.r-stretch
    {:controls true :data-autoplay true}
    [:source {:src "vid/starwars-intro.mp4"
              :type "video/mp4"}]]
   [:aside.notes
    [:ul
     [:li (style {:color "red"}) "Do not press play on here, it's already playing"]]]])

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
     [:li "But loads of other data formats can claim these too. What's the real secret to its popularity?"]]]])

(def javascript
  [:section
   slide-config
   [:h1 "Javascript"]
   [:p "I'll just use " [:code "array.sort()"] " to sort these numbers"]
   [:img.r-stretch {:src "img/javascript.png"}]
   [:aside.notes
    [:ul
     [:li "The lowest common denominator, the race to the bottom: Javascript"]
     [:li "Javascript is everywhere and once it made the leap to the backend with Node there was no going back"]]]])

(def history
  [:section
   slide-config
   [:h1 "History"]
   [:img.r-stretch {:src "img/json-history.jpg"}]
   [:aside.notes
    [:ul
     [:li "JSON was proposed back in 2001 by Douglas Crockford"]
     [:li "It was a workaround so that he could make a Javascript object with the reserved keyword 'do' as a key"]
     [:li "His solution was very simple and one we can all admire"]
     [:li "He just put the object keys in quotes"]]]])

(def what-is-json
  [:section
   slide-config
   [:h1 "What is JSON?"]
   [:pre
    [:code {:data-trim true :data-noescape true}
     "{
  \"null\": null,
  \"string\": \"i am a string\",
  \"number\": 42,
  \"boolean\": true,
  \"array\": [
    \"item 0\",
  ]
}"]]
   [:aside.notes
    [:ul
     [:li "JSON has six basic data types - null, number, string, boolean, object and array"]
     [:li "This JSON document here demonstrates every feature"]
     [:li "I was going to end my talk here now that you all know what JSON is"]
     [:li "But it turns out we can do more"]]]])


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
     [:li "JSON is simple and great but we use it and abuse it to do more"]
     [:li "Comments were intentionally left out of JSON by its creator to avoid their use as parsing directives"]
     [:li "This was a noble goal, because it preserves the portability of the format"]
     [:li "But anything outside these primitives, including very common things like dates and times, are just shoved into strings"]
     [:li "Which immediately breaks the portability because we have to understand date formats between systems"]
     [:li "Attempts to improve on it have had limited uptake - CSON, HOCON, JSONC, JSON5, EDN"]
     [:li "If you've never heard of any of them you're just not hipster enough"]]]])

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
     [:li "We could say this is a well-formed, valid document"]
     [:li "It seems to represent a person called John who lives in New York and has two children"]]]])

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
     [:li "It conforms to the JSON spec and can be parsed by any JSON parser"]
     [:li "How can we specify what we mean by a valid document?"]]]])


(def json-schema
  [:section
   slide-config
   [:h1 "JSON Schema"]
   [:div
    [:img.r-stretch {:src "img/liquid-cats.png"}]
    [:audio {:data-autoplay true
             :src "audio/tada.mp3"}]]
   [:aside.notes
    [:ul
     [:li "JSON Schema lets us specify constraints"]
     [:li "If the values in a JSON document fit inside those constraints, we can say it is valid"]
     [:li "Conversely if it doesn't fit we can say it's invalid and throw it away"]
     [:li "It's always good to have validation like this at the edges of your system to avoid needing guards everywhere"]
     [:li "And it prevents garbage from propagating"]]]])

(def json-schema-example
  [:section
   slide-config
   [:h1 "Example"]
   [:pre.r-stretch
    [:code {:data-trim true :data-noescape true
            :data-line-numbers "1-100|4|5,6,10|8,12|15|2,3,7,11"}
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
  \"required\": [ \"productId\" ]
}"]]
   [:aside.notes
    [:ul
     [:li (style {:color "red"}) "Code highlighting"]
     [:li "Here is an example of a schema for a JSON object"]
     [:li "It has two properties, productId and productName"]
     [:li "The type field tells us their JSON types, which are integer and string respectively"]
     [:li "We can express optionality with the required field"]
     [:li "There is also metadata which we can use to give human descriptions and information"]
     [:li "These let us describe and convey intent, just like comments would"]]]])

(def validation
  [:section
   slide-config
   [:h1 "Validation"]
   [:pre
    [:code {:data-trim true :data-noescape true}
     "ajv.validate(schema, {});"]]
   [:pre
    [:code {:data-trim true :data-noescape true
            :data-line-numbers "1-100|4,7-9|5,6|10"}
     "{
  \"errors\": [
    {
      \"instancePath\": \"\",
      \"schemaPath\": \"#/required\",
      \"keyword\": \"required\",
      \"params\": {
        \"missingProperty\": \"productId\"
      },
      \"message\": \"must have property 'productId'\",
    }
  ]
}"]]
   [:aside.notes
    [:ul
     [:li "The original use case for JSON schema is validation"]
     [:li "Does this JSON fit inside my JSON Schema box?"]
     [:li "Third party validators like AJV produce structured and unambiguous errors"]
     [:li (style {:color "red"}) "Code highlighting"]
     [:li "This one tells us that the document is missing the key 'productId' at its root"]
     [:li "It's telling us that the 'required' part of the schema says productId must be present"]
     [:li "And there's even a nice human-readable message we can show to the user"]]]])

(def type-constraints-numbers
  [:section
   slide-config
   [:h1 "Type constraints"]
   [:pre
    [:code {:data-trim true :data-noescape true}
     "{
  \"type\": \"number\",
}"]]
   [:pre
    [:code {:data-trim true :data-noescape true}
     "{
  \"type\": \"integer\"
}"]]
   [:pre
    [:code {:data-trim true :data-noescape true}
     "{
  \"type\": \"integer\",
  \"minimum\": 0,
  \"maximum\": 100
}"]]
   [:aside.notes
    [:ul
     [:li "Where else can JSON Schema take us further?"]
     [:li "Numbers can be constrained to integers"]
     [:li "We can also constrain to positive integers, and set upper bounds"]
     [:li "If you're a Javascript programmer this is already pretty mind-blowing stuff"]]]])

(def type-constraints-strings
  [:section
   slide-config
   [:h1 "Type constraints"]
   [:pre
    [:code {:data-trim true :data-noescape true}
     "{
  \"type\": \"string\",
  \"enum\": [\"Sunny\", \"Cloudy\", \"Rainy\"]
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
     [:li "Remember a valid JSON string is every permutation of every unicode character"]
     [:li "But JSON Schema lets us constrain it to something meaningful, like an enum describing the weather"]
     [:li "It has standards for dates and times"]
     [:li "Finally no more arguments about what date formats to use"]
     [:li "We have patterns for uuids, email addresses, ip addresses"]
     [:li "We can specify the length of a string, or even a pattern it has to match"]
     [:li "So now we have a whole load of richer types that we can use in JSON without unnecessary coupling"]]]])


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
     [:li (style {:color "red"}) "Code highlighting"]
     [:li "This example represents an address in either the USA or Canada"]
     [:li "We can say that if the country is USA, then a zip code matching this pattern is also required"]]]])

(def features-list
  [:section
   slide-config
   [:h1 "Features"]
   [:ul
    [:li "Additional primitive types"]
    [:li "Range or character constraints"]
    [:li "Annotations"]
    [:li "Default values"]
    [:li "Conditionality"]
    [:li "Composition e.g. anyOf, allOf"]
    [:li "Optionality"]
    [:li "Read-only / write-only"]
    [:li "Referencing other schemas"]]
   [:aside.notes
    [:ul
     [:li "JSON Schema has a huge range of features that I've really only just touched on"]
     [:li "It gives us a richer set of primitives that we've all been yearning for"]
     [:li "We have a way of providing comments"]
     [:li "We can give default values"]
     [:li "We can represent even quite complicated things by using conditionality"]
     [:li "We can compose schemas together in various ways by saying a document must match all these schemas, or only one of them"]
     [:li "There should be nothing preventing representation of your data structures in JSON Schema"]]]])

(def ui
  [:section
   slide-config
   [:h1 "Generated UI"]
   [:img {:src "img/generated-ui.png"}]
   [:aside.notes
    [:ul
     [:li "One area I've spent a lot of time in recently is in UIs"]
     [:li "Schema metadata is rich enough to generate a UI"]
     [:li "Types map on to form inputs"]
     [:li "UIs are for use by humans, so the annotations like title and description are used to tell the human what each field is"]
     [:li "We can validate fields as the user types"]
     [:li "The goal is to have a UI which is easy to use and helps the user capture data accurately"]
     [:li "But also be driven by the needs of the back end which can state everything required to make a successful API call"]
     [:li "Once you have a UI for data capture driven by JSON Schema, it becomes a hammer for every nail"]
     [:li "Need to capture some data? Describe it in a JSON Schema and we'll drop it in, here's your UI"]]]])

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
     [:li "JSON Schema is extensible"]
     [:li "That means we can decorate the schema with custom keywords"]
     [:li "This allows us to make a richer, more domain-specific UI at the cost of some coupling"]
     [:li "There are plenty of financial domain types which are not statically enumerable - think about currencies or stocks"]
     [:li "But we can produce a list of them from somewhere, and help the user find the one they want"]
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
     [:li "It turns out you can build a Structured Products platform with a generated UI!"]
     [:li "Now for a brief intro to Structured Products from someone who doesn't know very much to a room full of experts"]
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
     [:li "The option gives the upside of the underlying asset"]]]])

(def composite-payoff
  [:section
   slide-config
   [:h1 "Composite payoff"]
   [:div [:img {:src "img/payoff.webp"}]]
   [:div
    [:small "https://www.investopedia.com/articles/optioninvestor/07/structured_products.asp"]]
   [:aside.notes
    [:ul
     [:li "The payoff profile of the product - how much it's worth, compared to how much the underlying is worth - can have interesting features"]
     [:li "Here the upside is twice as good as the underlying, but capped at 15%"]
     [:li "There is no downside protection, the investor is exposed to 100% of any losses"]
     [:li "So if you think the underlying will go up, but not by much, you can buy this and double your returns"]]]])

(def megazord
  [:section
   slide-config
   [:h1 "Megazord"]
   [:img {:src "img/megazord.gif"}]
   [:aside.notes
    [:ul
     [:li "I like to think of Structured Products as a bit like Megazord"]
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
     [:li "So let's go back to JSON Schema and talk about why we might want to model Structured Products this way"]
     [:li "We have all these different features from various financial instruments which we can combine together to make something better, like a megazord"]
     [:li "There are loads of permutations, and maybe some are rubbish but lots of them are very attractive to buyers"]
     [:li "So we stick that combination together, give it a fancy name and now we have a new Structured Product"]
     [:li "They are complex, yes"]
     [:li "But they can be modelled by composing JSON Schemas"]
     [:li "By using composition, conditionality and all the other tools that JSON Schema gives us, we can represent the features themselves"]
     [:li "And this means that we can represent all the structured products, whatever their combination"]]]])

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
     [:li "The schema is there for everyone to see and inspect, you don't have to drag it out of a POJO or some class model"]
     [:li "Just data, so that tools can be used for comprehension"]
     [:li "Annotated to be readable to humans and convey intent"]
     [:li "Plenty of third party tooling available"]
     [:li "Can be shared with any application regardless of language"]
     [:li "Sometimes we might spend a lot of effort modelling inside an application"]
     [:li "Those models might be published as a side effect, like a Swagger API"]
     [:li "And they might still differ from another application in the system that our app talks to"]]]])

(def schema-central
  [:section
   slide-config
   [:h1 "Schema central"]
   [:img.r-stretch {:src "img/sn-platform.svg"
                    :style "background-color: #eee; padding: 1rem;"}]
   [:aside.notes
    [:ul
     [:li "A Structured Products platform is a big complicated system"]
     [:li "Lots of applications, lots of people - business users, analysts, quants, developers, product owners"]
     [:li "We can put the schema in the middle of the system to describe the domain of structured products"]
     [:li "All the applications can treat it as a blob, but they can refer to the schema if they need to inspect it to perform their task"]
     [:li "Domain entities can flow through the applications without impedance"]
     [:li "Meaning, we don't need every application to have a layer of mappings between their representation and every other app's representation"]
     [:li "New products can be released in the schema without upheaval across the whole system"]
     [:li "The applications can use different languages and technologies"]
     [:li "Developers don't need to be SP experts - you already know I'm not"]
     [:li "We can write tests using these schemas knowing that they accurately represent data we can expect from other applications"]
     [:li "We can use the schema as a useful guard at the edges of the system to avoid garbage coming in"]]]])

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
     [:li "They don't need to understand what the colony is doing, but the colony relies on them to perform their specific role"]
     [:li "We can make applications more like ants, which makes them simpler, easier to understand and test"]
     [:li "They - and their developers - don't need to know much about Structured Products to do their job"]
     [:li "But the system is capturing, pricing, booking Structured Products"]]]])

(def ui-schema-enhancement
  [:section
   slide-config
   [:h1 "Progressive enhancement"]
   [:img.r-stretch {:src "img/ui-schema-pipeline.svg"
                    :style "background-color: #eee; padding: 1rem;"}]
   [:aside.notes
    [:ul
     [:li "My particular experience is building a pricing and booking UI from these schemas"]
     [:li "One concern you probably have is how can we generate a UI that is actually good?"]
     [:li "We built a pipeline where schemas were progressively enhanced before rendering"]
     [:li "We applied defaults, based on who you are, to pre-fill as many fields as possible"]
     [:li "We hid enums with only one choice"]
     [:li "We hid read-only fields"]
     [:li "We renamed fields with localised terminology"]
     [:li "We applied ordering to display the fields in a logical order"]
     [:li "We end up with an enhanced schema which describes a form which is concise and relevant to the user"]]]])

(def ui-state-machine
  [:section
   slide-config
   [:h1 "State machine"]
   [:img.r-stretch {:src "img/ui-state-machine.svg"
                    :style "background-color: #eee; padding: 1rem;"}]
   [:aside.notes
    [:ul
     [:li "We can build a state machine using the schema and a JSON document"]
     [:li "We use the schema to template an initial document by using defaults"]
     [:li "We render a form on the screen using the schema to choose the right component and the document supplies the values"]
     [:li "When a user changes a value, we push it into the document, run validation and templating and produce a new document"]
     [:li "This gets rendered as before and we end up with a responsive, helpful UI that enables accurate data capture"]
     [:li "Building a bespoke UI is not an easy job for complex things like structured products"]
     [:li "JSON Schema actually makes it a lot easier"]]]])


(def ui-rendering
  [:section
   slide-config
   [:h1 "Rendering"]
   [:img {:src "img/ui-rendering.svg"}]
   [:aside.notes
    [:ul
     [:li "Now we can use the data in our state machine to render a form"]
     [:li "The renderer doesn't actually have much to do, other than choose the right component for each field"]
     [:li "We can have a mix of primitive and progressively enhanced components"]
     [:li "Rendering becomes essentially a pure function of the state machine's state"]
     [:li "That means we can relatively cheaply have multiple renderers depending on the use case"]
     [:li "On the left we have what you might call a card view, filling most of the screen"]
     [:il "On the right we have a grid view, allowing you to show products side by side for comparison"]]]])

(def summary
  [:section
   slide-config
   [:h1 "Summary"]
   [:img.r-stretch {:src "img/summary.jpg"}]
   [:aside.notes
    [:ul
     [:li "All of which is a very long way of saying"]
     [:li "Make first-class models of your domain with your business people"]
     [:li "Applications and developers can use it to build the system of simple applications"]
     [:li "It turns out JSON Schema has enough features to describe even complex domains"]
     [:li "We can generate good UIs for capturing data to feed the system"]
     [:li "Thank you for listening"]]]])

(def questions
  [:section
   slide-config
   [:h1.r-fit-text (style {:color "#111"
                           :text-shadow "0 0 10px #fff, 0 0 20px #fff, 0 0 30px orange "})
    "Q&A"]
   [:aside.notes
    [:ul
     [:li "Any questions?"]]]])


(defn all []
  [title-page
   me
   intro
   json-everywhere
   javascript
   history
   what-is-json
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
   ui-state-machine
   ui-rendering

   summary
   questions

   ;; SP ui - any screenshots of sphere?
   ;; enrichment, validation, pricing, doc gen, risk booking - ants

   ;; practical-application ;; common definition used across multiple services, UI, ETL pipe
   ;; case-study-structured-products ;; services can treat it as a blob while knowing they can peer in. because these things are complicated
   ;; so we like to split the domain processes out, and each one may care about different features but maybe nothing compares about the whole (deal + product)
   ;; so instead of inferring that from the parts we are explicit
   ;;

   ])
