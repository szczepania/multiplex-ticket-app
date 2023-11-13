package tables

import connection.DatabaseConnector
import model.Reservation
import slick.jdbc.PostgresProfile.api._
import tables.ScreeningsDataTable
import java.time.LocalDateTime

trait ReservationsDataTable {

  val databaseConnector: DatabaseConnector

  class ReservationsTable(taq: Tag)
      extends Table[Reservation](taq, "reservations") {

    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)

    def username = column[String]("user_name")

    def usersurname = column[String]("user_surname")

    def screeningId = column[Long]("screening_id")

    def time = column[LocalDateTime]("time")

    override def * =
      (id, username, usersurname, screeningId, time).<>(Reservation.tupled, Reservation.unapply)

  }

  lazy val reservationsTable = TableQuery[ReservationsTable]
}
