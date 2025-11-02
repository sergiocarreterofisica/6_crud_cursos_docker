#FROM openjdk:17-jdk-slim
#docker run -d -p 8080:8080 crud_cursos
FROM mcr.microsoft.com/openjdk/jdk:17-ubuntu
COPY target/6_crud_cursos_docker-0.0.1-SNAPSHOT.jar crud_cursos.jar
EXPOSE 8080
ENTRYPOINT [ "java", "-jar", "/crud_cursos.jar" ]