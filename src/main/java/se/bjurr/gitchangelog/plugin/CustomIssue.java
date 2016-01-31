package se.bjurr.gitchangelog.plugin;

import org.apache.maven.plugins.annotations.Parameter;

public class CustomIssue {
 @Parameter(property = "name", required = true)
 private String name;
 @Parameter(property = "pattern", required = true)
 private String pattern;
 @Parameter(property = "link", required = false)
 private String link;

 public void setLink(String link) {
  this.link = link;
 }

 public void setName(String name) {
  this.name = name;
 }

 public void setPattern(String pattern) {
  this.pattern = pattern;
 }

 public String getLink() {
  return link;
 }

 public String getName() {
  return name;
 }

 public String getPattern() {
  return pattern;
 }
}
