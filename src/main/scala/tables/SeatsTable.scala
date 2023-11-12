package tables

import connection.DatabaseConnector
import model.Seats
import slick.jdbc.PostgresProfile.api._
import slick.lifted.ProvenShape

trait SeatsDataTable {

  val databaseConnector: DatabaseConnector

  class SeatsTable(tag: Tag) extends Table[Seats](tag, "seats") {

    def id: Rep[Long] = column[Long]("id", O.PrimaryKey, O.AutoInc)

    def reservationId: Rep[Long] = column[Long]("reservation_id")

    def row: Rep[Int] = column[Int]("row")

    def columnNumber: Rep[Int] = column[Int]("column_number")

    override def * =
      (id, reservationId, row, columnNumber).<>(Seats.tupled, Seats.unapply)

  }

  lazy val seatsTable = TableQuery[SeatsTable]
}
