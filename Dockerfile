# Use as image
FROM openjdk:17-jdk-slim-buster

# Set the working directory
WORKDIR /app

# Copy the Spring Boot jar file to the container
COPY target/backend.jar /app/backend.jar

# Expose port 8080 for the app
EXPOSE 8080

# Set the command to start the Spring Boot app
CMD ["java", "-jar", "/app/backend.jar"]

