FROM openjdk:8
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} user-service.jar
EXPOSE 3005
ENTRYPOINT ["java","-jar","/user-service.jar"]