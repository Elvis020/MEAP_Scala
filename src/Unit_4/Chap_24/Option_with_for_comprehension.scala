package Unit_4.Chap_24

import Unit_4.Chap_23.Options_with_map_and_FlatMap.Car

/**
 * Project: Scala_with_Daniela
 * Package: Unit_4.Chap_24
 *
 * User: elvis 😎😎
 * Date: 29/09/2021
 * Time: 07:30
 *
 * Happy Coding
 */


/*
  The alternative syntax to simplify verbose expressions for
  map and flatMap is for-comprehension

  Note that yield returns a value. Use for-comprehension on every class
  that has a flatMap function


  Apart from methods, like map, flatten and flatMap, other methods also
  exist for Optional values, like isDefined eyc


  Note that, isDefined is the opposite of isEmpty
  isDefined returns true if an Optional instance is present, and false if its absent

  There are other methods like: isEmpty,find, getOrElse, exists
 */
object Option_with_for_comprehension extends App{

  // Filtering with for-comprehension
  def ownerDriversLicense(car:Option[Car],ownerName:String): Option[String] = {
    car.flatMap{
      car => car.owner.flatMap({
        person => {
          if(person.name == ownerName) person.driversLicense.map(_.toUpperCase)
          else None
        }
      })
    }
  }


  def ownerDriversLicense_2(car:Option[Car], owner:String): Option[String] = {
    for{
      myCar <- car
      person <- myCar.owner
      if(person == owner)
      drivingLicense <- person.driversLicense
    } yield drivingLicense.toUpperCase
  }

  def foo(optA:Option[Int]): Option[Int] = {
    for{
      a <- optA
      b <- ok(a)
      c <- Some(5 * b)
    } yield c
  }

  def ok(n:Int): Option[Int] = {
    if(n < 5) Some(5 * n)
    else None
  }


  // Trying out examples
  println(foo(Some(1)))
  println(foo(Some(5)))
  println(foo(None))


  // Owner before age for-comprehension
  def ownerBelowAge(car:Car, age:Int): Option[String] = {
    for{
      a <- car.owner.flatMap{
        p => if(p.age < age) Some(p.name) else None
      }
    } yield a
  }

  // Alternatively
//  def ownerBelowAge(car:Car, age:Int): Option[String] = {
//    for{
//      ownerAge <- car.owner
//      if(ownerAge.age < age)
//    } yield ownerAge.name
//  }

  // Using other essential methods include

  // getOrElse
  println(Some(1).getOrElse(-1))
  println(None.getOrElse(-1))

  // find
  println(Some(10).find(_ > 2))
  println(Some(0.3).find(_ > 2))

  // exists
  println(Some(10).exists(_ > 3))
  println(Some(1).exists(_ > 3))
  /*
    Note that using the get method from an Option is considered an anti-pattern,
    get returns a value if present and return NoSuchElementException if absent. Don't
    use get on an Option, to be safe just use .getOption
   */

  def carWithLicensedOwner(optCar:Option[Car]): Option[Car] = {
    optCar.flatMap {
      p => if (p.owner.flatMap(_.driversLicense).isDefined) optCar else None
    }
  }

  // Alternatively 1
//  def carWithLicensedOwner(optCar:Option[Car]): Option[Car] = {
//    optCar.find{
//      p => p.owner.flatMap(_.driversLicense).isDefined
//    }
//  }


  // Alternatively 2
  //    optCar.map(_.owner.flatMap(_.driversLicense)) match {
//    case Some(value) => optCar
//    case _ => None
//  }



  // Student-Professor-Assistant-Relation part II
  case class Student(id: Long, name: String, tutor: Option[Professor])
  case class Professor(id: Long, name: String, assistant: Option[Assistant])
  case class Assistant(id: Long, name: String)

  // TODO - Retrieve the name of the tutor of a given student
  def retrieveNameOfTutor(student: Student): Option[String] = {
    for{
      nameOfTutor <- student.tutor.map(_.name)
    } yield nameOfTutor
  }

  // TODO - Find the id of the assistant of a professor tutoring a given student
  def assistantID(student: Student): Option[Long] = {
    for{
      assist <- student.tutor.flatMap(_.assistant).map(_.id)
    } yield assist
  }

  // TODO - Return a given student only if having a tutor with a given id
  def studentWithTutorWithID(student: Student):Option[Student] ={
    for{
      specialTutor <- student.tutor.map(_.id)
      if(Some(specialTutor).isDefined)
    } yield student
  }

}