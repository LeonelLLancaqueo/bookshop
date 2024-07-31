FROM  openjdk:17-jdk-slim
ARG JAR_FILE=build/libs/Libros-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} libros.jar
ENTRYPOINT [ "java", "-jar", "/libros.jar"]