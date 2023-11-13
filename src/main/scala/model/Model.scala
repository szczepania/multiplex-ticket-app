package model

import java.time.LocalDateTime
import akka.protobufv3.internal.Value

final case class Movies(id: Long, title: String)

final case class ScreeningRooms(id: Long, name: String, rows: Int, columns: Int)

final case class Screenings(
    id: Long,
    movieId: Long,
    screeningRoomId: Long,
    screeningTime: LocalDateTime
)

final case class Seats(id: Long, reservationId: Long, row: Int, columnNumber: Int)

final case class Reservation(id: Long, username: String, usersurname: String, screeningId: Long, time: LocalDateTime)
