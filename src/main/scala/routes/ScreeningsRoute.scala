package routes

import services.ScreeningsService
import scala.concurrent.ExecutionContext
import de.heikoseeberger.akkahttpcirce.FailFastCirceSupport
import io.circe.generic.auto._
import io.circe.syntax._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import java.time.LocalDateTime

class ScreeningsRoute(screeningsService: ScreeningsService)(implicit
    executionContext: ExecutionContext
) extends FailFastCirceSupport {

  val route: Route = pathPrefix("screenings") {
    concat(
      pathEndOrSingleSlash {
        get {
          complete(screeningsService.listScreenings.map(_.asJson))
        }
      },
      path(LongNumber) { id =>
        get {
          complete(screeningsService.getScreeningById(id).map(_.asJson))
        }
      },
      path("time") {
        get {
          parameters("start".as[String], "end".as[String]) { (start, end) =>
            complete(
              screeningsService
                .listMoviesAndRoomsInTimeInterval(
                  LocalDateTime.parse(start),
                  LocalDateTime.parse(end)
                )
                .map(_.asJson)
            )
          }
        }
      }
    )
  }
}
