# Etapa 1: Build do projeto com Maven
FROM maven:3.9.4-eclipse-temurin-17 AS build

# Define o diretório de trabalho no container
WORKDIR /app

# Copia todos os arquivos do projeto para dentro do container
COPY . .

# Executa o build do projeto e gera o JAR (sem testes)
RUN mvn clean package -DskipTests

# -----------------------------------------------------------------------------

# Etapa 2: Runtime com imagem leve do Java
FROM openjdk:17-jdk-slim

# Define o diretório onde a aplicação vai rodar
WORKDIR /app

# Copia apenas o JAR gerado da etapa anterior para a nova imagem
COPY --from=build /app/target/cadusers-0.0.1-SNAPSHOT.jar cadusers.jar

# Define o comando de entrada para rodar a aplicação
ENTRYPOINT ["java", "-jar", "cadusers.jar"]
