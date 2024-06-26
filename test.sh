startTime=$(date -u -d '2022-01-04 00:00:00' +%s)
endTime=$(date -u -d '2022-01-05 23:59:59' +%s)

echo "Return list of available movies and screening rooms in given time and date range"
curl -X GET "http://localhost:8081/api/screenings/time?start=2024-01-04T10:00:00&end=2024-01-04T13:59:59"
echo
echo

echo "Return available seats for given screening"
curl -X GET "http://localhost:8081/api/seats/availability/1"
echo
echo

echo "Create new reservation by providing name, surname, ticket type and picked seats"
curl -X POST "http://localhost:8081/api/reservations" -H "Content-Type: application/json" -d '{
  "id": 1, 
  "name": "Anna",
  "surname": "Szczepanik",
  "seats": [{"row": 3, "column": 1, "id": 0, "ticketType": "adult"}, {"row": 3, "column": 2, "id": 1, "ticketType": "student"}, {"row": 3, "column": 3, "id": 2, "ticketType": "child"}]
}'
echo
echo

echo "Not allow to do reservation when reservation time is over"
curl -X POST "http://localhost:8081/api/reservations" -H "Content-Type: application/json" -d '{
  "id": 27, 
  "name": "Jan",
  "surname": "Kowalski",
  "seats": [{"row": 3, "column": 1, "id": 0, "ticketType": "adult"}]
}'
echo
echo

echo "Not allow to do reservation without providing picked seats" 
curl -X POST "http://localhost:8081/api/reservations" -H "Content-Type: application/json" -d '{
  "id": 1, 
  "name": "Anna",
  "surname": "Szczepanik",
  "seats": []
}'
echo
echo

echo "Not allow to do reservation when name or surname is invalid" 
curl -X POST "http://localhost:8081/api/reservations" -H "Content-Type: application/json" -d '{
  "id": 1, 
  "name": "An",
  "surname": "Sz",
  "seats": [{"row": 3, "column": 1, "id": 0, "ticketType": "adult"}]
}'
echo
echo
