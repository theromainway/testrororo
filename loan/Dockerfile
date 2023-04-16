FROM openjdk:17
EXPOSE 9002
COPY target/*.jar loan.jar
ADD target/*.jar  loan.jar
ENTRYPOINT ["java", "-jar", "loan.jar"]