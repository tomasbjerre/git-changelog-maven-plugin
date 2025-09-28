package se.bjurr.gitchangelog.plugin.model;

public enum ExtensionType {
  MARKDOWN("md"),
  ASCIIDOC("adoc");

  private final String value;

  ExtensionType(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
