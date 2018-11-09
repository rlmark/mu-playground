package service

import cats.effect.IO
import mu.rpc.server.{AddService, GrpcConfig, GrpcServer}
import service.implicits._
import protocol.greeter._
import mu.rpc.server._
import mu.rpc.server.implicits._
import scala.language.experimental.macros

object Main {

  def main(args: Array[String]): Unit = {

    val grpcConfigs: List[GrpcConfig] = List(
      // commenting this back in fails with a `value Greeter not found` error
      AddService(Greeter.bindService[IO])
    )

    val runServer = GrpcServer.default[IO](8080, grpcConfigs).flatMap(GrpcServer.server[IO])

    runServer.unsafeRunSync()
  }
}
