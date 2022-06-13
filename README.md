# simple-interest - aplazotest
This repository contains an microservice application developed using Java 11, Spring Boot, JUnit 5, Docker, and H2 database.
## Getting started
To get this application running, first execute the `build.sh` script, which will build a Docker image named `aplazotest/springboot` in the host machine.  This script was based on the instructions to create a Docker image using SpringBoot in this link: https://spring.io/guides/gs/spring-boot-docker/.

> **Note:** The `docker` command is executed with `sudo` in this script and you should change that depending on the docker installation in your environment.

Next, run the application in a container using the command:
```sh
sudo docker run -p 8081:8080 aplazotest/springboot
```
> **Note:** If you want the `build.sh` script to build and run the project, uncomment the last line in the file.

The application should start and be available under http://localhost:8081.

## Endpoints
This application provides the following endpoints:

- [/](http://localhost:8081): Returns the string `simple-interest`, which is the name of the application.
- [/calculate-simple-interest-payments](http://localhost:8081/calculate-simple-interest-payments): This is the the main endpoint of the application. It performs the operation requested in the task: calculating, storing and returning the payments for a simple interest request.
- [/h2-console](http://localhost:8081/h2-console): This endpoint enables you to manage the application H2 database. It is enabled by default, but this endpoint should be available only as a development tool. To disable this endpoint set `spring.h2.console.settings.web-allow-others=false` in the `application.properties` file. Other properties for the database are also defined in this file.