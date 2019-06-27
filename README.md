# offer-service

Tech Stack
 - Java 8, Spring Boot 2.1, Spring Web and Data, H2 in memory database, Maven, Junit, Mockito and mapstruct.

Import and Run
 - The project is maven based, it is easily imported to any IDE as usual way
 - mvn clean install
 - java -jar target/offer-service-0.0.1-SNAPSHOT.jar
 - during the application start-up, it creates a schema in the H2 in-memory db.

Endpoints
 - POST http://localhost:8080/offer-service/offers, creates an offer with the following request body
 ```
 {
     "description": "Test offerRequest",
     "price": 12.99,
     "currency": "GBP",
     "expireTime": {
         "time": 10000,
         "unit": "MILLISECONDS"
     }
 }
 unit value could be DAYS, HOURS, MICROSECONDS, MILLISECONDS, MINUTES, NANOSECONDS, SECONDS .
 ```

 - GET http://localhost:8080/offer-service/offers/3; retrieves the specific offer by given id

 - PUT http://localhost:8080/offer-service/offers/3/cancel; cancel the specific offer by given id

Notes and Assumptions :
 - Each commit is kind of TDD's green/refactor step and it has explanation, what is done etc.
 - Assume that no need to handle exceptional cases since there is no requirements about how to behave when something goes wrong, once we had it, need to refactor a few places which currently return `null`.
 - The only way to get offer by its id.
 - Cancelling an offer endpoint updates the status without checking that offer is Expired/Valid.
 - Logging will be introduced by proper logging template that could be json based etc.



