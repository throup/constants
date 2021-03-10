name := "constants"
organization := "eu.throup"
version := "0.1"

scalaVersion := "2.13.5"

libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.2.3"
libraryDependencies += "com.typesafe.scala-logging" %% "scala-logging" % "3.9.2"
libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.2"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.2" % Test
logBuffered in Test := false
testOptions in Test += Tests.Argument(TestFrameworks.ScalaTest, "-W", "120", "60")
