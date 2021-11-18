
/*
  Purity: A pure function is total, and has no side-effects

  Totality: A function is total if it is well-defined for every input such that:
    1. for every possible parameter it must terminate
    2. It must return a value that matches its return type


  Side Effects
    A side effect is an operation that has an observable interchange with elements outside its local scope: 
    by interacting with the outside world, it affects or is affected by the state of the app

  In Chap 21, there is a picture that perfectly describes

  Note: 😎 When defining a method with no parameters, you should omit the parentheses if the function is pure.


 */

object Purity101 extends App{

  // The thermostat example 🌡
  def monitorTemperature(current:Double, recovery:Double): Double = if(current>0) current else recovery
  val example_1 = monitorTemperature(10,5)
  println(example_1)

  val example_2 = monitorTemperature(
    current = 5.0,
    recovery = {println("Emergency! Triggering recovery"); 10}
  )
  println(example_2)
  // Example_2 misbehaves, because of the side effects.


  // Example of a total and non-total function 😝
  def plus(n:Int):Int = n+2                      //total
  def divide(n:Int):Int = 42/n                  //non-total
  def rec(n:Int):Int = if(n>0) n else rec(n-1) // non-total


  // Quick Check 21.1 - answers
  // 1. total
  // 2. non-total -> non-terminating
  // 3. total
  // 4. non-total -> has side effect
  // 5. non-total -> doesn't have consistent return type


  // Side Effects
  def negate(predicate:Boolean) = !predicate // has no side effect

  class Counter(){
    private var counter = 0

    def incr() = counter+=1   // (write) has side effect
    def get():Int = counter       // (read) has side effect

    def helloName(name:String):String = {
      val msg = s"Hello $name"
      println(msg)               // (write) has side effect
      msg
    }




      



  }

  // Pure and Impure functions
  def pureF(name:String):String = s"Hello,$name"
  def impureF(name:String):String = {
    println(".... 🤣 doing something here 🤣 ...")
    s"Hello,$name"
  }
  println(pureF("Opoku"))
  println(impureF("Amoako"))




}