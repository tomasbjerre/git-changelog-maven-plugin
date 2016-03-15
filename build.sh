#!/bin/bash
mvn clean install eclipse:eclipse || exit 1
cd git-changelog-maven-plugin-example
mvn clean generate-sources -e -DGITHUB_OAUTH2TOKEN=$GITHUB_OAUTH2TOKEN
cp CHANGELOG.md ..
