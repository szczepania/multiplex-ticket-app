package routes

import akka.http.scaladsl.testkit.ScalatestRouteTest
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import services.SeatsService
import connection.DatabaseConnector
import scala.concurrent.ExecutionContext
import akka.http.scaladsl.model.StatusCodes

class SeatsRouteTest extends AnyWordSpec with Matchers with ScalatestRouteTest {
  "SeatsRoute" should {
    "return OK for GET requests to the seats path" in {
      val databaseConnector = new DatabaseConnector()
      val seatsService = new SeatsService(databaseConnector)
      val seatsRoute = new SeatsRoute(seatsService)(ExecutionContext.global)

      Get("/seats") ~> seatsRoute.route ~> check {
        status shouldBe StatusCodes.OK
      }

      Get("/seats/1") ~> seatsRoute.route ~> check {
        status shouldBe StatusCodes.OK
      }
    }
  }
}
