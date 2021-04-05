# Git Changelog Maven Plugin
[![Build Status](https://travis-ci.org/tomasbjerre/git-changelog-maven-plugin.svg?branch=master)](https://travis-ci.org/tomasbjerre/git-changelog-maven-plugin)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/se.bjurr.gitchangelog/git-changelog-maven-plugin/badge.svg)](https://maven-badges.herokuapp.com/maven-central/se.bjurr.gitchangelog/git-changelog-maven-plugin)

This is a Maven plugin for [Git Changelog Lib](https://github.com/tomasbjerre/git-changelog-lib).

## Usage ##
There is a running example [here](https://github.com/tomasbjerre/git-changelog-maven-plugin/tree/master/git-changelog-maven-plugin-example).

Have a look at the [pom.xml](https://github.com/tomasbjerre/git-changelog-maven-plugin/blob/master/git-changelog-maven-plugin-example/pom.xml) where you will find some more examples.

Here is and example that will generate a CHANGELOG.md when running `mvn generate-resources`.

```xml
  <build>
    <plugins>
     <plugin>
      <groupId>se.bjurr.gitchangelog</groupId>
      <artifactId>git-changelog-maven-plugin</artifactId>
      <version>${changelog}</version>
      <executions>
       <execution>
        <id>GenerateGitChangelog</id>
        <phase>generate-sources</phase>
        <goals>
         <goal>git-changelog</goal>
        </goals>
        <configuration>
         <templateContent>
         <![CDATA[
# Changelog
Changelog of My Project.

{{#tags}}
## {{name}}
 {{#issues}}
  {{#hasIssue}}
   {{#hasLink}}
### {{name}} [{{issue}}]({{link}}) {{title}} {{#hasIssueType}} *{{issueType}}* {{/hasIssueType}} {{#hasLabels}} {{#labels}} *{{.}}* {{/labels}} {{/hasLabels}}
   {{/hasLink}}
   {{^hasLink}}
### {{name}} {{issue}} {{title}} {{#hasIssueType}} *{{issueType}}* {{/hasIssueType}} {{#hasLabels}} {{#labels}} *{{.}}* {{/labels}} {{/hasLabels}}
   {{/hasLink}}
  {{/hasIssue}}
  {{^hasIssue}}
### {{name}}
  {{/hasIssue}}

  {{#commits}}
**{{{messageTitle}}}**

{{#messageBodyItems}}
 * {{.}} 
{{/messageBodyItems}}

[{{hash}}](https://github.com/{{ownerName}}/{{repoName}}/commit/{{hash}}) {{authorName}} *{{commitTime}}*

  {{/commits}}

 {{/issues}}
{{/tags}}
         ]]>
         </templateContent>
        </configuration>
       </execution>
      </executions>
     </plugin>
    </plugins>
  </build>
```

More documentation can be found in the [Git Changelog Lib](https://github.com/tomasbjerre/git-changelog-lib).
