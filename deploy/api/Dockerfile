FROM openjdk:19-jdk-alpine3.16

# Install maven
RUN apk update
RUN apk add maven

WORKDIR /code

ADD ./tpfinal-jar-with-dependencies.jar tpfinal-jar-with-dependencies.jar
ENTRYPOINT ["java", "-jar", "tpfinal-jar-with-dependencies.jar"]
EXPOSE 9000