package option

import option.model.User

object ScalaBillCalculator {

  /**
   * We can use Option and closures!
   */
  def calculateBillForUser_2(user: User): Int = {
    Option(user).
      flatMap(user => Option(user.getContract)).
      flatMap(contract => Option(contract.getTariff)).
      map(tariff => tariff.basicFee).
      getOrElse(0)
  }

  /**
   * Also we can use the for comprehension style
   */
  def calculateBillForUser_3(user: User): Int = {
    val mayTariff: Option[Int] = for {
        mayUser <- Option(user)
        contract <- Option(mayUser.getContract)
        tariff <- Option(contract.getTariff)
    } yield tariff.basicFee

    mayTariff.getOrElse(0)
  }
}
