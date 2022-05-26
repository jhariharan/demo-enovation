This is a REST API which manages the employees details of a system, which is protected by Basic Auth

Technologies used 
Spring Boot
Spring
Maven
Java
Hibernate
HikariCP
H2 in-memory database
Junit

Below are some of the operations done by the API
Add an employee
POST : http://localhost:8080/api/v1/employees   

Find an employee
GET : api/v1/employees/{id}    

Find all existing employees
GET : api/v1/employees

Modify an employee details given an id
PUT : api/v1/employees/{id}

Delete an existing employee given an i
DELETE : api/v1/employees/{id} 

Delete all employees
DELETE : api/v1/employees





