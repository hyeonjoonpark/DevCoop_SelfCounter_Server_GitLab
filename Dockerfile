FROM openjdk:17-alpine

ARG JAR_PATH=./build/libs

COPY ${JAR_PATH}/kiosk-0.0.1-SNAPSHOT.jar ${JAR_PATH}/kiosk.jar

CMD ["java","-jar","./build/libs/kiosk.jar"]
