FROM maven:3.8-openjdk-17 AS build

WORKDIR /app

COPY pom.xml .

COPY src src
RUN mvn package

FROM openjdk:17-jdk-slim

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar
EXPOSE 8084

ENTRYPOINT ["java", "-jar", "app.jar"]