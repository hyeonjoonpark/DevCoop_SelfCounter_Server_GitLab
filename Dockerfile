FROM openjdk
VOLUME /tmp
COPY /build/libs/kiosk-0.0.1-SNAPSHOT.jar .jar
ENTRYPOINT ["java","-jar","/build/libs/kiosk-0.0.1-SNAPSHOT.jar"]
