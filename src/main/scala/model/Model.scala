package model

import java.time.LocalTime

final case class Movies (id: Long, title: String, duration: LocalTime)

final case class ScreeningRooms (id: Long, name: String, rows: Int, columns: Int)
