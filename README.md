# E-commerce

Assumptions:
- Data are stored in a Map
- The currency is defined
- There is a test which fails, this is just to show how I've applied TDD (testing before writing the behavior). I've also used BDD for some tests. 
- The expTime is in minutes
- He can't add two elements with the same title

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
