FROM openjdk:17-jdk-slim
WORKDIR /app
COPY build/libs/backend.jar backend.jar
ENTRYPOINT ["java", "-jar", "backend.jar"]
