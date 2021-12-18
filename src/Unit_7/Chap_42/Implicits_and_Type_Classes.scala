package Unit_7.Chap_42

/**
 * Project: Scala_with_Daniela
 * Package: Unit_7.Chap_42
 *
 * User: elvis 😎😎
 * Date: 28/11/2021
 * Time: 23:52
 *
 * Happy Coding
 */

/*
  Implicits is one of the most controversial traits.
  It allows you to write less code and express extremely powerful abstractions.
  On the other hand, it can make your program difficult to understand and it will increase your compilation time if misused

  Implicit resolution: This is when the compiler searches for an implicit parameter match
  When defining a function you can declare the last group of parameters as implicit. This is done in Scala 3 using the keyword "using" and in Scala 2 using the keyword "implicit"

  If the compiler searches for a suitable match and finds no unique and unambiguous match, the compiler will fail

  Ad hoc polymorphism enables us to use type classes which is very popular in functional programming.

  Ad hoc because you can override the behaviour by providing an implicit in your current scope rather than using the one you defined in the associated types.

  Didn't really understand the Tpe classes part

 */




object Implicits_and_Type_Classes extends App {

  /* Scenario 1 - Placing an order */
  //  case class User(id:Int)
  //  case class PersonalDetails()
  //  case class Account()
  //  case class UserContext(id:Int,details:PersonalDetails,account:Account)
  //  case class ProductSelection(productIds:List[Int])
  //
  //
  //  def purchase(userId:Int, selection:ProductSelection):Either[String,Int] = {
  //    val userContext = getUserContext(userId)
  //    for{
  //      _ <- validateAddressWithinDistance(userContext)
  //      _ <- validateSelection(selection,userContext)
  //      _ <- validateBalance(selection,userContext)
  //    } yield placeOrder(selection,userContext)
  //  }
  //  private def getUserContext(userId: Int):UserContext = ???
  //  private def validateBalance(selection:ProductSelection,userContext: UserContext):Either[String,Double] = ???
  //  private def validateAddressWithinDistance(userContext: UserContext):Either[String,UserContext] = ???
  //  private def validateSelection(selection:ProductSelection,userContext: UserContext):Either[String,ProductSelection] = ???
  //  private def placeOrder(selection:ProductSelection,userContext: UserContext):Int = ???


  /* Scenario 2 - Placing an order using due groups of parameters */
  //  case class User(id:Int)
  //  case class PersonalDetails()
  //  case class Account()
  //  case class UserContext(id:Int,details:PersonalDetails,account:Account)
  //  case class ProductSelection(productIds:List[Int])
  //
  //
  //  def purchase(userId:Int, selection:ProductSelection):Either[String,Int] = {
  //    val userContext = getUserContext(userId)
  //    for{
  //      _ <- validateAddressWithinDistance(userContext)
  //      _ <- validateSelection(selection)(userContext)
  //      _ <- validateBalance(selection)(userContext)
  //    } yield placeOrder(selection)(userContext)
  //  }
  //  private def getUserContext(userId: Int):UserContext = ???
  //  private def validateBalance(selection:ProductSelection)(userContext: UserContext):Either[String,Double] = ???
  //  private def validateAddressWithinDistance(userContext: UserContext):Either[String,UserContext] = ???
  //  private def validateSelection(selection:ProductSelection)(userContext: UserContext):Either[String,ProductSelection] = ???
  //  private def placeOrder(selection:ProductSelection)(userContext: UserContext):Int = ???


  // Scenario 3 - Placing an order using implicits
  case class User(id: Int)

  case class PersonalDetails()

  case class Account()

  case class UserContext(id: Int, details: PersonalDetails, account: Account)

  case class ProductSelection(productIds: List[Int])


  def purchase(userId: Int, selection: ProductSelection): Either[String, Int] = {
    implicit val userContext = getUserContext(userId)
    for {
      _ <- validateAddressWithinDistance
      _ <- validateSelection(selection)
      _ <- validateBalance(selection)
    } yield placeOrder(selection)
  }

  private def getUserContext(userId: Int): UserContext = ???

  private def validateBalance(selection: ProductSelection)(implicit userContext: UserContext): Either[String, Double] = ???

  private def validateAddressWithinDistance(implicit userContext: UserContext): Either[String, UserContext] = ???

  private def validateSelection(selection: ProductSelection)(implicit userContext: UserContext): Either[String, ProductSelection] = ???

  private def placeOrder(selection: ProductSelection)(implicit userContext: UserContext): Int = ???

  // Other examples of using implicit
  def welcome(name: String)(implicit msg: String = "Hello"): Unit = println(s"$msg, $name")

  welcome("Elvis")

  def welcome_2(name: String)(implicit msg: String): Unit = println(s"$msg, $name")

  welcome_2("Mary Jane")("Hello")

  implicit val h1 = "Hi"
  welcome("Spocky") // finds a suitable match, and uses it



  //  implicit val h2 = "Hola"
  //  welcome("Spocky") // cant find suitable match because there are two values declared as String, making it ambiguous for the compiler


  // Example
  def plusOne(implicit n: Double) = n + 1

  implicit val k: Double = 90
  println(plusOne)

  println()


  // Implicit Resolution
  def powerFunction(exp: Int)(implicit base: Int): Double = Math.pow(base, exp)

  implicit val j = 2
  //  println(s"Power function: ${powerFunction(3)}")
  //  println(Math.pow(2,3))


  // Does it work or not
  /*
    It works, explanation: It first looks in the scope, when it finds a match, it uses it else it looks into companion objects
   */
  class A {
    def test(implicit n: Int): String = n.toString
  }

  object A {
    implicit val n: Int = 7
  }

  println((new A()).test)


  // Type Classes

  // Sorting an order in the reverse order
  case class Order(n:Int)
  object Order {
    implicit val ordering: Ordering[Order] = ???
  }

  // The trait Show
  trait Show[T]{
    def show(t:T):String
  }
  object Show{
    implicit val stringShow = new Show[String]{override def show(t: String): String = ???}
  }

}