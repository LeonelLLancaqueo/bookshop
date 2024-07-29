FROM  eclipse-temurin:17-jdk-alpine
ARG JAR_FILE=build/libs/Libros-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} libros.jar
EXPOSE 8080
ENTRYPOINT [ "java", "-jar", "/libros.jar"]