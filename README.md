# A RESTful API written in JAVA using Spring Boot for Solstice

## Scope
```
Develop a RESTful API that would allow a web or mobile front-end to:
● Create a contact record
● Retrieve a contact record
● Update a contact record
● Delete a contact record
● Search for a record by email or phone number
● Retrieve all records from the same state or city
The contact record should represent the following information: name, company, profile image, email,
birthdate, phone number (work, personal) and address.
```
---

## Compiling and Running
```
Within the directory run:

   ./gradlew build 
   java -jar build/libs/solstice-0.0.1-SNAPSHOT.jar
   

Or to directly run:
   ./gradlew bootRun
```
---

The email is the main unique identifier for this API as many people can have the same name, etc, but not the same email. 


The database is a simple SQL database that is not persistant for ease of Solstice testing. Inside data.sql are some preloaded contacts however


1. 9 Unit Tests
2. /get

   http://localhost:8080/get

   http://localhost:8080/get?work_number=

   http://localhost:8080/get?email=
3. /getall

   http://localhost:8080/getall?state=IL

   http://localhost:8080/getall?city=chicago

3. /delete

   http://localhost:8080/delete?email= (It is based only on the email as I have set emails to be the unique identifier) 

4. /add

   Arguments: name (required), email (required), work (required), personal, company, street, state, city, image, bd

   http://localhost:8080/add?name=Tom%20Cruise&email=myemail@live.com&work=+1(555)555-5555&personal=6305555555&company=walmart&bd=11/27/1992&street=99+S+6th&state=IL&city=Chicago'

   Spacing can be emulated by + or %20

5. /update

   Updating can only be done if a contact has been added and is based on email as it is the unique identifier

   Arguments: name (required), email (required), work (required), personal, company, street, state, city, image, bd
   
   http://localhost:8080/update?name=Johnny%20Depp&email=myemail@live.com&work=+1(123)555-4567&personal=6306666666&company=Jewel


---
## TODO

* Abstract away more things with the validator

* Do more checking for valid entries

* Make a cooler looking error page for browsers

* Allow for updating only some values and retaining old if value is empty within update method

* Add more unit tests

* Provide better feedback on delete, add, update, etc in form of a JSON response


