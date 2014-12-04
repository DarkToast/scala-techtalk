package eyecatcher

import org.scalatest.FunSuite

/**
 * @author Klaus Lutterjohann, tarent solutions GmbH
 */
class PatternMatchingSpec extends FunSuite {

  class Person
  case class EmployedWorker(name: String, company: String, boss: String) extends Person
  case class Manager(name: String, company: String) extends Person
  case class Freelancer(name: String) extends Person
  case class Unemployed(name: String) extends Person

  val max: Person = EmployedWorker("Max Mustermann", "tarent", "Olli")
  val moritz: Person = Unemployed("Moritz Mustermann")

  test("'match' is the new 'switch'") {

    def describeNumber(number: Any) = number match {
      case 4711 => "Eau de cologne"
      case 815  => "Langweilig"
      case 42   => "Die Antwort"
    }

    assert(describeNumber(42) == "Die Antwort")
    // assert on Exception...
    intercept[MatchError](describeNumber(7))
  }


  test("finally we also can match on strings!") {
    val string = "foo"

    val result = string match {
      case "foo" => 1
      case "bar" => 2
      case _     => 3
    }

    assert(result == 1)
  }


  test("Case classes can be used for switching") {

    def describePerson(someone: Person): String =
      someone match {
        case EmployedWorker(_, _, _)  => "Angestellter"
        case Manager(_, _)            => "Chef"
        case Freelancer(_)            => "Einzelkämpfer"
        case _                        => "Ein Default"
      }

    assert(describePerson(max) == "Angestellter")
    assert(describePerson(moritz) == "Ein Default")
  }


  test("Case classes can be deconstructed while matching") {

    def describePerson(someone: Person): String =
      someone match {
        case EmployedWorker(name, firma, _) => name + " ist angestellt bei " + firma
        case Manager(name, firma)           => s"$name ist leitender Angestellter in $firma"
        case Freelancer(name)               => s"$name ist ein Einzelkämpfer"
        case _                              => "Ein Default"
      }

    assert(describePerson(max) == "Max Mustermann ist angestellt bei tarent")
    assert(describePerson(moritz) == "Ein Default")
  }


  test("Collections can also be deconstructed") {
    val numbers = List(1, 2, 3)
    val strings = List("foo", "bar", "baz")

    def describeList(list: List[Any]): String = list match {
      case List(1,2,3)        => "Die Ziffern 1 bis 3"
      case List("foo", "bar") => "2 Strings"
      case List(_*)           => "Andere Elemente"
    }

    assert(describeList(numbers) == "Die Ziffern 1 bis 3")
    assert(describeList(strings) == "Andere Elemente")
  }


  test("Cases can even contain further conditions") {

    def describeNumber(n: Any) = n match {
      case n: Int if n < 0  => "negativ"
      case n: Int if n == 0 => "Null"
      case n: Int if n > 0  => "positiv"
      case n: Double        => "Kommazahl"
      case _                => "Keine Zahl"
    }

    assert(describeNumber(42) == "positiv")
    assert(describeNumber(3.14159265) == "Kommazahl")
    assert(describeNumber(max) == "Keine Zahl")
  }

}
