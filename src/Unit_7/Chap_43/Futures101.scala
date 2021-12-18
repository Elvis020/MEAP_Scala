package Unit_7.Chap_43

import java.io.File
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success, Try}

/**
 * Project: Scala_with_Daniela
 * Package: Unit_7.Chap_43
 *
 * User: elvis 😎😎
 * Date: 21/12/18
 * Time: 4:55 EW
 * Month: Dec
 *
 * Happy Coding!
 */

/*
  Apps are said to be asynchronous if they can run without waiting for its outcome.
  You can process the result once its complete.

  Latency: This is the time spent btn requesting an operation and getting a response back.
  Programs traditionally perform operations in a traditional manner.

  The CPU can split a program into smaller independent sequences of instructions called
  THREADS OF EXECUTION.

  The type future allows you to perform an operation asynchronously. In scala, when using the future type a separate thread eventually completes its execution while your program's current thread runs other processes


  The Try type is very similar to the Future type, with a difference of the Future type having an ExecutionContext as an implicit parameter

  The ExecutionContext gives your Future instance a thread pool to use and for execution strategy

  It is advised to use the ExecutionContext as an implicit parameter rather than an import.It gives you more control on which context to use and customize if needed.

  In the case of PM(Pattern Matching) you can't use it on the type futures, because PMs are synchronous in nature.
  PM is synchronous, as it requires the value wrapped inside a type to be available.

 */

object Futures101 extends App{


  // Creating an instance of Future
  def isProductAvailable(productId:Int,quantity:Double):Future[Boolean] = Future{requestProductAvailability(productId,quantity)}

  private def requestProductAvailability(productId: Int, quantity: Double):Boolean = ???

  // More examples with Future
//  val divideExample: Future[Int] = Future(10/2)
//  println(divideExample)
//  println()

  // With foo there is no order of execution.
//  val foo = {
//    println("Hello")
//    Future(println("World!"))
//    22/2
//  }
//
//  println(foo)



  // Does it compile? - Yes
  def test() = {
    Future(('a' to 'c').map(print))
    (0 to 2).map(print)
  }
  test()

  // Processing futures on completion
  // Tracking the availability of a product

  case class Availability(id:Int,quantity: Double)
  def trackAvailability(availability: Future[Availability]): Unit = {
    availability.onComplete{
      case Success(p) if(p.quantity <= 0) => println(s"Product ${p.id} is not available")
      case Success(p) => println(s"Product ${p.id} is available with quantity ${p.quantity}")
      case Failure(ex) => println(s"Couldn't get the availability because of the ${ex.getMessage}")
    }
  }



  // The onComplete method of future returns a Unit, so if f has a return type it is lot in the process
  // If you need to retain the result of f, use the map method on the onComplete Method
  // def onComplete[U](f:Try[T] => U)(implicit executor:ExecutionContext)

  // This prints out Good, because its wrapped in Unit
//  val retainingResult = Future(10/2).onComplete{
//    value => if(value.isFailure) println("Bad!") else println("Good!")
//  }
//  retainingResult

  // This won't print anything out because, onComplete returns a Unit but its of type String
//  val retainingResult_2 = Future(10/2).onComplete{
//    value => if(value.isFailure) println("Bad!") else "Good!"
//  }
//  retainingResult_2




  // Does it compile? - Yes, but is won't print out anything.
//  def isSuccess[T](f:Future[T]) =f.onComplete(_.isSuccess)
//  isSuccess(Future(10/1))



  // Printing all the files in a directory using an asynchronous operation
//  val tryingToOpenAFile: Unit = new File(".").listFiles().foreach(println)
//  tryingToOpenAFile

  // Using Future
//  val openAsynchronous = Future(new File(".").listFiles().foreach(println)).onComplete{
//    value => println(s"Good, done!")
//  }
//  openAsynchronous

}
