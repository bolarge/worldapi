# WorldAPI 
Worldapi is a REST API that exposes functions for the organisation, management and usage of standardised information about
countries and cities of the world.

## Functional Requirement
1. GET the top <number_of_cities> cities in order of population (descending) of Italy, New Zealand
   and Ghana, where <number_of_cities> is a query parameter passed in the query. If there are not
   enough cities, just return the ones you can find;
2. An endpoint that takes a country as a parameter (e.g. Italy, Nigeria, …) and returns:
   ○ population
   ○ capital city
   ○ location
   ○ currency
   ○ ISO2&3
3. An endpoint that takes a country as a parameter (e.g. Italy, Nigeria, …) and returns the full list of
   all the states in the country and all the cities in each state.
4. An endpoint that takes a country as a parameter (e.g. Italy, Nigeria, …), a monetary amount and
   a target currency and provides:
   ○ the country currency

## System Requirements
* Java 17+
* Mysql 8
* Maven
* Springboot 3
* Spring Web MVC
* Spring Data JPA
* Docker
* Docker Compose

## Getting Started with Deployment
Application is a single maven project with no internal sub modules. It
inherits from springboot-parent P

### Database Setup
Hibernate ddl.auto flag is set to none, therefore, cards db database needs to be created. Run below code
from any mysql client terminal
```properties
CREATE DATABASE world_db;
```

### Database Configuration
Set the following environment variables in cardConfig.properties
```properties
WORLDAPI_MANAGER_DB_USER=<database username>
WORLDAPI_MANAGER_DB_PASSWORD=<database user password>
WORLDAPI_MANAGER_DB_URL=<database jdbc url>
```

#### Run locally
Start server using
```bash
./mvnw spring-boot:run
```

To view specs, visit http://localhost:8585/worldapi/swagger-ui/index.html#/

#### Run locally via docker compose
##### Requirement: Ensure your Docker-daemon is up and running. Docker Desktop.

##### Run Docker login
```bash
    docker login -u <username> docker
```

##### Spawn up the containers
```bash
    TAG=1.0.0 docker-compose up
```

Where tag is the docker tag you wish to run locally, you can build and push your local branch using
```bash
bash .mvn/publish.sh
```

You can build and publish and run locally using
```bash
bash run.sh
```

#### Run tests
```bash
./mvnw clean test
```

#### Build and publish image locally
```bash
bash .mvn/publish.sh
```
