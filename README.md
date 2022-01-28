# Covid19Api
Microservice that gets the current Covid-19 statistics from URL https://api.covid19api.com/summary
and prepares them so they can be accessed per country.

On every 6 hours the database is updated but can also be updated with "/save" request.
There is another request(GET request) which shows information about requested country.
(/country/{COUNTRYCODE})
COUNTRYCODE can include only two capital letters.

Used Technologies:
Spring Boot
JPA
PostgreSQL
