package Unit_5.Chap_30

import Unit_5.Chap_28.DetailedContact

/**
 * Project: Scala_with_Daniela
 * Package: Unit_5.Chap_30
 *
 * User: elvis 😎😎
 * Date: 07/11/2021
 * Time: 18:40
 *
 * Happy Coding
 */
object Working_with_List_Element_Selection extends App{
//  Continuing on the DetailedContact class

//  A function to select a contact based on its position on the address book
  def getSpecificContact(listOfContact:List[DetailedContact],index:Int): DetailedContact = listOfContact(index)
  def getSpecificContact_2(listOfContact:List[DetailedContact],index:Int): DetailedContact = listOfContact.apply(index)

  // When selecting an element in the sequence, you can use the apply method

  val apply_example = List(1,2,3).apply(2)
  println(apply_example)

//  Note: .apply() is an impure function, because, it throws an IndexOutOfBoundsException, when the index is out of bounds.
//  head is the same as apply(0). headOption is the pure alternative


  val headOptionExample = List().headOption
  val headOptionExample_2 = List(1,2,3,4).headOption
  println(headOptionExample)
  println(headOptionExample_2)

  // Implement a method like apply but does not throw an indexOutOfBounds Exception
  def safeApply[A](listOfSomething:List[A],n:Int): Option[A] = n match {
    case d if(d!=0 & d<=listOfSomething.size) => Some(listOfSomething.apply(d))
    case _ => None
  }


  // A function to find the first contact, with a name starting with a specific text
  // the .find() method is used when you want to find something by its feature, not by its position
  def findByName(addressBook:List[DetailedContact],name:String): Option[DetailedContact] = addressBook.find(_.name.startsWith(name))


  val findExample_1 = List(1,2,3,0,4,5).find(_ >3) // gets the first element that confirms the predicate
  println(findExample_1)

  // A function that finds by company
  def findByCompany(listOfContacts:List[DetailedContact],company:String): Option[DetailedContact] =
    listOfContacts.find(_.company.get.equalsIgnoreCase(company))

  // A function to find the contact with the shortest fullname(surname+firstname)
  def findShortestFullName(listOfContacts:List[DetailedContact]): DetailedContact = {
    listOfContacts.minBy(contact => contact.name.length + contact.surname.length)
  }


  // A function to find the last contact by FullName
  def lastContactByFullName(listOfContacts:List[DetailedContact]) = {
    listOfContacts.maxBy(contact => s"${contact.surname} ${contact.name}")
  }

  // Note that when a list is empty and you try the min/max operation on it, it gives you an UnSupportedOperationException
  // In Scala, many types have pre-defined order
  val mx_1 = List(1,2,3).max
  println(mx_1)

  val mx_2 = List("scala","hello").max
  println(mx_2)

  // By default, scala sorts string in an alphabetical order
  // The method maxBy() returns a maximum lement according to a given parameter

  case class Foo(n:Int,text:String)
  val mx_3 = List(Foo(1,"z"),Foo(200,"a")).maxBy(_.n)
  println(mx_3)

  // We can define a custom ordering rule on Foo. We can use a tuple when we want to provide multiple criteria for ordering
  val mx_4 = List(Foo(1,"zz"),Foo(200,"a"),Foo(321,"fff")).maxBy(foo => (foo.text.length,foo.text))
  println(mx_4)

  case class Awards(award:String)

  case class Movie(title:String,
                   director:String,
                   publicationYear:Int,
                   shortDescription:String,
                   listOfAwards:List[Awards])

  // A function to find the most recent movie production, with the most awards
  def findMostAwardsByProductionYear(listOfMovies:List[Movie]) = {
    listOfMovies.maxBy{
      movie => (movie.publicationYear,movie.listOfAwards.length)
    }
  }

}