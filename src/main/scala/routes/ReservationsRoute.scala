package routes

import services.ReservationsService
import scala.concurrent.ExecutionContext
import de.heikoseeberger.akkahttpcirce.FailFastCirceSupport
import io.circe.generic.auto._
import io.circe.syntax._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route

class ReservationsRoute(reservationsService: ReservationsService)(implicit
    executionContext: ExecutionContext
) extends FailFastCirceSupport {

  val route: Route = pathPrefix("reservations") {
    concat(
      pathEndOrSingleSlash {
        get {
          complete(reservationsService.listReservations.map(_.asJson))
        }
      },
      path(LongNumber) { id =>
        get {
          complete(reservationsService.getReservationById(id).map(_.asJson))
        }
      }
    )
  }
}
