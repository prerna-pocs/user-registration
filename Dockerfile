FROM java:8-jdk-alpine
EXPOSE 8082
ADD /target/user-registration-0.0.1-SNAPSHOT.jar user-registration-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "user-registration-0.0.1-SNAPSHOT.jar"]