package languageProperties

import org.scalatest.FunSuite

class Basics extends FunSuite {

  test("Scala knows mutable variables like normal java references") {
    var variable: String = "Hallo Welt"
    assert( variable == "Hallo Welt" )

    variable = "Hello World"
    assert( variable == "Hello World" )
  }

  test("Scala knows immutable values like final references in java") {
    val variable: String = "Hallo Welt"
    assert( variable == "Hallo Welt" )

    // Won't compile
    //variable = "Hello World"
    assert( variable == "Hallo Welt" )
  }

  test("Scala even knows immutable lazy values, which are evaluated on first read") {
    lazy val variable: String = {
      println("Hallo Welt")
      "Foobar"
    }

    assert( variable == "Foobar" )  // Should print "Hallo Welt"
    assert( variable == "Foobar" )
  }

}
