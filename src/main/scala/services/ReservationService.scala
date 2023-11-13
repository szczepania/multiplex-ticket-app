package services

import services.{SeatsService, ScreeningsService}
import scala.concurrent.ExecutionContext
import scala.concurrent.Future
import model.Reservation
import connection.DatabaseConnector
import slick.jdbc.PostgresProfile.api._
import tables.ReservationsDataTable
import cats.data.Op

class ReservationsService(val databaseConnector: DatabaseConnector)
    extends ReservationsDataTable {

  def listReservations: Future[Seq[Reservation]] =
    databaseConnector.db.run(reservationsTable.result)

  def getReservationById(id: Long): Future[Option[Reservation]] = {
    val query = reservationsTable.filter(_.id === id)
    databaseConnector.db.run(query.result.headOption)
  }

}
