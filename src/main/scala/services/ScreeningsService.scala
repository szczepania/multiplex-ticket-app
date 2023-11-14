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

  def listScreeningsInTimeInterval(
      start: LocalDateTime,
      end: LocalDateTime
  ): Future[Seq[(String, LocalDateTime)]] = {

    val moviesTable = TableQuery[MoviesTable]
    val screeningsTable = TableQuery[ScreeningsTable]

    val query = moviesTable
      .join(screeningsTable)
      .on(_.id === _.movieId)
      .filter { case (_, screening) =>
        screening.screeningTime >= start && screening.screeningTime <= end
      }
      .map { case (movie, screening) => (movie.title, screening.screeningTime) }

    val sortedQuery = query.sortBy { case (title, screeningTime) =>
      (title, screeningTime)
    }

    databaseConnector.db.run(sortedQuery.result)
  }

}
