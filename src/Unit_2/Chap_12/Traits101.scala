package Unit_2.Chap_12
/*
  Traits are there because you can't, express multiple inheritance, with
  classes and abstract classes

  Traits are very similar to abstract classes with some few alterations.
  The 2 main difference are:
    1. A class can inherit from more than one trait (not possible with abstract classes)
    2. Traits don't have parameters, abstract classes do

  Sealed traits allow you to define a closed set of implementations
  In Scala, traits are used to identify interfaces

  Traits can have bot abstract and non-abstract methods
  The compiler adds the App trait to your scope when you extend it, without
  declaring it explicitly

 - Class can extend a class // true
 - Class can extend an object // false
 - Class can extend a trait // true

 - An object can extend a class // true
 - An object can extend an object // false
 - An object can extend a trait // true


 - A trait can extend a class // true|false
 - A trait can extend a object // false
 - A trait can extend a trait // true

  Sealed traits are used to limit the elements that extend it
  When using the sealed keyword, the compiler knows that all objects/classes
  extending the trait are in the same file

  Because of the sealed keywords, the compiler knows all the implementations of the trait
  Sealed trait can be used to express the concepts of Union types


 */

trait Color{
  val color:String
}
class Furniture(val color:String) extends Color
class Clothes(val color:String) extends Color


trait Printable{
  def print():Unit
}

trait Animal{
  def sleep:String="zZz"
  def move(x:Int,y:Int):String
  def eat(food:String):String
}
trait Nameable{
  def name:String
}

class Cat extends Animal{
  override def move(x: Int, y: Int): String = s"The cat is moving to ($x,$y)"
  override def eat(food: String): String = s"The cat is eating $food"
  override def sleep: String = s"Sleepy cat"
}


// You can override a function as a val, but not vice-versa
class Dog(val name:String) extends Animal with Nameable{
  override def move(x: Int, y: Int): String = s"Let's go to ($x,$y)"
  override def eat(food: String): String = s"$food $food"
}


sealed trait Suit
object Clubs extends Suit
object Diamonds extends Suit
object Heart extends Suit
object Spade extends Suit


sealed trait Currency
object USD extends Currency
object CAD extends Currency
object EUR extends Currency



object Traits101 extends App{
  val tiggerTheDog = new Dog("Tigger")
  val newCat = new Cat

  def feedTreat(animal:Animal): String = animal.eat("treat")
  feedTreat(tiggerTheDog)
  feedTreat(newCat)

  def welcome(nameable:Nameable) = {
    println(s"Hi, ${nameable.name}")
  }

  welcome(tiggerTheDog) // Does compile
//  welcome(newCat) // Doesn't compile

}
