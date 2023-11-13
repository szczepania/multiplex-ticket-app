package services

import connection.DatabaseConnector
import model.Movies
import slick.jdbc.PostgresProfile.api._
import tables.MovieDataTable

import scala.concurrent.Future
import java.time.LocalDateTime
import scala.concurrent.ExecutionContext
import tables.ScreeningsDataTable
import model.Screenings
import java.time.LocalTime

class MoviesService(val databaseConnector: DatabaseConnector)(implicit
    executionContext: ExecutionContext
) extends MovieDataTable
    with ScreeningsDataTable {

  def listMovies: Future[Seq[Movies]] =
    databaseConnector.db.run(moviesTable.result)

  def getMovieById(id: Long): Future[Option[Movies]] = {
    val query = moviesTable.filter(_.id === id)
    databaseConnector.db.run(query.result.headOption)
  }

}
