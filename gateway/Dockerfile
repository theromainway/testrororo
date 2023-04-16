FROM openjdk:17
EXPOSE 9191
ARG SERVICE_NAME=gateway
COPY target/*.jar gateway.jar
ADD target/*.jar  gateway.jar
ENTRYPOINT ["java", "-jar", "gateway.jar"]