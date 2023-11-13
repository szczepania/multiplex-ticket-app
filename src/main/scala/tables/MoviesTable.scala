package tables

import connection.DatabaseConnector
import model.Movies
import slick.jdbc.PostgresProfile.api._

import java.time.LocalTime

trait MovieDataTable {

  val databaseConnector: DatabaseConnector

  class MoviesTable(tag: Tag) extends Table[Movies](tag, "movies") {

    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)

    def title = column[String]("title")

    override def * = (id, title).<>(Movies.tupled, Movies.unapply)

  }

  lazy val moviesTable = TableQuery[MoviesTable]

}
