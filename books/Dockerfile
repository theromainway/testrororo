FROM openjdk:17
EXPOSE 9001
COPY books-server/target/*.jar books-server.jar
ADD books-server/target/*.jar  books-server.jar
ENTRYPOINT ["java", "-jar", "books-server.jar"]