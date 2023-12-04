package routes

import akka.http.scaladsl.testkit.ScalatestRouteTest
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import services.ScreeningRoomsService
import connection.DatabaseConnector
import scala.concurrent.ExecutionContext
import akka.http.scaladsl.model.StatusCodes

class ScreeningRoomsRouteTest
    extends AnyWordSpec
    with Matchers
    with ScalatestRouteTest {
  "ScreeningRoomsRoute" should {
    "return OK for GET requests to the screeningrooms path" in {
      val databaseConnector = new DatabaseConnector()
      val screeningRoomsService = new ScreeningRoomsService(databaseConnector)
      val screeningRoomsRoute =
        new ScreeningRoomsRoute(screeningRoomsService)(ExecutionContext.global)

      Get("/screeningrooms") ~> screeningRoomsRoute.route ~> check {
        status shouldBe StatusCodes.OK
      }

      Get("/screeningrooms/1") ~> screeningRoomsRoute.route ~> check {
        status shouldBe StatusCodes.OK
      }
    }
  }
}
