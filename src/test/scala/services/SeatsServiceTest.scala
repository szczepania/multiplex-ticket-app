package services

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import scala.concurrent.ExecutionContext.Implicits.global
import connection.DatabaseConnector

class SeatsServiceTest extends AnyFlatSpec with Matchers {
  val databaseConnector = new DatabaseConnector()
  val seatsService = new SeatsService(databaseConnector)

  "SeatsService" should "list all seats" in {
    val result = seatsService.listSeats

    result.map { seats =>
      seats should not be empty
    }
  }

  it should "get a seat by id" in {
    val result = seatsService.getSeatById(1)

    result.map { seat =>
      seat should not be empty
    }
  }

  it should "check seat availability" in {
    val result = seatsService.checkSeatAvailability(1)

    result.map { seats =>
      seats should not be empty
    }
  }
}
