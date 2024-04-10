# jsonschema-talk

## We need to talk about JSON

- Basic introduction
  - JSON has won the war (starwars intro)
    - Every language supports it
    - Databases support it
    - Content type of the internet
    - Just enough data literals to express anything
  - But JSON is missing things
    - Comments
    - Dates, times, keywords
    - Sets
  - Constraints over JSON
    - Examples of valid JSON that represent garbage
- Things you can describe
  - Primitives (including enums, numbers with min/max, strings with formats or patterns)
  - Objects
  - Arrays
  - Conjunctions (allOf, oneOf, anyOf)
  - Logical branches (if then else)
  - Metaschema, hyperschema!
- Uses
  - Common, language agnostic reference point for collaborators
  - Validation
  - UI generation
  - Documentation
- Shares basic grammar with OpenAPI, although that is also concerned with transport
- Best practices
  - Arrays of objects, rather than special keys
  - Avoid unconstrained strings
  - Meaningful, human readable titles and descriptions
- UI generation
  - Strangely lacking, with exception of rjsf but that is not very composable
  - Diagram of redux / render loop
  - Ideal props to render with
  - Mention jsonpath, jsonpointer
