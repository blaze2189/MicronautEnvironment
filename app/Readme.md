## Running with `docker`

**Docker** is a platform as a service, which offer the possibility to create containers where we can deploy services 
like databases, queues, etc.

#### Basic commands

+ `docker build` build a Docker image from a `Docker` file
+ `docker run ${image_name}` creates a container from a specified image and start it. Some options can be added to
this command like `--detached, -d` to run in background, `--name` which assign a name to the container, `--rm` 
that removes the container when it exits (for further information review docker documentation, link at the end).
+ `docker exec`	run a command in a running container
+ `docker images` list images
+ `docker ps` list containers

### PostgresDB

First we need to build the container from `Docker` file inside `./docker/postgres` folder

```
docker build -t ${container_name} .
```
then we can run an image using the next command
```
docker run --rm -p ${host_port}:5432/tcp --name ${container_name} ${image_name}
```

to test the connection use next data

```
host: localhost
port: ${host_port}
user: docker
password: docker
```

### Rest-api

To deploy it's needed to have `Postgres db` up.

+ Apply `mvn clean verify` command
+ Inside `./docker/java` execute the jar with `java -jar app-`


## Running with `docker-compose`

 **Docker Compose** is a tool to define and run multiple containers by using one `YAML` file configuration.
 Inside the `YAML` file we define/configure the services we will use. 
 
 Docker Compose will preserve data when a container is created. This process ensures previous data isn't lost.
 `docker-compose up` command will search for any container previously created and then copies the old volume container
  into the new one. Although you can remove the volume by adding `--volumes ` or `-v` to `docker-compose down`.

#### Basic commands

- `docker-compose build` to create the images needed.
- `docker-compose up` start up your containers. Use `docker-compose up -d` to run detached mode.
- `docker-compose down` down your images.
- `docker-compose rm` to remove your containers.

you can up and build your images by using just one command `docker-compose up --build `

### Deploying

 Inside `./docker` run command `docker-compose up --build`,  by doing that you'll be able to make request to
 [http://localhost:8080/](http://localhost:8080) and connect to the database with this properties
 
 
```
host: localhost
port: 32768
user: docker
password: docker
```

## References

- Review docker-compose documentation [here](https://docs.docker.com/compose/)
- Review docker command [here](https://docs.docker.com/engine/reference/commandline/docker/)