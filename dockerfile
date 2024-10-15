FROM bellsoft/liberica-openjdk-alpine:17

WORKDIR /app
COPY . .

RUN ./gradlew clean build

RUN cp build/libs/subwate-backend-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar"]