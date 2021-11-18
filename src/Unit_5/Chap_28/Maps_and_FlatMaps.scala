package Unit_5.Chap_28

/**
 * Project: Scala_with_Daniela
 * Package: Unit_5.Chap_28
 *
 * User: elvis 😎😎
 * Date: 27/10/2021
 * Time: 18:51
 *
 * Happy Coding
 */
case class DetailedContact(name:String,
                           surname:String,
                           numbers:List[ContactNumber],
                           company:Option[String],
                           email:Option[String]
                          ){
  def toPrettyPrint=s"$surname $name"
}

sealed trait Label
case object Work extends Label
case object Home extends Label
case class ContactNumber(number:String, label:Label)

case class Person(name:String,age:Int)



object Maps_and_FlatMaps extends App{
  // Using map to extract all the surnames in the list of contacts
  def getSurnamesUsingMap(list_of_contacts:List[DetailedContact]): List[String] =
    list_of_contacts.map(_.surname)

  // A function that adds the number 5 to each element in a list
  def plus5(nums:List[Int]): List[Int] = nums.map(_+5)

  val list_of_nums = List(1,2,3,4,5,6,7,8,9,10)
  val list_of_doubles = List(1,2,3,4,5,6,7,8,9,10).map(_.toDouble) :: List(1.0,90.0) ::Nil
  println(plus5(list_of_nums))
  println(list_of_doubles.flatten)

//  Using flatMap, to get all contacts
  def getAllContacts(list_of_contacts:List[DetailedContact]): List[ContactNumber] = {
    list_of_contacts.flatMap(_.numbers)
  }


//  Triple
  def repeat3times(a:Int) = a.toString * 3
  def triple(ns:List[Int]): List[Int] = {
//    ns.flatMap(_.toString*3)
//    OR
//    ns.flatMap(repeat3times)
//    OR
    ns.flatMap(n => List(n,n,n))
  }
  def tripleWitForComprehension(ns:List[Int]): List[Int] = {
    for{
      n <- ns
      i <- List(n,n,n)
    }yield i
  }

  println(triple(List(1, 2, 3)))


//  In case you want to select contacts based on a given list of emails -> without for-comprehension
  def getContactWithEmails(list_of_contacts:List[DetailedContact],list_of_emails:List[String]) = {
    list_of_contacts.flatMap{contact =>
        list_of_emails.flatMap{ email =>
            if(contact.email.exists(_.equalsIgnoreCase(email))) List(contact)
            else Nil
        }
      }
  }

  // NB: You can rewrite flatMaps with for-comprehension
  def getContactWithEmail_v2(list_of_contacts:List[DetailedContact],list_of_emails:List[String]) = {
    for{
      contact <- list_of_contacts
      email <- list_of_emails
      if(contact.email.exists(_.equalsIgnoreCase(email)))
    } yield contact
  }

  def extractPersons18orMore(list_of_people:List[Person]): List[Person] ={
    list_of_people.flatMap(person => if(person.age>18) List(person) else Nil)
  }
  def extractPersons18orMore_v2(list_of_people:List[Person]): List[Person] ={
    for{
      person <- list_of_people
      if(person.age>18)
    } yield person
  }

}