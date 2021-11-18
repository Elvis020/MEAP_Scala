package Unit_2.Chap_11


/*
  The essence of OOP is representing elements of the real world using classes that describe their behaviour through methods

  Instantiating a coding element at once is known as "singleton"
  In Scala, you implement static methods in an object and non-static method in a class

  Objects are not instantiated using the "new" keyword. The compiler even makes sure the JVM
  instantiates a object no more than once

  It uses the singleton pattern in this process
  Object in Scala is different from an Object in Python,C++,JS etc

  Object in JS,Python,Java means an instance of a class but in Scala its different
  Every program has a main/executable, and this main is a singleton

  A companion object is an object with the same name as an existing class
  Static functions/methods are methods acting on a class rather than on a specific instance
  of a class

  The most popular static method in Scala is the apply method in an object
  For the method apply, you must explicitly add the return type
  Basically, apply is a static method used to construct class instances

  The unapply static method is a compliment of the apply method, i.e it deconstructs a class
  into its parameters
  Unapply is used in PM





 */

class Robot(name:String){
  def welcome:String = ???
}

object Vocabs{
  val sentenceA = "Hi, there!"
  val sentenceB = "Welcome!"
  val sentenceC = "What's up bro"
}
object Robot{
  def mostTalkative(r1:Robot,r2:Robot):Robot ={
    val r1_size = r1.welcome.length
    val r2_size = r2.welcome.length
    if(r1_size>=r2_size) r1 else r2
  }
}

class Person(val name:String,val age:Int)
object Person{
  def apply(p1:Person,p2:Person): Person = {
    val name = s"Son of ${p1.name} and ${p2.name}"
    val age = 0
    new Person(name, age)
  }
  def apply(name:String): Person = new Person(name, 0)
  def apply(age:Int): Person = new Person("Mr. Unknown", age)
}

object Singleton101 extends App{
  println(Vocabs.sentenceA)
  println(Vocabs.sentenceB)

  val bob = new Robot("Bob"){
    override def welcome: String = Vocabs.sentenceA
  }
  val tom = new Robot("Tom"){
    override def welcome: String = Vocabs.sentenceB
  }

  Robot.mostTalkative(bob, tom)


  val kofi = new Person("kofi",32)
  val abreewa = new Person("abreewa",90)
  Person(kofi,abreewa)


}


