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

}
