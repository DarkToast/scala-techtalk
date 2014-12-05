package languageProperties

import org.scalatest.FunSuite

class Functions extends FunSuite {

  test("Functions are first class citizens") {
    val add: (Int, Int) => Int = (x: Int, y: Int) => x + y
    val add2 = add

    assert( add(2, 3) == 5 )
    assert( add2(10, 6) == 16 )
  }


  test("We can pass a function to a higher order function") {
    def transform(x: Int, rule: Int => Int): Int = rule(x)
    val myRule = (x: Int) => x * x

    assert( transform(10, myRule) == 100 )
  }


  test("Let us combine this features :-)") {
    def combine(f: (Int) => Int, g: (Int) => Int): (Int) => Int = {
      (x: Int) => g(f(x))
    }

    val multiply = (x: Int) => x * x
    val divideByTwo = (x: Int) => x / 2

    val combination = combine(multiply, divideByTwo)

    assert( combination(10) == 50 )

    // Functions already have their own compose method
    assert(  divideByTwo.compose(multiply)(10) == 50  )
  }


  test("An often seen use case is to use functions as lambda expressions") {
    val list = List(1, 2, 3, 4, 5)
    val newList = list.filter( x => x % 2 == 0).map(x => x * x)

    assert( newList.length == 2 )

    // Scala Lists are immutable. Each modification results in a new list object
    assert( list.length == 5 )
  }
}
