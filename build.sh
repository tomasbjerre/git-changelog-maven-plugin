#!/bin/bash
./mvnw versions:update-properties \
 && ./mvnw install \
 && cd git-changelog-maven-plugin-example \
 && mvn versions:update-properties -DallowSnapshots=true \
 && mvn clean generate-sources -e -DGITHUB_OAUTH2TOKEN=$GITHUB_OAUTH2TOKEN
