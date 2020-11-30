
FROM openjdk:latest

WORKDIR /payment
COPY ./target/payment-0.0.1-SNAPSHOT.jar /payment

EXPOSE 8084

CMD ["java", "-jar", "payment-0.0.1-SNAPSHOT.jar"]