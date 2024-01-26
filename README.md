# Upgate assessment

## Used technologies

- Kotlin 1.9
- Spring Boot 3.2.2 (coroutines stack)
- PostgreSQL
- Liquibase

## Configuration

### Application

Application config defined in the _.env_ file. There are four application-specific parameters:

- `APPLICATION_UPGATE_API_KEY` - Upgate API key
- `APPLICATION_UPGATE_BASE_URL` - Base upgate API URL
- `APPLICATION_UPGATE_SUCCESS_URL` - Success URL callback
- `APPLICATION_UPGATE_FAILURE_URL` - Failure URL callback

### Database

Default Database username and password are `postgres / postgres`. On first run, database `upgate_db` is created.

Application uses liquibase for migrations and R2DBC for database connect. Connection properties are also in _.env_ file (these are default spring properties)

- `SPRING_R2DBC_URL=r2dbc:postgresql://db/upgate_db`
- `SPRING_R2DBC_USERNAME`
- `SPRING_R2DBC_PASSWORD`

- `SPRING_LIQUIBASE_URL=jdbc:postgresql://db/upgate_db`
- `SPRING_LIQUIBASE_USER`
- `SPRING_LIQUIBASE_PASSWORD`

## Launch instructions

There is one command to start a service with its dependencies

```shell
docker compose up -d --build
```

This command will launch postgres, whcli and our app on port 8080. It will also initialize the database. Application can be configured using .env file in the project root directory.
