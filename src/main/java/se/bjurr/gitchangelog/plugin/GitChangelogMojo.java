package se.bjurr.gitchangelog.plugin;

import static com.google.common.base.Strings.isNullOrEmpty;
import static org.apache.maven.plugins.annotations.LifecyclePhase.PROCESS_SOURCES;
import static se.bjurr.gitchangelog.api.GitChangelogApi.gitChangelogApiBuilder;

import java.io.File;
import java.net.MalformedURLException;
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

 @Parameter(property = "toRef", required = false)
 private String fromRef;
 @Parameter(property = "toCommit", required = false)
 private String fromCommit;

 @Parameter(property = "settingsFile", required = false)
 private String settingsFile;
 @Parameter(property = "templateFile", required = false)
 private String templateFile;
 @Parameter(property = "templateContent", required = false)
 private String templateContent;
 @Parameter(property = "filePath", required = false)
 private String filePath;

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
 @Parameter(property = "dateFormat", required = false)
 private String dateFormat;
 @Parameter(property = "timeZone", required = false)
 private String timeZone;
 @Parameter(property = "removeIssueFromMessage", required = false)
 private boolean removeIssueFromMessage;
 @Parameter(property = "ignoreCommitsIfMessageMatches", required = false)
 private String ignoreCommitsIfMessageMatches;
 @Parameter(property = "untaggedName", required = false)
 private String untaggedName;
 @Parameter(property = "noIssueName", required = false)
 private String noIssueName;

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
    builder.withIgnoreCommitsWithMesssage(ignoreCommitsIfMessageMatches);
   }
   if (isSupplied(untaggedName)) {
    builder.withUntaggedName(untaggedName);
   }
   if (isSupplied(noIssueName)) {
    builder.withNoIssueName(noIssueName);
   }
   for (CustomIssue customIssue : customIssues) {
    builder.withCustomIssue(customIssue.getName(), customIssue.getPattern(), customIssue.getLink());
   }

   if (isSupplied(filePath)) {
    builder.toFile(filePath);
    getLog().info("#");
    getLog().info("# Wrote: " + filePath);
    getLog().info("#");
   }

   if (isSupplied(mediaWikiUrl)) {
    builder//
      .toMediaWiki(//
        mediaWikiUsername,//
        mediaWikiPassword, //
        mediaWikiUrl,//
        mediaWikiTitle);
    getLog().info("#");
    getLog().info("# Created: " + mediaWikiUrl + "/index.php/" + mediaWikiTitle);
    getLog().info("#");
   }
  } catch (MalformedURLException e) {
   getLog().error("GitChangelog", e);
  }
 }

 private boolean isSupplied(String parameter) {
  return !isNullOrEmpty(parameter);
 }
}
