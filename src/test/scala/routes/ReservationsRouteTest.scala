package routes

import akka.http.scaladsl.testkit.ScalatestRouteTest
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import services.ReservationsService
import connection.DatabaseConnector
import scala.concurrent.ExecutionContext
import akka.http.scaladsl.model.StatusCodes

class ReservationsRouteTest
    extends AnyWordSpec
    with Matchers
    with ScalatestRouteTest {
  "ReservationsRoute" should {
    "return OK for GET requests to the reservations path" in {
      val databaseConnector = new DatabaseConnector()
      val reservationsService = new ReservationsService(databaseConnector)
      val reservationsRoute =
        new ReservationsRoute(reservationsService)(ExecutionContext.global)

      Get("/reservations") ~> reservationsRoute.route ~> check {
        status shouldBe StatusCodes.OK
      }

      Get("/reservations/1") ~> reservationsRoute.route ~> check {
        status shouldBe StatusCodes.OK
      }
    }
  }
}
