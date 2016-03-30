name := "music-oracle"

version := "1.0"

scalaVersion := "2.11.8"

homepage := Some(url("https://github.com/mses-bly/music-oracle"))

startYear := Some(2016)

libraryDependencies ++= Seq(
	"org.scala-lang" % "scala-library" % scalaVersion.value,
	"com.typesafe.scala-logging" %% "scala-logging" % "3.1.0",
	"org.scalatest" %% "scalatest" % "2.2.4" % "test"
)


