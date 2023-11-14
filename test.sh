startTime=$(date -u -d '2022-01-04 00:00:00' +%s)
endTime=$(date -u -d '2022-01-05 23:59:59' +%s)

echo "Return list of available movies in given time and date range"
curl -X GET "http://localhost:8081/api/screenings/time?start=2022-01-04T10:00:00&end=2022-01-04T13:59:59"

echo "Return available seats for given screening"
curl -X GET "http://localhost:8081/api/seats/availability/1"
