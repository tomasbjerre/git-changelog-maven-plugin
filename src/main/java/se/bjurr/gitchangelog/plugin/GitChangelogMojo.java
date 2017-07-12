package se.bjurr.gitchangelog.plugin;

import static com.google.common.base.Strings.isNullOrEmpty;
import static org.apache.maven.plugins.annotations.LifecyclePhase.PROCESS_SOURCES;
import static se.bjurr.gitchangelog.api.GitChangelogApi.gitChangelogApiBuilder;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import se.bjurr.gitchangelog.api.GitChangelogApi;

@Mojo(name = "git-changelog", defaultPhase = PROCESS_SOURCES)
public class GitChangelogMojo extends AbstractMojo {
  @Parameter(property = "toRef", required = false)
  private String toRef;

  @Parameter(property = "toCommit", required = false)
  private String toCommit;

  @Parameter(property = "fromRef", required = false)
  private String fromRef;

  @Parameter(property = "fromCommit", required = false)
  private String fromCommit;

  @Parameter(property = "settingsFile", required = false)
  private String settingsFile;

  @Parameter(property = "templateFile", required = false)
  private String templateFile;

  @Parameter(property = "templateContent", required = false)
  private String templateContent;

  @Parameter(property = "file", required = false)
  private File file;

  @Parameter(property = "mediaWikiUrl", required = false)
  private String mediaWikiUrl;

  @Parameter(property = "mediaWikiTitle", required = false)
  private String mediaWikiTitle;

  @Parameter(property = "mediaWikiUsername", required = false)
  private String mediaWikiUsername;

  @Parameter(property = "mediaWikiPassword", required = false)
  private String mediaWikiPassword;

  @Parameter(property = "readableTagName", required = false)
  private String readableTagName;

  @Parameter(property = "ignoreTagsIfNameMatches", required = false)
  private String ignoreTagsIfNameMatches;

  @Parameter(property = "dateFormat", required = false)
  private String dateFormat;

  @Parameter(property = "timeZone", required = false)
  private String timeZone;

  @Parameter(property = "removeIssueFromMessage", required = false)
  private boolean removeIssueFromMessage;

  @Parameter(property = "ignoreCommitsIfMessageMatches", required = false)
  private String ignoreCommitsIfMessageMatches;

  @Parameter(property = "ignoreCommitsOlderThan", required = false)
  private Date ignoreCommitsOlderThan;

  @Parameter(property = "untaggedName", required = false)
  private String untaggedName;

  @Parameter(property = "noIssueName", required = false)
  private String noIssueName;

  @Parameter(property = "gitHubApi", required = false)
  private String gitHubApi;

  @Parameter(property = "gitHubApiIssuePattern", required = false)
  private String gitHubApiIssuePattern;

  @Parameter(property = "gitHubToken", required = false)
  private String gitHubToken;

  @Parameter(property = "gitHubIssuePattern", required = false)
  private String gitHubIssuePattern;

  @Parameter(property = "gitLabServer", required = false)
  private String gitLabServer;

  @Parameter(property = "gitLabProjectName", required = false)
  private String gitLabProjectName;

  @Parameter(property = "gitLabToken", required = false)
  private String gitLabToken;

  @Parameter(property = "jiraIssuePattern", required = false)
  private String jiraIssuePattern;

  @Parameter(property = "jiraPassword", required = false)
  private String jiraPassword;

  @Parameter(property = "jiraServer", required = false)
  private String jiraServer;

  @Parameter(property = "jiraUsername", required = false)
  private String jiraUsername;

  @Parameter(property = "ignoreCommitsWithoutIssue", required = false)
  private boolean ignoreCommitsWithoutIssue;

  @Parameter(property = "customIssues", required = false)
  private List<CustomIssue> customIssues;

