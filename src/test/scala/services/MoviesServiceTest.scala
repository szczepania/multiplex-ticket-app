package services

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import scala.concurrent.ExecutionContext.Implicits.global
import connection.DatabaseConnector

class MoviesServiceTest extends AnyFlatSpec with Matchers {
  val databaseConnector = new DatabaseConnector()
  val moviesService = new MoviesService(databaseConnector)

  "MoviesService" should "list all movies" in {
    val result = moviesService.listMovies

    result.map { movies =>
      movies should not be empty
    }
  }

  it should "get a movie by id" in {
    val result = moviesService.getMovieById(1)

    result.map { movie =>
      movie should not be empty
    }
  }
}
