package services

import connection.DatabaseConnector
import model.ScreeningRooms
import slick.jdbc.PostgresProfile.api._
import tables.ScreeningRoomsDataTable

import scala.concurrent.Future

class ScreeningRoomsService(val databaseConnector: DatabaseConnector)
    extends ScreeningRoomsDataTable {

  def listRooms: Future[Seq[ScreeningRooms]] =
    databaseConnector.db.run(screeningRoomsTable.result)

  def getRoomById(id: Long): Future[Option[ScreeningRooms]] =
    databaseConnector.db.run(
      screeningRoomsTable.filter(_.id === id).result.headOption
    )

}
