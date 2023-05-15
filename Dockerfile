#
# Build stage
#
FROM maven:3.8.2-openjdk-17 AS build
COPY . .
RUN curl -s checkip.amazonaws.com
RUN mvn clean package -DskipTests

#
# Package stage
#
FROM openjdk:17-jdk-slim
COPY --from=build /target/spring-boot-0.0.1-SNAPSHOT.jar demo.jar
# ENV PORT=8080
EXPOSE 443
ENTRYPOINT ["java","-jar","demo.jar"]