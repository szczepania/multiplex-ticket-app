package services

import scala.concurrent.Future
import connection.DatabaseConnector
import slick.jdbc.PostgresProfile.api._
import tables.{
  ScreeningsDataTable,
  ReservationsDataTable,
  SeatsDataTable,
  MovieDataTable
}
import java.time.LocalDateTime
import config.Config
import java.time.Duration
import model.{Movies, Seats, Reservations, Summary, Reservation, BookedSeat}
import scala.concurrent.ExecutionContext
import services.ScreeningsService

class ReservationsService(
    val databaseConnector: DatabaseConnector,
    screeningsService: ScreeningsService,
    seatsService: SeatsService
)(implicit
    executionContext: ExecutionContext
) extends ReservationsDataTable
    with ScreeningsDataTable
    with SeatsDataTable
    with MovieDataTable {

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

  def selectDayAndMovie(
      time: LocalDateTime,
      movie: Movies
  ): Future[Option[Movies]] = {
    val query = screeningsTable
      .filter(_.screeningTime === time)
      .filter(_.movieId === movie.id)
    databaseConnector.db.run(query.result.headOption).map {
      case Some(screening) => Some(movie)
      case None            => None
    }
  }

  def mapTicketPriceToBigDecimal(ticketType: String): BigDecimal = {
    val prices = config.prices

    ticketType match {
      case "adult"   => BigDecimal(prices.adult)
      case "child"   => BigDecimal(prices.child)
      case "student" => BigDecimal(prices.student)
    }
  }

  def makeReservation(
      reservation: Reservation
  ): Future[Either[String, Summary]] = {

    if (reservation.seats.isEmpty) {
      Future(Left("No seats were selected"))
    } else {
      val query =
        screeningsService.getScreeningById(reservation.id).flatMap {
          screening =>
            seatsService.checkSeatAvailability(reservation.id).map {
              availableSeats =>
                (screening, availableSeats)
            }
        }

      query.map { data =>
        val screening = data._1
        val availableSeats = data._2

        val reservationTime = LocalDateTime.now()
        val screeningTime = screening.get.screeningTime
        val paymentTime = checkReservationTime(screeningTime, reservationTime)

        val name = reservation.name
        val surname = reservation.surname
        val seats = reservation.seats

        val validUserData = validateUserData(name, surname)

        if (validUserData) {
          if (paymentTime) {
            if (seats.length <= availableSeats.length) {
              val price =
                seats.map(seat => mapTicketPriceToBigDecimal(seat.ticket)).sum
              val reservation = Reservations(
                0,
                name,
                surname,
                price,
                screening.get.id,
                reservationTime
              )

              val query = (reservationsTable returning reservationsTable.map(
                _.id
              )) += reservation

              val reservationId = reservation.id

              val seatsToBook =
                seats.map(seat =>
                  Seats(0, reservationId, seat.row, seat.column)
                )

              val reserved = seatsTable ++= seatsToBook

              databaseConnector.db.run(reserved)

              Right(Summary(name, surname, price, seatsToBook))
            } else {
              Left("Not enough seats available")
            }
          } else {
            Left("Reservation time expired")
          }
        } else {
          Left("Invalid user data")
        }
      }
    }
  }
}
