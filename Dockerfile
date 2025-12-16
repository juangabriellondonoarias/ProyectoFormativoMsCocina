# --- Etapa 1: Construcción (Build) ---
# Usamos una imagen de Maven con Java 21 para compilar el proyecto
FROM maven:3.9.6-eclipse-temurin-21 AS build

# Establecemos el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiamos el archivo pom.xml y descargamos las dependencias (para aprovechar el caché)
COPY pom.xml .

# Copiamos todo el código fuente
COPY src ./src

# Compilamos el proyecto y creamos el .jar (saltando los tests para ir más rápido)
RUN mvn clean package -DskipTests

# --- Etapa 2: Ejecución (Run) ---
# Usamos una imagen ligera de Java 21 solo para correr la app
FROM eclipse-temurin:21-jre-alpine

# Directorio de trabajo
WORKDIR /app

# Copiamos el .jar generado en la etapa anterior
# Nota: El nombre ms_cocina-0.0.1-SNAPSHOT.jar sale de tu pom.xml (artifactId + version)
COPY --from=build /app/target/ms_cocina-0.0.1-SNAPSHOT.jar app.jar

# Exponemos el puerto que configuraste en application.properties
EXPOSE 8083

# Comando para iniciar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]