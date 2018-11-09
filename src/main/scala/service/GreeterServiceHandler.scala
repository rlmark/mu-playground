package service

import cats.effect.Async
import monix.execution.Scheduler
import monix.reactive.Observable
import mu.rpc.protocol.Empty
import protocol.greeter._

class GreeterServiceHandler[F[_]: Async](implicit S: Scheduler) extends Greeter[F] {
  override def sayHello(request: HelloRequest): F[HelloResponse] = Async[F].pure(HelloResponse("Hi there!"))

  override def emptyResponse(request: HelloRequest): F[Empty.type] = Async[F].pure(Empty)

  override def emptyRequest(request: Empty.type): F[HelloResponse] = Async[F].pure(HelloResponse("Hello response!"))

  override def emptyRequestResponse(request: Empty.type): F[Empty.type] = Async[F].pure(Empty)

  override def lotsOfReplies(request: HelloRequest): Observable[HelloResponse] =
    Observable.fromIterable(1 to 3 map {i =>
      Thread.sleep(1000)
      HelloResponse(s"Hello #$i ${request.name}")}
    )

  override def lotsOfGreetings(request: Observable[HelloRequest]): F[HelloResponse] =
    request.foldLeftL((0, HelloResponse(""))) {
      case ((i, response), currentRequest) =>
        (i+1, response.copy(message = s"${response.message} \n Request ${currentRequest.name} -> Response: Reply $i"))
    }
      .map( _._2)
      .to[F]

  override def bidirectionalHello(request: Observable[HelloRequest]): Observable[HelloResponse] = {
    request.flatMap{r: HelloRequest =>
      println(s"receiving request: ${r.name}")
      Observable.now(HelloResponse("HI"))
    }
  }
}
