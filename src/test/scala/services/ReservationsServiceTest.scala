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

}
