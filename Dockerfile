# syntax=docker/dockerfile:1
FROM openjdk:23-jdk-oraclelinux8
WORKDIR /app
COPY target/spring-transaction-1.0.jar spring-transaction-1.0.jar
COPY src/main/resources/application-docker.yaml /app/application-docker.yaml
ENTRYPOINT ["java", "-Dspring.config.location=/app/application-docker.yaml", "-jar", "spring-transaction-1.0.jar"]
#docker build -t spring-transaction:v1.0 .
