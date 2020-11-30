
FROM openjdk:latest

WORKDIR /eureka-server
COPY ./target/discovery-0.0.1-SNAPSHOT.jar /eureka-server

EXPOSE 8761

CMD ["java", "-jar", "discovery-0.0.1-SNAPSHOT.jar"]