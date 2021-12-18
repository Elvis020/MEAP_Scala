package Unit_4.Chap_25

/**
 * Project: Scala_with_Daniela
 * Package: Unit_4.Chap_25
 *
 * User: elvis 😎😎
 * Date: 07/10/2021
 * Time: 08:28
 *
 * Happy Coding
 */

/*
  Tuples helps o quickly group data in a given order. While apply is used to create an instance of n object, unapply is used to extract information from an object


  In Scala, a tuple is a tool that allows you to grp data quickly, and have a minimum of 1 and a maximum of 22

  There are 3 ways of extracting information from a tuple
    1. PM
    2. Decomposition
    3. Using getters

  Try to use tuples only in short and concise fragments if your program: your code will be more readable and easier to maintain.


 */

object Intro_to_Tuple_and_Unapply extends App{

//  Tuples
  val ex1 = 1 -> 2 // You can create a tuple using the arrow
  val ex2 = (3,2)
  println(ex1)
  println(ex2)

  // You can use PM to deconstruct a tuple
  val t = ("hello","Scala", "!")

  val pm101: String = t match {
    case (_,_,c) if c == "!" => "?"
    case (a,b,c) => s"$a-$b-$c"
  }

  println(pm101)
  println()
  println()

//  Using the getters of tuple
  println(t._1)
  println(t._2)
  println(t._3)

  // Decomposition without PM
  val (a,b,_) = t
  println(a)
  println(b)

  println()
  println()

  // Tuple example 5
  val (x,y,z) = (5,"John",3)
  println(y * z)

  // implementing the unapply method
  case class Person(name:String, age:Int)
  object Person{
//    def unapply(p:Person): Option[(String, Int)] = Some(p.name,p.age)
    def unapply(p:Person): Option[(String, Int)] = if(p.name.equalsIgnoreCase("James Bond")) None else Some(p.name,p.age)
  }



}