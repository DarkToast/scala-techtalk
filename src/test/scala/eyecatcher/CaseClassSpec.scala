package eyecatcher

import org.scalatest.FunSuite

/**
 * @author Klaus Lutterjohann, tarent solutions GmbH
 */
class CaseClassSpec extends FunSuite {

  case class Employee(name: String, salary: BigInt, jobTitle: String)

  test("Case classes are the new POJO: Immutable and without boilerplate code") {
    val maxMustermann = Employee("Max Mustermann", 2000, "Hausmeister")

    assert(maxMustermann.jobTitle == "Hausmeister")
  }

}
