package Unit_1.Chap_8


class Vending_Machine{
  var chocolateBar: Int = 0
  var granolaBar: Int = 0
  var totalMoney: Double = 0.0


  // An action of the customer
  def buy(product: String, money:Double): String = {
    if(!isProductAvailable(product)) s"Sorry $product is not available"
    else if(!isMoneyEnough(product,money)) s"Please insert more money!"
    else completeRequest(product,money)
  }


  // Checks to make before proceeding with the buy
  def isProductAvailable(product: String): Boolean = {
    val productQuantity: Int = {
      if(product.equals("chocolate")) chocolateBar
      else if(product.equals("granola")) granolaBar
      else 0
    }
    productQuantity > 0
  }
  def isMoneyEnough(product: String, money:Double): Boolean = {
    val cost = if(product.equals("chocolate")) 1.5 else 1
    money >= cost
  }
  def completeRequest(product: String, money:Double): String = {
    collectMoney(money)
    releaseProduct(product)
    s"There you go! Have a $product bar"

  }
  def collectMoney(money:Double): Unit = totalMoney += money
  def releaseProduct(product:String): Unit = {
    if(product.equals("chocolate")) chocolateBar -= 1
    else if(product.equals("granolaBar")) granolaBar -= 1
  }


}


object Vending_Machine_Runner extends App{
  val machine = new Vending_Machine
  val trying_to_buy = machine.buy("chocolate",1.5)

  // Adding some bars to the vending machine
  machine.chocolateBar +=2
  machine.granolaBar +=1

  val trying_to_buy_2nd_attempt = machine.buy("chocolate",1)
  val trying_to_buy_granola = machine.buy("granola",2)
//  val trying_to_buy_granola_2nd_attempt = machine.buy("granola",2)
//  println(trying_to_buy_2nd_attempt)
  println(machine.granolaBar)
  println(machine.buy("granola", 2))
  println(machine.totalMoney)
//  println(trying_to_buy_granola_2nd_attempt)
//  println(trying_to_buy_granola_2nd_attempt)


  /*
    Problems of the Vending Machine are as follows
    1. Everything is publicly accessible
    2. Vars are Evil
    3. String as a representation of Product(Use enums instead-sealed traits + objects)
    4. String as return types - use Try and Either to prevent errors and fragility
    5. Vending Machine is not configurable

   */
}
