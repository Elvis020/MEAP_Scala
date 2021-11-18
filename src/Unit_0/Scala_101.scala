package Unit_0

class MyClass(name:String){
  def sayHello(otherName:String):String = s"Hi $otherName, my name is $name!"
}

object Scala_101 extends App{
    /*
      Case class present data in an immutable manner.
      OOP is basically classes and subclasses.

      Singleton instances: Instances that you should initialize at most once.
      These are objects in Scala.

      Error handling and partial functions
     */
  val ex1 = new MyClass("Elvis")
  println(ex1.sayHello("Opoku"))

  // Examples of mutability
  var f = "Ok"
  f = "Ama"
  println(f)

  // Examples of HOF
  def divideByTwo(n:Int): Int = n/2

  // input parameter is a function value
  def addOne(f:Int => Int) = f andThen(_+1)

  def concatTheOps = addOne(divideByTwo)


  // andThen is more of a function composition
  // h(x) = g(f(x))

  println(concatTheOps(23))


}
