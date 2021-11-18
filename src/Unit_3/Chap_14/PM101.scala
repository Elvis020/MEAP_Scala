package Unit_3.Chap_14
/*
   Note that everytime you see _, you are trying to
   tell the compiler, that its not relevant
 */

object PM101 extends App{
  // PM vrs if-else

  // Using if-else
  def dayOfTheWeek(n:Int):String = {
    if(n.equals(1)) "Sunday"
    else if(n.equals(2)) "Monday"
    else if(n.equals(3)) "Tuesday"
    else if(n.equals(4)) "Wednesday"
    else if(n.equals(5)) "Thursday"
    else if(n.equals(6)) "Friday"
    else if(n.equals(7)) "Saturday"
    else "Unknown"
  }


  // Using PM
  def dayOfWeek(n:Int): String = n match{
    case 1 => "Sunday"
    case 2 => "Monday"
    case 3 => "Tuesday"
    case 4 => "Wednesday"
    case 5 => "Thursday"
    case 6 => "Friday"
    case 7 => "Saturday"
    case _ => "Unknown"
  }

  def objInfo(param:Any): String = param match {
    case n:Int if(n>0)=> s"$n is a positive integer"
    case d:Double => s"$d is a double"
    case "ping" => "pong"
    case _:String => s"You gave me a String"
    case obj => obj.toString
  }

  println(objInfo(-1))
  println(objInfo(true))
  println(objInfo(200))
  println(objInfo(200.00))
  println(objInfo("ping"))

  // Sealed Pattern Matching
  sealed trait Currency

  object USD extends Currency
  object GBP extends Currency
  object EUR extends Currency
  object CAD extends Currency

  // A function that takes in a currency and returns the exchange rate
  def exchangeRateUSD(currency: Currency): Double = currency match{
    case USD => 1
    case GBP => 0.744
    case EUR => 0.848
    case CAD  => 0.5
  }

  // Price Discount using PM
  def priceDiscounter(price:Int): Double = price match{
    case 50 => 50.toDouble
    case p:Int if(p>50 && p<100) => (0.1*p) + p
    case h:Int if(h>=100) => (0.15*h)+h
    case _ => 0.0
  }
  println(exchangeRateUSD(CAD))

  println(priceDiscounter(24))
  println(priceDiscounter(140))
  println(priceDiscounter(50))


}
