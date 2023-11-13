package tables

import connection.DatabaseConnector
import model.Reservations
import slick.jdbc.PostgresProfile.api._
import tables.ScreeningsDataTable
import java.time.LocalDateTime

trait ReservationsDataTable {

  val databaseConnector: DatabaseConnector

  class ReservationsTable(taq: Tag)
      extends Table[Reservations](taq, "reservations") {

    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)

    def username = column[String]("user_name")

    def usersurname = column[String]("user_surname")

    def screeningId = column[Long]("screening_id")

    def time = column[LocalDateTime]("time")

    override def * =
      (id, username, usersurname, screeningId, time).<>(Reservations.tupled, Reservations.unapply)

  }

  lazy val reservationsTable = TableQuery[ReservationsTable]
}
