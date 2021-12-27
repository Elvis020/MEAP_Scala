package Unit_7.Chap_45

import scala.concurrent.{ExecutionContext, Future}

/**
 * Project: Scala_with_Daniela
 * Package: Unit_7.Chap_45
 *
 * User: elvis 😎😎
 * Date: 21/12/21
 * Time: 4:45 EW
 * Month: Dec
 *
 * Happy Coding!
 */

/*
  With for-comprehension, you have the ability to:

  1. Define a cain of asynchronous operations
  2. Select the first Future instance to complete or not
  3. Find the fastest asynchronous operation to produce a value
  4. Run independent Future instances in parallel and collect their results in a sequence


  When working with several asynchronous computations and independently, Scala offers methods to help you coordinate them. They are: firstCompletedOf,find,sequence

  firstCompletedOf: This returns the first instance of Future to complete successfully or not.
  find: Returns the first result of an asynchronous operation given a predicate
  sequence: Collects all the values that Future instances run in parallel produce
 */

//import scala.concurrent.ExecutionContext.Implicits.global

object Futures_with_for_Comprehension extends App {
  case class Order(id:Int,userId:Int,productId:Int,quantity:Double)
  case class Product(id:Int,description:Int,price:Double)
  case class User(id:Int,fullName:String,email:String)
//  def getOrder(id:Int)(implicit ec:ExecutionContext):Future[Order] = ???
//  def getUser(id:Int)(implicit ec:ExecutionContext):Future[User] = ???
//  def getProduct(id:Int)(implicit ec:ExecutionContext):Future[Product] = ???
//  case class OrderDetails(order:Order,user:User,product:Product)

// We can combine map and flatMap to retrieve the full details of an Order
//  def getOrderDetails(orderId:Int)(implicit ec:ExecutionContext): Future[OrderDetails] = {
//    getOrder(orderId).flatMap(
//      order => getUser(order.userId).flatMap{
//        user => getProduct(order.productId)
//          .map(product => OrderDetails(order,user,product))
//      }
//    )
//  }
//
//  // Using for-comprehension to digest the same scenario
  // One downside about this approach is that, its not efficient, it is executed sequentially
//  def getOrderDetailsWithFC(orderId:Int)(implicit ec:ExecutionContext) = for{
//    order <- getOrder(orderId)
//    user <- getUser(order.userId)
//    product <- getProduct(order.productId)
//  } yield OrderDetails(order,user,product)
//

//  val ex_4 = for{
//    n <- Future(12/2)
//    res <- Future(n.toString)
//  } yield res
//
//  ex_4.onComplete(println)


  // Refactoring to use for-comprehension
//  case class Order(id:Int,userId:Int,productId:Int,quantity:Double)
//  def getOrder(id:Int)(implicit ec:ExecutionContext):Future[Order] = ???
//  def getUserId(orderId:Int)(implicit ec:ExecutionContext):Future[Int] = getOrder(orderId).map(_.userId)
//
//  // Using for-comprehension
//  def getUserId_2(orderId:Int)(implicit ec:ExecutionContext): Future[Int] =for{
//    order <- getOrder(orderId)
//  } yield order.userId
//
//  // To retrieve items more efficiently using the for-comprehension
//    def getOrderDetailsWithFC(orderId:Int)(implicit ec:ExecutionContext) = for{
//      order <- getOrder(orderId)
//      futureUser = getUser(order.userId)
//      futureProduct = getProduct(order.productId)
//      user <- futureUser
//      product <- futureProduct
//    } yield OrderDetails(order,user,product)
//
//  // Alternatively
//    def getOrderDetailsWithFC_v2(orderId:Int)(implicit ec:ExecutionContext) = {
//      getOrder(orderId).flatMap{ order => {
//        val futureUser = getUser(order.userId)
//        val futureProduct = getProduct(order.productId)
//        for{
//          user <- futureUser
//          product <- futureProduct
//          } yield OrderDetails(order,user,product)
//        }
//      }
//    }
//
//
//  // Improving the code below
//  def opA(a:String)(implicit ec:ExecutionContext):Future[Long] = ???
//  def opB(b:Int)(implicit ec:ExecutionContext):Future[Long] = ???
//  def opC(c:Long)(implicit ec:ExecutionContext):Future[Long] = ???
//
//
//  def myOp(s:String)(implicit ec:ExecutionContext): Future[Long] = for{
//    a <- opA(s)
//    b <- opB(s.length)
//    c <- opC(a - b)
//  } yield a * b * c
//
//  // Alternatively and efficiently
//  def myOp_2(s:String)(implicit ec:ExecutionContext): Future[Long] = for{
//    a <- opA(s)
//    futureB = opB(s.length)
//    futureC = opC(a - futureB)
//    b <- futureB
//    c <- futureC
//  } yield a * b * c
//
//
//  // Retrieving the first Future to complete
//  sealed trait Warehouse
//  case object London extends Warehouse
//  case object Brighton extends Warehouse
//  case object Leeds extends Warehouse
//
//  object Warehouse{
//    val all: List[Warehouse] = List(London,Brighton,Leeds)
//  }
//
//  case class Availability(productId:Int, quantity:Double,location:Warehouse)
//  def checkAvailability(productId:Int,warehouse: Warehouse)(implicit ec:ExecutionContext):Future[Availability] = ???
//
//
//  def getAvailability(productId:Int,quantity: Double)(implicit ec:ExecutionContext):Future[Option[Availability]] = {
//    Future.find(Warehouse.all.map(checkAvailability(productId,_)))(availability => availability.quantity >= quantity)
//  }


  // Working with methods that help in the asynchronous execution/parallelism in Scala

  // Using Future.firstCompletedOf()
//  def futureA: Future[Int] = Future{Thread.sleep(42);42}
//  def futureB: Future[Int] = Future(123/1)
//
//  // Should return futureB first, since sleep will take some time
//  val test_1: Future[Int] = Future.firstCompletedOf(Seq(futureA,futureB))
//  println(test_1)



  // Using find
//  def futureA2: Future[Int] = Future{Thread.sleep(42);42}
//  def futureB2: Future[Int] = Future(123/1)
////  val test_2: Future[Option[Int]] = Future.find(Seq(futureA2,futureB2))(_ > 50)
//  val test_2b: Future[Option[Int]] = Future.find(Seq(futureA2,futureB2))(_ > 2350)
//  println(test_2b)


  // Using Sequence
//  def futureA2: Future[Int] = Future{Thread.sleep(42);42}
//  def futureB2: Future[Int] = Future(123/1)
//  def futureC2: Future[Int] = Future(10)
//
//  val combo = Future.sequence(Seq(futureB2,futureC2))
//  combo
//  println(combo)


  // A function that takes a list of Future instances and returns the one that has completed successfully
  def firstSuccessful[T](in:List[Future[T]])(implicit executionContext: ExecutionContext): Future[Option[T]] ={
    Future.firstCompletedOf(in).map(Option(_))
  }
  //OR
  def firstSuccessful_2[T](in:List[Future[T]])(implicit executionContext: ExecutionContext): Future[Option[T]] ={
    Future.find(in)(_ == true)
  }


}
