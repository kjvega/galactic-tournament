# Etapa 1: Compilar el proyecto con Maven
FROM maven:3.9.9-eclipse-temurin-17 AS builder
WORKDIR /app

# Copiar archivos del proyecto
COPY pom.xml .
COPY src ./src

# Compilar el proyecto y generar el JAR
RUN mvn clean package -DskipTests

# Ejecutar el proyecto con JDK liviano
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

#JAR desde la etapa anterior
COPY --from=builder /app/target/*.jar app.jar

#Puerto de la app
EXPOSE 8081

# Comando de ejecución
ENTRYPOINT ["java", "-jar", "app.jar"]

