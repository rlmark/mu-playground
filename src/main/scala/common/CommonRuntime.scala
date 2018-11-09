package common

trait CommonRuntime {
implicit val scheduler = monix.execution.Scheduler.Implicits.global
}
