FROM  openjdk:17-jdk-slim
ARG JAR_FILE=build/libs/Libros-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} libros.jar
EXPOSE 8080
ENTRYPOINT [ "java", "-jar", "/libros.jar"]