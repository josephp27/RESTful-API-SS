# A RESTful API written in JAVA using Spring Boot for Solstice

## Scope
Develop a RESTful API that would allow a web or mobile front-end to:

* Create a contact record
* Retrieve a contact record
* Update a contact record
* Delete a contact record
* Search for a record by email or phone number
* Retrieve all records from the same state or city

The contact record should represent the following information: name, company, profile image, email,
birthdate, phone number (work, personal) and address.

## Compiling and Running
```
Within the directory run:

   ./gradlew build 
   java -jar build/libs/solstice-0.0.1-SNAPSHOT.jar
   

Or to directly run:

   ./gradlew bootRun
```
## Notes and Assumptions
* The email is the main unique identifier for this API as many people can have the same name, etc, but not the same email. 


* The database is a simple SQL database that is not persistant for ease of Solstice testing. Inside data.sql are some preloaded contacts however

* When running the below tests I recommend using curl as navigating with the browser on errors looks slightly different than a terminal request as Spring Boot adds in more info.

* Assumes images are links

* 9 Unit Tests [located in the tests folder](https://github.com/josephp27/RESTful-API-SS/blob/master/src/test/java/com/solstice/api/SolsticeApiApplicationTests.java). Feel free to use these to understand parameters to requests

* Performs validations on various inputs like phone numbers using Regular Expressions

## Requests
2. /get

   http://localhost:8080/get (returns all contacts)

   http://localhost:8080/get?work_number= (returns contact assigned to work number)

   http://localhost:8080/get?email= (returns contact assigned to email)
   
3. /getall (retrieve all records from state or city. States are 2 char codes like IL or MO)

   http://localhost:8080/getall?state=IL

   http://localhost:8080/getall?city=chicago

3. /delete (It is based only on the email as I have set emails to be the unique identifier) 

   http://localhost:8080/delete?email= 

4. /add (Add a user if the email does not already exist)

   Arguments: name (required), email (required), work (required), personal, company, street, state, city, image, bd

   http://localhost:8080/add?name=Tom%20Cruise&email=myemail@live.com&work=+1(555)555-5555&personal=6305555555&company=walmart&bd=11/27/1992&street=99+S+6th&state=IL&city=Chicago

   Spacing can be emulated by + or %20

5. /update (Update a user if they exist in database. Updates based on email address. If parameter not specified, sets to blank)

   Arguments: name (required), email (required), work (required), personal, company, street, state, city, image, bd
   
   http://localhost:8080/update?name=Johnny%20Depp&email=myemail@live.com&work=+1(123)555-4567&personal=6306666666&company=Jewel

## TODO

* Abstract away more things with the validator and cleanup. Make most methods private from protected

* Do better checking for valid entries

* Make a cooler looking error page for browsers

* Allow for updating only some values and retaining old if value is empty within update method

* Add more unit tests

* Provide better feedback on delete, add, update, etc in form of a JSON response
