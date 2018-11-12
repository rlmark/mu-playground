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
      AddService(Greeter.bindService[IO])
    )

    val runServer2 = GrpcServer.default[IO](9090, grpcConfigs).flatMap(GrpcServer.server[IO])
    runServer2.unsafeRunSync()
  }
}
