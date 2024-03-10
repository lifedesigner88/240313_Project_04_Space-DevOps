# Stage 1: Building the Application
FROM openjdk:17-jdk-slim as builder
WORKDIR app
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY src src

# Build
RUN chmod +x ./gradlew
RUN ./gradlew bootJar

# Stage 2: Running the Application
FROM openjdk:17-jdk-slim
WORKDIR app

COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]