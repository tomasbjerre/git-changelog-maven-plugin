#!/bin/bash
mvn versions:update-properties
mvn release:prepare release:perform -B -DperformRelease=true || exit 1
./build.sh
git commit -a --amend --no-edit
git push -f
