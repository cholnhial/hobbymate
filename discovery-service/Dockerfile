FROM openjdk:17-jdk-slim
MAINTAINER Chol Nhial <c.chol@uqconnect.edu.au>
ADD target/discovery-service.jar discovery-service.jar
ENTRYPOINT ["java", "-jar", "/discovery-service.jar"]
EXPOSE 8094