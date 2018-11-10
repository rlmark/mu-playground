package common

import monix.execution.Scheduler

trait CommonRuntime {
implicit val scheduler: Scheduler = monix.execution.Scheduler.Implicits.global
}
