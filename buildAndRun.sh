#!/bin/sh
if [ $(docker ps -a -f name=my-web-app | grep -w my-web-app | wc -l) -eq 1 ]; then
  docker rm -f my-web-app
fi
mvn clean package && docker build -t com.mycompany.app/my-web-app .
docker run -d -p 9080:9080 -p 9443:9443 --name my-web-app com.mycompany.app/my-web-app
