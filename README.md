# E-commerce

Assumptions:
- Data are stored in a Map
- The currency is £
- The expiration time (expTime) is in minutes
- He can't add two elements with the same title (just to add more business logic)

Notes:
- I've written just a few tests to show how I work with JUnit, TDD, BDD and Mockito. The name of a test tells what kind of test I've done (E.g. AddOfferOk or AddOfferWithSameTitle). I've used this structure, along with the pattern build-operate-check, to make the tests as easy as possible to read.
- There is a test which fails, this is just to show how TDD works (testing the function before writing its behavior).

This Spring Rest Application provides the following services:

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

More comments and explanations can be found in the source code
