package services

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import scala.concurrent.ExecutionContext.Implicits.global
import connection.DatabaseConnector

class ScreeningRoomsServiceTest extends AnyFlatSpec with Matchers {
  val databaseConnector = new DatabaseConnector()
  val screeningRoomsService = new ScreeningRoomsService(databaseConnector)

  "ScreeningRoomsService" should "list all screening rooms" in {
    val result = screeningRoomsService.listRooms

    result.map { screeningRooms =>
      screeningRooms should not be empty
    }
  }

  it should "get a screening room by id" in {
    val result = screeningRoomsService.getRoomById(1)

    result.map { screeningRoom =>
      screeningRoom should not be empty
    }
  }
}
