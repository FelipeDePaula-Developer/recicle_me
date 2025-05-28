# Imagem base com Java 17
FROM openjdk:17-jdk-slim

# Diretório de trabalho dentro do container
WORKDIR /app

# Copia o arquivo JAR gerado pelo Maven para o container
COPY target/cadusers-0.0.1-SNAPSHOT.jar /app/cadusers.jar

# (Opcional) Se quiser usar o wait-for-it para aguardar o banco de dados:
# COPY wait-for-it.sh /app/
# RUN chmod +x wait-for-it.sh

# Comando para rodar o JAR
ENTRYPOINT ["java", "-jar", "cadusers.jar"]
