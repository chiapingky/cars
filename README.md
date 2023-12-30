# Cars service
Service to process car data

## Tech stack
1. Language: Java 17
2. Framework: Spring Boot 3
3. Database: Postgresql

## Database Schema
```
Car (
  id int primary key,
  name varchar(255),
  brand_id int reference brand(id)
)

brand (
  id int primary key,
  name varchar(255)
)

```

## Code Structure
```
-root
--service-test
---api-test
---test-infrastructure
--main-module
```

## Gradlew Commands
### Build Command
`./gradlew build` : build the code and generate `.jar` file

### Clean Command
`./gradlew clean` : clean up and delete all build artifact

## Guides

### Run Test Infrastructure

### Pre-requisite:
- Install Docker (refer to https://docs.docker.com/get-docker/)
- Install Docker compose if not included in your installation (refer to https://docs.docker.com/compose/install/)

### How to run:
1. Make sure docker is already running
2. Open a terminal
3. Run `./gradlew clean build` command to create updated `.jar` file
4. Run `cd service-test/test-infrastructure/` to go to `test-infrastructure` directory
5. Run `docker compose up cars`
6. After a few moment you can run `docker ps` to check the containers status
7. Run `docker ps -q | % { docker rm -f $_ }` to shut down and delete all containers

### Run API Test

#### Pre-requisite:
Test infrastructure must be running with the newest build `.jar`

#### How to run:
1. Open a terminal
2. Run `cd service-test` to go to `service-test` directory
3. Run `./gradlew clean test` and wait for it to finish
4. The build log will print where the report is generated