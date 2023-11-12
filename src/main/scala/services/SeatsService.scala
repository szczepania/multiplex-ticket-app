package services

import connection.DatabaseConnector
import model.Seats
import slick.jdbc.PostgresProfile.api._
import tables.SeatsDataTable

import scala.concurrent.Future

class SeatsService(val databaseConnector: DatabaseConnector)
    extends SeatsDataTable {

  def listSeats: Future[Seq[Seats]] =
    databaseConnector.db.run(seatsTable.result)

  def getSeatById(id: Long): Future[Option[Seats]] =
    databaseConnector.db.run(seatsTable.filter(_.id === id).result.headOption)
}
