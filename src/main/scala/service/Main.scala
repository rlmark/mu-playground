package service

import cats.effect.IO
import mu.rpc.server.{AddService, GrpcConfig, GrpcServer}
import service.implicits._
import protocol.greeter._
import mu.rpc.server._
import mu.rpc.server.config.BuildServerFromConfig
import mu.rpc.server.implicits._

import scala.language.experimental.macros

object Main {

  def main(args: Array[String]): Unit = {

    val grpcConfigs: List[GrpcConfig] = List(
      AddService(Greeter.bindService[IO])
    )

    val runServer = for {
      server <- BuildServerFromConfig[IO]("rpc.server.port", grpcConfigs)
      _      <- GrpcServer.server[IO](server)
    } yield ()
    runServer.unsafeRunSync()
  }
}
