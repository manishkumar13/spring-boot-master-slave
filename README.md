# Spring Boot JPA MySQL - Building Rest CRUD API example

### Create tutorial in Slave db
> curl --location --request POST 'http://localhost:8080/api/tutorials?type=slave' \
--header 'Content-Type: application/json' \
--data-raw '{
"title":"C++",
"description":"Intro to C++"
}'

### Create tutorial in Master db
>curl --location --request POST 'http://localhost:8080/api/tutorials?type=master' \
--header 'Content-Type: application/json' \
--data-raw '{
"title":"Java",
"description":"Intro to Java"
}'

### Get all tutorial from Master db
>curl --location --request GET 'http://localhost:8080/api/tutorials?type=master'

### Get all tutorial from Slave db
>curl --location --request GET 'http://localhost:8080/api/tutorials?type=slave'

### Get tutorial by id  from Master db
>curl --location --request GET 'http://localhost:8080/api/tutorials/1?type=master'

### Get all tutorial by id from Slave db
>curl --location --request GET 'http://localhost:8080/api/tutorials/1?type=slave'