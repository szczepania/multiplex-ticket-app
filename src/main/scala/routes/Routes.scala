package routes

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import services.MoviesService

import scala.concurrent.ExecutionContext

class Routes(movies: MoviesService)(implicit
    executionContext: ExecutionContext
) {
  val moviesRouter = new MovieRoute(movies)

  val route: Route =
    concat(
      pathPrefix("healthcheck") {
        get {
          complete("200 -> health OK")
        }
      },
      pathPrefix("api") {
        concat(
          moviesRouter.route
        )
      }
    )
}
