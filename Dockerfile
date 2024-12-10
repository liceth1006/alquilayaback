# Usa una imagen base de OpenJDK (por ejemplo, Java 17)
FROM openjdk:17-jdk-slim

# Define el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el archivo JAR de la carpeta 'target' al contenedor
COPY target/*.jar app.jar

# Expone el puerto donde se ejecuta la aplicación
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
