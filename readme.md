# Spring Non Blocking
(also called spring reactive)

# Bauen als native image mit GraalVM

mvn -Pnative package

./mvnw -Pnative native:compile -DskipTests  

## Docker + Postgresql
https://www.baeldung.com/spring-boot-postgresql-docker

images löschen die nicht mehr laufen:
- Get the container id using `docker ps`
- Stop it using `docker container stop <id>`
  https://stackoverflow.com/questions/73860082/how-to-stop-image-in-docker

## How to run
### working
in application yaml:
```yaml
spring.application.name=webflux-postgres
spring.r2dbc.url=r2dbc:postgresql://localhost:5432/bookdatabase
spring.r2dbc.username=postgres
spring.r2dbc.password=123
```

fill out {path_to_schema} with the correct path   
this one is the correct docker run command:
```
docker run --name postgrestestdb -e POSTGRES_PASSWORD=123 -e POSTGRES_DB=bookdatabase -v {path_to_schema}/schema.sql:/docker-entrypoint-initdb.d/schema.sql -d -p 5432:5432 postgres
```

in the end to ensure that everything docker is killed run this:

`docker ps -a` // shows all running containers  
`docker container stop <id>` // kill specific docker container   
`docker rm $(docker ps -aq)`  // kill ALL docker containers

### check if api is running

curl:  
curl -v http://localhost:8080/books

curl -v http://localhost:8080/init

## encountered Erros

org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'bookController': Unsatisfied dependency expressed through field 'bookService': Error creating bean with name 'bookService': Unsatisfied dependency expressed through field 'bookRepository': Error creating bean with name 'bookRepository' defined in wilfer.webfluxpostgres.BookRepository defined in @EnableR2dbcRepositories declared on WebfluxPostgresApplication: Cannot resolve reference to bean 'r2dbcEntityTemplate' while setting bean property 'entityOperations'
at org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor$AutowiredFieldElement.resolveFieldValue
