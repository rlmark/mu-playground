package service

import common.CommonRuntime
import mu.rpc.server.handlers._
import mu.rpc.server.implicits._
import protocol.greeter._
import cats.effect.IO


sealed trait ServerImplicits extends CommonRuntime {
  implicit val greeterServiceHandler: GreeterServiceHandler[IO] = new GreeterServiceHandler[IO]
}

object implicits extends ServerImplicits
