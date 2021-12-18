package Unit_3.Chap_15
/*
  Anonymous functions are functions you can define quick and concise
  When implementing anonymous functions, the name of the function is
  no longer needed

  Scala offers a more concise notation to anonymous functions

 */


// calculator program
object CalculatorProgram{
//  def sum(a:Int, b:Int): Int = a+b
//  // Anonymous equivalent
//  // {(a:Int,b:Int) => a+b}
//  def subtract(a:Int, b:Int): Int = a-b
//  def multiply(a:Int, b:Int): Int = a*b
//  def divide(a:Int, b:Int): Double = a/b
//  def negate(a:Int): Int = subtract(0,a)


  // Re-implementing the above methods using anonymous functions
//  val sum = (a:Int,b:Int) => a+b
//  val subtract = (a:Int, b:Int) => a-b
//  val multiply = (a:Int, b:Int) => a*b
//  val divide = (a:Int, b:Int)=> a/b
//
//  // If the function parameter is one, you can omit the normal braces
//  val negate = { a: Int => subtract(0, a) }
//


  // Re-implementing using the concise anonymous function notation
  val sum:(Int,Int) => Int = (_+_)
  val subtract:(Int,Int) =>Int = (_ - _)
  val multiply:(Int,Int) => Int =  (_*_)
  val divide:(Int,Int)=>Int  = (_/_)
  val negate:Int => Int = subtract(0,_)


}

object Anonymous_Functions_101 extends App{
  // Playing around with the calculator object
//  import CalculatorProgram._
//
//  println(sum(2, 3))
//  println(multiply(4,21))
//  println(subtract(100, 90))
//  println(negate(6))
//
//
//  // Anonymous function of hello world
//  val hello_1 = { n: String => s"Hello, $n!"}
//
//
//  // Anonymous functions with concise notations
//  val hello:(String => String) = n => s"Hello, $n!"
//
//  println(hello("Elvis"))



  // Rewriting functions as concise anonymous functions
//  def multiply(s:String, n:Int): Int = s.length * n
//  def toDouble(n:Int): Double = n.toDouble
//  def concat(s1:String,s2:String): String = s1+s2
//  def inverseConcat(s1:String,s2:String): String = s2+s1
//  def longFunctions(s:String): String = {
//    val length = s.length
//    s.reverse * length
//  }



  // Solutions
  val multiply:(String,Int) => Int = {_.length * _}
  val toDouble:Int => Double = _.toDouble
  val concat:(String,String) => String = {_+_}
  val inverseConcat = (a:String,b:String) => {b+a}
  val longFunctions = (s:String) => {
    val length = s.length
    s.reverse * length
  }


  println()
  println()
  println("++++++++ try catch +++++++")
  // try catch play around
  val l = List("1","2","xx","3","4","0")
  val res = l.flatMap(t => try{Some(t.toInt)} catch {case e:Exception => None})
    .map(f => try{Some(12/f)} catch {case e:Exception => None}).flatten
  println(res)


}
