FROM openjdk:21-jdk-slim
WORKDIR /app
RUN apt-get update && apt-get install -y maven && mvn clean package
COPY ./target/*.jar /app/app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/app/app.jar"]