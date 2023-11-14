import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import config.Config
import connection.DatabaseConnector

import scala.concurrent.{ExecutionContext, Future}
import routes.Routes
import services.{MoviesService, ScreeningRoomsService, ScreeningsService, SeatsService}

import scala.io.StdIn
import services.ReservationsService

object Main {

  implicit val system: ActorSystem = ActorSystem()
  implicit val executor: ExecutionContext = system.dispatcher

  val config = new Config()
  val dbConnector = new DatabaseConnector()

  val moviesService = new MoviesService(dbConnector)
  val screeningRoomsService = new ScreeningRoomsService(dbConnector)
  val screeningsService = new ScreeningsService(dbConnector)
  val seatsService = new SeatsService(dbConnector)
  val reservationsService = new ReservationsService(dbConnector, screeningsService, seatsService)

  val routes = new Routes(moviesService, screeningRoomsService, screeningsService, seatsService, reservationsService)

  val bind: Future[Http.ServerBinding] =
    Http().newServerAt(config.host, config.port).bind(routes.route)

  def main(args: Array[String]): Unit = {
    StdIn.readLine()

    for {
      binding <- bind
      _ <- binding.unbind()
    } yield system.terminate()
  }
}
