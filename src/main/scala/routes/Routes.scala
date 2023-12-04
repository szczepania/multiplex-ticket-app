package routes

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.{ExceptionHandler, Route}
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

  implicit def exceptionHandler: ExceptionHandler =
    ExceptionHandler {
      case e: NoSuchElementException =>
        extractUri { uri =>
          complete(StatusCodes.NotFound, s"Resource not found at $uri, error message: ${e.getMessage}")
        }
      case e: Exception =>
        extractUri { uri =>
          complete(StatusCodes.InternalServerError, s"An error occurred at $uri, error message: ${e.getMessage}")
        }
    }

  val route: Route =
    handleExceptions(exceptionHandler) {
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
}
