# A RESTful API written in Spring Boot for Solstice


The email is the main unique identifier for this API as many people can have the same name, etc, but not the same email.

1. 9 Unit Tests
2. /get

   http://localhost:8080/get

   http://localhost:8080/get?work_number=

   http://localhost:8080/get?email=
3. /getall

   http://localhost:8080/getall?state=IL

   http://localhost:8080/getall?city=chicago

3. /delete

   localhost:8080/delete?email= (It is based only on the email as I have set emails to be the unique identifier) 

4. /add

   Arguments: email, work, personal, company, street, state, city, image, bd

   localhost:8080/add?name=Tom%20Cruise&email=myemail@live.com&work=+1(555)555-5555&personal=6305555555&company=walmart&bd=11/27/1992&street=99+S+6th&state=IL&city=Chicago'

   Spacing can be emulated by + or %20
