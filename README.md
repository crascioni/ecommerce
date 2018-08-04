# E-commerce

Assumptions:
- Data are stored in a Map
- The currency is £
- There is a test which fails, this is just to show how TDD works (testing before writing the behavior of the function). I've also used BDD for some tests.
- The expiration time (expTime) is in minutes
- He can't add two elements with the same title
- I've written just a few tests to show how I work with JUnit, TDD and BDD.

As a Spring Rest Application, it provides the following services:

- Retrive all the offers
    - PATH: /getOffers 
    - METHOD: GET
 
- Add an offer
    - PATH: /addOffer
    - METHOD: POST
    - JSON EXAMPLE:   {
        "title": "Mock",
        "desc": "this is just a mock",
        "price": 200,
        "expTime": 2
   }

- Remove an offer
    - PATH: /removeOffer
    - METHOD: POST
    - JSON EXAMPLE:    {
        "title": "Mock"
    }

More comments and explanation can be found in the source code
