# --- Etapa 1: Builder (Usando la imagen oficial de Maven) ---
FROM maven:3.9.6-eclipse-temurin-21 AS builder

# Directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el pom.xml y el código fuente
COPY pom.xml .
COPY src/ /app/src/

# Construye el proyecto Spring Boot y genera el jar ejecutable
# El comando 'mvn package' también descarga las dependencias
RUN mvn package -DskipTests

# --- Etapa 2: Final (Usando solo JRE para la ejecución) ---
# Usa una imagen base ligera con JRE 21
FROM eclipse-temurin:21-jre-jammy

# Directorio de trabajo
WORKDIR /app

# Expone el puerto de la aplicación (8085)
EXPOSE 8085

# Copia el archivo JAR generado desde la etapa 'builder'
# El nombre del JAR se basa en <artifactId>-<version>.jar de tu pom.xml
COPY --from=builder /app/target/ms_cocina-0.0.1-SNAPSHOT.jar /app/app.jar

# Define el comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]