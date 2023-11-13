package routes

import akka.http.scaladsl.testkit.ScalatestRouteTest
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import services.{MoviesService, ScreeningRoomsService, ScreeningsService, SeatsService, ReservationsService}
import connection.DatabaseConnector
import scala.concurrent.ExecutionContext
import akka.http.scaladsl.model.StatusCodes

class RoutesTest extends AnyWordSpec with Matchers with ScalatestRouteTest {
  "Routes" should {
    "return OK for GET requests to the api path" in {
      val databaseConnector = new DatabaseConnector()
      val movieService = new MoviesService(databaseConnector)
      val screeningService = new ScreeningsService(databaseConnector)
      val screeningRoomService = new ScreeningRoomsService(databaseConnector)
      val seatService = new SeatsService(databaseConnector)
      val reservationsService = new ReservationsService(databaseConnector)
      val routes = new Routes(movieService, screeningRoomService, screeningService, seatService, reservationsService)(ExecutionContext.global)

      Get("/healthcheck") ~> routes.route ~> check {
        status shouldBe StatusCodes.OK
      }
    }
  }
}
