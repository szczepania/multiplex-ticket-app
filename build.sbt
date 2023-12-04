scalaVersion := "2.13.12"

val akkaVersion = "2.8.0"
val akkaHttpVersion = "10.5.0"
val akkaHttpTestKitVersion = "10.2.6"
val akkaTestKitVersion = "2.8.0"
val akkaHttpJsonSerializersVersion = "1.39.2"
val circeVersion = "0.14.5"
val slickVersion = "3.4.1"
val slickPgVersion = "0.21.1"
val postgresVersion = "42.5.4"
val pureconfigVersion = "0.17.4"
val scalaTestVersion = "3.2.9"

lazy val root = (project in file("."))
  .settings(
    name := "multiplex-ticket-app",
    organization := "com.szczepania",
    version := "0.0.1"
  )

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-actor-typed" % akkaVersion,
  "com.typesafe.akka" %% "akka-stream" % akkaVersion,
  "de.heikoseeberger" %% "akka-http-circe" % akkaHttpJsonSerializersVersion,
  "io.circe" %% "circe-core" % circeVersion,
  "io.circe" %% "circe-generic" % circeVersion,
  "io.circe" %% "circe-parser" % circeVersion,
  "com.typesafe.slick" %% "slick" % slickVersion,
  "org.postgresql" % "postgresql" % postgresVersion,
  "com.typesafe.slick" %% "slick-hikaricp" % slickVersion,
  "com.github.tminglei" %% "slick-pg" % slickPgVersion,
  "com.github.tminglei" %% "slick-pg_play-json" % slickPgVersion,
  "com.github.pureconfig" %% "pureconfig" % pureconfigVersion,
  "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpTestKitVersion % Test,
  "com.typesafe.akka" %% "akka-testkit" % akkaTestKitVersion % Test,
  "org.scalatest" %% "scalatest" % scalaTestVersion % Test
)
