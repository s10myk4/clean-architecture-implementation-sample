name := "clean-architecture-implementation-sample"
organization := "com.10myk4"

version := "1.0-SNAPSHOT"

scalaVersion := "2.12.8"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8", "-Xlint")

scalacOptions ++= Seq(
  "-unchecked",
  "-deprecation",
  "-feature",
  "-language:reflectiveCalls",
  "-language:implicitConversions",
  "-Ypartial-unification",
)

scalacOptions in Test ++= Seq("-Yrangepos")

scalafmtConfig in ThisBuild := file(".scalafmt.conf")

scalafmtOnCompile := true

lazy val root = (project in file("."))
  .enablePlugins(PlayScala)
  .disablePlugins(PlayLayoutPlugin)
  .settings(
    libraryDependencies ++= Seq(
      "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % Test,
      "org.scalatest" %% "scalatest" % "3.0.4" % Test,
      "org.scalacheck" %% "scalacheck" % "1.14.0" % Test,
      "org.mockito" % "mockito-core" % "2.10.0" % Test,
      "org.scalaz" %% "scalaz-core" % "7.2.16",
      "org.scalactic" %% "scalactic" % "3.0.4",
      "org.typelevel" %% "cats-core" % "1.6.0",
      "org.typelevel" %% "cats-effect" % "1.3.0",
      "io.circe" %% "circe-core" % "0.10.0",
      "io.circe" %% "circe-generic" % "0.10.0",
      "io.circe" %% "circe-parser" % "0.10.0"
    ),
    resolvers ++= Seq(
      "Sonatype OSS Releases" at "http://oss.sonatype.org/content/repositories/releases/",
      "Atlassian" at "https://maven.atlassian.com/content/repositories/atlassian-public/",
      "SO release" at "https://tool.devsep.com/mvn/release",
      "SO snapshot" at "https://tool.devsep.com/mvn/snapshot"
    ),
    addCompilerPlugin("org.spire-math" %% "kind-projector" % "0.9.4")
  )
