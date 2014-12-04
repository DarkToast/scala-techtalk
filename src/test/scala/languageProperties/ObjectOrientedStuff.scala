package languageProperties


import org.scalatest.FunSuite

class ObjectOrientedStuff extends FunSuite {
  test("Scala knows normal classes. Wen can use them as java classes. We only have a little bit other syntax") {

    // We define in the first line our main constructor
    // The vals are public.
    class Rational(val numer: Int, val denom: Int) {

      // Additional constructor
      def this(numer: Int) {
        this(numer, 1)
      }

      def add (r: Rational): Rational = new Rational(
        r.numer * denom + numer * r.denom,
        r.denom * denom
      )

      override def toString: String = {
        s"( $numer/$denom )"
      }
    }

    val r1: Rational = new Rational(2, 4)
    val r2: Rational = r1.add(new Rational(2, 4))

    assert(r1.toString == "( 2/4 )")
    assert(r2.toString == "( 16/16 )")
  }


  test("Scala does not know of static contents. Instead we have objects") {
    object StaticService {
      def add(x: Int, y: Int) = x + y
    }

    assert( StaticService.add(3, 5) == 8 )
  }


  test("To use static contents in classes, scala knows companion objects") {
    class CalculateValue(private val value: Int)

    object CalculateValue {
      def add(x: Int, y: Int) = x + y

      def add(x: CalculateValue, y: CalculateValue) = x.value + y.value
    }

    val x = new CalculateValue(3)
    val y = new CalculateValue(5)

    assert( CalculateValue.add(x, y) == 8 )
  }


  test("Scala knows Traits for multiple mixins") {
    trait Add {
      def add(x: Int, y: Int) = x + y
    }

    trait Print {
      def print(msg: String): String = s"Message: $msg"
    }

    class Example extends Add with Print {
      def sub(x: Int, y: Int) = add(x, -y)
    }

    assert( new Example().sub(5, 2) == 3)
    assert( new Example().print("Hallo Welt") == "Message: Hallo Welt")
  }

}
