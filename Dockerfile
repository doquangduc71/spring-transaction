# syntax=docker/dockerfile:1
FROM openjdk:23-jdk-oraclelinux8
WORKDIR /app
COPY target/spring-transaction-1.0.jar spring-transaction-1.0.jar
ENV SPRING_PROFILES_ACTIVE=docker
ENTRYPOINT ["java", "-jar", "spring-transaction-1.0.jar"]
#docker build -t spring-transaction:v1.0 .
