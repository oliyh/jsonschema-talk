# Intro

https://starwarsintrogenerator.com/scroller?u=2y23vl2c

```
We need to talk about...
JSON

The year is 2024
JSON has won the war

Every programming language and database supports JSON
Users can read it, even managers can understand it
The galaxy is being serialised to JSON, one star system at a time...
```

## Resistance is futile
JSON is the content type of the internet
Just about anything can be represented in JSON

Diagram from https://www.json.org/json-en.html

## Lowest common denominator
When it comes to cross-language, cross-platform popularity we are usually limited to the lowest common denominator.
JSON rode the wave of Javascript adoption to become ubiquitous.

JSON has six basic data types - number, string, boolean, object, array and null - but does not cover e.g. dates
Comments were intentionally left out of JSON by its creator Douglas Crockford to avoid their use as parsing directives

Attempts to improve on it - comments, additional data types - have had limited success...
- CSON
- HOCON
- JSON5
- JSONC
- EDN

## JSON examples

This is JSON
```
{
  "first_name": "John",
  "last_name": "Smith",
  "age": 27,
  "address": {
    "street_address": "21 2nd Street",
    "city": "New York",
    "state": "NY",
  },
  "children": [
    "Catherine",
    "Thomas",
    "Trevor"
  ]
}
```

But this is JSON too
```
{
  "a": "x",
  "x": "-1",
  "x": 27,
  "\r": {
    "😐": "{\"true\": -1.07e3}",
  },
  "z": [
    "null",
    null
  ]
}
```

How can we represent a specification for a JSON document so that we can describe acceptable values?

# JSON Schema

A JSON document that describes _constraints_ which must be adhered to in order to pass validation and be considered a valid document.

## Why?

- Common, language agnostic reference point for collaborators
- Validation
- UI generation
- Documentation

## Example

```
{
  "$schema": "https://json-schema.org/draft/2020-12/schema",
  "$id": "https://example.com/product.schema.json",
  "title": "Product",
  "description": "A product from Acme's catalog",
  "type": "object",
  "properties": {
    "productId": {
      "description": "The unique identifier for a product",
      "type": "integer"
    },
    "productName": {
      "description": "Name of the product",
      "type": "string"
    }
  },
  "required": [ "productId", "productName" ]
}
```

The most important things here are the `type` keys, which define which of JSON's six data types the value should be.
Note there is metadata, like `title` and `description` - useful for UI - more on this later.

## Validation

A JSON Schema validator will take a JSON Schema and a JSON document and tell you if the document conforms to the schema.
If it does not, you will get an error:

```
{
  "productId": 1
}
```

Will give a validation error like `Missing property: productName`.

## Type constraints

In addition to restricting a value to a particular type, we can specify further constraints on that value.

Numbers can have a minimum, maximum
Arrays can have minSize, maxSize, uniqueItems
Objects can have required and optional properties
Strings can have enumerated allowed values, regex, and also has formats 'missing' from JSON like date, time

... and so on

This lets us be much more specific and expressive about what constitutes a valid document than just the inbuilt types and we additionally
have metadata to help communicate the intention

## Conjunctions

Todo - allOf, oneOf

## Conditionality

Todo - if then else

## Extensible

You can add extra keys to JSON Schema to implement your own extensions.
Example of 'domain' extensions e.g. Currency, or postcodes - don't want to enumerate it, there's no regex, but there is a restriction to what string is allowed

# UI Generation

Todo

# Best practices

Todo
