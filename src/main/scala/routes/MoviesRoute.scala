package routes

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import de.heikoseeberger.akkahttpcirce.FailFastCirceSupport
import io.circe.generic.auto._
import io.circe.syntax._
import services.MoviesService

import scala.concurrent.ExecutionContext

class MovieRoute(movieService: MoviesService)(implicit
    executionContext: ExecutionContext
) extends FailFastCirceSupport {

  val route: Route = pathPrefix("movies") {
    pathEndOrSingleSlash {
      get {
        complete(movieService.listMovies.map(_.asJson))
      }
    }
  }
}
