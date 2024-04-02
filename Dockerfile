FROM openjdk:17-jdk

# microdnf를 사용하여 필요한 도구 설치
RUN microdnf install findutils && \
    microdnf clean all

# 작업 디렉토리 설정
WORKDIR /app

# CMD는 docker-compose.yml에서 재정의할 수 있으므로, 여기서는 제거합니다.
