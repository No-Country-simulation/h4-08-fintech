# Usa una imagen base de Java (OpenJDK 17)
FROM openjdk:17-jdk-slim

# Establece un directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el archivo JAR generado al contenedor
COPY target/backend-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto 8080, que es el puerto por defecto en Spring Boot
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
