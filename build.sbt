

val log4j = "log4j" % "log4j" % "1.2.17" % "provided"

lazy val commonSettings = Seq(
  organization := "com.ftel",
  version := "0.1.0-SNAPSHOT",
  scalaVersion := "2.11.7"
)

lazy val root = (project in file("."))
  .settings(
    commonSettings,
    name := "slack-integration",
    libraryDependencies ++= Seq(
      "com.github.gilbertw1" %% "slack-scala-client" % "0.2.1",
      log4j,
      "com.typesafe.akka" % "akka-http_2.11" % "10.0.8"
    )
  )

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case x => MergeStrategy.first
}

assemblyExcludedJars in assembly := {
  val cp = (fullClasspath in assembly).value
  cp filter {_.data.getName == "scala-library.jar"}
  cp filter {_.data.getName == "junit-3.8.1.jar"}
}

resolvers += Resolver.mavenLocal