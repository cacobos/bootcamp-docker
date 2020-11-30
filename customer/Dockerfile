
FROM openjdk:latest

WORKDIR /customer
COPY ./target/customer-0.0.1-SNAPSHOT.jar /customer

EXPOSE 8082

CMD ["java", "-jar", "customer-0.0.1-SNAPSHOT.jar"]