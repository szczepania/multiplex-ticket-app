package services

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import java.time.LocalDateTime
import scala.concurrent.ExecutionContext.Implicits.global
import connection.DatabaseConnector

class ScreeningsServiceTest extends AnyFlatSpec with Matchers {
  val databaseConnector = new DatabaseConnector()
  val screeningsService = new ScreeningsService(databaseConnector)

  "ScreeningsService" should "list screenings in a given time interval" in {
    val start = LocalDateTime.of(2022, 1, 4, 10, 0)
    val end = LocalDateTime.of(2022, 1, 4, 23, 59)

    val result = screeningsService.listScreeningsInTimeInterval(start, end)

    result.map { screenings =>
      screenings should not be empty
      screenings.forall(screening =>
        screening._2.isAfter(start) && screening._2.isBefore(end)
      ) shouldBe true
    }
  }

  it should "list all screenings" in {
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

}