  @Override
  public void execute() throws MojoExecutionException {
    try {
      GitChangelogApi builder;
      builder = gitChangelogApiBuilder();
      if (isSupplied(settingsFile)) {
        builder.withSettings(new File(settingsFile).toURI().toURL());
      }

      if (isSupplied(toRef)) {
        builder.withToRef(toRef);
      }

      if (isSupplied(templateFile)) {
        builder.withTemplatePath(templateFile);
      }
      if (isSupplied(templateContent)) {
        builder.withTemplateContent(templateContent);
      }
      if (isSupplied(fromCommit)) {
        builder.withFromCommit(fromCommit);
      }
      if (isSupplied(fromRef)) {
        builder.withFromRef(fromRef);
      }
      if (isSupplied(toCommit)) {
        builder.withToCommit(toCommit);
      }

      if (isSupplied(ignoreTagsIfNameMatches)) {
        builder.withIgnoreTagsIfNameMatches(ignoreTagsIfNameMatches);
      }
      if (isSupplied(readableTagName)) {
        builder.withReadableTagName(readableTagName);
      }
      if (isSupplied(dateFormat)) {
        builder.withDateFormat(dateFormat);
      }
      if (isSupplied(timeZone)) {
        builder.withTimeZone(timeZone);
      }
      builder.withRemoveIssueFromMessageArgument(removeIssueFromMessage);
      if (isSupplied(ignoreCommitsIfMessageMatches)) {
        builder.withIgnoreCommitsWithMessage(ignoreCommitsIfMessageMatches);
      }
      if (ignoreCommitsOlderThan != null) {
        builder.withIgnoreCommitsOlderThan(ignoreCommitsOlderThan);
      }
      if (isSupplied(untaggedName)) {
        builder.withUntaggedName(untaggedName);
      }
      if (isSupplied(noIssueName)) {
        builder.withNoIssueName(noIssueName);
      }
      builder.withIgnoreCommitsWithoutIssue(ignoreCommitsWithoutIssue);
      for (CustomIssue customIssue : customIssues) {
        builder.withCustomIssue(
            customIssue.getName(),
            customIssue.getPattern(),
            customIssue.getLink(),
            customIssue.getTitle());
      }
      if (isSupplied(gitHubApi)) {
        builder.withGitHubApi(gitHubApi);
      }
      if (isSupplied(gitHubToken)) {
        builder.withGitHubToken(gitHubToken);
      }
      if (isSupplied(gitHubIssuePattern)) {
        builder.withGitHubIssuePattern(gitHubIssuePattern);
      }

      if (isSupplied(gitLabProjectName)) {
        builder.withGitLabProjectName(gitLabProjectName);
      }
      if (isSupplied(gitLabServer)) {
        builder.withGitLabServer(gitLabServer);
      }
      if (isSupplied(gitLabToken)) {
        builder.withGitLabToken(gitLabToken);
      }

      if (isSupplied(jiraUsername)) {
        builder.withJiraUsername(jiraUsername);
      }
      if (isSupplied(jiraPassword)) {
        builder.withJiraPassword(jiraPassword);
      }
      if (isSupplied(jiraIssuePattern)) {
        builder.withJiraIssuePattern(jiraIssuePattern);
      }
      if (isSupplied(jiraServer)) {
        builder.withJiraServer(jiraServer);
      }

      if (file != null) {
        builder.toFile(file);
        getLog().info("#");
        getLog().info("# Wrote: " + file);
        getLog().info("#");
      }

      if (isSupplied(mediaWikiUrl)) {
        builder //
            .toMediaWiki( //
            mediaWikiUsername, //
            mediaWikiPassword, //
            mediaWikiUrl, //
            mediaWikiTitle);
        getLog().info("#");
        getLog().info("# Created: " + mediaWikiUrl + "/index.php/" + mediaWikiTitle);
        getLog().info("#");
      }
    } catch (Exception e) {
      getLog().error("GitChangelog", e);
    }
  }

  private boolean isSupplied(String parameter) {
    return !isNullOrEmpty(parameter);
  }
}
