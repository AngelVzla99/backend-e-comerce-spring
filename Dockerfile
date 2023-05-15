FROM openjdk:17-jdk-alpine

EXPOSE 8080

COPY spring-boot-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]