import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import config.Config
import connection.DatabaseConnector

import scala.concurrent.{ExecutionContext, Future}
import routes.Routes
import services.{MoviesService, ScreeningRoomsService}

import scala.io.StdIn

object Main {

  implicit val system: ActorSystem = ActorSystem()
  implicit val executor: ExecutionContext = system.dispatcher

  val dbConnector = new DatabaseConnector()
  val moviesService = new MoviesService(dbConnector)
  val screeningRoomsService = new ScreeningRoomsService(dbConnector)
  val routes = new Routes(moviesService, screeningRoomsService)
  val config = new Config()

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
