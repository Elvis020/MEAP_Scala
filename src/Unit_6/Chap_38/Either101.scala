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

    The Either class is a abstract and cannot be instantiated.
    Because, Either is sealed, when you provide an implementation for Right, you need to provide one for Left as well. If you fail to do this, the compiler will show a warning, at compile time
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
  def toMessage(outcome:Either[String,Pass]): String = outcome match{
    case Left(msg) => s"Fail: $msg"
    case Right(pass) => s"Pass with score ${pass.score}"
  }

  // A function that uses PM to implement sqrt of a number
  def sqrtOrZero(n:Int): Double = n match {
    case n if n>0 => sqrtImplementation(n).right.get
    case _ => 0.0
  }
  def sqrtOrZeroAlt(n:Int): Double = sqrtImplementation(n) match {
    case Left(_) => 0.0
    case Right(d) => d
  }

  println()

  /* Using map and flatMap implementation on Either */

  // Transforming a pass to Percentage
  case class PassP(score:Int){
    require(score>=60 & score<=100,"Invalid pass: score must be between 60 and 100")
    def toPercentage:Double = score/100
  }
  def toPercentage(outcome:Either[String,PassP]) =
    outcome.right.map(_.toPercentage)
  // .map on Either instance is used to transform the value wrapped into a Left or Right
  /*
    With the .right and .left projections, 2 things happen:
      1. If the instance matches the selected side, it applies the the function to the value
      2. If not, it returns the instance without performing any transformations
   */


  val e: Either[String, Int] = Right(42)
  println(e.left.map(_.size)) // returns instance without any transformation
  println(e.right.map(_ * 2.0)) // applies transformation


  // The truncate function that truncates an input text to its first 24 chars
  def truncate(e:Either[Double,String]): Either[Double, String] = e.map(_.substring(0,23))

  def truncateAlt(e:Either[Double,String]): Either[Double, String] = e.map(_.take(23))

  println(truncate(Right("Good morning, how are you doing today! Have a wonderful evening")))
  println(truncateAlt(Right("Good morning, how are you doing today! Have a wonderful evening")))


  /* Using flatMap */

  // A function that combines 2 outcomes(grades) and finds the average
  def combine(outcome1:Either[String,Pass],outcome2:Either[String,Pass]): Either[String, Pass] ={
    outcome1.flatMap{
      s1 => {
        outcome2.map{
          s2 =>
            val avg = (s1.score + s2.score)/2
            Pass(avg)
        }
      }
    }
  }


  // Example to show how to ue flatMap on an instance of Either
  val a:Either[String,Int] = Right(25)
  val t: Either[Any, Double] = a.flatMap{
    n => {
      if(n<0) Left(-1)
      else Right(Math.sqrt(n))
    }
  }
  println(t)

  val t2: Either[String, Int] = Left("").left.flatMap{
    text => {
      if(text.isEmpty) Right(42)
      else Left(text)
    }
  }
  println(t2)


  // Consider this
  def validation(a:Either[String,Int],b:Either[String,Int]) = {
    a.flatMap{
      aa => {
        b.map{
          bb => aa + bb
        }
      }
    }
  }

  println(validation(Left("first failure"), Left("second failure"))) // Left("first failure") -> It wont even continue since its a Left
  println(validation(Right(22), Right(44))) // Right(66)

  println()

  // Refactoring the combine function above using for-comprehension
  def combineWithFor(outcome1:Either[String,Pass],outcome2:Either[String,Pass]): Either[String, Pass] = for{
    o1 <- outcome1
    o2 <- outcome2
    avg = (o1.score+o2.score)/2
  } yield Pass(avg)

  val ex:Either[Int,String] = Left(1234)

  // Using map
  println(ex.left.map(_ + 1))

  // Using for-comprehension
  val ex2 = for{
    v <- ex.left
  } yield v+1

  println(ex2)

  // Implementing validation using for-comprehension
  def validationWithFor(a:Either[String,Int],b:Either[String,Int]): Either[String, Int] = for{
    aa <- a
    bb <- b
  } yield aa + bb

  println(validationWithFor(Left("first failure"), Left("second failure")))


  def validationWithAccumulatedErrors(a:Either[String,Int],b:Either[String,Int]): Either[List[String], Int] =
    a match {
      case Left(value1a) =>
      b match {
        case Left(value1b) => Left(List(value1a,value1b))
      }
      case Right(value2a) => b match {
        case Right(value2b) => Right(value2a + value2b)
      }
    }

  println(validationWithAccumulatedErrors(Left("first failure"), Left("second failure")))
  println(validationWithAccumulatedErrors(Right(22), Right(44)))
}