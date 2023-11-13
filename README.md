"# multiplex-ticket-app" 

GET http://localhost:8081/api/movies

GET http://localhost:8081/api/movies/{$id}

GET http://localhost:8081/api/screeningrooms

GET http://localhost:8081/api/screeningrooms/{$id}

GET http://localhost:8081/api/screenings

GET http://localhost:8081/api/screenings/{$id}

GET http://localhost:8081/api/seats

GET http://localhost:8081/api/seats/${id}

GET http://localhost:8081/api/screenings/time?start=${startTime}&end=${endTime}

```
example: time?start=2022-01-04T10:00:00&end=2022-01-04T13:59:59
```
