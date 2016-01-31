#!/bin/bash
mvn clean install eclipse:eclipse || exit 1
cd git-changelog-maven-plugin-example
mvn generate-sources
cp CHANGELOG.md ..
