val scala3Version = "3.2.1"
val zioVersion = "2.0.5"

lazy val root = project
  .in(file("."))
  .settings(
    name := "zio-learning",
    version := "0.1.0",
    scalaVersion := scala3Version,
    libraryDependencies ++= Seq(
      "dev.zio" %% "zio" % zioVersion,
      "dev.zio" %% "zio-streams" % zioVersion,
      "dev.zio" %% "zio-direct" % "1.0.0-RC1",
      "org.scalameta" %% "munit" % "1.0.0-M7" % Test
    )
  )
