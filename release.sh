#!/bin/bash

gpg -o /tmp/dummy --sign .gitignore

./mvnw \
  se.bjurr.gitchangelog:git-changelog-maven-plugin:1.95.0:semantic-version \
  release:prepare \
  release:perform \
  -B \
  -DperformRelease=true \
 && npx git-changelog-command-line -of CHANGELOG.md -ip ".*maven-release-plugin.*" \
 && git commit -a -m "chore: updating changelog" \
 && git push \
 || git clean -f && git checkout pom.xml
