package config

import pureconfig._
import pureconfig.error.ConfigReaderException
import pureconfig.generic.ProductHint
import pureconfig.generic.auto._

class Config {
  implicit def hint[A]: ProductHint[A] =
    ProductHint[A](ConfigFieldMapping(CamelCase, CamelCase))

  private val serviceConfig =
    ConfigSource.default.at("multiplexTicketApp").load[ServiceConf]

  private val extractConfig = serviceConfig.left
    .map(err => new ConfigReaderException[Config](err))
    .toTry

  private val httpConnectionConfig = extractConfig.get.httpConnection
  val host = httpConnectionConfig.host
  val port = httpConnectionConfig.port

  private val reservationConfig = extractConfig.get.reservation

  val prices = reservationConfig.prices

  val bookingTimeInMinutes = reservationConfig.bookingTimeInMinutes
}

final case class ServiceConf(
    httpConnection: HttpConnection,
    reservation: ReservationConfig
)

final case class ReservationConfig(bookingTimeInMinutes: Int, prices: Prices)

final case class HttpConnection(host: String, port: Int)

final case class Prices(adult: String, child: String, student: String)
