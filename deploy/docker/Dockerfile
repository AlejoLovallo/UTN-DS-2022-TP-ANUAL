FROM openjdk:19-jdk-alpine3.16

# Install maven
RUN apk update
RUN apk add maven

WORKDIR /code

# Prepare by downloading dependencies
ADD pom.xml /code/pom.xml
RUN ["mvn", "dependency:resolve"]
RUN ["mvn", "verify"]

# Adding source, compile and package into a fat jar
ADD src /code/src
RUN ["mvn", "-Dmaven.test.skip", "package"]

EXPOSE 4567
CMD ["/usr/lib/jvm/java-19-openjdk-amd64/bin/java", "-jar", "target/sparkexample-jar-with-dependencies.jar"]