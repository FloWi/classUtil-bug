lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.example",
      scalaVersion := "2.12.6"
    )),
    name := "scalatest-example"
  )

Test / fork := true

libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % Test
libraryDependencies ++= List(
  "org.clapper" %% "classutil" % "1.3.0",
  "org.ow2.asm" % "asm" % "6.1.1",
  "org.ow2.asm" % "asm-commons" % "6.1.1",
  "org.ow2.asm" % "asm-util" % "6.1.1",
  "org.clapper" %% "grizzled-scala" % "4.4.2",
  "org.clapper" %% "grizzled-slf4j" % "1.3.2",
  "org.slf4j" % "slf4j-api" % "1.7.+",
  "ch.qos.logback" % "logback-core" % "1.2.3",
  "ch.qos.logback" % "logback-classic" % "1.2.3"
)

//libraryDependencies += "io.swagger.parser.v3" % "swagger-parser-v3" % "2.0.4" excludeAll ExclusionRule(organization = "org.ow2.asm")
