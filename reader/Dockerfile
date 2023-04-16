FROM openjdk:17
EXPOSE 9003
COPY target/*.jar reader.jar
ADD target/*.jar reader.jar
ENTRYPOINT ["java", "-jar", "reader.jar"]