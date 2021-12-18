package Unit_7.Chap_43

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

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



 */

object Futures101 extends App{


  // Creating an instance of Future
  def isProductAvailable(productId:Int,quantity:Double):Future[Boolean] = Future{requestProductAvailability(productId,quantity)}

  private def requestProductAvailability(productId: Int, quantity: Double):Boolean = ???
}
