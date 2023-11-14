startTime=$(date -u -d '2022-01-04 00:00:00' +%s)
endTime=$(date -u -d '2022-01-05 23:59:59' +%s)

echo "Test available endpoints"
echo

echo "Test GET for all the movies available"
curl -X GET http://localhost:8081/api/movies
echo
echo

echo "Test GET for movie with specified id"
curl -X GET http://localhost:8081/api/movies/5
echo
echo

echo "Test GET for all the screenings available"
curl -X GET http://localhost:8081/api/screenings
echo
echo

echo "Test GET for screening with specified id"
curl -X GET http://localhost:8081/api/screenings/3
echo
echo

echo "Test GET for available movies in specified time and date"
curl -X GET "http://localhost:8081/api/screenings/time?start=2022-01-04T10:00:00&end=2022-01-04T13:59:59"
