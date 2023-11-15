package services

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import java.time.LocalDateTime
import scala.concurrent.ExecutionContext.Implicits.global
import connection.DatabaseConnector

class ScreeningsServiceTest extends AnyFlatSpec with Matchers {
  val databaseConnector = new DatabaseConnector()
  val screeningsService = new ScreeningsService(databaseConnector)

  "ScreeningsService" should "list all screenings" in {
    val result = screeningsService.listScreenings

    result.map { screenings =>
      screenings should not be empty
    }
  }

  it should "get a screening by id" in {
    val result = screeningsService.getScreeningById(1)

    result.map { screening =>
      screening should not be empty
    }
  }

  it should "list movies and rooms in time interval correctly" in {
    val start = LocalDateTime.now() 
    val end = start.plusHours(2)

    val result = screeningsService.listMoviesAndRoomsInTimeInterval(start, end)

    result.map { moviesAndRooms =>
      moviesAndRooms shouldBe empty
    }
  }

}
