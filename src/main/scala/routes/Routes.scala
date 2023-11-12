package routes

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import services.{MoviesService, ScreeningRoomsService, ScreeningsService}

import scala.concurrent.ExecutionContext
import services.SeatsService

class Routes(movies: MoviesService, screeningRooms: ScreeningRoomsService, screenings: ScreeningsService, seats: SeatsService)(
    implicit executionContext: ExecutionContext
) {
  val moviesRouter = new MovieRoute(movies)
  val screeningRoomsRouter = new ScreeningRoomsRoute(screeningRooms)
  val screeningsRouter = new ScreeningsRoute(screenings)
  val seatsRouter = new SeatsRoute(seats)

  val route: Route =
    concat(
      pathPrefix("healthcheck") {
        get {
          complete("200 -> health OK")
        }
      },
      pathPrefix("api") {
        concat(
          moviesRouter.route,
          screeningRoomsRouter.route,
          screeningsRouter.route,
          seatsRouter.route
        )
      }
    )
}
