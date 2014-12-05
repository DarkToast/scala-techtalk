package eyecatcher

import org.scalatest.FunSuite

/**
 * @author Klaus Lutterjohann, tarent solutions GmbH
 */
class CaseClassSpec extends FunSuite {

  case class Employee(name: String, salary: BigInt, jobTitle: String)

  test("Case classes are the new POJO: Immutable and without boilerplate code") {
    val maxMustermann = Employee("Max Mustermann", 2000, "Hausmeister")

    // does not compile (read-only!):
    // maxMustermann.setName("Paul")
    // maxMustermann.name = "Paul"

    assert(maxMustermann.jobTitle == "Hausmeister")
  }

}
