package se.bjurr.gitchangelog.plugin;

import static org.apache.maven.plugins.annotations.LifecyclePhase.PROCESS_SOURCES;
import static se.bjurr.gitchangelog.api.GitChangelogApi.gitChangelogApiBuilder;

import java.io.File;
import org.apache.maven.model.Model;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Component;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
import se.bjurr.gitchangelog.api.GitChangelogApi;
import se.bjurr.gitchangelog.internal.semantic.SemanticVersion;

@Mojo(name = "semantic-version", defaultPhase = PROCESS_SOURCES, threadSafe = true)
public class SemanticVersionMojo extends AbstractMojo {
  @Component private MavenProject project;

  @Parameter(
      property = "updatePomWithNextSemanticVersionSuffixSnapshot",
      required = false,
      defaultValue = "true")
  private boolean updatePomWithNextSemanticVersionSuffixSnapshot;

  @Parameter(property = "semanticMajorVersionPattern", required = false)
  private String semanticMajorVersionPattern;

  @Parameter(property = "semanticMinorVersionPattern", required = false)
  private String semanticMinorVersionPattern;

  @Parameter(property = "semanticPatchVersionPattern", required = false)
  private String semanticPatchVersionPattern;

  @Override
  public void execute() throws MojoExecutionException {
    try {
      final GitChangelogApi gitChangelogApiBuilder = gitChangelogApiBuilder();
      if (this.isSupplied(this.semanticMajorVersionPattern)) {
        gitChangelogApiBuilder.withSemanticMajorVersionPattern(this.semanticMajorVersionPattern);
      }
      if (this.isSupplied(this.semanticMinorVersionPattern)) {
        gitChangelogApiBuilder.withSemanticMinorVersionPattern(this.semanticMinorVersionPattern);
      }
      if (this.isSupplied(this.semanticPatchVersionPattern)) {
        gitChangelogApiBuilder.withSemanticPatchVersionPattern(this.semanticPatchVersionPattern);
      }
      final SemanticVersion nextSemanticVersion = gitChangelogApiBuilder.getNextSemanticVersion();
      final String nextVersion =
          this.updatePomWithNextSemanticVersionSuffixSnapshot
              ? nextSemanticVersion.getVersion() + "-SNAPSHOT"
              : nextSemanticVersion.getVersion();

      final Model model = this.project.getModel();
      final String versionOrig = model.getVersion();
      final File pomFile = this.project.getFile();
      this.getLog()
          .info("Setting version to " + nextVersion + " was (" + versionOrig + ") in " + pomFile);
      // Change version during build
      model.setVersion(nextVersion);

      // Change version in file
      new XmlModifier(pomFile).setVersion(nextVersion);
    } catch (final Exception e) {
      throw new MojoExecutionException(e.getMessage(), e);
    }
  }

  private boolean isSupplied(final String param) {
    return param != null && !param.isEmpty();
  }
}
