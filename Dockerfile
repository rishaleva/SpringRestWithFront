FROM maven:3.8.4-openjdk-17-slim AS builder

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

FROM openjdk:17-slim

COPY --from=builder target/springBootSecurity-0.0.1-SNAPSHOT.jar /app.jar

COPY src/main/resources/static /app/static

CMD ["java","-jar","/app.jar"]
