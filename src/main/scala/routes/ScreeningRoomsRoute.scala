package routes

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import de.heikoseeberger.akkahttpcirce.FailFastCirceSupport
import io.circe.generic.auto._
import io.circe.syntax._

import services.ScreeningRoomsService
import de.heikoseeberger.akkahttpcirce.FailFastCirceSupport
import scala.concurrent.ExecutionContext

class ScreeningRoomsRoute(screeningRooms: ScreeningRoomsService)(implicit
    executionContext: ExecutionContext
) extends FailFastCirceSupport {

  val route: Route = pathPrefix("screeningrooms") {
    pathEndOrSingleSlash {
      get {
        complete(screeningRooms.listRooms.map(_.asJson))
      }
    }
  }
}
