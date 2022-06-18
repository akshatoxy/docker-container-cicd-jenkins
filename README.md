# Node application
 
- Create a Dockerfile to build a Docker image of the node application.
- Create a Docker Compose file to create 3 containers for node app, mongodb and mongo express.
- Create a Jenkins pipeline to automate building image, pushing the image to `ECR` and deploying to `EC2`.

## 1. Create a Dockerfile

- Create a file with name Dockerfile in your root folder.

- Add following commands in your Dockerfile

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