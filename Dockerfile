FROM openjdk:8
EXPOSE 3005
ADD target/demo-0.0.1-SNAPSHOT.jar user-service.jar
ENTRYPOINT ["java","-jar","user-service.jar"]