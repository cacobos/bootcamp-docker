
FROM openjdk:latest

WORKDIR /gateway
COPY ./target/gateway-0.0.1-SNAPSHOT.jar /gateway

EXPOSE 8080

CMD ["java", "-jar", "gateway-0.0.1-SNAPSHOT.jar"]