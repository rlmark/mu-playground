name := "mu-playground"

version := "0.1"

scalaVersion := "2.12.7"

resolvers += Resolver.sonatypeRepo("releases")

// Intellij seems to like this version more, but sbt still can't find the plugin
// addCompilerPlugin("org.scalamacros" % "paradise_2.12.7" % "2.1.1"  cross CrossVersion.patch) 

addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.1" cross CrossVersion.patch)

libraryDependencies := Seq(
  "org.typelevel"     %% "cats-effect"              % "1.0.0",
  "org.typelevel"     %% "cats-core"                % "1.4.0",
  "io.higherkindness" %% "mu-rpc-server"            % "0.16.0",
  "io.higherkindness" %% "mu-rpc-client-core"       % "0.16.0",
  "io.higherkindness" %% "mu-rpc-client-netty"      % "0.16.0",
  scalaVersion("org.scala-lang" % "scala-reflect" % _).value,
)

scalacOptions ++= Seq(
  "-encoding", "utf8",
  "-Xfatal-warnings",
  "-Ypartial-unification",
  "-deprecation",
  "-unchecked",
  "-language:implicitConversions",
  "-language:experimental.macros",
  "-language:higherKinds",
  "-language:existentials",
  "-language:postfixOps",
//  "-Xplugin-require:macroparadise", // Can't find the macroparadise plugin
)
