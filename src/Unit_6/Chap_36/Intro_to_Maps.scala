package Unit_6.Chap_36

import java.time.LocalDate
import scala.collection.immutable

/**
 * Project: Scala_with_Daniela
 * Package: Unit_6.Chap_36
 *
 * User: elvis 😎😎
 * Date: 17/11/2021
 * Time: 06:38
 *
 * Happy Coding
 */
/*
  In Scala, Map is an immutable data structure that stores a set of keys, with each of them associated with a value.You can transform entries of a Map, using the flatMap and Map functions

  The keys in a Map are unordered and must be unique(just like Sets)
  Scala represents each key-value association, a.k.a entry with a tuple. Each instance of a Map is of type Map[K,V]

  The operation of adding and removing from an element is fast, because it occurs at constant time

  Removing a non-existent key in a map returns the original data structure
  Map also has access to methods like map and flatMap

  When you apply map over a Map, you are iterating through each tuple
  Note that you cannot flatten a Map in Scala, the compiler will complain

  Map and List are both implementation of Iterable, so they can be mixed when using flatMap

 */

case class StudentWithSession(id:Int,name:String)
case class ExamSession(title:String,localDate:LocalDate)


object Intro_to_Maps extends App{

  val historySession = ExamSession("History",LocalDate.now().plusDays(30))
  val chemistrySession = ExamSession("Chemistry",LocalDate.now().plusDays(10))

  val jon = StudentWithSession(1,"Jon Snow")
  val davos = StudentWithSession(2,"Ser Davos")
  val arya = StudentWithSession(3,"Arya Stark")

//  val registrations = Map(
//    (historySession,List(jon,davos)),
//    (chemistrySession,List(arya)),
//  )
  // OR
  val registrations: Map[ExamSession, List[StudentWithSession]] = Map(
  historySession -> List(jon,davos),
  chemistrySession -> List(arya),
  )



  val map_ex_1 = Map((1,"hi"),(2,"scala"))
  println(map_ex_1)

  val map_ex_2 = Map((42.3,32))
  println(map_ex_2)

  val map_ex_3 = Map()
  println(map_ex_3)

  val map_ex_4 = Map.empty[String,Double]
  println(map_ex_4)

  println()
  println("Creating tuples")
  // Creating tuples
  val tup1 = ("hello","world")
  println(tup1)

  val tup2 = "hey" -> "there"
  println(tup2)

  println()
  println("Map of days of the week")
  val daysOfWeek: Map[Int, String] = Map(
    1 -> "Monday",
    2 -> "Tuesday",
    3 -> "Wednesday",
    4 -> "Thursday",
    5 -> "Friday",
    6 -> "Saturday",
    7 -> "Sunday"
  )
  println(daysOfWeek)

  // Adding and removing elements from a Map
  println()
  println("Adding and removing elements from a Map")
  val mathSession = ExamSession("Math",LocalDate.now())

  // Adding mathsSession and removing chemistrySession
  val newRegistration = (registrations + (mathSession -> List(davos))) - chemistrySession
  println(newRegistration)

  // Removing from a map, all you need is the key
  val map_ex_5 = Map(1 -> "hello",2 -> "Goodbye",3 -> "Adios") - 3
  println(map_ex_5)

  val map_ex_6 = Map(32.4 -> "ok") - 3
  println(map_ex_6)


  // Merging two Maps
  def mergeMaps(regA:Map[ExamSession, List[StudentWithSession]],
                regB:Map[ExamSession, List[StudentWithSession]]): Map[ExamSession, List[StudentWithSession]] = regA ++ regB

  val map_ex_7 = Map(1 -> "hello") ++ Map(2 -> "Scala")
  println(map_ex_7)

  // You can use the -- method to remove entries from a Map
  val map_ex_8 = Map("Rome" -> "Italy","London" -> "UK") -- Set("Rome","Paris")
  println(map_ex_8)

  val weekDays = daysOfWeek -- Set(7,6)
  println(weekDays)
  println()
  println()

