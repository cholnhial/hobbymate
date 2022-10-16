FROM openjdk:17-jdk-slim
MAINTAINER Chol Nhial <c.chol@uqconnect.edu.au>
ADD target/project-service.jar project-service.jar
ENTRYPOINT ["java", "-jar", "/project-service.jar"]
EXPOSE 8090