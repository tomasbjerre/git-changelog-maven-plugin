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
 @Parameter(property = "filePath", required = true)
 private String filePath;

 @Override
 public void execute() throws MojoExecutionException {
  try {
   GitChangelogApi builder;
   builder = gitChangelogApiBuilder() //
     .withSettings(new File(settingsFile).toURI().toURL()) //
     .withTemplatePath(templateFile) //
     .withToRef(toRef);

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

   builder //
     .toFile(filePath);

   getLog().info("Git Changelog written to " + filePath);
  } catch (MalformedURLException e) {
   getLog().error("GitChangelog", e);
  }
 }
}
