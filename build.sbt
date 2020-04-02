name := "QA-Cinema"
 
version := "0.1"

lazy val `qa-cinema` = (project in file(".")).enablePlugins(PlayScala)

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"
      
resolvers += "Akka Snapshot Repository" at "https://repo.akka.io/snapshots/"
      
scalaVersion := "2.12.2"

libraryDependencies ++= Seq( jdbc , ehcache , ws , specs2 % Test , guice,
  "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test,
  "org.mockito" % "mockito-core" % "2.7.22" % Test,
  "org.reactivemongo" %% "play2-reactivemongo" % "0.20.3-play27",
  "org.slf4j" % "slf4j-api" % "1.7.25",
  "codes.reactive" %% "scala-time" % "0.4.2",
  "org.scalatest" %% "scalatest" % "3.0.8" % Test
)

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )
