package eyecatcher

import org.scalatest.FunSuite
import scala.concurrent.{Promise, Await, Future}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration

class FutureSpec extends FunSuite {

  test("We can use the Future object to make simple asynchronous calculations") {
    val list: List[Int] = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

    println("Future will start soon")
    val myFirstFuture: Future[Int] = Future {
      var acc = 0
      list.foreach { elem =>
        println(s"Processing value: $elem")
        Thread.sleep(500)
        acc = acc + elem
      }
      acc
    }

    println("Now we wait...")
    val result: Int = Await.result(myFirstFuture, Duration.Inf)

    assert(  result == 10*11/2  )
  }


  test("We add a handler to our future for non blocking result handling.") {
    def divideAsynchronous(x: Int, y: Int): Future[Int] = {
      Future {
        println("Future startet..")
        Thread.sleep(2000)
        println("Future ended")
        x / y
      }
    }

    val f = divideAsynchronous(10, 15)
    f.onSuccess {
      case result: Int => assert( result == 25 )
    }
    f.onFailure {
      case exception: Exception => println(s"An exception occured: ${exception.getMessage}")
    }

    // An await.result awaits the result, but NOT the callback!
    // To await the callback, we can use Promises. At this point, we just block
    Thread.sleep(3000)
  }


  test("We can use Promises to synchronize callbacks with our main thread") {
    val promise: Promise[Int] = Promise[Int]()

    val future = Future {
      println("Future")
      Thread.sleep(1000)
      42
    }

    future.onSuccess {
      case result: Int => {
        println("Success handler")
        Thread.sleep(1000)
        println(s"result: $result")
        promise.success(result + 10)
      }
    }

    val handlerResult = Await.result(promise.future, Duration.Inf)
    assert(  handlerResult == 52)
  }
}
