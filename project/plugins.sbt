logLevel := Level.Warn

resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"
resolvers += "Sonatype OSS Releases" at "https://oss.sonatype.org/content/repositories/releases/"
resolvers += "Typesafe Snapshots" at "http://repo.typesafe.com/typesafe/snapshots/"
resolvers += "Flyway" at "https://flywaydb.org/repo"
resolvers += "Atlassian Releases" at "https://maven.atlassian.com/public/"
resolvers += "Artima Maven Repository" at "http://repo.artima.com/releases"

addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.6.10")

addSbtPlugin("com.lucidchart" % "sbt-scalafmt" % "1.12")
