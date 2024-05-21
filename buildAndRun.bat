@echo off
call mvn clean package
call docker build -t com.mycompany.app/my-web-app .
call docker rm -f my-web-app
call docker run -d -p 9080:9080 -p 9443:9443 --name my-web-app com.mycompany.app/my-web-app