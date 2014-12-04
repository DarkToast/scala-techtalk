package languageProperties

import org.scalatest.FunSuite

class ObjectsAndMethods extends FunSuite {

  test("Every value is an object. Even booleans, ints, and so on") {
    assert(  true.toString == "true"  )
    assert(  42.toString == "42"  )
  }

  test("Every operator is just a method of its object. Method names can be of every character") {
    assert(  true.!=(false)  )
    assert(  42.+(12) == 54  )
  }

  test("Scala can have a verbose or short syntax") {
    assert(  42.+(12) == 42 + 12  )
    assert(  List(1, 2).drop(0) == (List(1, 2) drop 0)  )
  }
}
