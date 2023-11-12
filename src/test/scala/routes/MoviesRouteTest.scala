package routes

import akka.http.scaladsl.testkit.ScalatestRouteTest
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import services.MoviesService
import connection.DatabaseConnector
import scala.concurrent.ExecutionContext
import akka.http.scaladsl.model.StatusCodes

class MovieRouteSpec extends AnyWordSpec with Matchers with ScalatestRouteTest {
  "MovieRoute" should {
    "return OK for GET requests to the movies path" in {
      val databaseConnector = new DatabaseConnector()
      val movieService = new MoviesService(databaseConnector)
      val movieRoute = new MovieRoute(movieService)(ExecutionContext.global)

      Get("/movies") ~> movieRoute.route ~> check {
        status shouldBe StatusCodes.OK
      }

      Get("/movies/1") ~> movieRoute.route ~> check {
        status shouldBe StatusCodes.OK
      }
    }
  }
}
