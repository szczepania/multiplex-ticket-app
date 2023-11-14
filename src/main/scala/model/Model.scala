package model

import java.time.LocalDateTime

final case class Movies(id: Long, title: String)

final case class ScreeningRooms(id: Long, name: String, rows: Int, columns: Int)

final case class Screenings(
    id: Long,
    movieId: Long,
    screeningRoomId: Long,
    screeningTime: LocalDateTime
)

final case class Seats(id: Long, reservationId: Long, row: Int, columnNumber: Int)

final case class Reservations(id: Long, username: String, usersurname: String, price: BigDecimal, screeningId: Long, time: LocalDateTime)

final case class Summary(
    name: String,
    surname: String,
    price: BigDecimal,
    seats: Seq[Seats]
)

final case class Reservation(id: Long, name: String, surname: String, seats: Seq[BookedSeat])

final case class BookedSeat(row: Int, column: Int, id: Long, ticket: String)

