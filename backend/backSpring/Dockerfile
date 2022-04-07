# JDK11 기반으로 도커 이미지 생성
FROM openjdk:11-jdk
WORKDIR /backend
COPY . .

# 권한 주기
RUN chmod +x ./gradlew

# spring 프로젝트 build
CMD ["./gradlew", "clean", "build"]

# JAR_FILE 위치에 있는 jar파일을 app.jar이름의 파일로 복사
ARG JAR_FILE=build/libs/artSider-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar

# 8080 포트 사용
EXPOSE 8080
ENTRYPOINT ["java","-jar","./app.jar"]