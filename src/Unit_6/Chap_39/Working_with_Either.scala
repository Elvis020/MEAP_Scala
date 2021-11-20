package Unit_6.Chap_39

/**
 * Project: Scala_with_Daniela
 * Package: Unit_6.Chap_39
 *
 * User: elvis 😎😎
 * Date: 20/11/2021
 * Time: 18:16
 *
 * Happy Coding
 */
object Working_with_Either extends App{
  // Retrieving an Either value
  case class AnotherPass(score:Int){
    require(score>=60 & score <=100,"Invalid pass: score must be between 60 and 100")
  }
  def mark(score:Int,msg:Option[String]=None) = {
    if(score>= 60) Right(score)
    else Left(msg.getOrElse("Score below 60"))
  }

  // Getting a message preview
  def getPreview(outcome:Either[String,AnotherPass]) =
    outcome.left.getOrElse("You passed the exam, well done") // If the outcome is left

  /*
    When retrieving values from Either, you need to indicate which side you are doing it, and this is done using .left and .right and then you can use the .getOrElse to retrieve it
   */

  val ex_1 = Right(23).getOrElse(0)
  println(ex_1)

  val ex_2 = Left("hello").left.getOrElse("scala")
  println(ex_2)

  val ex_3 = Right(21).left.getOrElse("scala")
  println(ex_3)

  val ex_4 = Right(99).getOrElse{println("Getting value");0} // It doesn't evaluate expression after getting the value for the right projection
  println(ex_4)

  val ex_5 = Left("Oii").getOrElse{println("generating default.....");0} // It evaluates expression
  println(ex_5)


  println()
  // A function returns a value wrapped in its right projection or zero
  def getOrZero(e:Either[String,Double]) =e.getOrElse(0.0)

  println(getOrZero(Right(23)))
  println(getOrZero(Left("hello")))

  /* Properties of an either value  */

  // A function to check if the outcome is AnotherPass
  def isPass(outcome:Either[String,AnotherPass]): Boolean = outcome.isRight

  // A function to check if an outcome is distinct, i.e the score is >=80
  def isDistinct(outcome:Either[String,AnotherPass])= outcome.exists(_.score >= 80)

  println()

  println(Right(90).isLeft)
  println(Left("OK").isLeft)

  // Using exists
  val w:Either[String,Int] = Left("hello")

  println(w.exists(_ > 0)) // false
  println(w.left.exists(_ == "Hello".toLowerCase)) // true


  // A function that returns true if it contains a double bigger than 0
  def isPositive(item:Either[String,Double]): Boolean = item.exists(_ > 0)

  println()
  println(isPositive(Right(0)))
  println(isPositive(Right(23.0)))

  // A function that returns true if the given text is Scala
  def isScala(value:Either[String,Int]): Boolean = value.left.exists(_ == "Scala,")

  println(isScala(Left("OK")))
  println(isScala(Right(32)))
  println(isScala(Left("Scala,")))


}