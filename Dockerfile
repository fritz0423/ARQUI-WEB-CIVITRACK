# Imagen base de Java 17
FROM eclipse-temurin:17-jdk-alpine

# Directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiamos pom.xml y el código fuente
COPY pom.xml ./
COPY mvnw ./
COPY .mvn ./.mvn
COPY src ./src

# Construimos la app con Maven y saltamos los tests
RUN ./mvnw clean package -DskipTests

# Exponemos el puerto que usa tu app (Spring Boot por defecto 8080)
EXPOSE 8080

# Comando para ejecutar la app, toma cualquier jar generado
CMD ["java", "-jar", "target/*.jar"]
