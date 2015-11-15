#!/bin/bash
mvn clean install eclipse:eclipse
cd git-changelog-maven-plugin-example
mvn generate-sources
cp CHANGELOG.md ..
