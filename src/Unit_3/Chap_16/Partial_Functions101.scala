package Unit_3.Chap_16
/*
  Partial functions are  related to PMs
  Partial functions are functions defined only for some input

  They are useful in extracting commonalities between functions to form more complex functions
  Partial functions can be used to handle try-catch exceptions

  Consider this: You have a PM, and the cases are almost the same except for the last case,
  we can use Partial functions to refactor to avoid code repetition

  Partial functions can be viewed as anonymous functions with more than
  one case statement

  With Partial Functions, if the input doesn't define/match any of the case statements
  you will get a MatchError

  Function composition generally refers to chaining 2 or more functions
  Functions are chained by generally feeding the result of the first function to the input
  of the second function

  Depending on your use case, function composition may have a different meaning
  You may want to define a PF as a callback in case the previous PF fails, this can be done
  using the OrElse

  Exceptions interrupt the execution flow of your program; you need to intercept it or
  it wil terminate your program

  Exceptions are like time bombs, and should always be avoided in Scala
  Exceptions are unpredictable. They are drastic solutions, if not caught will destroy
  or terminate the whole program

  Partial functions will throw errors when input doesn't match case



 */

object Partial_Functions101 extends App{

  def sqrtOrZero(n:Double): Double = n match{
    case n if(n>0) =>Math.sqrt(n)
    case _ => 0
  }
  def sqrtOrValue(n:Double): Double = n match{
    case n if(n>0) =>Math.sqrt(n)
    case x => x
  }

  println("+++++++ Sqrt or Zero +++++++")
  printf("%2.2f",sqrtOrZero(21))
  println()
  printf("%2.2f",sqrtOrZero(-21))
  println()


  println("+++++++ Sqrt or Value +++++++")
  printf("%2.2f",sqrtOrValue(21))
  println()
  printf("%2.2f",sqrtOrValue(-21))
  println()


  // Implementing a partial function in case of the PM
  // The compiler cannot infer the type so, what you do is add the
  // type to the signature of the PartialFunction[-A,+B]
  val sqrtMethod_2:PartialFunction[Double,Double] = {
    case x if (x > 0) => Math.sqrt(x)
    case _ => 0.0
  }

  val toPrettyString:PartialFunction[Any,String] = {
    case x:Int if(x>0) =>s"$x is a positive number"
    case s:String => s
  }

  println("+++++++ Sqrt or Zero With Partial functions+++++++")
  printf("%2.2f",sqrtMethod_2(21))
  println()
  printf("%2.2f",sqrtMethod_2(-21))
  println()

  println("+++++++ To PrettyString  With Partial functions+++++++")
  println(toPrettyString(21))
  println(toPrettyString("Hey there"))


  println("+++++++ Challenge 1 With Partial functions+++++++")
  val transform:PartialFunction[String,String] = {
    case n if(n.startsWith("a")) => n.reverse
    case n if(n.startsWith("s")) => n.toUpperCase
  }

  println(transform("serlom is here, and we are looking for him!"))
  println(transform("a man is sleeping here!!"))



  // Function Composition
  println("+++++++ Function Composition +++++++")

  val f:String => Int = _.size
  val g:Int => Boolean = _>2
  val gof:String => Boolean = s=>  g(f(s))

  // or

  val gof2:String=>Boolean = f andThen(g)

  println(gof("Ama"))
  println(gof2("Ama"))


  // Partial Functions as callbacks using orElse
  println("+++++++ Function Composition using partial functions as callbacks +++++++")
  val sqrt:PartialFunction[Int,Double] = {case c if(c>0) => Math.sqrt(c)}
  val zero:PartialFunction[Int,Double] = {case _ => 0}
  val value:PartialFunction[Int,Double] = {case x => x}

  def sqrtOrZero3(n:Int):Double = sqrt.orElse(zero)(n)
  def zeroOrSqrt(n:Int):Double = zero.orElse(sqrt)(n)
  def sqrtOrValue3(n:Int):Double= sqrt.orElse(value)(n)


  printf("%.2f",sqrtOrZero3(45))
  println()
  printf("%.2f",sqrtOrZero3(-15))
  printf("%.2f",zeroOrSqrt(-15))
  println()
  printf("%.2f",sqrtOrValue3(-15)) // -15
  println()
  printf("%.2f",sqrtOrValue3(95))
  println()
  println(sqrtOrZero3(-15).equals(zeroOrSqrt(-15)))



  // Partial Functions for exception handling
  println("+++++++ Exception handling with Partial Functions +++++++")
  class MyException(message:String) extends Exception(message)

  // An instance of an exception can be created using the throw keyword
  // throw new myException("Booom!!")

  val n:Int =
    try{
      throw new MyException("Boom!!!")
      42
    }
    catch{
      case e:Exception => println(s"Ignoring exception $e, returning zero instead")
      0
    }


  val b = "hello"
  val d = "true"
  val solution:PartialFunction[String,Boolean] ={
    case s:String => {
      try{
        s.toBoolean
      }catch{
        case _:IllegalArgumentException => false
      }
    }
  }

  println(solution(b))
  println(solution(d))




}
