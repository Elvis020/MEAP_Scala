package Unit_5.Chap_31

import Unit_5.Chap_28.DetailedContact

/**
 * Project: Scala_with_Daniela
 * Package: Unit_5.Chap_31
 *
 * User: elvis 😎😎
 * Date: 08/11/2021
 * Time: 21:51
 *
 * Happy Coding
 */

/*
  When working with lists you can drop and take elements based on a certain criteria.
  drop(n) - this creates a new list without the first n elements
  take(n) -  this is a complimentary of drop

  Both take and drop create a new list in the process.
  They return an empty list if the specified item is not found

  takeWile() and dropWhile() takes in a predicate


 */


object Working_wit_List_filtering extends App{

  // A function to store the first n contacts in your address book
  def firstNContacts(listOfContacts:List[DetailedContact],n:Int): List[DetailedContact] = listOfContacts.take(n)


  // Drop in action
  val newList = List(1,2,3,4).drop(2) // Drops the first 2 element and takes what is left
  println(newList)

  val takeList = List(1,2,3,4).take(2) // Takes the first 2 element and forms a lost out of them
  println(takeList)

  val dropWhileExample: List[Int] = List(1,2,3,4,5,6,7,8,9).dropWhile(_ < 3)
  println(dropWhileExample)

  val takeWhileExample: List[Int] = List(1,2,3,4,5,6,7,8,9).takeWhile(_ < 2)
  println(takeWhileExample)


  // Pagination Example ["1","2","3","1","2","3","1","2","3","1","2","3","1","2","3"]
  def paginateData(data:List[String],pageN:Int,pageSize:Int): List[String] = pageN match{
    case 1 => data.take(pageSize)
    case p if p>1 => data.drop((pageN-1)*pageSize)
    case _ => Nil
  }

  // Alternative solution to the paginate function
  def paginate_Alt(data:List[String],pageN:Int,pageSize:Int): List[String] = {
    val toSkip = (pageN-1) * pageSize
    data.drop(toSkip).take(pageSize)
  }

  val tryList = List("a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","1","2","3","1","2","3","1","2","3","1","2","3","1","2","3")


  val example_1 = paginateData(tryList,1,10)
  println(example_1)

  val example_2 = paginateData(tryList,3,10)
  println(example_2)

  val example_3 = paginateData(tryList,4,10)
  println(example_3)


  // A function that identifies all contacts that belong to a particular company
  def fromCompany(listOfContacts:List[DetailedContact],corps:String) = {
    listOfContacts.filter(_.company.exists(_.equalsIgnoreCase(corps)))
//    listOfContacts.filter(_.company == corps)
  }

  println()
  println()
  // The class List has a method called filter that takes in a predicate, and returns a new list containing elements that respect the predicate. And filterNot is the complimentary of filter
  val filterExample_1 = List(1,2,3,4).filter(_>2)
  println(filterExample_1)

  val filterNotExample_1 = List(1,2,3,4).filterNot(_>2)
  println(filterNotExample_1)


  // A function that returns a list of non-negative double numbers
  def nonNegDoubles(listOFDoubles:List[Double]): List[Double] = listOFDoubles.filter(_>0)


  // You can remove duplicates in a list using the distinct method
  // A function that removes duplicates
  def removeDups(listOfContact:List[DetailedContact]): List[DetailedContact] = listOfContact.distinct

  class myA(i:Int)
  val myClassTest = List(new myA(1), new myA(2),  new myA(1))
  println(myClassTest.distinct) // show all, since a new instance is created everytime


  sealed trait NewGenre
  case object ACTION extends NewGenre
  case object COMIC extends NewGenre
  case object DRAMA extends NewGenre

  case class Author(nameOfAuthor:String)
  case class Book(title:String,listOfAuthors:List[Author],genre:NewGenre )

  // Implement a function to return all its drama authors
  val elias = Author("Elias")
  val elvis = Author("Elvis")
  val david = Author("David")

  val charlotte = Author("Charlotte")
  val zee = Author("Zee")
  val nii = Author("Nii")




  val book_1 = Book("Welcome",List(elias,elvis,david),ACTION)
  val book_2 = Book("Hehe",List(elvis,elvis,zee,zee),DRAMA)
  val book_3 = Book("Ok",List(nii,charlotte),COMIC)
  val listOfBooks = List(book_1,book_2,book_3)

  def returnDramaAuthors(listOfBooks:List[Book]): List[Author] = {
    listOfBooks.filter(_.genre == DRAMA).flatMap(_.listOfAuthors).distinct
  }

  println(returnDramaAuthors(listOfBooks))

}