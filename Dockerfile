FROM openjdk:17
VOLUME /tmp
COPY /build/libs/kiosk-0.0.1-SNAPSHOT.jar kiosk.jar
ENTRYPOINT ["java","-jar","kiosk.jar"]
