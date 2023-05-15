FROM openjdk:17-jdk-slim
COPY . /app
WORKDIR /app
RUN ./mvnw clean package -DskipTests
EXPOSE 8080
ENTRYPOINT ["java","-jar","target/spring-boot-0.0.1-SNAPSHOT.jar"]
# Add HEALTHCHECK
HEALTHCHECK --interval=1m --timeout=3s \
  CMD curl -f http://localhost:8080/api/home || exit 1