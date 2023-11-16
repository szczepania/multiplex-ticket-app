package services.reservationsConfig

import model.Seats

final case class Summary(
    name: String,
    surname: String,
    price: BigDecimal,
    seats: Seq[Seats]
)

final case class Reservation(id: Long, name: String, surname: String, seats: Seq[BookedSeat])

final case class BookedSeat(row: Int, column: Int, id: Long, ticket: String)
