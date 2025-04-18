FROM gradle:8.3.0-jdk17-alpine

COPY . .

RUN gradle build --no-daemon

EXPOSE 8080

ENTRYPOINT ["java","-jar","build/libs/AnexoIIDigital-0.0.1-SNAPSHOT.jar"]
