# multiplex-ticket-app
![ci workflow](https://github.com/szczepania/multiplex-ticket-app/actions/workflows/ci.yml/badge.svg)

Seat reservation system for multiplex

## How to install

Prerequisites for running locally:
- Java 1.8 at least
- Docker
- Postgres
- sbt
- bash

1. Run docker
```
docker-compose up -d
```

2. Run application
```
sbt run
```

3. Run test.sh using Bash
```
bash test.sh
```

4. When you are done shut down the container
```
docker-compose down
```

## Available endpoints

- **GET** http://localhost:8081/api/movies

- **GET** http://localhost:8081/api/movies/{$id}

- **GET** http://localhost:8081/api/screeningrooms

- **GET** http://localhost:8081/api/screeningrooms/{$id}

- **GET** http://localhost:8081/api/screenings

- **GET** http://localhost:8081/api/screenings/{$id}

- **GET** http://localhost:8081/api/seats

- **GET** http://localhost:8081/api/seats/${id}

- **GET** http://localhost:8081/api/screenings/time?start=${startTime}&end=${endTime}

## Technologies and tools used

- Scala
- ScalaTest
- Akka
- Circe
- Slick
- PureConfig
- PostgreSQL
- Docker
