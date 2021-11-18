package Unit_5.Chap_27

/**
 * Project: Scala_with_Daniela
 * Package: Unit_5.Chap_27
 *
 * User: elvis 😎😎
 * Date: 27/10/2021
 * Time: 08:57
 *
 * Happy Coding
 */
/*
  Lists allows you to represent an immutable order sequence of elements
  List is a DS used to represent a sequence of items of the same kind in a given order

  If something is in the scala package, there is no need to import it
  You can't initialize an instance of List, because its a sealed abstract class


  Varargs is an inherited concept from Java.
  The compiler can infer the type of list

  Nil is a case object(A singleton with a meaningful String representation)
  If a list contains more than one type it is inferred as List[Any]

  NB, :: is a final case class in Scala. Normally referred to as the Cons Class
  List = Nil + ::
  Nil -> reps an empty list
  :: -> reps a non-empty list

  Prepending to a list is efficient, has O(1) -> time complexity
  Appending to a list is expensive, has O(n) -> time complexity
 */

case class Contact(name:String,surname:String,number:String)


object Lists104 extends App{
  val martin = Contact("Martin","Odersky","+123546987")
  val daniela = Contact("Daniela","Sfregola","+789456123")
  val john = Contact("John","Pretty","+459678123")
  val elvis = Contact("Elvis","Amoako","+985376214")

  val contacts = List(martin,daniela)
  val list_1: List[Double] = List(1,2,34,90.0)
  val list_2 = List("Hello","World")
  val list_with_cons: ::[Int] = ::(1,::(2,Nil))
  val list_with_cons_2: List[Int] = 1::2::3::4::Nil
  val moreContactsAppended = contacts :+ john // Adding one element, and creating a new list in the process
  val moreContactsPrepended = elvis +: moreContactsAppended


  println(contacts)
  println(list_1)
  println(list_2)
  
  println()
  println(list_with_cons)
  println(list_with_cons_2)

  println()
  println("Appending and Prepending")
  println(moreContactsAppended)
  println(moreContactsPrepended)

  println()
  // Concatenating 2 lists
  println("Concatenating lists with ++")
  val add_2_lists = List(1,2,89) ++ (65::43::90::Nil)
  println(add_2_lists)


  //  Pattern Matching on Lists
 // Imagine you need a function to ensure you always have at least 2 contacts
 def validateContacts(contacts: List[Contact]): List[Contact] = contacts match{
   case Nil => throw new IllegalStateException("Invalid empty address book! Please provide at least two contacts")
   case a::Nil => throw new IllegalStateException(s"Only contact ${a.name} ${a.surname} found.Please provide at least another one")
   case cs => cs
 }

  // Function to get the surnames of contacts
  def getSurnames(contacts:List[Contact]):List[String] = contacts match {
    case Nil => Nil
    case head::tail => head.surname :: getSurnames(tail)
  }

  println(getSurnames(moreContactsPrepended))

  // A function that uses PM to sum all integers in a List[Int]
  def sumInts(listOfInts:List[Int]):Int = listOfInts match{
    case Nil => 0
    case head::tail => head + sumInts(tail)
  }
  println(sumInts(add_2_lists))

  // A function that uses PM to filter all even numbers
  def filterEven(listOfNums:List[Int]):List[Int] = listOfNums match {
    case Nil => Nil
    case head::tail =>if(head%2 == 0)  head :: filterEven(tail) else filterEven(tail)
  }


  println(filterEven(add_2_lists))

}