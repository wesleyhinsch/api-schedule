FROM adoptopenjdk/openjdk11:alpine
ADD target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
EXPOSE 8091