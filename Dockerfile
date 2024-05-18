FROM gradle:7.5.0-jdk17-alpine AS build

WORKDIR /home/gradle/project

COPY . .

# Build the application, excluding tests
RUN gradle build --no-daemon -x test

FROM openjdk:17-alpine

WORKDIR /app

COPY --from=build /home/gradle/project/build/libs/kiosk-0.0.1-SNAPSHOT.jar /app/kiosk.jar

CMD ["java", "-jar", "/app/kiosk.jar"]