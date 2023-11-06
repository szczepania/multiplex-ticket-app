import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.Directives._
import de.heikoseeberger.akkahttpcirce.FailFastCirceSupport
import model.{ReservationService, ReservationStorage}

import java.util.UUID

object Main extends FailFastCirceSupport {
  import io.circe.generic.auto._

  implicit val system = ActorSystem(Behaviors.empty, "Main")

  private val route: Route = (path("reservations") & post) {
    entity(as[ReservationService]) { reservation: ReservationService =>
      complete(
        ReservationStorage(
          UUID.randomUUID().toString(),
          System.currentTimeMillis()
        )
      )
    }
  }

  def main(args: Array[String]): Unit = {
    Http().newServerAt("localhost", 8081).bind(route)
  }
}
