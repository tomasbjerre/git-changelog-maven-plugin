# Git Changelog Maven Plugin [![Build Status](https://travis-ci.org/tomasbjerre/git-changelog-maven-plugin.svg?branch=master)](https://travis-ci.org/tomasbjerre/git-changelog-maven-plugin)

This is a Maven plugin for [Git Changelog](https://github.com/tomasbjerre/git-changelog-lib).


## Usage ##
Here is and example that will generate a CHANGELOG.md. There is also a running example [here](https://github.com/tomasbjerre/git-changelog-maven-plugin/tree/master/git-changelog-maven-plugin-example) in the [pom.xml](https://github.com/tomasbjerre/git-changelog-maven-plugin/blob/master/git-changelog-maven-plugin-example/pom.xml) you will find some examples of how it can be configured.

```
  <build>
    <plugins>
      <plugin>
        <groupId>se.bjurr.gitchangelog</groupId>
        <artifactId>git-changelog-maven-plugin</artifactId>
        <version>1.13</version>
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


A settings file may be used, [documented here](https://github.com/tomasbjerre/git-changelog/blob/master/src/main/java/se/bjurr/gitchangelog/internal/settings/Settings.java). It may look something like this:

```
{
 "fromRepo": ".",
 "fromCommit": "0000000000000000000000000000000000000000",
 "toRef": "refs/heads/master",
 
 "ignoreCommitsIfMessageMatches": "^\\[maven-release-plugin\\].*|^\\[Gradle Release Plugin\\].*|^Merge.*",
 "readableTagName": "/([^/]+?)$",
 "dateFormat": "YYYY-MM-dd HH:mm:ss",
 "untaggedName": "Next release",
 "noIssueName": "Other changes",
 "timeZone": "UTC",
 "removeIssueFromMessage": "true",

 "jiraServer": "https://jiraserver/jira",
 "jiraIssuePattern": "\\b[a-zA-Z]([a-zA-Z]+)-([0-9]+)\\b",

 "gitHubApi": "https://api.github.com/repos/tomasbjerre/git-changelog-maven-plugin",
 "gitHubIssuePattern": "#([0-9]+)",

 "customIssues": [
  { "name": "Bugs", "pattern": "#bug" },
  { "name": "Features", "pattern": "#feature" }
 ]
}
```

A custom template file may be used and can look like this:

```
# Git Changelog changelog

Changelog of Git Changelog.

{{#tags}}
## {{name}}
 {{#issues}}
  {{#hasLink}}
### {{name}} [{{issue}}]({{link}}) {{title}}
  {{/hasLink}}
  {{^hasLink}}
### {{name}}
  {{/hasLink}}

   {{#commits}}
{{{message}}}

   {{/commits}}
 {{/issues}}
{{/tags}}
```

And then to generate changelog, just run:
```
mvn generate-sources
```

## Developer instructions

To make a release, first run:
```
mvn release:prepare -DperformRelease=true
mvn release:perform
```
Then release the artifact from [staging](https://oss.sonatype.org/#stagingRepositories). More information [here](http://central.sonatype.org/pages/releasing-the-deployment.html).
