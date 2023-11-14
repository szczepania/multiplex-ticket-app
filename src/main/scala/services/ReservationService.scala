package services

import scala.concurrent.Future
import model.Reservations
import connection.DatabaseConnector
import slick.jdbc.PostgresProfile.api._
import tables.{ScreeningsDataTable, ReservationsDataTable, SeatsDataTable}
import java.time.LocalDateTime
import config.Config
import java.time.Duration

class ReservationsService(val databaseConnector: DatabaseConnector)
    extends ReservationsDataTable
    with ScreeningsDataTable
    with SeatsDataTable {

  val config = new Config()

  def listReservations: Future[Seq[Reservations]] =
    databaseConnector.db.run(reservationsTable.result)

  def getReservationById(id: Long): Future[Option[Reservations]] = {
    val query = reservationsTable.filter(_.id === id)
    databaseConnector.db.run(query.result.headOption)
  }

  def checkReservationTime(
      screeningTime: LocalDateTime,
      reservationTime: LocalDateTime
  ): Boolean = {
    val duration = Duration.between(reservationTime, screeningTime)
    duration.toMinutes >= 15
  }

  def validateUserData(name: String, surname: String): Boolean = {
    val namePattern = "^[A-ZĄĆĘŁŃÓŚŹŻ][a-ząćęłńóśźż]{2,}$".r
    val surnamePattern =
      "^[A-ZĄĆĘŁŃÓŚŹŻ][a-ząćęłńóśźż]{2,}(-[A-ZĄĆĘŁŃÓŚŹŻ][a-ząćęłńóśźż]*)?$".r

    namePattern.matches(name) && surnamePattern.matches(surname)
  }
}
