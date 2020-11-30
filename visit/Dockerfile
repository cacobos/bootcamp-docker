
FROM openjdk:latest

WORKDIR /visit
COPY ./target/visit-0.0.1-SNAPSHOT.jar /visit

EXPOSE 8081

CMD ["java", "-jar", "visit-0.0.1-SNAPSHOT.jar"]