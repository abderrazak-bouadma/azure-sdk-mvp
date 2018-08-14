FROM openjdk:8-jdk-alpine

LABEL maintainer=" bouadma.abderrazak@gmail.com"

ARG JAR_FILE
ARG AZURE_CREDS_FILE_LOCATION
ARG AZURE_RESOURCE_GROUP

VOLUME /tmp

COPY ${JAR_FILE} app.jar
COPY ${AZURE_CREDS_FILE_LOCATION} .azure.properties

ENV AZURE_AUTH_LOCATION=.azure.properties
ENV AZURE_RESOURCE_GROUP=${AZURE_RESOURCE_GROUP}

EXPOSE 8080

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
