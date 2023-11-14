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

final case class Reservations(id: Long, username: String, usersurname: String, screeningId: Long, time: LocalDateTime)
