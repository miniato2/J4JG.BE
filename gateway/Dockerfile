FROM openjdk:17-jdk-slim
WORKDIR /app
COPY build/libs/gateway.jar gateway.jar
ENTRYPOINT ["java", "-jar", "gateway.jar"]
