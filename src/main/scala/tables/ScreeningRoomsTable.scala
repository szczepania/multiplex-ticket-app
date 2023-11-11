package tables

import connection.DatabaseConnector
import model.ScreeningRooms
import slick.jdbc.PostgresProfile.api._

trait ScreeningRoomsDataTable {

  val databaseConnector: DatabaseConnector

  class ScreeningRoomsTable(tag: Tag)
      extends Table[ScreeningRooms](tag, "screeningrooms") {

    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)

    def name = column[String]("name")

    def rows = column[Int]("rows")

    def columns = column[Int]("columns")


    override def * = (id, name, rows, columns).<>(
      ScreeningRooms.tupled,
      ScreeningRooms.unapply
    )

  }

  lazy val screeningRoomsTable = TableQuery[ScreeningRoomsTable]

}
