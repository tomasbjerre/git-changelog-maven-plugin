# Git Changelog Maven Plugin [![Build Status](https://travis-ci.org/tomasbjerre/git-changelog-maven-plugin.svg?branch=master)](https://travis-ci.org/tomasbjerre/git-changelog-maven-plugin) [![Maven Central](https://maven-badges.herokuapp.com/maven-central/se.bjurr.gitchangelog/git-changelog-maven-plugin/badge.svg)](https://maven-badges.herokuapp.com/maven-central/se.bjurr.gitchangelog/git-changelog-maven-plugin)

This is a Maven plugin for [Git Changelog Lib](https://github.com/tomasbjerre/git-changelog-lib).

## Usage ##
There is a running example [here](https://github.com/tomasbjerre/git-changelog-maven-plugin/tree/master/git-changelog-maven-plugin-example).

Have a look at the [pom.xml](https://github.com/tomasbjerre/git-changelog-maven-plugin/blob/master/git-changelog-maven-plugin-example/pom.xml) where you will find some more examples.

Here is and example that will generate a CHANGELOG.md. 

```
  <build>
    <plugins>
      <plugin>
        <groupId>se.bjurr.gitchangelog</groupId>
        <artifactId>git-changelog-maven-plugin</artifactId>
        <version>1.37</version>
        <executions>
          <execution>
            <id>GenerateGitChangelog</id>
            <phase>generate-sources</phase>
            <goals>
              <goal>git-changelog</goal>
            </goals>
            <configuration>
              <!-- A file on filesystem //-->
              <filePath>CHANGELOG.md</filePath>

              <!-- Or post to MediaWiki //-->
              <mediaWikiUsername>tomas</mediaWikiUsername>
              <mediaWikiPassword>tomaskod</mediaWikiPassword>
              <mediaWikiUrl>http://localhost/mediawiki</mediaWikiUrl>
              <mediaWikiTitle>Tomas Title</mediaWikiTitle>
            </configuration>
          </execution>
        </executions>
      </plugin>
    </plugins>
  </build>
```

To generate changelog, just run:
```
mvn generate-sources
```

More documentation can be found in the [Git Changelog Lib](https://github.com/tomasbjerre/git-changelog-lib).

## Developer instructions

To make a release, first run:
```
mvn release:prepare -DperformRelease=true
mvn release:perform
```
Then release the artifact from [staging](https://oss.sonatype.org/#stagingRepositories). More information [here](http://central.sonatype.org/pages/releasing-the-deployment.html).
