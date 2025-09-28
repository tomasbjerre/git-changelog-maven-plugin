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

  @Parameter(property = "skip", required = false)
  private Boolean skip;

  @Parameter(
      property = "updatePomWithCurrentSemanticVersionSuffixSnapshot",
      required = false,
      defaultValue = "false")
  private boolean updatePomWithCurrentSemanticVersionSuffixSnapshot;

  @Parameter(
      property = "updatePomWithCurrentSemanticVersionSuffixSnapshotIfNotTagged",
      required = false,
      defaultValue = "true")
  private boolean updatePomWithCurrentSemanticVersionSuffixSnapshotIfNotTagged;

  @Parameter(property = "semanticMajorVersionPattern", required = false)
  private String semanticMajorVersionPattern;

  @Parameter(property = "semanticMinorVersionPattern", required = false)
  private String semanticMinorVersionPattern;

  @Parameter(property = "semanticPatchVersionPattern", required = false)
  private String semanticPatchVersionPattern;

  @Parameter(property = "ignoreTagsIfNameMatches", required = false)
  private String ignoreTagsIfNameMatches;

  @Override
  public void execute() throws MojoExecutionException {
    if (this.skip != null && this.skip) {
      this.getLog().info("Skipping semantic version");
      return;
    }

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
      if (this.isSupplied(this.ignoreTagsIfNameMatches)) {
        gitChangelogApiBuilder.withIgnoreTagsIfNameMatches(this.ignoreTagsIfNameMatches);
      }
      final SemanticVersion nextSemanticVersion =
          gitChangelogApiBuilder.getCurrentSemanticVersion();
      final boolean notTagged = nextSemanticVersion.findTag().isEmpty();
      final boolean suffixWithSnapshot =
          this.updatePomWithCurrentSemanticVersionSuffixSnapshot
              || this.updatePomWithCurrentSemanticVersionSuffixSnapshotIfNotTagged && notTagged;
      final String nextVersion =
          suffixWithSnapshot
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
