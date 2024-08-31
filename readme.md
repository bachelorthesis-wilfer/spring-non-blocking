# Spring Non Blocking
(also called spring reactive)

## Setup Postgres Database for local development

I used docker to run postgres. Here's the setup:

fill out {path_to_schema} with the correct path to the schema.sql (located under src/main/resources)  
and then run this command:

```
docker run --name postgrestestdb -e POSTGRES_PASSWORD=123 -e POSTGRES_DB=bookdatabase -v {path_to_schema}/schema.sql:/docker-entrypoint-initdb.d/schema.sql -d -p 5432:5432 postgres
```

Afterwards you can start your application and everything should work. 

:warning: Remember to stop and remove the used container and volumes, as docker uses quite a lot of data.

show all running containers to get the id : `docker ps -a`  
kill/stop specific docker container: `docker container stop <id>`   
kill docker container: `docker rm <id>`

remove volumes
```
docker volume ls
docker volume prune
```

# Build and Run the application

I use IntelliJ to run the application locally. 

## Build a jar

The application can be packaged using:

```shell script
./mvnw package
```

## Build a native executable with GraalVM

You can create a native executable using:

```shell script
./mvnw -Pnative native:compile -DskipTests
```

theres now a runner file in your target folder from where you can execute it. 
