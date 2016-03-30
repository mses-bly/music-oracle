name := "music-oracle"

version := "1.0"

scalaVersion := "2.11.8"

homepage := Some(url("https://github.com/mses-bly/music-oracle"))

startYear := Some(2016)

libraryDependencies ++= Seq(
	"org.scala-lang" % "scala-library" % scalaVersion.value,
	"org.slf4j" % "slf4j-api" % "1.7.19",
	"org.scalatest" %% "scalatest" % "2.2.4" % "test"
)


