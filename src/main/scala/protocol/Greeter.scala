package protocol

import monix.reactive.Observable
import mu.rpc.protocol._

@message
case class Person(name: String, id: Int, has_ponycoptor: Boolean)

object greeter {
  @message
  case class HelloRequest(name: String)

  @message
  case class HelloResponse(message: String)

  @service(Protobuf)
  trait Greeter[F[_]] {
    // unary
    def sayHello(request: HelloRequest): F[HelloResponse]

    def emptyResponse(request: HelloRequest): F[Empty.type]

    def emptyRequest(request: Empty.type): F[HelloResponse]

    def emptyRequestResponse(request: Empty.type): F[Empty.type]

    // streaming
    def lotsOfReplies(request: HelloRequest): Observable[HelloResponse]

    def lotsOfGreetings(request: Observable[HelloRequest]): F[HelloResponse]

    def bidirectionalHello(request: Observable[HelloRequest]): Observable[HelloResponse]
  }
}
