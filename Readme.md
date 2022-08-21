#Account Service

##Introduction
#### Account Service provides following functionality 
1. Maintain Account balance 
2. Balance Transfer functionality

##Documentation
Swagger documentation for the Api's is available on the following link provided
 http://localhost:8082/swagger-ui/index.html

## How to run
1. App has docker compose and docker files
2. ```docker compose up -d ``` command can be used to run mysql and redis servers
3. App uses mysql as a database but for running test H2 database is integrated
4. Redis is being used for Distributed locking to make sure app can handle concurrent requests as well as App is scalable
5. After running mysql and redis container we need to run ```mvn clean install``` to build jar file
6. Lastly we can run the following command to build and run docker container
   1. ```docker image build -t accounts .```
   2. ```docker run -p 8080:8080 --network host accounts```

