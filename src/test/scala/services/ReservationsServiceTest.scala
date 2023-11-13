package services

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import java.time.LocalDateTime
import scala.concurrent.ExecutionContext.Implicits.global
import connection.DatabaseConnector

class ReservationsServiceTest extends AnyFlatSpec with Matchers {
  val databaseConnector = new DatabaseConnector()
  val reservationsService = new ReservationsService(databaseConnector)

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

}
