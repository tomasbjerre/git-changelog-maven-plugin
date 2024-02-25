# Git Changelog Maven Plugin

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/se.bjurr.gitchangelog/git-changelog-maven-plugin/badge.svg)](https://maven-badges.herokuapp.com/maven-central/se.bjurr.gitchangelog/git-changelog-maven-plugin)

This is a Maven plugin for [Git Changelog Lib](https://github.com/tomasbjerre/git-changelog-lib).

## Usage

There is a running example [here](/git-changelog-maven-plugin-example). See also [bjurr-bom](https://github.com/tomasbjerre/bjurr-bom).

Have a look at the [pom.xml](/git-changelog-maven-plugin-example/pom.xml) where you will find some more examples.

Here is and example that will generate a CHANGELOG.md when running `mvn generate-resources`.

```xml
  <build>
    <plugins>
     <plugin>
      <groupId>se.bjurr.gitchangelog</groupId>
      <artifactId>git-changelog-maven-plugin</artifactId>
      <version>${changelog}</version>
      <dependencies>
       <!-- This dependency is only needed if you add your own javascript-helpers //-->
       <dependency>
        <groupId>org.openjdk.nashorn</groupId>
        <artifactId>nashorn-core</artifactId>
        <version>15.4</version>
       </dependency>
      </dependencies>
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
          Optional template here!
          Context documented here: https://github.com/tomasbjerre/git-changelog-lib
         ]]>
         </templateContent>
        </configuration>
       </execution>
      </executions>
     </plugin>
    </plugins>
  </build>
```

If you have a multimodule you may want to put `<inherited>false</inherited>` within the `<plugin>` tag to avoid it being applied to all child projects.

### Template - Simple

```hbs
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
```

### Template - Semantic versioning from conventional commits

If you are using [conventional commits](https://www.conventionalcommits.org/en/v1.0.0/):

```shell
<type>[optional scope]: <description>

[optional body]

[optional footer(s)]
```

A changelog can be rendered (using [Helpers](https://github.com/tomasbjerre/git-changelog-lib#Helpers)) like this:

```hbs
# Changelog

{{#tags}}
{{#ifReleaseTag .}}
## [{{name}}](https://gitservice/{{name}}) ({{tagDate .}})

  {{#ifContainsType commits type='feat'}}
### Features

    {{#commits}}
      {{#ifCommitType . type='feat'}}
 - {{#eachCommitScope .}} **{{.}}** {{/eachCommitScope}} {{{commitDescription .}}} ([{{hash}}](https://gitservice/commit/{{hashFull}}))
      {{/ifCommitType}}
    {{/commits}}
  {{/ifContainsType}}

  {{#ifContainsType commits type='fix'}}
### Bug Fixes

    {{#commits}}
      {{#ifCommitType . type='fix'}}
 - {{#eachCommitScope .}} **{{.}}** {{/eachCommitScope}} {{{commitDescription .}}} ([{{hash}}](https://gitservice/commit/{{hashFull}}))
      {{/ifCommitType}}
    {{/commits}}
  {{/ifContainsType}}

{{/ifReleaseTag}}
{{/tags}}
```

### Example - custom helpers

You can add your own helpers and use them in the template. There are also [built in Helpers](https://github.com/tomasbjerre/git-changelog-lib#Helpers).

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
          <javascriptHelper>
<![CDATA[
Handlebars.registerHelper('startsWith', function(messageTitle, options) {
  const s = options.hash['s']
  if (new RegExp('^' + s + '.*').test(messageTitle)) {
    return options.fn(this);
  } else {
    return options.inverse(this);
  }
});

Handlebars.registerHelper('firstLetters', function(from, options) {
  const num = parseInt(options.hash['number'])
  return from.substring(0,num)
});
]]>
          </javascriptHelper>
          <templateContent>
<![CDATA[
{{#commits}}
  {{#startsWith messageTitle s='Removing'}}
    Starts with Removing: "{{messageTitle}}"
    first 10 letters of hash is: {{firstLetters hash number='10'}}
  {{/startsWith}}
{{/commits}}
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

### Configuration

Have a look at the [pom.xml](/git-changelog-maven-plugin-example/pom.xml) where you will find some more examples.

#### Update version based on conventional commits

The version in `pom.xml` can be automatically updated based on [conventional commits](https://www.conventionalcommits.org/en/v1.0.0/).

From command line:

```sh
mvn se.bjurr.gitchangelog:git-changelog-maven-plugin:VERSION_HERE:semantic-version
```

You can combine it with maven release plugin like this:

```sh
mvn \
  se.bjurr.gitchangelog:git-changelog-maven-plugin:VERSION_HERE:semantic-version \
  release:prepare release:perform
```

Or in `pom.xml`:

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
         <goal>semantic-version</goal>
        </goals>
        <configuration>
          <!-- Suffix version with -SNAPSHOT //-->
          <updatePomWithNextSemanticVersionSuffixSnapshot>false</updatePomWithNextSemanticVersionSuffixSnapshot>
          <updatePomWithCurrentSemanticVersionSuffixSnapshotIfNotTagged>true</updatePomWithCurrentSemanticVersionSuffixSnapshotIfNotTagged>

          <!-- Regexp patterns used to identify next version can optionally be adjusted //-->
          <semanticMajorVersionPattern>^[Bb]reaking</semanticMajorVersionPattern>
          <semanticMinorVersionPattern>[Ff]eature</semanticMinorVersionPattern>
          <semanticPatchVersionPattern>[Ff]ix</semanticPatchVersionPattern>
        </configuration>
       </execution>
      </executions>
     </plugin>
    </plugins>
  </build>
```
