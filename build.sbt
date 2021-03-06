val Http4sVersion = "0.18.9"
val Specs2Version = "4.1.0"
val LogbackVersion = "1.2.3"
val ScalaCheckVersion = "1.14.0"
val NscalaTimeVersion = "2.20.0"
val DoobieVersion = "0.5.3"
val TypesafeVersion = "1.3.1"

lazy val root = (project in file("."))
    .settings(
        organization := "io.skyhills",
        name := "jaguar-scala",
        version := "0.0.1-SNAPSHOT",
        scalaVersion := "2.12.6",
        libraryDependencies ++= Seq(
            "com.typesafe" % "config" % TypesafeVersion,
            "org.http4s" %% "http4s-blaze-server" % Http4sVersion,
            "org.http4s" %% "http4s-circe" % Http4sVersion,
            "org.http4s" %% "http4s-dsl" % Http4sVersion,
            "io.circe" %% "circe-generic" % "0.9.3",
            "io.circe" %% "circe-literal" % "0.9.3",
            "org.specs2" %% "specs2-core" % Specs2Version % Test,
            "ch.qos.logback" % "logback-classic" % LogbackVersion,
            "org.specs2" %% "specs2-scalacheck" % Specs2Version % Test,
            "org.scalacheck" %% "scalacheck" % ScalaCheckVersion % Test,
            "com.github.nscala-time" %% "nscala-time" % NscalaTimeVersion,
            "org.tpolecat" %% "doobie-core" % DoobieVersion,
            "org.tpolecat" %% "doobie-postgres" % DoobieVersion,
            "org.tpolecat" %% "doobie-hikari" % DoobieVersion
        )
    )

