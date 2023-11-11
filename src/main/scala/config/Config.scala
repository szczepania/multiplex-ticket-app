package config

import pureconfig._
import pureconfig.error.ConfigReaderException
import pureconfig.generic.ProductHint
import pureconfig.generic.auto._

class Config {
  implicit def hint[A]: ProductHint[A] =
    ProductHint[A](ConfigFieldMapping(CamelCase, CamelCase))

  val serviceConfig =
    ConfigSource.default.at("multiplexTicketApp").load[ServiceConf]

  val extractConfig = serviceConfig.left
    .map(err => new ConfigReaderException[Config](err))
    .toTry

  val host = extractConfig.get.httpConnection.host
  val port = extractConfig.get.httpConnection.port
}

case class ServiceConf(httpConnection: HttpConnection)

case class HttpConnection(host: String, port: Int)
