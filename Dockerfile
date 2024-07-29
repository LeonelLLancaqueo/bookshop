FROM  openjdk
ARG JAR_FILE=build/libs/Libros-0.0.1-SNAPSHOT.jar
RUN echo ${pwd} && ls -lR  
COPY ${JAR_FILE} libros.jar
EXPOSE 8080
ENTRYPOINT [ "java", "-jar", "/libros.jar"]