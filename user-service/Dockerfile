FROM openjdk:17-jdk-slim
MAINTAINER Chol Nhial <c.chol@uqconnect.edu.au>
ADD target/user-service.jar user-service.jar
ENTRYPOINT ["java", "-jar", "/user-service.jar"]
EXPOSE 8093