# HobbyMate
HobbyMate is a web application that lets you find projects to work on and also lets you advertise your projects to others.
Once you complete your project you can list your artefact in the shop.
The audience are hobbyist and anyone interested in collaborating with others on projects.

## Technologies
* Spring Boot
* Angular
* Netflix Eureka (Service Discovery)
* Netflix Ribbon (Clientside LoadBalancing) - Eureka runs at http://localhost:8761/

# Important NOTE
This will only work on a local machine as the client application (Angular) needs to have the ip address configured.
It has been configured to local host and compiled with it. If you run on the real cloud you'll need to modify the `hobbymate-webapp/src/frontend/src/app/enviroment`.
So I highly recommend you run this on your local docker environment.

## Database Issues!
cleans all the volumes for mysql
```bash
docker-compose rm -v -f
docker volume prune -f
```

# Where is the app?
After running with docker compose or deploying to swarm the app will be available at http://localhost:9797

# Building & Running
The jar files have been included so that they can be put into docker images.

## 1. Easy option
This option will use the jarfiles in the project to build the images.
## Run with compose
```bash
docker-compose up --build --force-recreate
```

## Deploy to swarm
```bash
docker-compose build  --force-rm
docker swarm init
docker stack deploy --compose-file=docker-compose-swarm.yml hobbymate
```

## 2. Recompiling Java and building images
This option requires that you have Java 17.0.4 installed to build the project.
**This is in case there's an issue running the jars I've provided**.

### Build
```bash
./mvnw clean package -DskipTests=true
```
### Then deploy to warm or;
Give the services sometime to start they will take a while as some will be attempting to connect to the DB while it's still being launched.
```bash
docker-compose build  --force-rm
docker swarm init
docker stack deploy --compose-file=docker-compose-swarm.yml hobbymate
```

### Run with docker-compose
Give the services sometime to start they will take a while as some will be attempting to connect to the DB while it's still being launched.
```bash
docker-compose up --build --force-recreate
```