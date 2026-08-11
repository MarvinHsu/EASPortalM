# EASPortalM
Enterprise Application Stack Portal Management (EASPortalM) is a Java 21 web application built with Spring Boot 4.1.0, JoinFaces 6.1.0 / PrimeFaces, Spring Security, Spring Data JPA, Quartz, and CAS-based authentication. The project is packaged as a JAR and built with Maven. The product introduction link is [https://www.hsuforum.com/default.jsf?tabParam=easPortalTab](https://www.hsuforum.com/default.jsf?tabParam=easPortalTab "Go to The product introduction").

## Current project facts

- Java 21 and Spring Boot 4.1.0
- JoinFaces 6.1.0 with PrimeFaces integration
- HSUCommon 5.1.0 as an internal dependency
- Maven profiles: `tomcat-db2`, `tomcat-mysql`, `tomcat-oracle`, `tomcat-postgresql`, and `tomcat-sqlserver`
- The default active profile is `tomcat-mysql` in `pom.xml`
- The build includes native2ascii resource conversion and AspectJ weaving
- Runtime configuration is profile-driven and uses SSL on port `7443` with CAS SSO values from the active profile

## Repository structure

- `pom.xml` — Maven build, dependency versions, profiles, and plugin configuration
- `src/main/java` — application source code
- `src/main/resources` — runtime configuration, security policies, and web resources
- `src/main/native2ascii` — localization source files for native2ascii processing
- `src/main/resources/META-INF/resources` — JSF/PrimeFaces web assets
- `src/test/java` — tests and test support code
- `DB Init Data SQL/` — database seed scripts for the portal data model

## Build and run

### Typical commands

```bash
mvn clean package
mvn -DskipTests package
mvn test
```

### Notes

- The current `pom.xml` configures the Surefire plugin with `skip=true`, so a plain `mvn test` run may not execute tests unless that configuration is changed.
- Full builds run native2ascii conversion and AspectJ weaving, so they can take longer than a basic Java build.
- Main runtime settings come from `src/main/resources/application.properties` and the profile-specific properties files under `src/main/resources`.

## Authentication and seed data

- The seed scripts do not define a hard-coded login/password pair; initial credentials depend on the deployment environment and initialization steps.
- The default configuration is for CAS-based authentication.

## Important notes

- Edit localization sources under `src/main/native2ascii`; do not edit generated resources under `src/main/resources`.
- Avoid editing generated files under `target/`.
- Preserve the existing Spring Boot / JSF / PrimeFaces patterns unless a change clearly requires otherwise.