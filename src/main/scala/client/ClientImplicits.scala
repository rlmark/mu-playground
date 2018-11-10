package client

import common.CommonRuntime
import monix.eval.Task
import mu.rpc.config._
import mu.rpc.client._
import mu.rpc.client.implicits._
import mu.rpc.client.config.ConfigForAddress
import protocol.greeter._
import scala.concurrent.duration.Duration

// Note: base configuration of the project seems to be lacking the config being brought in transitively
trait ClientImplicits extends CommonRuntime {
  // Note: this needs to change in the documentation, the documentation needs to specify that these come from
  // config and talk about setting those up, or the service and the default config values for the client need to be the same.
  val channelFor = ConfigForAddress[Task]("rpc.client.host", "rpc.client.port").runSyncUnsafe(Duration.Inf)
  implicit val serviceClient = Greeter.client[Task](channelFor)
}

object implicits extends ClientImplicits
