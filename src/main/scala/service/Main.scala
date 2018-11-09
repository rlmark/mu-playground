package service

import cats.effect.IO
import mu.rpc.server.{AddService, GrpcConfig, GrpcServer}
import protocol.greeter
import service.implicits._
import mu.rpc.server._
import mu.rpc.server.implicits._

object Main {

  def main(args: Array[String]): Unit = {

    val grpcConfigs: List[GrpcConfig] = List(
      AddService(greeter.Greeter.bindService[IO])
    )

    val runServer = GrpcServer.default[IO](8080, grpcConfigs).flatMap(GrpcServer.server[IO])

    runServer.unsafeRunSync()
  }
}
