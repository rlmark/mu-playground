name := "mu-playground"

version := "0.1"

scalaVersion := "2.12.7"

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
  "-Xplugin-require:macroparadise", 
)

resolvers += Resolver.sonatypeRepo("releases")
addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full)
