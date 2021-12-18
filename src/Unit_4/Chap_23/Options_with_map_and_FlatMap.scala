package Unit_4.Chap_23

import Unit_4.Chap_24.Option_with_for_comprehension.Student

/**
 * Project: Scala_with_Daniela
 * Package: Unit_4.Chap_23
 *
 * User: elvis 😎😎
 * Date: 28/09/2021
 * Time: 21:41
 *
 * Happy Coding
 */
object Options_with_map_and_FlatMap extends App{
  // Transforming Options
  case class Car(model:String,owner:Option[Person],registrationPlate:Option[String])
  case class Person(name:String,age:Int,driversLicense:Option[String])



  def ownerOfCar(car:Car): Option[String] = car.owner match {
    case Some(p) => Some(p.name)
    case None => None
  }


  // rewriting ownerOfCar method using map
  def ownerOfCar_2(car:Car): Option[String] = car.owner.map(_.name)

  def extractingRegistrationPlate(car:Car): Option[String] = car.registrationPlate.map(_.toUpperCase)

  val abu = Person("Abu",32,Some("KK7866"))
  val car_1 = Car("Toyota Seqia",Some(abu),Some("LL3112"))
  println(extractingRegistrationPlate(car_1))


  // The flatten method is used combine nested optional values
  def ownerDrivingLicense(car: Car): Option[String] = car.owner.map(_.driversLicense).flatten


  // Super-flatten example
  def superFlatten(instance:Option[Option[Option[String]]]): Option[String] = instance.flatten.flatten


  // FlatMap function: It is the ad-hoc function for map+flatten
  def ownerDrivingLicense_2(car: Car): Option[String] = car.owner.flatMap(_.driversLicense)


  // Check her implementation
  def ownerBelowAge(car:Car, resolvedAge:Int): Option[String] ={
    car.owner.flatMap{
      p => if(p.age < resolvedAge) Some(p.name) else None
    }
  }

  def ownerBelowAge_3(car:Car, age:Int): Option[String] = {
    for{
      a <- car.owner.flatMap{
        p => if(p.age < age) Some(p.name) else None
      }
    } yield a
  }

  println( "Owner car issue => " + ownerBelowAge_3(car_1, 10))
  /*
    Flatmap allows you to chain multiple Optionals together
   */


  // TODO - Give it to David and Others to try it
  case class Assistant(id:Long, name:String)
  case class Professor(id:Long, name:String, assistant:Option[Assistant])
  case class Student(id:Long, name:String,professor:Option[Professor])


  /*
    1. Retrieve the name of a tutor, given a student
    2. Find the id of the assistant of a professor tutoring a given student
    3. Return a given student only if having a tutor with a given id
   */
  def retrieveTutor(student: Student): Option[String] =student.professor.map(_.name)
  def assistantID(student: Student): Option[String] = student.professor.flatMap(_.assistant).map(_.name)
  def specialStudent(student: Student): Product = student.professor.map(_.id) match {
    case Some(g) => student
    case None => None
  }


  def specialStudent_2(student: Student): Option[Student] = student.professor.map(_.id).flatMap{
    g => if(g.getClass==Long) Some(student) else None
  }

  // If you have nested Options and you want to use conditions, use flatMap{}, else use flatMap()
}