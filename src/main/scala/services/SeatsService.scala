package services

import connection.DatabaseConnector
import model.Seats
import slick.jdbc.PostgresProfile.api._
import tables.{SeatsDataTable, ReservationsDataTable}

import scala.concurrent.Future

class SeatsService(val databaseConnector: DatabaseConnector)
    extends SeatsDataTable
    with ReservationsDataTable {

  def listSeats: Future[Seq[Seats]] =
    databaseConnector.db.run(seatsTable.result)

  def getSeatById(id: Long): Future[Option[Seats]] =
    databaseConnector.db.run(seatsTable.filter(_.id === id).result.headOption)

  def checkSeatAvailability(id: Long): Future[Seq[Seats]] = {
    val joinQuery = seatsTable
      .join(reservationsTable)
      .on(_.reservationId === _.id)
      .map { case (seat, reservation) =>
        (reservation.screeningId, seat)
      }

    val query = joinQuery
      .filter(_._1 === id)
      .map(_._2)
      .distinct
    databaseConnector.db.run(query.result)

  }
}
