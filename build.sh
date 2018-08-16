#!/usr/bin/env bash

mvn clean install -DskipTests
docker build --build-arg JAR_FILE=target/qa-infra-search-api-0.0.1-SNAPSHOT.jar \
    --build-arg AZURE_CREDS_FILE_LOCATION=.azure.properties \
    --build-arg AZURE_RESOURCE_GROUP=QA-DIOR \
    -t dioracr.azurecr.io/qa-environment:dev .
