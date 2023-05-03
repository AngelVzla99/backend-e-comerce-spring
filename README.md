# Backend Spring Boot Rest Api 
Basic backend for an e-commerce made in spring boot using a SQL database.

# Technology
* JPA and Hibernate - ORM to map the SQL model
* Flyway - To handle migrations in the database
* JWT - user authentication and authorization 

# Database information

* The DBMS used is Postgres ( the relational model can be found in src/main/resources/db.migration/V1__Create_first_table.sql ).
* I'm creating the database in a docker container (can be found in the docker-compose file). 

# Run and test the server locally

## Running the server
Requires Maven and Java11+ to run.
```sh
$ cd backend-e-commerce
$ mvn clean install
$ maven package
$ java -jar target/backend-0.0.1-SNAPSHOT.jar
```

You can also run Spring Boot app with Maven plugin :

```sh
$ mvn spring-boot:run
```

Once the server is setup you should be able to access following URL :
- http://localhost:8080

## Testing

```sh
$ mvn test
```

## Run database in docker container

```sh
$ docker-compose up
```

The data will be saved in a volume folder db-data

# TODO

* Save user image (?) 
* Save User genre (?) 
