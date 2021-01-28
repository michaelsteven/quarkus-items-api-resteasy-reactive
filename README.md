# quarkus-items-api-resteasy-reactive project

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Purpose

The purpose of the quarkus-items-api-resteasy-reactive project is to provide an archetype for how to make a quarkus CRUD application that mimics the springboot-items-api project that is in an adjacent repository.  This version uses the resteasy-reactive libraries.

## Limitations / Problems

- Need to fix getting the last identifier on the save
- Need to implement the pageable interface to be consistant with the other examples
- Uses the SQL Client without using the hibernate ORM
- localization currently only working with javax validation messages, localization of other strings, currency, and times need work
- issue with openapi in showing the pageable as a sample needs to be fixed
- need to find out how to properly use filters for logging since AOP for cross-cutting concerns doesn't seem to work.
- can't use junit5 nested classes - https://github.com/quarkusio/quarkus/issues/4393
- need to add unit/functional tests

## Advantages
- written as a reactive using the newly released resteasy reactive libraries
- successfully compiles as a native binary; uses @RegisterForReflection for DTOs and reflection-config.json entries for third party model objects
- uses javax validation and successfully throws constraint violation exceptions when ran as a native executable
- all the size and memory advantages outlined at quarkus.io

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```

## Packaging and running the application

The application can be packaged using:
```shell script
./mvnw package
```
It produces the `quarkus-items-api-resteasy-reactive-1.0.0-SNAPSHOT-runner.jar` file in the `/target` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/lib` directory.

If you want to build an _über-jar_, execute the following command:
```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The application is now runnable using `java -jar target/quarkus-items-api-resteasy-reactive-1.0.0-SNAPSHOT-runner.jar`.

## Creating a native executable

You can create a native executable using: 
```shell script
./mvnw package -Pnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 
```shell script
./mvnw package -Pnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/quarkus-items-api-resteasy-reactive-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/maven-tooling.html.

# RESTEasy JAX-RS

<p>A Hello World RESTEasy resource</p>

Guide: https://quarkus.io/guides/rest-json

# Swagger-UI

https://localhost:8080/swagger-ui/
