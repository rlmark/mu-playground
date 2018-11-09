package client

import cats.effect.IO
import monix.eval.Task
import mu.rpc.{ChannelFor, ChannelForAddress}
import mu.rpc.client._
import mu.rpc.client.implicits._
import protocol.greeter._

class Implicits {
  // Note, this needs to change in the documentation
  val channelFor: ChannelFor = ChannelForAddress("localhost", 9090)
//  implicit val serviceClient: Greeter.Client[Task]
}

object implicits extends Implicits
