package services

import connection.DatabaseConnector
import model.Screenings
import slick.jdbc.PostgresProfile.api._
import tables.ScreeningsDataTable

import scala.concurrent.Future

class ScreeningsService(val databaseConnector: DatabaseConnector)
    extends ScreeningsDataTable {

  def listScreenings: Future[Seq[Screenings]] =
    databaseConnector.db.run(screeningsTable.result)

}
