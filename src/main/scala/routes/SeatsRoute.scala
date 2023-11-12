package routes

import services.SeatsService
import de.heikoseeberger.akkahttpcirce.FailFastCirceSupport
import scala.concurrent.ExecutionContext
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.Directives._
import io.circe.generic.auto._
import io.circe.syntax._

class SeatsRoute(SeatsService: SeatsService)(implicit
    executionContext: ExecutionContext
) extends FailFastCirceSupport {

  val route: Route = pathPrefix("seats") {
    concat(
      pathEndOrSingleSlash {
        get {
          complete(SeatsService.listSeats.map(_.asJson))
        }
      },
      path(LongNumber) { id =>
        get {
          complete(SeatsService.getSeatById(id).map(_.asJson))
        }
      }
    )
  }
}
