#!/bin/bash
mvn versions:update-properties
mvn clean install eclipse:eclipse || exit 1
cd git-changelog-maven-plugin-example
mvn versions:update-properties -DallowSnapshots=true
mvn clean generate-sources -e -DGITHUB_OAUTH2TOKEN=$GITHUB_OAUTH2TOKEN || exit 1
cp CHANGELOG.md ..
