
FROM openjdk:latest

WORKDIR /bill
COPY ./target/bill-0.0.1-SNAPSHOT.jar /bill

EXPOSE 8083

CMD ["java", "-jar", "bill-0.0.1-SNAPSHOT.jar"]