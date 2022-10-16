FROM openjdk:17-jdk-slim
MAINTAINER Chol Nhial <c.chol@uqconnect.edu.au>
ADD target/gateway-service.jar gateway-service.jar
ENTRYPOINT ["java", "-jar", "/gateway-service.jar"]
EXPOSE 8095