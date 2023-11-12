package services

import connection.DatabaseConnector
import model.Movies
import slick.jdbc.PostgresProfile.api._
import tables.MovieDataTable

import scala.concurrent.Future

class MoviesService(val databaseConnector: DatabaseConnector)
    extends MovieDataTable {

  def listMovies: Future[Seq[Movies]] =
    databaseConnector.db.run(moviesTable.result)

  def getMovieById(id: Long): Future[Option[Movies]] = {
    val query = moviesTable.filter(_.id === id)
    databaseConnector.db.run(query.result.headOption)
  }
}
