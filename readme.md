# Spring Non Blocking
(also called spring reactive)

## Run/ develop code locally

I used docker to run postgres. Here's the setup:

First setup the database:
```
docker run --name non-blocking_spring -e POSTGRES_PASSWORD=123 -d -p 5432:5432 postgres
```
then create the database:
```
docker exec -it non-blocking_spring psql -U postgres -c "CREATE DATABASE bookdatabase;"
```

depending on whether or not your code automatically creates a table you might need to go into the docker postgres and initialize the table like this:
```
CREATE TABLE IF NOT EXISTS book (id SERIAL PRIMARY KEY, title VARCHAR(255), author VARCHAR(255), price INTEGER, isbn INTEGER);
```

Afterwards you can start your application and everything should work. (I use IntelliJ to run it.)

:warning: Remember to stop and remove the used container and volumes, as docker uses quite a lot of data.

show all running containers to get the id : `docker ps -a`  
kill/stop specific docker container: `docker container stop <id>`   
kill docker container: `docker rm <id>`

remove volumes
```
docker volume ls
docker volume prune
```
