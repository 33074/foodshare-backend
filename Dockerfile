# Stage 1: Build the Spring Boot application using Maven
FROM maven:3.8.5-openjdk-17 AS builder
WORKDIR /app
COPY pom.xml ./
COPY src ./src
RUN mvn package -DskipTests

# Stage 2: Create the final runtime image using a JRE
# --- THIS IS THE FIX ---
FROM openjdk:17-jdk-slim 
# --- END FIX ---
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8086
ENTRYPOINT ["java", "-jar", "app.jar"]
