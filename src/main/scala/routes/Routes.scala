package routes

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import services.{MoviesService, ScreeningRoomsService, ScreeningsService}

import scala.concurrent.ExecutionContext
import services.SeatsService
import services.ReservationsService

class Routes(
    movies: MoviesService,
    screeningRooms: ScreeningRoomsService,
    screenings: ScreeningsService,
    seats: SeatsService,
    reservations: ReservationsService
)(implicit
    executionContext: ExecutionContext
) {
  private val moviesRouter = new MoviesRoute(movies)
  private val screeningRoomsRouter = new ScreeningRoomsRoute(screeningRooms)
  private val screeningsRouter = new ScreeningsRoute(screenings)
  private val seatsRouter = new SeatsRoute(seats)
  private val reservationsRouter = new ReservationsRoute(reservations)

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
          seatsRouter.route,
          reservationsRouter.route
        )
      }
    )
}
