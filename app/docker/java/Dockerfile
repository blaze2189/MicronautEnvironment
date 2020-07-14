FROM ubuntu:18.04
RUN apt-get update && \
    apt-get install default-jdk -y
COPY ./micronaut-rest-api.jar /usr/app/
WORKDIR /usr/app
ENTRYPOINT ["java","-Dmicronaut.environments=docker", "-jar", "micronaut-rest-api.jar"]