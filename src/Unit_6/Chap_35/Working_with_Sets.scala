package Unit_6.Chap_35

import Unit_6.Chap_34.Student

/**
 * Project: Scala_with_Daniela
 * Package: Unit_6.Chap_35
 *
 * User: elvis 😎😎
 * Date: 15/11/2021
 * Time: 09:04
 *
 * Happy Coding
 */

/*
  Sets in Scala mimics the concept of mathematical sets

  Common methods used on Sets in Scala
  ------------------------------------
  union is the combination of 2 sets and eliminating duplicates,It has an alias method of ++, and this can be used in place of .union()

  intersect is used to select elements that happen to be in both sets. It has an alias method of &

  diff returns all elements in the original set(left side) not in the given set(right side). It has an alias method of --

  In scala, both List and Set implement a common interface called Iterable
  There are 2 exception to list that cannot b applied to a set:
    1. You cannot sort a set since its an unordered type of collection
    2. You cannot use the method .distinct() because it elements are already unique




 */

object Working_with_Sets extends App{
  // Select students based on their topic
  def eitherTopic(topicA:Set[Student],topicB:Set[Student]): Set[Student] = topicA.union(topicB)
  def bothTopic(topicA:Set[Student],topicB:Set[Student]): Set[Student] = topicA.intersect(topicB)
  def topicAnoTopicB(topicA:Set[Student],topicB:Set[Student]): Set[Student] = topicA.diff(topicB)
  def topicBnoTopicA(topicA:Set[Student],topicB:Set[Student]): Set[Student] = topicB.diff(topicA)



  println("Union")
  val union_example_1 = Set(1,2,3).union(Set(4,5,6))
  println(union_example_1)

  val union_example_2 = Set(1,2,3) ++ (Set(4,5,6))
  println(union_example_2)

  println()
  println("Intersection")

  val intersect_example_1 = Set(1,2,3).intersect(Set(2,5,6))
  println(intersect_example_1)

  val intersect_example_2 = Set(1,2,3) & (Set(4,1,6))
  println(intersect_example_2)


  println()
  println("Diff")

  val diff_example_1 = Set(1,2,3) diff (Set(1,5,6))
  println(diff_example_1)

  val diff_example_2 = Set(1,2,3) -- (Set(4,1,6))
  println(diff_example_2)


  // Are the examples below equivalent? -> Nope they are not
  val ex1 = Set(1,2,3).diff(Set(3,4)) //Set(1,2)
  val ex2 = Set(3,4).diff(Set(1,2,3)) // Set(4)

  println(ex1)
  println(ex2)


  println()
  println("Analyzing a group of students")

  // Check if a student with a specific id is in the group
  def existsById(listOfStudents:Set[Student],id:Int): Boolean = listOfStudents.exists(_.id == id)

  // Filter those that have selected a given topic
  def selectByTopic(listOfStudents:Set[Student],topic:String): Set[Student] =
    listOfStudents.filter(_.topics.contains(topic))

  // Find the one that is following the most topics
  def studentWithMaxTopics(listOfStudents:Set[Student]): Student = listOfStudents.maxBy(_.topics.size)

  println()
  println("Sum in Range example")

  // Sum in range exercise
  def sumInRange(doubleNums:Set[Double]): Double = (doubleNums.filter(_<100) & doubleNums.filter(_>0)).sum
  val test_1: Set[Double] = Set( 0.5, -1, 0,50.5, 99, 100)
  println(sumInRange(test_1))

  // A function that returns a new set containing students that are taking any of the topics
  case class NewStudent(id:Int, name:String, topics:Set[String])
  def selectedStudTakingTopics(listOfStudents:Set[NewStudent],listOfTopics:Set[String]): Set[NewStudent] = for{
    student <- listOfStudents
    topic <- listOfTopics
    if(student.topics.contains(topic))
  } yield student
}