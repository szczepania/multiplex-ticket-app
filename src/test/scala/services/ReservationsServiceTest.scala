package services

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import java.time.LocalDateTime
import scala.concurrent.ExecutionContext.Implicits.global
import connection.DatabaseConnector
import java.time.LocalDate
import model.{BookedSeat, Screenings, Movies, Reservation}

class ReservationsServiceTest extends AnyFlatSpec with Matchers {
  val databaseConnector = new DatabaseConnector()
  val screeningService = new ScreeningsService(databaseConnector)
  val seatsService = new SeatsService(databaseConnector)
  val reservationsService =
    new ReservationsService(databaseConnector, screeningService, seatsService)

  "ReservationsService" should "list all reservations" in {
    val result = reservationsService.listReservations

    result.map { reservations =>
      reservations should not be empty
    }
  }

  it should "get a reservation by id" in {
    val result = reservationsService.getReservationById(1)

    result.map { reservation =>
      reservation should not be empty
    }
  }

  it should "check reservation time correctly" in {
    val screeningTime =
      LocalDateTime.now.plusHours(1)
    val reservationTime = LocalDateTime.now

    val result =
      reservationsService.checkReservationTime(screeningTime, reservationTime)

    result shouldBe true
  }

  it should "return false for late reservations" in {
    val screeningTime = LocalDateTime.now
    val reservationTime = LocalDateTime.now.plusMinutes(1)

    val result =
      reservationsService.checkReservationTime(screeningTime, reservationTime)

    result shouldBe false
  }

  it should "validate user data correctly" in {
    val validName = "Jan"
    val validSurname = "Kowalski-Nowak"
    val invalidName = "jan"
    val invalidSurname = "kowalski-nowak"
    val validNameWithPolishCharacters = "Przemysław"
    val validSurnameWithPolishCharacters = "Przemysławski-Nowak"
    val invalidShortName = "Ja"
    val invalidShortSurname = "Ko"

    reservationsService.validateUserData(validName, validSurname) shouldBe true
    reservationsService.validateUserData(
      invalidName,
      validSurname
    ) shouldBe false
    reservationsService.validateUserData(
      validName,
      invalidSurname
    ) shouldBe false
    reservationsService.validateUserData(
      invalidName,
      invalidSurname
    ) shouldBe false
    reservationsService.validateUserData(
      validNameWithPolishCharacters,
      validSurnameWithPolishCharacters
    ) shouldBe true
    reservationsService.validateUserData(
      validName,
      validSurname
    ) shouldBe true
    reservationsService.validateUserData(
      invalidShortName,
      invalidShortSurname
    ) shouldBe false
  }

  it should "select day and movie correctly" in {
    val screeningsExample = new Screenings(1, 1, 1, LocalDateTime.now)
    val screeningsTime = screeningsExample.screeningTime
    val movie = new Movies(1, "Django")

    val result = reservationsService.selectDayAndMovie(screeningsTime, movie)

    result.map(screenings => {
      screenings should not be empty
      screenings.foreach(screening => {
        screening.id shouldBe screeningsExample.movieId
        screening.title shouldBe movie
      })
    })
  }

  it should "make reservation correctly" in {
    val seats =
      Seq(BookedSeat(1, 1, 1L, "adult"), BookedSeat(1, 2, 2L, "child"))
    val name = "John"
    val surname = "Doe"

    val result = reservationsService.makeReservation(
      new Reservation(1, name, surname, seats)
    )

    result.map {
      case Right(summary) =>
        summary.name shouldBe name
        summary.surname shouldBe surname
        summary.seats shouldBe seats
      case Left(error) =>
        fail(s"Expected a successful reservation, but got an error: $error")
    }
  }

}
