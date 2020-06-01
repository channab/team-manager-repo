# Team hierarchy manager

This is system that manage team members, hierarchies and user accesses. The application contains 3 projects. 

* team-manager-service
* api-gateway-service
* discovery-service


## Run the application

Run the projects in following order

* discovery-service
* api-gateway-service
* team-manager-service

Maven:

Go to root folder of each project in command line and issue this command

```
mvn spring-boot:run
```

Docker:

Build each project

```
docker build -t <project name>

docker run -p <host port>:<container port> <project name>
```

## To Do

The following have to added as improvements

* Add oauth2/jwt authentication and authorization
* Move configurations to cloud config server backed by GIT
* Enable https for api gateway server
* Adding support for non in memory database like MySQL
