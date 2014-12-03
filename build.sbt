name := "scala-techtalk"

version := "0.1.0-SNAPSHOT"

organization := "de.tarent"

scalaVersion := "2.11.4"

scalacOptions ++= Seq("-Xmax-classfile-name", "128", "-deprecation","-unchecked")

libraryDependencies += "org.scala-lang.modules" %% "scala-async" % "0.9.1"

libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.3.2"

libraryDependencies += "commons-io" % "commons-io" % "2.4"

libraryDependencies += "org.scalatest" %% "scalatest" % "2.1.3" % "test"

libraryDependencies += "org.mockito" % "mockito-core" % "1.9.5" % "test"

libraryDependencies += "org.specs2" %% "specs2" % "2.3.11" % "test"

libraryDependencies += "org.scalacheck" %% "scalacheck" % "1.11.3" % "test"

libraryDependencies += "javax.inject" % "javax.inject" % "1"

resolvers ++= Seq("snapshots" at "http://oss.sonatype.org/content/repositories/snapshots",
                  "releases"  at "http://oss.sonatype.org/content/repositories/releases")

lazy val root =
  project.in( file(".") )

