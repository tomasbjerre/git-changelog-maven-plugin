# Byggarmonster Maven Plugin [![Build Status](https://travis-ci.org/tomasbjerre/byggarmonster-maven-plugin.svg?branch=master)](https://travis-ci.org/tomasbjerre/byggarmonster-maven-plugin)

This is a Maven plugin for [Byggarmonster](https://github.com/tomasbjerre/byggarmonster-lib).

## Usage ##
Here is and example that will scan a given package and produce builders for the classes found in it. There is also a running example [here](https://github.com/tomasbjerre/byggarmonster-maven-plugin/tree/master/byggarmonster-maven-plugin-example).

    <plugins>
      <plugin>
        <groupId>se.bjurr.byggarmonster</groupId>
        <artifactId>byggarmonster-maven-plugin</artifactId>
        <version>1.1</version>
        <executions>
          <execution>
            <id>GenerateBuilders</id>
            <phase>generate-sources</phase>
            <goals>
              <goal>generate-builders</goal>
            </goals>
            <configuration>
              <fromPackage>se.bjurr.byggarmonster.model</fromPackage>
              <fromFolder>src/main/java</fromFolder>
              <toFolder>target/generated-sources</toFolder>
            </configuration>
          </execution>
        </executions>
      </plugin>
      
      <plugin>
        <groupId>org.codehaus.mojo</groupId>
        <artifactId>build-helper-maven-plugin</artifactId>
        <version>1.7</version>
        <executions>
          <execution>
            <id>add-source</id>
            <phase>generate-sources</phase>
            <goals>
              <goal>add-source</goal>
            </goals>
            <configuration>
              <sources>
                <source>src/main/java</source>
                <source>target/generated-sources</source>
              </sources>
            </configuration>
          </execution>
        </executions>
      </plugin>
    </plugins>

And then to generate builders, just run:
```
mvn generate-sources
```

## Developer instructions

To make a release, first run:
```
mvn release:prepare -DperformRelease=true
mvn release:perform
```
Then release the artifact from [staging](https://oss.sonatype.org/#stagingRepositories). More information [here](http://central.sonatype.org/pages/releasing-the-deployment.html).
