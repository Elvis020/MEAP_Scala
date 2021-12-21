package Unit_7.Chap_44

import scala.concurrent.ExecutionContext.Implicits.global
import java.io.File
import scala.concurrent.{ExecutionContext,Future}

/**
 * Project: Scala_with_Daniela
 * Package: Unit_7.Chap_44
 *
 * User: elvis 😎😎
 * Date: 21/12/19
 * Time: 12:09 EW
 * Month: Dec
 *
 * Happy Coding!
 */

/*
  The map function allows you to transform a value that is an asynchronous operation produces.

  The flatten method merges 2 nested instances of a Future into 1.
  The flatMap method is the composition of both the map and flatten, and it allows you to chain multiple asynchronous instances

  Futures can catch Exception, so if you know the ones that may happen then you can throw them

  With map, if the futute instance completes successfully, then the function is applied to it else nothing happens

  Remember that the method flatMap is a combination of two functions:flatten and map.
  The flatMap method allows you to express an execution dependency



 */

object Futures_with_Map_et_all extends App{

  case class Availability(id:Int,quantity:Double)
  case class Order(id:Int, customerId:Int,productId:Int,quantity:Double)

//  private def getAvailability(productId:Int)(implicit ec:ExecutionContext):Future[Availability] = ???
//  private def createOrder(customerId:Int,productId:Int,quantity:Double):Order = ???
//  def placeOrder(customerId:Int,productId:Int,quantity:Double)(implicit ex:ExecutionContext):Future[Order] ={
//    getAvailability(productId).map{
//      availability => {
//        if(quantity <= availability.quantity) createOrder(customerId, productId, quantity)
//        else throw new IllegalStateException(s"" +
//        s"Product: ${productId} unavailable: requested $quantity, available ${availability.quantity}")
//      }
//    }
//  }

  // Examples of Future with .map()
//  val ex_1: Future[Int] = Future(12/3).map(_ * 45)
//  println(ex_1)


//  val ex_2 = Future(12/2).map{ n =>
//    if(n>10) n
//    else throw new Exception(s"Too small: $n")
//  }
//  println()
//  println(ex_2)


  // A function to parse a String to an Int using Future
//  def toInt(value:Future[String])(implicit ec: ExecutionContext) = value.map(_.toInt)

//  implicit val ec: scala.concurrent.ExecutionContext = scala.concurrent.ExecutionContext

//  toInt(Future("2")).onComplete(println)


  // Using the flatten function
//  private def createOrder(customerId:Int,productId:Int,quantity:Double)(implicit executionContext: ExecutionContext):Future[Order] = ???

//  def placeOrder(customerId:Int,
//                 productId:Int,
//                 quantity:Double)
//                (implicit executionContext: ExecutionContext): Future[Order] = {
//   getAvailability(productId).map{
//     availability => {
//       if(quantity <= availability.quantity) createOrder(customerId,productId, quantity)
//       else throw new IllegalStateException(s"" +
//               s"Product: ${productId} unavailable: requested $quantity, available ${availability.quantity}")
//     }
//   }.flatten
//  }

  // Other examples of flatten with Future includes
//  val flatten_example = Future(Future(3/9)).flatten


  // Consider this
  case class Account(id:String)
  case class User(name:String)

//  def getAccount(orderId:Int)(implicit executionContext: ExecutionContext):Future[Account] = ???
//  def getUser(accountId:String)(implicit executionContext: ExecutionContext):Future[User] = ???

  // Using the 2 methods above, create a function to return a user given an order
//  def getUser(orderId:Int)(implicit executionContext: ExecutionContext): Future[User] = {
//    getAccount(orderId).map{
//      acc => getUser(acc.id)
//    }.flatten
//  }



  // Using the flatMap function
//    def placeOrder(customerId:Int,
//                   productId:Int,
//                   quantity:Double)
//                  (implicit executionContext: ExecutionContext): Future[Order] = {
//     getAvailability(productId).flatMap{
//       availability => {
//         if(quantity <= availability.quantity) createOrder(customerId,productId, quantity)
//         else throw new IllegalStateException(s"" +
//                 s"Product: ${productId} unavailable: requested $quantity, available ${availability.quantity}")
//       }
//     }
//    }

  // Using the 2 methods above, create a function to return a user given an order using flatMap
//  def getUserWithFlatMap(orderId:Int)(implicit executionContext: ExecutionContext): Future[User] = {
//    getAccount(orderId).flatMap{
//      acc => getUser(acc.id)
//    }
//  }

//    implicit val ec: scala.concurrent.ExecutionContext = scala.concurrent.ExecutionContext.global



  // Final exercise(Counting the items in a directory using concurrency)
  def contentInDir(path:String): Future[List[String]] ={
    Future{
      val file = new File(path)
      if(file.isDirectory) file.listFiles().toList.map(_.getAbsolutePath)
      else List.empty[String]
    }
  }

  def countItemsInDir(listOfStuff:Future[List[String]])= {
    listOfStuff.onComplete{
      i => println(s"There are ${(i.map(_.size)).get} item(s) in this directory")
    }
  }

  val interestedPath = "/home/elvis/Documents/Courses/Codes/Scala/Daniela_Book/Scala_with_Daniela/src"
  val test_2 = contentInDir(interestedPath)

  countItemsInDir(test_2)
}
