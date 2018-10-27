#!/bin/bash
mvn versions:update-properties
mvn release:prepare release:perform -B || exit 1
./build.sh
git commit -a --amend --no-edit
git push -f
