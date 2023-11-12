package routes

import akka.http.scaladsl.testkit.ScalatestRouteTest
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import services.ScreeningsService
import connection.DatabaseConnector
import scala.concurrent.ExecutionContext
import akka.http.scaladsl.model.StatusCodes

class ScreeningsRouteTest
    extends AnyWordSpec
    with Matchers
    with ScalatestRouteTest {
  "ScreeningsRoute" should {
    "return OK for GET requests to the screenings path" in {
      val databaseConnector = new DatabaseConnector()
      val screeningsService = new ScreeningsService(databaseConnector)
      val screeningsRoute =
        new ScreeningsRoute(screeningsService)(ExecutionContext.global)

      Get("/screenings") ~> screeningsRoute.route ~> check {
        status shouldBe StatusCodes.OK
      }

      Get("/screenings/1") ~> screeningsRoute.route ~> check {
        status shouldBe StatusCodes.OK
      }
    }
  }
}
