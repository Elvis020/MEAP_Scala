package Unit_6.Chap_38

/**
 * Project: Scala_with_Daniela
 * Package: Unit_6.Chap_38
 *
 * User: elvis 😎😎
 * Date: 18/11/2021
 * Time: 22:41
 *
 * Happy Coding
 */
object Either101 extends App{

  /*
    Scenario - Why Either?
    ----------------------
    Imagine we want to validate a phone number, we have some predicates:containsOnlyDigits and hasExpectedSize, to help us validate. But the issue with this approach is that, if the number is invalid, we wont know why any of the predicates invalidated it. So this is where the type Either comes in.


    Sometimes in validation, we use Option or Boolean, but they often not expressive.
    Either has so many use cases and validation is one of the popular one.

    Either allows you to return a value that can have one or 2 possibilities. They are Right and Left
    In Scala, Either is an immutable data structure to indicate one of 2 possible values

    The Either class is a abstract and cannot be instantiated
   */
  private def containsOnlyDigits(phoneNumber:String):Boolean = {
    val numbs: Option[Int] = phoneNumber.toIntOption
    numbs match {
      case Some(value) => true
      case None => false
    }
  }
  private def hasExpectedSize(phoneNumber:String):Boolean = phoneNumber.size == 10

  def validatePhoneNumber(phoneNumber:String): Option[String] = {
    if(!containsOnlyDigits(phoneNumber)) None
    else if(!hasExpectedSize(phoneNumber)) None
    else Some(phoneNumber)
  }


  // Re-implementing validatePhoneNumber using Either
  def validatePhoneNumberUsingEither(phoneNumber:String): Either[String, String] = {
    if(!containsOnlyDigits(phoneNumber)) Left("A phone number should have only digits")
    else if(!hasExpectedSize(phoneNumber)) Left("Unexpected number of digits. A number has 10 digits")
    else Right(phoneNumber)
  }

  // TODO - Work on from String to Int
  println(validatePhoneNumberUsingEither("hello"))
//  println("12345678912")




  // Marking exams using Either
  case class Pass(score:Int){
    require(score>=60 & score<=100,"Invalid pass: A score must be between 60 and 100")
  }
  def mark(score:Int,message:Option[String]=None) = {
    if(score>=60) Right(Pass(score))
    else Left(message.getOrElse("Score below 60"))
  }

  // Instantiating Either
  val ex_1: Either[String, Int] = Left("scala")
  println(ex_1)

//  val ex_2: Either[String, Int] = Left(42) // Left accepts a parameter of type String
  val ex_2_correct: Either[String, Int] = Right(42)
  println(ex_2_correct)
  println()

  // A function that validates an integer before taking the square root
  def sqrtImplementation(n:Int): Either[String, Double] = {
    if(n>0) Right(Math.sqrt(n))
    else Left("This operation for negative numbers is not supported")
  }

  println(sqrtImplementation(-12))
  println(sqrtImplementation(12))


  // Pattern Matching on Either
  // Transforming an exam outcome to a message
  def toMessage(outcome:Either[String,Pass]) = outcome match{
    case Left(msgf) => ???
  }
}