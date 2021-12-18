package Unit_2.Chap_10
/*
  Its important to know that you dont have to make every fragment of your code
  accessible. You can protect the access parts of your app and reduce entry
  points of your package. To make it easier to use.

  When a field/member is private , you can only access it from the class you declared it.
  Private access modifiers block the access to your code from anywhere but the current scope

  With protected, the members/fields are only accessible from the class and its subclasses

 */

class Person{
  private val age = 28
}
class Student extends Person


object Scoping_using_Access_Modifiers extends App{
  val student  = new Student
  val person = new Person
//  person.age -> Can't access person.age unless you are in the Person class
//  student.age -> Can't access student.age unless you are in the Person class

  val employee = new Employee
  println(employee.name)

  val k = new Kweku
  println(k.name)
  println(k.kweku_age) // Can only access the protected fields if it has been used in the subclass

}

class Event{
  protected def estimateCosts(attendees:Int): Double = if(attendees<10) 50.00 else attendees*12.34
}


// Using the private access modifier
class Party extends Event {
  private var attendees: Int = 0
  def register(guests:Int): Unit = attendees += guests
  var cost: Double = estimateCosts(attendees)
}

class Employee{
  val name:String = "Unknown"
  protected var age:Int = 25
  private val salary:Double = 0.0
}
class Kweku extends Employee{
  override val name: String = "Kweku"
  val kweku_age = age += 32
}


