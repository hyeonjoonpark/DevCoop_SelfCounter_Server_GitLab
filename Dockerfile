# Oracle Linux 기반의 OpenJDK 이미지를 사용합니다.
FROM openjdk:11
ARG JAR_FILE=build/libs/kiosk-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} ./build/libs/kiosk-0.0.1-SNAPSHOT.jar
ENV TZ=Asia/Seoul
ENTRYPOINT ["java", "-jar", "./build/libs/kiosk-0.0.1-SNAPSHOT.jar"]