  // A function that tracks the number of registrations for each exam session
  def registrationPerSession_1(registration:Map[ExamSession,List[StudentWithSession]]) = registration.flatMap{
    reg => Map(reg._1 -> reg._2.size)
  }
  def registrationPerSession_2(registration:Map[ExamSession,List[StudentWithSession]]) = registration.map{
    case (examSession,listOfStu) => examSession -> listOfStu.size
  }

  println(registrationPerSession_1(registrations))
  println(registrationPerSession_2(registrations))

  // Swapping key and value using map and flatMap
  val map_ex_9: Map[String, String] = map_ex_8.flatMap(value => Map(value._2 -> value._1))
  val map_ex_10: Map[String, String] = map_ex_8.map(value => (value._2 -> value._1))

  println(map_ex_9)
  println(map_ex_10)

  // Replacing ._2 and ._1 for code readability
  val map_ex_11: Map[String, Int] = map_ex_7.map{
    case (key,value) => value -> key
  }
  println(map_ex_11)

  // You can also transform a Map into any type, and not only tuples
  val map_ex_12 = Map("Rome" -> "Italy","London" -> "UK") .map{
    case(capital, country) => s"$capital is the capital of $country"
  }
  println(map_ex_12)

  // What will this return. Ans: It updates the entry so therefore, it always returns the second entry
  val map_ex_13 = Map("hello"->1,"goodbye"->1).map{
    case (w,n) => (n,w)
  }
  println(map_ex_13)

  println()


  /* Using FlatMaps in Maps */
  // A function to filter students by a given list of Ids
  def filterStudentsById(
                          registration:Map[ExamSession, List[StudentWithSession]],
                          listOfIds:List[Int]) = {
    registration.flatMap{
      case (exam,studs) => {
        val matches = studs.filter(s => listOfIds.contains(s.id))
        if(matches.nonEmpty) List(exam -> matches) else Nil
      }
    }
  }


  // A function that filters entries based on positive numbers
  val map_ex = Map(1->2,0->2,2->0).flatMap{
    case (k,v) => if(k>0 & v>0) Some(k,v) else None
    // Options are automatically converted to List and List and Map are the same when using flatMap
  }
  // A function that multiplies the  entries
  val map_ex_ = Map(1->2,0->2,2->0).flatMap{
    case (k,v) => if(k>0 & v>0) Some(k*v) else None // Converts to List, since result is not key-value pair
  }
  println(map_ex)
  println(map_ex_)


  val map_ex_22 = Map("hello"->1,"scala"->10).flatMap{
    case(w,n) => if(w.length > n) Some(w -> n) else None
  }
  println(map_ex_22)


  /* Using for-comprehensions in Maps */
  // Re-implementing the filterByStudentIds with for-yield
  def filterByStuIds( registration:Map[ExamSession, List[StudentWithSession]],
                      listOfIds:List[Int]) = for{
    (exam,students) <- registration
    matches = students.filter(s => listOfIds.contains(s))
    if(matches.nonEmpty)
    } yield exam -> matches


  // Re-implementing map_ex using for-comprehension
  val map_exam = Map("hello"->1,"scala"->10)
  val map_ex_reimplementation: Map[String, Int] = for{
    (k,v) <- map_exam
    if(k.length>v)
  } yield k->v
  println(map_ex_reimplementation)

  println()

  val capital_to_country:Map[String,String] =
    Map("Accra" -> "Ghana",
        "London" -> "UK",
        "Sao Paulo" -> "Brazil",
        "Beigin" -> "China")
  val country_to_continent:Map[String,String] =
    Map("Ghana" -> "Africa",
        "UK" -> "Europe",
        "Brazil" -> "South America",
        "China" -> "Asia")

  // A function to link each capital to its continent
  def linkCapitalToContinent(c1:Map[String,String],
                             c2:Map[String,String]) = for{
    (capital,country_c1) <- c1
    (country_c2,continent) <- c2
    if(country_c1 == country_c2)
  } yield capital -> continent

  println(linkCapitalToContinent(capital_to_country, country_to_continent))
}