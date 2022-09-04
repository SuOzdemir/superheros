FROM adoptopenjdk/openjdk14:jdk-14.0.2_12-alpine
LABEL MAINTAINER=" Sumeyra Ozdemir: esumeyra@gmail.com"
VOLUME /tmp
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} superhero.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/superhero.jar"]
