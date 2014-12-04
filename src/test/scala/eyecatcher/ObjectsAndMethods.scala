package eyecatcher

import org.scalatest.FunSuite

class ObjectsAndMethods extends FunSuite {

  test("Every value is an object. Even booleans, ints, and so on") {
    assert(true.toString == "true")
    assert(42.toString == "42")
  }

}
