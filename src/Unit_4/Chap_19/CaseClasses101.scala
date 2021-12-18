package Unit_4.Chap_19
/*
  Case classes represent a suitable way to present data in an immutable way
  Case objects are very useful when serialization is involved

  Case classes are very convenient for PM, this is because of the unapply
  method that they have with case classes the compiler adds ad-hoc code

  In scala 2.11, you couldn't provide a case class with more than 22 parameters,
  now its possible. Case classes encourage the use of immutability

  The copy method is equivalent to creating a new class
  It is very convenient if a class has many fields and you want
  to change 1 or 2 things, you use the copy method

  A case class redefines how it inherits methods like: toString,
  hashCode, equals etc

  Case class has a way of printing its toString
  For hashCodes in case class, it considers only the internal structure
  For equals, it considers the type and structure not the location in
  memory, like the way Java does it


  Whenever you create a case class the compiler creates a
  companion object, in which it generates an apply and
  unapply method

  apply() -> Helps compose a class
  unapply() -> Helps decompose a class

  When pattern matching a value, you can bind the entire class to a
  value using @

 */

case class Brewery(name:String)
case class Beer(name:String, brewery: Brewery)

case class Person(name:String, age:Int)

object CaseClasses101 extends App{

  def welcome(person:Person): String = person match{
    // @ matches it and binds it to the value k and p respectively
    case k @ Person("Tom",_) => s"Hello ${k.name}"
    case Person(name,age) if age>18 => s"Good to see you $name"
    case p @ Person(_,18) => s"${p.name}, you look older"
    case Person(_,_) => s"Hi Bro!!"
  }

  val p1 = new Person("Kojo",32)

  // The compiler provides getters and setters
  println("Before changing value")
  println(p1.name)
  println(p1.age)

  // To change the value of the parameters, you will need the copy function
  val p2 = p1.copy(p1.name,90)
  println("After changing value")
  println(p2.name)
  println(p2.age)

  case class Test(a:Int,b:Int,c:Int,d:Int)
  val test = Test(1,2,3,4)

  // Modifying using the apply method
  val testA = Test(a=0,b=test.b,c=test.c,d=test.d)

  // Modifying using the copy method
  val testB = test.copy(0)

  // testB looks readable and more convenient than testA


  println("++++++ Testing for hashcode method ++++++")


  // HashCode difference
  class D(f:Int)
  println(new D(9).hashCode() == new D(9).hashCode()) //false


  case class H(i:Int)
  println(new H(23).hashCode() == new H(23).hashCode()) //true


println("++++++ Testing for equals method ++++++")

  // Equals difference
  class P(f:Int)
  println(new D(9).equals(new D(9))) //false


  case class W(i:Int)
  println(new W(9).equals(new W(9))) //true

  println("++++++ Pattern matching in Scala using case classes ++++++")

  val elvis = Person("Elvis",29)
  val kofi = Person("Kofi",18)
  println(welcome(elvis))
  println(welcome(kofi))


}