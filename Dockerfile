FROM openjdk:8-jdk-alpine

COPY ./target/task.jar task.jar

ENTRYPOINT ["java", "-jar", "task.jar"]