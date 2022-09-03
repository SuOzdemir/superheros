FROM adoptopenjdk/openjdk11:alpine-jre
LABEL maintainer="DataGuard Superhero Code Task App"
VOLUME /tmp
ARG JAR_FILE=target/*.jar
ADD ${JAR_FILE} /superhero.jar
WORKDIR /opt/superhero
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/superhero.jar"]
