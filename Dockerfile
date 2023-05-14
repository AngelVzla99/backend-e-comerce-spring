#
# Build stage
#
FROM openjdk:17-jdk-slim-buster as build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

#
# Package stage
#
FROM adoptopenjdk:17-jre-hotspot
WORKDIR /app
COPY --from=build /app/target/demo-0.0.1-SNAPSHOT.jar demo.jar
# ENV PORT=8080
EXPOSE 8080
ENTRYPOINT ["java","-jar","demo.jar"]
