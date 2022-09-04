FROM adoptopenjdk/openjdk14:jdk-14.0.2_12-alpine
LABEL MAINTAINER=" Sumeyra Ozdemir: esumeyra@gmail.com"
ARG JAR_FILE=target/*.jar
ADD ${JAR_FILE} superhero.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/superhero.jar"]
