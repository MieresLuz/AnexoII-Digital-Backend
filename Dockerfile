# Etapa 1: build
FROM gradle:8.3.0-jdk17-alpine as build
WORKDIR /app
COPY . .
RUN chmod +x ./gradlew && ./gradlew build --no-daemon

# Etapa 2: runtime
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/build/libs/AnexoIIDigital-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
