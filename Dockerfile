# Imagem base para construção
FROM maven:3.9.9-eclipse-temurin-17 AS builder

# Definindo o diretório de trabalho para a construção
WORKDIR /app

# Copiando o pom.xml e os arquivos de configuração do Maven
COPY pom.xml .

# Baixando as dependências para cache
RUN mvn dependency:go-offline

# Copiando o restante do código para o container
COPY /src ./src

# Construindo o JAR da aplicação
RUN mvn clean package -DskipTests

# Imagem final para execução
FROM openjdk:17-jdk-slim

# Definindo o diretório de trabalho dentro do container
WORKDIR /app

# Copiando o JAR gerado no estágio anterior
COPY --from=builder /app/target/*.jar app.jar

# Expondo a porta 8081 (porta padrão do Spring Boot)
EXPOSE 8081

# Comando para rodar a aplicação Spring Boot
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
