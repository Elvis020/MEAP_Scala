package Unit_4.Chap_22

/**
 * Project: Scala_with_Daniela
 * Package:
 *
 * User: elvis 😎😎
 * Date: 27/09/2021
 * Time: 22:36
 *
 * Happy Coding
 */

/*
  In scala, using null to represent missing values is an anti-pattern.
  Option ensures that you deal with the presence and absence of an element

  Option prevents NullPointerExceptions
  Points to note about an option:
    1. Option is an abstract class. i.e you can't instantiate it
    2. Its sealed. i.e it has a finite set of possible implementations

  Difference btn None,null and Nothing
  None: Its an instance of Option
  Noting: Its the subclass of all classes
  Null: Its a keyword, used to indicate a missing reference of an object

 */

object Option101 extends App {


  // Classic example of handling null values
  //  def sqrt(n:Int):Double = if(n>0) Math.sqrt(n) else null

  /*
    The problem with the above function is that, the signature of the function
    is not correct for all cases
   */

  // Creating an Option yourself
  sealed abstract class MyOption[A]

  case class mySome[A](a: A) extends MyOption[A]

  case object myNone extends MyOption[Nothing]


  val ex1: Option[Int] = Some(32)
  val ex2: Option[String] = Some("32")

  val ex3: Option[Int] = None
  val ex4: Option[Nothing] = None


  import scala.reflect.runtime.universe._

  val class_example = classOf[String]
  val type_example = typeOf[String]
  println(class_example == type_example) //false
  println()


  // This is not the case for the Option type,
  val str_option = classOf[Option[String]]
  val int_option = classOf[Option[Int]]
  println(str_option == int_option) //true because
  // they are of the wrapper class Option


  // But for type, they are different
  val str_option_2 = typeOf[Option[String]]
  val int_option_2 = typeOf[Option[Int]]
  println(str_option == int_option) //false Because
  // they have different types although they all belong to the same class


  // Re-writing the classic example above using Option
  def sqrt(n: Int): Option[Double] = if (n > 0) Some(Math.sqrt(n)) else None

  println(sqrt(23))


  // The filter Example
  def filter(text: String, word: String): Option[String] = if (text.contains(word)) Some(text) else None

  println(filter("Kweku", "k"))

  // Rewriting the log example using Option
  def log(x:Int):Option[Double] = x match{
    case d if d>0 => Some(Math.log(d))
    case _ => None
  }




  // Pattern Matching on an Option
  /*
    Note that with PM, you get MatchError, if you don't consider
    all the possible implementation.

    PM is not the only way to handle Optional values, you can also use map, flatMap etc

   */


  // Sqrt example using both PM and Options
  def sqrtOrZero(n: Int): Double = sqrt(n) match {
    case Some(result) => result
    case _ => 0
  }



  // Greetings Example
  def greetings(mssg:Option[String]):String = mssg match {
    case Some(something) => something
    case _ => "Greetings, Human!!"
  }


  println(greetings(Some("Hello, Scala")))
  println(greetings(None))


  // Person example
  case class Person(firstName:String,middleName:Option[String],lastName:String)
  def personTaker(p:Person):String = p.middleName match {
    case Some(mName) => s"${p.firstName} ${mName} ${p.lastName}"
    case None => s"${p.firstName} ${p.lastName}"
  }
  val fullName = Person("Elvis",Some("Opoku"),"Amoako")
  val partialName = Person("Kwabena",None,"Sensei")

  println(personTaker(fullName))
  println(personTaker(partialName))


}