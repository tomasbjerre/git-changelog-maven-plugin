# Git Changelog Maven plugin changelog

Changelog of Git Changelog Maven plugin.

## These commits are not included in any tag
### These commits have not issue in their commit comment
   chore: updating changelog *05:47:45*

   chore: setting version in pom *05:46:34*

   chore(deps): dependency updates *05:46:19*

   chore(deps): dependencies *16:13:05*

   chore: updating changelog *10:02:54*

   chore: setting version in pom *10:01:46*

   chore: using parent pom *10:01:21*

   chore: updating changelog *09:58:38*



## 2.2.9
### These commits have not issue in their commit comment
   chore: setting version in pom *09:57:21*

   chore: using parent pom *09:57:11*

   chore: using parent pom *09:53:03*

   chore: updating changelog *08:49:37*



## 2.2.8
### These commits have not issue in their commit comment
   fix: set version in parent pom reference *08:48:39*

   chore: updating changelog *04:31:46*



## 2.2.7
### These commits have not issue in their commit comment
   fix: trying to use new Sonatype OSS API *04:30:35*

   chore: updating changelog *16:16:48*



## 2.2.6
### These commits have not issue in their commit comment
   chore: trying to use new Sonatype OSS API *16:12:01*

   chore: trying to use new Sonatype OSS API *16:10:44*

   chore: prop *16:04:36*

   chore(deps): Updated pom.xml by Safer #67 *16:03:45*

   chore: updating changelog *15:04:51*



## 2.2.5
### These commits have not issue in their commit comment
   chore(deps): git-changelog-lib *15:03:36*

   chore: updating changelog *10:20:01*



## 2.2.4
### These commits have not issue in their commit comment
   fix: reverse order in eachUrlPart *10:18:54*

   chore: updating changelog *07:15:45*



## 2.2.3
### These commits have not issue in their commit comment
   chore(deps): upgrading dependencies *07:14:32*

   chore: updating changelog *07:19:55*



## 2.2.2
### These commits have not issue in their commit comment
   chore: updating changelog *13:15:06*



## 2.2.1
### These commits have not issue in their commit comment
   chore(deps): git-changelog-lib 2.4.1 *13:13:44*

   chore: updating changelog *14:21:04*



