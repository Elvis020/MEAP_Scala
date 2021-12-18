package Unit_6.Chap_37

import Unit_6.Chap_36.Intro_to_Maps.{capital_to_country, country_to_continent}
import Unit_6.Chap_36.{ExamSession, StudentWithSession}

import java.time.LocalDate

/**
 * Project: Scala_with_Daniela
 * Package: Unit_6.Chap_37
 *
 * User: elvis 😎😎
 * Date: 18/11/2021
 * Time: 06:15
 *
 * Happy Coding
 */
/*
  Retrieving values associated with a key is a constant time operation.
  Scala offers strategies to retrieve values from a Map
    1. Using get: For a Map[K,V], this returns an Option[V]. Returns Some(v) if its there and a None if its not there

    2. Using getOrElse: This method takes in 2 parameters, 1 as key to get the value, and 2 is the value to use, when the value is absent

    3. Using apply: This does the same thing as .get, but throws an exception if no value is found

    When working with map, you can retrieve all the keys and the values using the .keys and .values respectively
    NB: The map collection implements the iterable interface so it has some of the methods on List


    The key differences btn a Map and a List are:
      1. Cannot sort
      2. Cannot use distinct
      3. Has a contains method that returns true if element exists and false if it doesn't. But it takes a key. Also, the time complexity of the contains method is O(1)


 */


object Working_with_Maps extends App{

  /* Retrieving values for a given key */
  def getStudentWithSession(registration:Map[ExamSession,List[StudentWithSession]],
                            session:ExamSession) =
    registration.getOrElse(session,Nil)

  // Retrieving with .get
  val ex_1 = Map(1->"a",2->"b").get(2)
  println(ex_1)

  val ex_2 = Map(1->"a",2->"b").get(3)
  println(ex_2)

  def defaultValue: String = {
    println("Missing Key!")
    "N/A"
  };
  val ex_3 = Map(1->"a",2->"b").getOrElse(3,defaultValue)
  println(ex_3)
  val ex_4 = Map(1->"a",2->"b").apply(2)
  println(ex_4)


  // A function that returns a capital's country or "unknown"
  def getCountry(capitalToCountry:Map[String,String],
                 capital:String): String = capitalToCountry.getOrElse(capital,"Unknown")


  /* Getting all keys and values */
  // A function to get all the exam sessions in a Map
  def getExamSession(registration:Map[ExamSession,List[StudentWithSession]])={
    registration.keys // returns keys in a Map
  }

  def getStudents(registration:Map[ExamSession,List[StudentWithSession]]) ={
    registration.values.flatten
  }
  println()

  val ex_5: Iterable[String] = Map(1->"a",2->"b").values
  println(ex_5)

  val ex_6: Iterable[Int] = Map(1->"a",2->"b").keys
  println(ex_6)

  // A function to return a list of capitals in a Map
  def getCapitals(capitalToCountry:Map[String,String]): List[String] = capitalToCountry.keys.toList


  // Analyzing exam registrations
  // A function that checks the number of scheduled exams
  def totExamScheduled(registration:Map[ExamSession,List[StudentWithSession]]) = {
    registration.size
  }

  // A function that filters the exam sessions on a given date
  def sessionPerDate(registration:Map[ExamSession,List[StudentWithSession]],date:LocalDate) = {
    // registration.filter(k => k._1 == date) // Method 1
    registration.filter{
      case (session,_) => session.localDate == date
    } // Method 2
  }

  // A function to find the exam session with most students
  def getMostStudentsPerSession(registration:Map[ExamSession,List[StudentWithSession]])= {
    // registration.maxBy(stud => -stud._2.size) // Method 1
    registration.maxBy{
      case(_,studs) => studs.size
    }
  }

  println()

  // Using .contains on Maps
  val ex_7 = Map(1 -> "scala",3 -> "java").contains(2)
  val ex_8 = Map(1 -> "scala",3 -> "java").contains(3)
  val list_1 = List(1,2,3,4,5).contains(2)
  println(ex_7)
  println(ex_8)
  println(list_1)


  // Function that returns the name of the longest capital, when given a map[Country,Capital]
  type Capital = String
  type Country = String

  def longestCapital(capitalToCountry:Map[Capital,Country]): Capital = {
    capitalToCountry.maxBy{
      case (capital,_) => capital.length
    }._1
  }


  // Use the retrieve strategy to map capitals to continent
  def capitalToContinent(c1:Map[String,String],
                        c2:Map[String,String]) = {
    for{
      (k1,v1) <- c1
    } yield k1 -> c2.getOrElse(v1,"unknown")
  }

  val try_1 = capitalToContinent(capital_to_country,country_to_continent)
  println(try_1)

}