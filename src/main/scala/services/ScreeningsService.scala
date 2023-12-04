package services

import connection.DatabaseConnector
import slick.jdbc.PostgresProfile.api._
import tables.ScreeningsDataTable
import scala.concurrent.Future
import java.time.LocalDateTime
import model.Screenings

class ScreeningsService(val databaseConnector: DatabaseConnector)
    extends ScreeningsDataTable {

  def listScreenings: Future[Seq[Screenings]] =
    databaseConnector.db.run(screeningsTable.result)

  def getScreeningById(id: Long): Future[Option[Screenings]] =
    databaseConnector.db.run(
      screeningsTable.filter(_.id === id).result.headOption
    )

  def listMoviesAndRoomsInTimeInterval(
      start: LocalDateTime,
      end: LocalDateTime
  ): Future[Seq[(String, String, LocalDateTime)]] = {
    val moviesTable = TableQuery[MoviesTable]
    val screeningsTable = TableQuery[ScreeningsTable]
    val screeningsRoomTable = TableQuery[ScreeningRoomsTable]

    val query = moviesTable
      .join(screeningsTable)
      .on(_.id === _.movieId)
      .join(screeningsRoomTable)
      .on(_._2.screeningRoomId === _.id)
      .filter { case ((_, screening), _) =>
        screening.screeningTime >= start && screening.screeningTime <= end
      }
      .map { case ((movie, screening), screeningRoom) =>
        (movie.title, screeningRoom.name, screening.screeningTime)
      }

    val sortedQuery = query.sortBy { case (title, name, screeningTime) =>
      (title, name, screeningTime)
    }

    databaseConnector.db.run(sortedQuery.result)
  }
}
