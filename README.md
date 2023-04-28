# backend-e-commerce-spring
Basic backend for an e-commerce made in spring boot using a SQL database.

# Libraries used

* JPA and Hibernate
* Flyway for migrations
* JWT for user authentication and authorization 

# Database information

* The DBMS used is Postgres ( the relational model can be found in src/main/resources/db.migration/V1__Create_first_table.sql ).
* I'm creating the database in a docker container (can be found in the docker-compose file). 

# TODO

* Save user image (?) 
* Save User genre (?) 