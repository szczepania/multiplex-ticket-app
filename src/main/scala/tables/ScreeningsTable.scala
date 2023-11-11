package tables

import connection.DatabaseConnector
import model.Screenings
import slick.jdbc.PostgresProfile.api._
import slick.lifted.TableQuery
import tables.{MovieDataTable, ScreeningRoomsDataTable}
import java.time.LocalTime

trait ScreeningsDataTable extends MovieDataTable with ScreeningRoomsDataTable {

  val databaseConnector: DatabaseConnector

  class ScreeningsTable(tag: Tag) extends Table[Screenings](tag, "screenings") {

    val screeningRoomsTable = TableQuery[ScreeningRoomsTable]
    val moviesTable = TableQuery[MoviesTable]

    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)

    def movieId = column[Long]("movie_id")

    def screeningRoomId = column[Long]("screening_room_id")

    def screeningTime = column[LocalTime]("screening_time")

    override def * =
      (id, movieId, screeningRoomId, screeningTime).<>(
        Screenings.tupled,
        Screenings.unapply
      )

  }

  lazy val screeningsTable = TableQuery[ScreeningsTable]

}
