name := "Play_DB"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache,
  "mysql" % "mysql-connector-java" % "5.1.22",
  "com.typesafe.slick" %% "slick" % "2.1.0",
  "org.slf4j" % "slf4j-nop" % "1.6.4"
)     

play.Project.playScalaSettings
