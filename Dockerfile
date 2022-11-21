FROM eclipse-temurin:17-jdk-alpine
MAINTAINER dverma9
COPY target/reactive-rest-message-service-1.0.0-SNAPSHOT.jar reactive-rest-message-service-1.0.0.jar
ENTRYPOINT ["java","-jar","/reactive-rest-message-service-1.0.0.jar"]