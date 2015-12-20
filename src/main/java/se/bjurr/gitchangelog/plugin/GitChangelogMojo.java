package se.bjurr.gitchangelog.plugin;

import static com.google.common.base.Strings.isNullOrEmpty;
import static org.apache.maven.plugins.annotations.LifecyclePhase.PROCESS_SOURCES;
import static se.bjurr.gitchangelog.api.GitChangelogApi.gitChangelogApiBuilder;

import java.io.File;
import java.net.MalformedURLException;

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

 @Parameter(property = "settingsFile", required = true)
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

 @Override
 public void execute() throws MojoExecutionException {
  try {
   GitChangelogApi builder;
   builder = gitChangelogApiBuilder() //
     .withSettings(new File(settingsFile).toURI().toURL()) //
     .withToRef(toRef);

   if (!isNullOrEmpty(templateFile)) {
    builder //
      .withTemplatePath(templateFile);
   }
   if (!isNullOrEmpty(templateContent)) {
    builder //
      .withTemplateContent(templateContent);
   }
   if (!isNullOrEmpty(fromCommit)) {
    builder //
      .withFromCommit(fromCommit);
   }
   if (!isNullOrEmpty(fromRef)) {
    builder //
      .withFromRef(fromRef);
   }
   if (!isNullOrEmpty(toCommit)) {
    builder //
      .withToCommit(toCommit);
   }

   if (!isNullOrEmpty(filePath)) {
    builder //
      .toFile(filePath);
    getLog().info("Git Changelog written to " + filePath);
   }

   if (!isNullOrEmpty(mediaWikiUrl)) {
    builder//
      .toMediaWiki(//
        mediaWikiUsername,//
        mediaWikiPassword, //
        mediaWikiUrl,//
        mediaWikiTitle);
    getLog().info("Git Changelog written to " + mediaWikiUrl + "/index.php/" + mediaWikiTitle);
   }
  } catch (MalformedURLException e) {
   getLog().error("GitChangelog", e);
  }
 }
}
