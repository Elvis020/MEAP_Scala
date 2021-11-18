package Unit_5.Chap_29

import Unit_5.Chap_27.Contact
import Unit_5.Chap_28.DetailedContact

/**
 * Project: Scala_with_Daniela
 * Package: Unit_5.Chap_29
 *
 * User: elvis 😎😎
 * Date: 01/11/2021
 * Time: 00:57
 *
 * Happy Coding
 */
object Working_with_List_Properties extends App{
  private def reject(msg:String) = throw new IllegalArgumentException(msg)
  private def validateNonEmptyContacts(contactList:List[Contact]): List[Contact] = {
    if(contactList.nonEmpty) contactList
    else reject("Address book cannot be empty")
  }

  private val maxConTactSize = 1000
  private def validateWithinContactSize(contactList:List[Contact]): List[Contact] = {
    val contactListSize = contactList.size
    if(contactListSize>maxConTactSize) reject(s"Address book collection too big, found $contactListSize contacts. Maximum contacts allowed is $maxConTactSize")
    else contactList
  }
  def validateAddressBook(listOfContacts:List[Contact]): List[Contact] = {
    validateWithinContactSize(listOfContacts)
    validateNonEmptyContacts(listOfContacts)
  }


  // Function to verify if a contact is present
  def isContactPresent(contact: Contact, listOfContacts:List[Contact]): Boolean = listOfContacts.contains(contact)

  // A function to check if a contact with a given name is present
  def isContactNamePresent(name:String, contactList:List[Contact]) = contactList.exists(_.name == name)

  // A function to count the number of contacts from a given company in an address book
  def sizeOfContacts(listOfContacts:List[DetailedContact], company:String) = listOfContacts.map(_.company.getOrElse("None") == company).size
  def sizeOfContacts_Alt(listOfContacts:List[DetailedContact], company:String) = listOfContacts.count(_.company.contains(company))



  val list_with_exists_tester = List(1,2,3,4,5,6).exists(e => e>2 & e<5)
  println(list_with_exists_tester)

//  println()

  val list_with_count_tester = List(1,2,3,4,90,11,5,6).count(_>15)
  println(list_with_count_tester)


  // Miscellaneous try
  val text = List("Welcome","to","Scala")
  class A(i:Int)
  case class B(i:Int)

  val example_1 = text.contains("scala") // false
  val example_2 = text.exists(_.endsWith("me")) // true
  val example_3 = text.count(_.contains("o")) // 2
  val example_4 = List(new A(1)).contains(new A(1)) // false
  val example_5 = List(new B(1)).contains(new B(1)) // true

  println(example_1)
  println(example_2)
  println(example_3)
  println(example_4)
  println(example_5)

  println(List(new A(1)))
  println(List(new B(1)))

//  Creating an empty List for a given type
  val listo: List[Nothing] = List()
  println(listo)

  // listo is an empty list of Nothing, in case we want an empty List of an Int
  List().asInstanceOf[List[Int]] //

  case class CustomPerson(name:String, age:Int)

  // A function to check if an individual with a name is in the list of people
  def ensureNameIsInList(listOfPeople:List[CustomPerson],givenName:String): Boolean = listOfPeople.exists(_.name == givenName)


}