package eyecatcher

import org.scalatest.{GivenWhenThen, FeatureSpec, WordSpec, FlatSpec}

/**
 * @author Klaus Lutterjohann, tarent solutions GmbH
 */
class ForComprehensionSpec extends FeatureSpec with GivenWhenThen {

  info("For comprehensions are far more than loops")

  feature("Looping") {

    scenario("Classical loop behaviour") {
      Given("A stringbuilder")
      val sb = new StringBuilder

      When("We repeat 'x' 10 times")
      for (i <- 1 to 10) {
        sb.append("x")
      }

      Then("We have 10 times 'x'")
      assert(  sb.toString() == "xxxxxxxxxx")
    }

    scenario("List iteration") {
      Given("Some object iterator")
      val list = List("a", "b", "c")

      And("A counter")
      var size = 0

      When("We want to determine size really old-school style")
      for (c <- list) {
        size = size + 1
      }

      Then("We can do that")
      assert(  size == 3  )
    }
  }

  feature("More advanced iteration") {

    scenario("Filtering") {
      When("We set any filter on the source")
      val result = for (
        i <- List(2, -2, 42, -3, -5, 4711) if i > 0
      ) yield i

      Then("We can do poor man's grep")
      assert(  result == List(2,42,4711)  )
    }

    scenario("Combination") {
      Given("Two lists")
      val twos = List(2,2)
      val threes = List(3,3,3)

      When("We iterate over both lists")
      val result = for (
        two <- twos;
        three <- threes
      ) yield two*three

      Then("We have combined them")
      assert(  result == List(6,6,6, 6,6,6)  )
    }

  }

  feature("Iterate not only over lists") {

    scenario("Option chaining") {
      Given("An operations that may or may not succeed")
      val aValue: Option[Double] = Some(2)

      And("Other operations depending on an actual value")
      def double(i: Double) = i * 2
      def inverse(i: Double): Option[Double] = if (i == 0) None else Some(1/i)

      When("We chain those operations in a for comprehension")
      val result: Option[Double] = for (
        assumeSomeNumber <- aValue;
        step1 = double(assumeSomeNumber);
        step2 <- inverse(step1)
      ) yield step2

      Then("We have effectively mapped the initial option to one with the final value")
      assert(  result == Some(0.25) )
    }

  }
}
