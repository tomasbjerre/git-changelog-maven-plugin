# Git Changelog Maven Plugin

[![Maven Central](https://img.shields.io/maven-central/v/se.bjurr.gitchangelog/git-changelog-maven-plugin.svg?label=Maven%20Central)](https://central.sonatype.com/artifact/se.bjurr.gitchangelog/git-changelog-maven-plugin)

This is a Maven plugin for [Git Changelog Lib](https://github.com/tomasbjerre/git-changelog-lib).

| Version                  | Java Version |
| ------------------------ | ------------ |
| version < 2.0.0          | 8            |
| 2.0.0 <= version < 2.2.0 | 11           |
| 2.2.0 <= version         | 17           |

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

#### `git-changelog` goal parameters

All of these go inside the `<configuration>` element of the `git-changelog` goal's execution.

**General**

| Parameter | Description |
| --- | --- |
| `skip` | Skip execution entirely. |
| `file` | Where to write the changelog, e.g. `CHANGELOG.md`. |
| `prependToFile` | Prepend to `file` instead of overwriting it. |
| `settingsFile` | Path to a JSON settings file, as an alternative to setting individual parameters here. See [example](/git-changelog-maven-plugin-example/changelog.json). |
| `fromRevision` / `fromRevisionStrategy` | Commit/tag/branch to start from, and whether it's `INCLUSIVE`, `EXCLUSIVE` or `DEFAULT`. Deprecated aliases: `fromRef`, `fromCommit`. |
| `toRevision` / `toRevisionStrategy` | Commit/tag/branch to end at, and its inclusiveness. Deprecated aliases: `toRef`, `toCommit`. |
| `pathFilter` | Only consider commits touching this path, analogous to `git log -- <path>`. Useful in monorepos. |

**Template**

| Parameter | Description |
| --- | --- |
| `templateFile` | Path to a Handlebars/Mustache template file. |
| `templateContent` | Template given inline instead of as a file. |
| `templateBaseDir` / `templateSuffix` | Base directory and file suffix for template [partials](#partials). |
| `extendedVariables` | Extra key/value pairs made available in the template as `custom.*`. |
| `extendedHeaders` | Extra key/value pairs, exposed the same way as `extendedVariables` but intended for header-only content. |
| `extendedVariablesCli` | Same as `extendedVariables`, but settable from the command line (Maven can't pass Map parameters via `-D`). |
| `javascriptHelper` | Inline JavaScript registering custom Handlebars helpers. Requires a JS engine like [Nashorn](https://central.sonatype.com/artifact/org.openjdk.nashorn/nashorn-core/overview) on the classpath. |
| `handlebarsHelperFile` | Same as `javascriptHelper`, but loaded from a file instead of inlined in the `pom.xml`. |
| `readableTagName` | Regular expression to shorten tag names shown in the changelog, e.g. extracting `1.6` out of `git-changelog-maven-plugin-1.6`. |
| `dateFormat` / `timeZone` | Formatting used for commit/tag dates in the template context. |

**Filtering**

| Parameter | Description |
| --- | --- |
| `ignoreTagsIfNameMatches` | Regular expression; matching tags are excluded from the changelog. |
| `ignoreCommitsIfMessageMatches` | Regular expression; matching commits are excluded. |
| `ignoreCommitsOlderThan` | Exclude commits older than this date. |
| `ignoreCommitsWithoutIssue` | Exclude commits whose message doesn't reference an issue. |
| `removeIssueFromMessage` | Strip the issue reference from the commit message text in the output. |
| `untaggedName` | Name of the virtual group for commits not yet included in any tag, e.g. "Next release". |
| `noIssueName` | Name of the virtual issue group for commits with no issue reference in their message. |

**Custom issue trackers**

| Parameter | Description |
| --- | --- |
| `customIssues` | Define your own issue pattern/link/title, for trackers not natively supported. See [example](/git-changelog-maven-plugin-example/pom.xml). |

**Integrations** (`useIntegrations` must be `true` for any of these to run)

| Parameter | Description |
| --- | --- |
| `useIntegrations` | Master switch enabling GitHub/GitLab/Jira/Redmine integrations below. |
| `gitHubEnabled`, `gitHubApi`, `gitHubApiIssuePattern`, `gitHubToken`, `gitHubIssuePattern` | GitHub issue title lookup. |
| `gitLabEnabled`, `gitLabServer`, `gitLabProjectName`, `gitLabToken` | GitLab issue title lookup. |
| `jiraEnabled`, `jiraServer`, `jiraUsername`, `jiraPassword`, `jiraBearer`, `jiraIssuePattern`, `jiraIssueAdditionalFields` | Jira issue title lookup. `jiraIssueAdditionalFields` fetches extra custom fields, available in the template's `issue.additionalFields`. |
| `redmineEnabled`, `redmineServer`, `redmineUsername`, `redminePassword`, `redmineToken`, `redmineIssuePattern` | Redmine issue title lookup. |

More documentation can be found in the [Git Changelog Lib](https://github.com/tomasbjerre/git-changelog-lib), which this plugin is a thin wrapper around.

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
