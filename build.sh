#!/bin/bash
mvn clean install eclipse:eclipse -DGITHUB_OAUTH2TOKEN=$GITHUB_OAUTH2TOKEN || exit 1
cd git-changelog-maven-plugin-example
mvn clean generate-sources -e
cp CHANGELOG.md ..
