package client
import client.implicits._
import monix.eval.Task
import protocol.greeter.{HelloRequest, HelloResponse}

import scala.concurrent.Await
import scala.concurrent.duration.Duration
import service._

object RPCDemoApp {

  def main(args: Array[String]): Unit = {
    val helloService: Task[HelloResponse] = serviceClient.sayHello(HelloRequest("Hi there"))
    val hello = helloService.flatMap { res =>
      Task.eval(println(res))
    }

    Await.result(hello.runAsync, Duration.Inf)
  }
}
