package Unit_5.Chap_32

import Unit_5.Chap_28.DetailedContact

import scala.util.Random

/**
 * Project: Scala_with_Daniela
 * Package: Unit_5.Chap_32
 *
 * User: elvis 😎😎
 * Date: 10/11/2021
 * Time: 00:00
 *
 * Happy Coding
 */
object Working_with_List_Sorting_etc extends App {
  // A function that sorts a list of contacts first by surname and then by name
  def sortByName(listOfContacts:List[DetailedContact]): List[DetailedContact] =
    listOfContacts.sortBy(contact => (contact.name, contact.surname))


  // The .sorted method sorts a list with a given order of type List[A]
  val sortNums = List(1,2,-32,88,54,21,99,4,66).sorted
  println(sortNums)

  val sortAlps = List("margaret","Hello","koo").sorted
  println(sortAlps)

  val sortingEmptyList = List.empty[Double].sorted
  println(sortingEmptyList)


  println()
  println()

  // .sortBy() sorts a lis according to a certain criterion
  val sortInDescOrder = List(3.0,2.0,-9.0,44.32).sortBy(i => -i)
  println(sortInDescOrder)

  case class A(n:Int,text:String)
  val listOfAParticularClass = List(A(1,"ok"),A(65,"michigan"),A(-89,"kazumi"),A(12,"tatami"))
  println(listOfAParticularClass.sortBy(item => (item.text, item.n))) // sorts by text and then by n

  println()
  println()

  // The reverse method returns a new list containing elements in the reverse order
  val rev_example_1 = List(9,6,21,55,47,61,88).reverse
  println(rev_example_1)

  val rev_example_2 = List.empty[String].reverse
  println(rev_example_2)

  println()
  println()


  // You can change the order of a sequence randomly, using the Random class
  val random_example = Random.shuffle(List(1,2,3,4,5,65))
  println(random_example)

  // Note that this will not compile, cuz the compiler doesn't know which ordering to use
//  val sortingWithoutType = List().sorted
//  println(sortingWithoutType)

  println()
  println()

  // Converting a list to String
  // A function list the surname and name of the first n contacts
  def describeFirstN(n:Int, listOfContacts:List[DetailedContact]) = {
    listOfContacts.take(n).map(_.toPrettyPrint).mkString("\n")
  }

  // You can use mkString when building a string that represents a sequence ans its items. It concatenates them using a default separator ""
  val mkstring_example_1 = List("Hello","Arnold").mkString
  println(mkstring_example_1)


  val mkstring_example_2 = List("Hello","Arnold").mkString(",")
  println(mkstring_example_2)

  // using prefix,suffix and separator with mkString
  val mkstring_example_3 = List("Hello","Arnold").mkString("[","-","]")
  println(mkstring_example_3)

  // using prefix,suffix and separator with mkString
  val mkstring_example_4 = List().mkString("[","-","]")
  println(mkstring_example_4)


  println()
  println()

  // Examples with class A
  class B(i:Int)
  case class C(i:Int)
  val listsOfBs = List(new B(1),new B(3),new B(90)).mkString(",")
  println(listsOfBs)

  val listsOfCs = List(C(1),C(3),C(90)).mkString(",")
  println(listsOfCs)

  println()
  println()

  // A function that gets the total number of contacts with phone numbers in an address book
  def getTotalContactWithNums(listOfContacts:List[DetailedContact]) =
    listOfContacts.map(_.numbers.size).sum



  // Note that the .sum method uses an implicit of Numeric to do the addition, so sum of String wont work
  val sum_example_1 = List(1,2,3,4,5,6,7,8,9).sum
  println(sum_example_1)

  val sum_example_2 = List(1.11,2.32,3.0).sum
  println(sum_example_2)

  val sum_example_3 = List.empty[Float].sum
  println(sum_example_3)

//  val sumWithStr = List("Hello","ok").sum // Wont work


  println()
  println()

  // A function to sum all numbers from 0 to n-inclusive
  def sumOfFirstN(n:Int):Int = n match {
    case n if (n == 1) => 1
    case d if d>1 => sumOfFirstN(n-1)+n
  }

  def sumOfFirstN_Alt(n:Int): Int = (0 to n).toList.sum

  println(List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).sum)
  println(sumOfFirstN(10))
  println(sumOfFirstN_Alt(10))
  println(sumOfFirstN_Alt(-10))

  println()
  println()
  /*
    groupBy can be used to group elements based on their characteristics, and it creates a key-value pair
   */

  // A function that displays contact by company
  def displayByCompany(listOfContacts:List[DetailedContact]) = listOfContacts.groupBy(_.company)

  val groupBy_example_1 = List(A(1,"z"),A(2,"a")).groupBy(_.text)
  println(groupBy_example_1)

  val groupBy_example_2 = List("hello","gooodbye","amina","akosua").groupBy(_.length)
  println(groupBy_example_2)

  val groupBy_example_3 = List.empty[String].groupBy(_.contains("a"))
  println(groupBy_example_3)


  // A function that takes a list of contacts and groups them by the first letter of the surname

  def perLetter(listOfContacts:List[DetailedContact]) = listOfContacts.groupBy(_.surname.headOption.getOrElse(' '))
  def perLetter_Alt(listOfContacts:List[DetailedContact]) = listOfContacts.groupBy(_.surname.charAt(0))

  println()
  println()
  println()


  // Marks question
  case class Mark(examName:String,score:Double,studentIdentifier:String)

  // A function that takes a given sequence of marks, and prints the student identifiers of the top 5 scores
  val mark_1 = Mark("Biology",22.0,"STU1")
  val mark_2 = Mark("Maths",90,"STU43")
  val mark_3 = Mark("Chemistry",12,"STU3")
  val mark_4 = Mark("Physics",55.67,"STU4")
  val mark_5 = Mark("Geography",89,"STU5")
  val mark_6 = Mark("Meta-Physics",48,"STU6")
  val mark_7 = Mark("Organic",33,"STU7")

  def top5StudentIdentifiers(listOfMarks:List[Mark]) = listOfMarks.sortBy(_.score).reverse.take(5).map(_.studentIdentifier)

  val test_1 = top5StudentIdentifiers(List(mark_1,mark_2,mark_3,mark_4,mark_5,mark_6,mark_7))
  println(test_1)
}