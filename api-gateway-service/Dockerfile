FROM java:8-jdk-alpine
COPY target/team-manager-service-1.0.0.jar /usr/app/
WORKDIR /usr/app
EXPOSE 9000
ENTRYPOINT ["java", "-jar", "team-manager-service-1.0.0.jar"]