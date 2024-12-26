# Primeiro estágio: Compilar o JAR
FROM openjdk:21-jdk-slim AS builder
RUN apt-get update && apt-get install -y maven
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Segundo estágio: Rodar a aplicação
FROM openjdk:21-jdk-slim
WORKDIR /app
COPY --from=builder /app/target/*.jar /app/app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
