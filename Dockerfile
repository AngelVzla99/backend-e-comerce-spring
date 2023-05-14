# Use a base image with an appropriate version of the JVM installed
FROM openjdk:17-jdk-alpine

# Set the working directory to /app
WORKDIR /app

# Copy the application JAR file into the container
COPY target/back-end.jar /app/back-end.jar

# Expose port 8080
EXPOSE 8080

# Set the command to run the application when the container starts
CMD ["java", "-jar", "my-spring-boot-app.jar"]