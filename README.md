# Node application
 
- Create a Dockerfile to build a Docker image of the node application.
- Create a Docker Compose file to create 3 containers for node app, mongodb and mongo express.
- Create a Jenkins pipeline to automate building image, pushing the image to `ECR` and deploying to `EC2`.

## 1. Create a Dockerfile

- Create a file with name `Dockerfile` in your root folder.

- Add following commands to your `Dockerfile`:

```Dockerfile
FROM node:18-alpine

ENV MONGO_DB_USERNAME=admin \
    MONGO_DB_PWD=password

RUN mkdir -p /home/app

COPY . /home/app

WORKDIR /home/app

RUN npm install

CMD ["node", "server.js"]
```

## 2. Create a docker-compose.yaml file

- Create a file with name `docker-compose.yaml` in your root folder.

- Add following configuration to your `docker-compose.yaml`:

```yaml
version: '3'
services:
  my-app:
    image: myapp:latest
    container_name: my-app
    ports:
     - 3000:3000
  mongodb:
    image: mongo
    container_name: mongodb
    ports:
     - 27017:27017
    environment:
     - MONGO_INITDB_ROOT_USERNAME=admin
     - MONGO_INITDB_ROOT_PASSWORD=password
    volumes:
     - mongo-data:/data/db
  mongo-express:
    image: mongo-express
    container_name: mongo-express
    restart: always
    ports:
     - 8080:8081
    environment:
     - ME_CONFIG_MONGODB_ADMINUSERNAME=admin
     - ME_CONFIG_MONGODB_ADMINPASSWORD=password
     - ME_CONFIG_MONGODB_SERVER=mongodb
    depends_on:
     - "mongodb"
volumes:
  mongo-data:
    driver: local
```
