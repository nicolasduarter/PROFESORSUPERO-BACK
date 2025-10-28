# Etapa 1: Construcción de la app
FROM maven:3.9.4-eclipse-temurin-17 AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: Imagen final para producción
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=builder /app/target/refuerzoMorado-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
