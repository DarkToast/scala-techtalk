package pittfalls.concurrency

import scala.concurrent.{Await, Future}
import scala.concurrent.duration.Duration
import scala.concurrent.ExecutionContext.Implicits.global


object ScalaFuture extends App {
  val future: Future[String] = Future {
    Thread.sleep(4000)
    "I run in an own scala future, so I am!"
  }

  future.onSuccess {
    case s: String => println(s"The future value is: '$s'")
  }

  // Hint: An await.result awaits the result, but NOT the callback!
  // To synchronize callbacks with other threads we can use promises
  System.out.println("Let's await our future")
  Await.ready(future, Duration.Inf)
  // Wait hard for the callback
  Thread.sleep(500)
}