## 2.2.0
### These commits have not issue in their commit comment
   feat: git-changelog-lib 2.3.2 and Java 17 *14:19:15*

   chore: documenting java compatibility (refs #52) *14:17:20*

   docs: readme *19:25:38*

   chore: building with java 21 *11:49:42*

   chore: updating changelog *07:05:56*



## 2.1.0
### These commits have not issue in their commit comment
   feat(conventional-commits): allow whitespace, colon and comma as separator in scope *07:04:59*

   chore: updating changelog *16:12:20*



## 2.0.1
### These commits have not issue in their commit comment
   fix: removing properties (refs #60) *16:07:12*

   chore: updating changelog *08:51:54*



## 2.0.0
### These commits have not issue in their commit comment
   breaking: JGit 6 and Java 11 *08:50:50*

   chore: updating changelog *07:28:20*



## 1.101.0
### These commits have not issue in their commit comment
   feat: skip parameter on semantic-version mojo (refs #53) *07:26:35*

   chore: updating changelog *17:42:22*



## 1.100.7
### These commits have not issue in their commit comment
   fix: mapping commits to lowest possible semantic tag *17:40:18*



## 1.100.6
### These commits have not issue in their commit comment
   fix: trying to set property to use with Maven Release Plugin *17:38:01*



## 1.100.5
### These commits have not issue in their commit comment
   fix: trying to set property to use with Maven Release Plugin *17:11:06*



## 1.100.4
### These commits have not issue in their commit comment
   fix: trying to set property to use with Maven Release Plugin *17:04:06*



## 1.100.3
### These commits have not issue in their commit comment
   fix: trim ignoreCommitsIfMessageMatches *17:59:40*



## 1.100.2
### These commits have not issue in their commit comment
   fix: current version *17:51:41*



## 1.100.1
### These commits have not issue in their commit comment
   fix: default CHANGELOG.md *11:36:52*



## 1.100.0
### These commits have not issue in their commit comment
   feat: set current version *11:02:10*



## 1.99.0
### These commits have not issue in their commit comment
   feat: add filtering for footer tokens *15:59:16*

   chore: updating changelog *18:38:38*



## 1.98.2
### These commits have not issue in their commit comment
   fix: correcting mapping of toRevision *18:37:24*

   chore: updating changelog *15:40:09*



## 1.98.1
### These commits have not issue in their commit comment
   chore: updating changelog *15:30:18*



## 1.98.0
### These commits have not issue in their commit comment
   feat: from/toRevision and prioritizing commits semantically when adding to tags *15:28:26*

   Merge pull request #51 from isaacsanders/patch-2

Update CHANGELOG.md *09:57:08*

   Update CHANGELOG.md *09:55:29*

   chore: updating changelog *14:15:20*



## 1.97.1
### These commits have not issue in their commit comment
   Merge pull request #50 from isaacsanders/patch-1

Update pom.xml *14:13:22*

   Update pom.xml

I was getting the following warnings:

```
[WARNING]
[WARNING] Plugin validation issues were detected in 4 plugin(s)
[WARNING]
[WARNING]  * se.bjurr.gitchangelog:git-changelog-maven-plugin:1.97.0
[WARNING]   Declared at location(s):
[WARNING]    * com.drw.risk:bloomberg_market_bridge:0.0.14-SNAPSHOT (pom.xml) @ line 296
[WARNING]   Used in module(s):
[WARNING]    * com.drw.risk:bloomberg_market_bridge:0.0.14-SNAPSHOT (pom.xml)
[WARNING]   Plugin issue(s):
[WARNING]    * Plugin should declare these Maven artifacts in `provided` scope: [org.apache.maven:maven-core:3.6.0, org.apache.maven:maven-model-builder:3.6.0, org.apache.maven:maven-model:3.6.0, org.apache.maven:maven-builder-support:3.6.0, org.apache.maven:maven-settings:3.6.0, org.apache.maven:maven-resolver-provider:3.6.0, org.apache.maven:maven-settings-builder:3.6.0, org.apache.maven:maven-repository-metadata:3.6.0, org.apache.maven:maven-artifact:3.6.0]
```

Hopefully this is an acceptable way to resolve them. *21:48:50*

   chore: updating changelog *11:54:41*



## 1.97.0
### These commits have not issue in their commit comment
   feat: stringHelpers *11:53:28*

   chore: updating changelog *07:00:31*



## 1.96.1
### These commits have not issue in their commit comment
   fix: avoiding stuck execution *06:59:31*

   chore: updating changelog *16:56:24*



## 1.96.0
### These commits have not issue in their commit comment
   feat: add ignoreTagsIfNameMatches to semantic-version (refs #49) *16:55:24*

   chore: updating changelog *20:23:23*



## 1.95.6
### These commits have not issue in their commit comment
   fix: parsing newest commits first *20:22:00*

   chore: updating changelog *16:08:30*



## 1.95.5
### These commits have not issue in their commit comment
   fix: allow space before : in commit message *16:07:23*

   chore: updating changelog *16:41:36*



## 1.95.4
### These commits have not issue in their commit comment
   fix: stepping git changelog lib *16:40:21*

   docs: updating readme *11:35:53*

   chore: updating changelog *09:05:14*



## 1.95.3
### These commits have not issue in their commit comment
   fix: let semantic tag have priority if several tags on same commit *09:04:13*

   docs: readme *06:24:17*

   chore: updating changelog *06:22:17*



## 1.95.2
### These commits have not issue in their commit comment
   fix: updating release script *06:21:30*

   chore: updating changelog *06:19:54*



## 1.95.1
### These commits have not issue in their commit comment
   fix: updating release script *06:19:01*

   chore: updating changelog *06:10:22*



## 1.95.0
### These commits have not issue in their commit comment
   chore: setting version 1.95.0-SNAPSHOT *06:09:34*

   feat: moving semantic versioning to its own mojo *06:09:18*

   chore: updating changelog *13:57:51*



## 1.94.0
### These commits have not issue in their commit comment
   chore: setting version 1.94.0-SNAPSHOT *13:57:13*

   feat: optional custom patterns for conventional commits *13:56:52*

   chore: updating changelog *13:26:11*



## 1.93.0
### These commits have not issue in their commit comment
   chore: setting version 1.93.0-SNAPSHOT *13:25:26*

   feat: update version based on conventional commits *13:24:58*

   chore: build script *09:55:05*

   chore: updating changelog *13:38:39*



## 1.92.2
### These commits have not issue in their commit comment
   chore: setting version 1.92.2-SNAPSHOT *13:37:50*

   fix: scope of maven-plugin-api *13:24:04*



## 1.92.1
### These commits have not issue in their commit comment
   chore: setting version 1.92.1-SNAPSHOT *13:08:37*

   chore: Maven Wrapper *13:01:40*

   fix: stepping library version *12:59:38*

   docs: mentioning inherited in README (refs #44) *12:39:21*

   fix: npe *10:26:18*

   feat: new variable urlParts *10:18:24*

   feat: prependToFile (refs #41) *15:46:01*

   fix: include first commit (refs #40) *05:52:13*

   fix: parsing scopes *15:21:32*

   fix: match first semantic combination *15:45:22*

   chore: using default changelog template *08:51:50*

   fix: disable integrations with a boolean useIntegrations *07:57:40*

   fix: only parse enabled issues *15:15:55*

   Merge pull request #37 from clockworkorange/master

Upgraded changelog-lib version to 1.163.2 *08:19:49*

   Upgraded changelog-lib version to 1.163.2 *08:41:11*

   feat: handlebarsHelperFile *15:42:59*

   Merge pull request #36 from rimuln/feature/13

feat Support Jira Bearer authorization *17:27:06*

   feat Support Jira Bearer authorization *23:49:06*

   Merge pull request #35 from chme/feat/partials

feat: Add support for including partials in mustache templates *20:01:23*

   feat: Add support for including partials in mustache templates *14:27:51*

   feat stepping lib *17:12:35*

   Merge pull request #33 from huygun/feature/redmine

feat: Redmine Support *17:11:19*

   feat: Redmine Support *12:20:16*

   fix: read template from file *16:37:46*

   fix: adding default changelog template jenkinsci/git-changelog-plugin#58 *18:02:26*

   fix: semantic version stepping *17:11:56*

   fix: NPE when given file has no parent *14:49:36*

   fix: when file was in a new folder, it created folder *14:33:39*

   feat: subString and ifMatches helpers *13:51:31*

   feat: regexp in ifCommitType and ifCommitScope *19:18:30*

   chore: stepping dependencies *16:09:07*

   chore: updating release-plugins *17:41:42*

   chore: release instructions *17:36:20*

   Maven central *17:27:33*

   feat: support conventional commits *17:22:23*

   Merge pull request #31 from edeso/simplePlainTextExample

Add unescaped simple plain text example *14:07:41*

   Add unescaped simple plain text example *13:57:25*

   Merge pull request #27 from drhip/feature/extended_headers

Support custom headers to JIRA to bypass 2 factor auth *16:28:30*

   Support custom headers to JIRA to bypass 2 factor auth *13:15:12*

   removing bintray link *11:01:17*

   pretty printing output JENKINS-65252 *15:45:20*

   Removing default ignore filter on message *17:11:18*

   Locking fmt version *06:50:16*

   More logging of extended variables from CLI #24 *06:36:49*

   Formatting code after merge #24 *06:21:39*

   Allow to pass extendedVariables using maven cli #24 *06:18:56*

   Mark as thread safe #21 *17:13:21*

   Adjustments after merge of PR #19 *17:18:11*

   Merge pull request #19 from gab1one/add-path-filter

Add support for pathfilter *17:17:05*

   Add support for pathfilter *15:41:30*

   openjdk8 *04:47:39*

   Merge pull request #18 from djn72/master

Add extendedVariables property *04:42:36*

   Add extendedVariables property *20:33:17*

   Adding skip option #12 *17:22:47*

   Create FUNDING.yml *07:06:15*

   Removing dependency on javax.xml *17:06:29*

   OpenJDK 11 compatible *16:38:05*

   Jira linked issues *18:10:02*

   Correcting GitLab integration after upgrade *17:52:22*

   GitLab API v4 *16:29:04*

   isJira, isGitHub... *06:09:27*

   Doc *15:37:37*

   Automatically stepping dependencies *18:56:12*

   using latest version in example *09:05:58*

   git-changelog-lib 1.82 *07:18:27*

   Merge pull request #10 from lennonjesus/patch-1

docs: Updates README.md to improve readability *14:34:08*

   docs: Updates README.md to improve readability *14:32:13*

   Closing RevWalk JENKINS-19994 *20:21:25*

   Doc *12:31:16*

   Description available for Jira issues *11:45:02*

   Doc *11:22:00*

   Description available for Jira issues *11:17:26*

   Adjusting to Bintray *11:17:26*

   Doc *12:25:21*

   Defaulting ignoreCommitsWithoutIssue #9 *12:49:36*

   Disabling integrations if not used *12:41:20*

   doc *18:30:57*

   Lib 1.71 *18:28:16*

   doc *20:03:46*

   Lib 1.70 *19:45:34*

   doc *18:54:04*

   Defaulting to CHANGELOG.md if no output given *18:49:28*

   Reading file parameter correctly *18:08:20*

   doc *19:51:59*

   ignoreCommitsOlderThan *06:45:33*

   doc *09:24:53*

   tag time added to tag model *09:21:27*

   doc *05:20:45*

   GitLab integration *05:17:22*

   doc *18:26:05*

   Fix Jira labels *18:21:07*

   doc *09:37:04*

   Git Changelog Lib 1.63 -> 1.64

 * Jira issueType and labels
 * GitHub labels *09:34:38*

   doc *18:38:13*

   GitHub timeout 10 seconds *18:33:46*

   Set theme jekyll-theme-slate *20:19:20*

   doc *21:16:51*

   doc *21:14:43*

   Merge pull request #7 from dunse/patch-1

Fix fromCommit and fromRef property typo *21:11:26*

   Fix fromCommit and fromRef property typo

The @Parameter annotation had the wrong property name which makes it impossible to provide parameter on command line. *10:51:15*

   doc *10:33:18*

   Adding annotation to context of tag *10:31:15*

   doc *19:31:25*

   Adding merge boolean to commits *18:43:43*

   doc *14:46:25*

   Lib 1.56 correcting link to Jira *14:41:25*

   doc *18:31:45*

   Adding {{hashFull}} variable *18:29:54*

   doc *00:04:33*

   Faster *00:02:44*

   doc *21:16:22*

   Fixing merge commits... again... *21:14:36*

   doc *09:31:53*

   Including commits from merges lib issue 49 *09:29:14*

   doc *19:07:44*

   Lib 1.49, finding first commit as parents from HEAD *18:34:16*

   doc *19:13:05*

   Ignoring trailing slash in JIRA URL *19:11:02*

   doc *18:44:20*

   Lib 1.45 *18:41:03*

   doc *16:55:58*

   JENKINS-34155 Support short SHA *16:49:07*

   doc *09:12:52*

   Lib 1.43, Parsing commits, oldest first *09:10:57*

   Lib 1.42, Parsing commits, oldest first *09:02:13*

   Updating doc *20:38:40*

   Lib 1.41, Commits added to correct tags *20:36:03*

   Updating example *19:17:24*

   Lib 1.40 can ignore tags by regexp *19:15:45*

   Updating example *09:48:23*

   Lib 1.39 *09:46:15*

   Updating example *08:46:55*

   Lib 1.38 *08:43:15*

   Updating README *16:12:21*

   Jira integration to get titles *16:09:27*

   Update example *21:16:30*

   Support GitHub OAuth2 tokens #6 *20:38:16*

   Adding example with github as custom issue *16:16:05*

   Updating CHANGELOG.md *08:52:37*

   Supplying commit in each issue mentioned in message *08:49:31*

   Updating CHANGELOG.md *18:23:16*

   Lib 1.30, supporting multiple tags on same commit *18:17:02*

   Updating CHANGELOG.md *18:00:16*

   Lib 1.29 *17:58:28*

   Lib 1.28, performance optimizations *16:57:24*

   Updating CHANGELOG.md *09:20:43*

   Significant performance improvements

 * Using lib 1.27. *09:05:15*

   Updating example *18:19:16*

   Using lib 1.25 and updating README.md *17:53:32*

   Updating docs after release *19:30:25*

   Added variables: messageTitle, messageBody, messageItems #5 *19:26:45*

   Adding example of custom issue without link

 * Like I123 *18:15:25*

   Maven Central version badge in README.md *21:14:03*

   Updating README.md after release *19:19:40*

   Implementing custom issues

 * Adding example with
  * QC matching QC1234
  * Incident matching INC1234 *17:03:30*

   Updagint README.md after release *11:14:02*

   Using lib 1.23 to sort commits by time, not formatted time #4 *11:10:56*

   Correcting example ignoring commits by comment *09:30:03*

   Updating README.md after release *07:36:20*

   Documentation and more configuration available in pom #3 *07:15:44*

   Using lib 1.22 *17:36:44*

   Documentation #3 *09:22:01*

   Using lib 1.19 *18:46:14*

   Template can be configured directly in the pom #2 *17:29:26*

   Using lib 1.18 *17:11:56*



## 1.9
### These commits have not issue in their commit comment
   Using lib 1.17 #1 *07:37:23*

   doc *22:58:13*



## 1.8
### These commits have not issue in their commit comment
   Using lib 1.15 #1 *22:18:12*



## 1.7
### These commits have not issue in their commit comment
   using lib 1.14 *21:41:20*

   doc *22:11:03*



## 1.6
### These commits have not issue in their commit comment
   using lib 1.13 *21:47:14*

   doc *18:10:41*



## 1.5
### These commits have not issue in their commit comment
   Using lib 1.12 #1 *18:02:24*



## 1.4
### These commits have not issue in their commit comment
   lib 1.10 *14:28:16*



## 1.3
### These commits have not issue in their commit comment
   lib 1.9 #feature *22:06:29*

   Setting exampe version to 1.2 #feature *19:52:35*



## 1.2
### These commits have not issue in their commit comment
   lib 1.3 #feature *19:48:16*

   doc *20:20:05*



## 1.1
### These commits have not issue in their commit comment
   MediaWiki support, using lib 1.2 *20:15:39*

   doc *14:44:03*



## 1.0
### These commits have not issue in their commit comment
   doc *14:36:57*

   Initial *14:30:51*

   Initial commit *13:18:29*



