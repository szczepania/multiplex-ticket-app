package connection

import slick.jdbc.PostgresProfile.api._

class DatabaseConnector {

  val db = Database.forConfig("multiplexTicketApp.postgres")

  db.createSession()
}
