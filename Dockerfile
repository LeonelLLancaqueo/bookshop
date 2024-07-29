FROM  openjdk
ARG JAR_FILE=build/libs/Libros-0.0.1-SNAPSHOT.jar
RUN echo ${PWD}
COPY ${JAR_FILE} Libros.jar
EXPOSE 8080
ENTRYPOINT [ "java", "-jar", "/libros.jar"]