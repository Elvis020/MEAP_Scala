package Unit_6.Chap_40

import java.time.LocalDate
import scala.util.{Failure, Success, Try}

/**
 * Project: Scala_with_Daniela
 * Package: Unit_6.Chap_40
 *
 * User: elvis 😎😎
 * Date: 20/11/2021
 * Time: 22:35
 *
 * Happy Coding
 */

/*
  The class Try allows you to control computations that may throw exceptions. And it forces you to provide instructions for both success and failure at compile time

  Whenever possible, use a Try instead of a try-catch expression

  The Try class has 2 possible implementation: Success and Failure
  When creating an instance of Failure, you need to provide an instance of Throwable

  Try has implementations for .map,.flatMap etc


 */

object Error_Handling_with_Try extends App{

  case class Student(id:Int,name:String,topics:Set[String])
  case class ExamSession(title:String,localDate:LocalDate,topic:String)
  case class Registration(studentId:Int,examSession: ExamSession,localDate: LocalDate = LocalDate.now())


  // A function that ensures students register for exam sessions of topics they selected
  def register(student: Student,examSession: ExamSession): Try[Registration] = {
    if(student.topics.contains(examSession.topic)) Success(Registration(student.id,examSession))
    else Failure(new IllegalStateException(s"Student ${student.id} is missing topic ${examSession.topic}"))
  }


  // Creating instances of Try
  val a: Try[Int] = Success(23)
  println(a)
//  val b: Try[Int] = Success("23")// Won't compile, type mismatch

  val c:Try[String] = Failure(new Exception("It failed"))
  println(c)



  // Using the apply method in Try
  def register_2(student: Student,examSession: ExamSession): Try[Registration] = {
    Try{
      if(student.topics.contains(examSession.topic)) Registration(student.id,examSession)
      else throw new IllegalStateException(s"Student ${student.id} is missing topic ${examSession.topic}")
    }
  }


  // Creating instances using the Try.apply method
  val f = Try(10/0)
  val g = Try(10/5)

  println()
  println(f)
  println(g)


  // A function that converts an instance of Either[Throwable,T] into Try[T]
  def toTry[T](e:Either[Throwable,T]): Try[T] = e match{
    case Left(ex) => Failure(ex)
    case Right(ex) => Success(ex)
  }


  // Using the map method

  println()
  // A function that extract the date of exam registration
  def getDateRegistration(examRegistration:Try[Registration]) =
    examRegistration.map(_.localDate)


  // map can be used to transform the value of a successful computation
  val o = Try(5).map(_ *200)
  println(o)


  // If instance is of type Failure, nothing happens
  val p = Try(5/0).map(_ *200)
  println(p)

  // A function that transforms an instance of Try[Double] to Try[Float]
  def toFloat(t:Try[Double]): Try[Float] = t.map(_.toFloat)

  // Using the flatten method
  def getNextExamSession(topic:String):Try[ExamSession] = ???

  // flatten method here unifies all the failures independently of what caused them
  def registerForNextExamSession(student: Student,topic:String) = {
    getNextExamSession(topic).map{
      examSession => register(student,examSession)
    }.flatten
  }

  println()
  // Examples of Try with flatten
  val t = Try(Try(89)).flatten
  println(t)

  val t2 = Try(Try(89/0)).flatten
  println(t2)

//  val t3 = Try(9).flatten // flatten works on nested structure so it won't even compile

  def superFlatten[T](tryT:Try[Try[Try[T]]]): Try[T] = tryT.flatten.flatten


  // Re-writing the registerForNextExamSession using flatMap
  def registerForNextExamSession_2(student: Student,topic:String) = {
    getNextExamSession(topic).flatMap{
      examSession => register(student,examSession)
    }
  }
  println()

  // Examples using flatMap
  val k = Try(32).flatMap(n => Try(n * 1))
//  val k_2 = Try("32").flatMap(n => Try(n / 43)) // Would not compile
  val k_3 = Try(0).flatMap(n => Try(8/n))

  println(k)
  println(k_3)

  def findStudent(id:Int):Try[Student] = ???
  def findStudent(id:String): Try[Student] = Try(id.toInt).flatMap(findStudent)

  // Rewriting registerForNextExamSession using for-comprehension
  def registerForNextExamSession_3(student: Student,
                                   topic: String) = for{
    examSession <- getNextExamSession(topic)
    registration <- register(student, examSession)
  } yield registration
  // NB: You can use for-comprehension in place of flatMap and map operations that are chained

  val for_ex = for{
    n <- Try(1)
    res <- Try(2/n)
  } yield res
  println(for_ex)

  // Rewriting the findStudent(id:String) to use for-comprehension
  def findStudent_2(id:String): Try[Student] = for{
    i <- Try(id.toInt)
    student <- findStudent(i)
  } yield student

  // A function to check if it was able to find an exam session
  def examSessionExists(e:Try[ExamSession]): Boolean = e.isSuccess

  // A function to retrieve a selected exam session or to provide a alternative one
//  val defaultExamSession:ExamSession = ???
//  def getExamSession(e:Try[ExamSession]): ExamSession = e getOrElse defaultExamSession
  /*
    The Try class has some few helper functions: isSuccess, isFailure, getOrElse
   */
  println()

  val t_ex1 = Try(5/2).isSuccess
  println(t_ex1)

  val t_ex2 = Try(5/0).isFailure
  println(t_ex2)

  println()

  // A function that does not throw an exception when trying to convert toBoolean
  def toSafeBoolean(text:String): Boolean = {
    if(Try(text.toBoolean).isSuccess) true
    else false
  }
  def toSafeBooleanAlternative(text:String): Boolean = Try(text.toBoolean).getOrElse(false)

  val test_3 = toSafeBoolean("true")
  val test_3c = toSafeBoolean("false")
  val test_3b = toSafeBoolean("rue")
  println(test_3)
  println(test_3c)
  println(test_3b)

  println()
  case class Person(age:Int,name:String)
  // A function that takes a text and parses it into a Person instance
  def stringToPerson(text:String) = {
    val splitter = text.split(",",2)
    val left = splitter(0).strip()
    val right = splitter(1).strip()
    Try(Person(left.toInt,right).toString) getOrElse("Couldn't parse data")
  }

  println(stringToPerson("21, Jane Doe"))
  println(stringToPerson("true, Jane Doe"))
  println(stringToPerson("Jane Doe,21"))

}