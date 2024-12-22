FROM openjdk:21-jdk-slim

WORKDIR /app

# Copia todos os arquivos do projeto para o container
COPY . .

RUN apt-get update && apt-get install -y maven && mvn clean package

# Copia o arquivo JAR gerado para o local apropriado
COPY ./target/*.jar /app/app.jar

EXPOSE 8081

CMD ["java", "-jar", "app.jar"]
