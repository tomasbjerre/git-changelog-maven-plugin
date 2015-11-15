# Git Changelog Maven Plugin [![Build Status](https://travis-ci.org/tomasbjerre/git-changelog-maven-plugin.svg?branch=master)](https://travis-ci.org/tomasbjerre/git-changelog-maven-plugin)

This is a Maven plugin for [Git Changelog](https://github.com/tomasbjerre/git-changelog-lib).


## Usage ##
Here is and example that will generate a CHANGELOG.md. There is also a running example [here](https://github.com/tomasbjerre/git-changelog-maven-plugin/tree/master/git-changelog-maven-plugin-example).

  <build>
    <plugins>
      <plugin>
        <groupId>se.bjurr.gitchangelog</groupId>
        <artifactId>git-changelog-maven-plugin</artifactId>
        <version>1.0-SNAPSHOT</version>
        <executions>
          <execution>
            <id>GenerateGitChangelog</id>
            <phase>generate-sources</phase>
            <goals>
              <goal>git-changelog</goal>
            </goals>
            <configuration>
              <toRef>refs/heads/master</toRef>
              <templateFile>changelog.mustache</templateFile>
              <settingsFile>changelog.json</settingsFile>
              <filePath>CHANGELOG.md</filePath>
            </configuration>
          </execution>
        </executions>
      </plugin>
    </plugins>
  </build>


This setup has a settings file, changelog.json, like this:

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
 
 "jiraIssuePattern": "\\b[a-zA-Z]([a-zA-Z]+)-([0-9]+)\\b",

 "githubIssuePattern": "#[0-9]*",
 
 "customIssues": [
  { "name": "Bugs", "pattern": "#bug" },
  { "name": "Features", "pattern": "#feature" }
 ]
}
```

It has a template file like this:

```
# Git Changelog changelog

Changelog of Git Changelog.

{{#tags}}
## {{name}}
 {{#issues}}
### {{name}}

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
