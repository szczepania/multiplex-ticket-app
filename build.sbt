scalaVersion := "2.13.12"

val akkaVersion = "2.8.0"
val akkaHttpVersion = "10.5.0"
val akkaHttpJsonSerializersVersion = "1.39.2"
val circeVersion = "0.14.5"

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
  "io.circe" %% "circe-parser" % circeVersion
)
