package Unit_6.Chap_34

/**
 * Project: Scala_with_Daniela
 * Package: Unit_6.Chap_34
 *
 * User: elvis 😎😎
 * Date: 15/11/2021
 * Time: 05:45
 *
 * Happy Coding
 */
/*
  Set is an immutable representation of a group of elements. Sets and Lists are very common in x'tics.
  The items in a set are unique and have no order.

  Elements of set are unordered
  Adding and removing an element from a set happen at constant time. O(1). But appending to a list is not, but rather O(n), unless prepending to a list

  You can iterate over a Set, using map,flatMap,flatten just like List and Options
  The map on a set is a HOF, that returns an empty Set if nothing is found
 */


object Sets101 extends App{
  // A program to track which topics a student has selected,and each of them must be unique
  val jon = Student(1,"Jon Snow",Set("History","Biology"))
  println(jon)


  val set_example_1: Set[Int] = Set(1,2,3,4)
  println(set_example_1)

  val set_example_2: Set[String] = Set("hello","hi","hello")
  println(set_example_2)

  val set_example_3: Set[Any] = Set(1,"hi")
  println(set_example_3)

  // Creating an empty set of type double
  val set_example_4:Set[Double] = Set.empty[Double]
  println(set_example_4)


  val set_example_5: Set[Double] = Set(1,3.4)
  println(set_example_5)

  println()
  println()

  // To update the topics of a student
  val updatedTopics = jon.topics + "Chemistry"  - "Biology"
  val updatedJon = jon.copy(topics = updatedTopics)
  println(updatedJon)

  // Sets are immutable, so when you add or remove, a new set instance is created in the process

  // Adding elements to a set
  val set_example_6 = Set(1)+1
  println(set_example_6)

  // Removing elements from a set
  val set_example_7 = Set(1,2)-1
  println(set_example_7)

  // Removing an element from a empty set, returns an empty set
  val set_example_8 = Set() - 9
  println(set_example_8)


  // Are they equal?
  val appSet = Set(2) + 1
//  val prepSet = 1 + Set(2)
  println(appSet) // You can append to a set
//  println(prepSet) // You can't prepend to a set


  println()
  println()

  // A function to extract the names of a group of unordered students
  def getStudentsNames(students:Set[Student]): Set[String] = students.map(_.name)

  val set_example_9 = Set(0,2,3).map(_*3)
  println(set_example_9)

  val set_example_10 = Set.empty[Int].map(_*3)
  println(set_example_10)

  // A function that takes a set of words and turns all of them into UpperCase
  def allUpper(words:Set[String]): Set[String] = words.map(_.toUpperCase)

  println(allUpper(Set("Home", "Good", "Deed")))

  println()
  println()
  // A function to get all the topics from a student
  // The flatten method is used to simplify a nested structure
  def getTopics(student:Set[Student]) = student.map(_.topics).flatten


  val flatten_example_1 = Set(Set(1),Set(89)).flatten
  println(flatten_example_1)

  val flatten_example_2 = Set(Set(),Set()).flatten
  println(flatten_example_2)

  println()
  println()
//  val flatten_example_3 = Set().flatten  // gives a no implicits found error. i.e flatten is expecting an iterable
//  println(flatten_example_3)

  // Using flatMap -> flatMap can be used to replace map and flatten combined
  // Rewriting the getTopics method again
  def getTopicsFromStudent(student: Set[Student]): Set[String] = student.flatMap(_.topics)


  val flatMap_example_1 = Set(1,2,3).flatMap(n => Set("a","b").map(_ * n))
  println(flatMap_example_1)


  // A function that takes 2 sets of integers and multiplies their elements
  def crossMultiplier(set1:Set[Int],set2:Set[Int]): Set[Int] = set1.flatMap(n => set2.map(_ * n))
  val ex = crossMultiplier(Set(1,2,3),Set(4,5,6))
  println(ex)

  // For comprehension
  // In instances, where you want to track a student's topic and its id, this can be achieved using flatMap
  // Basically, when you are looping through 2 different collections and want to compare something btn the 2
  def getTopicsForStudentIds(student:Set[Student],ids:Set[Int])= {
    student.flatMap{
      stud => {
        ids.flatMap{
          id => {
            if(stud.id == id) stud.topics
            else Set.empty
          }
        }
      }
    }
  }


  def getTopicsForStudentIdsEasy(student:Set[Student],ids:Set[Int]) = for{
    s <- student
    id <- ids
    topic <- s.topics
    if(s.id == id)
  } yield topic

  val flatMapExampleRefactored: Set[String] = for{
    i <- Set(1,2,3)
    j <- Set("a","b")
  } yield j * i
  println(flatMapExampleRefactored)


  def crossMultiplierRefactored(set1:Set[Int],set2:Set[Int]): Set[Int] = for{
    i <- set1
    j <- set2
  } yield i * j

  println(crossMultiplierRefactored(Set(1, 2, 3), Set(4, 5, 6)))

  println()
  println()
  println()


  // Final example
  /*
    Define a function that takes a set of books to return the set of genres that a given author has written.
   */
  case class Book(title:String, authors:List[String],genres:Set[String])
  val book1 = Book("Hello",List("James","Arthur"),Set("Horror","Drama"))
  val book2 = Book("Goodbye",List("Charles","Dickens"),Set("Fun","Playful"))
  val book3 = Book("OK",List("Ana","Caroline"),Set("Inspiration","Fiction"))
  val setOfBooks: Set[Book] = Set(book1,book2) + book3

  def setOfGenreByAnAuthor(setOfBooks:Set[Book],myAuthor:String) = for{
    book <- setOfBooks
    author <- book.authors
    if(myAuthor == author)
    genre <- book.genres
  } yield genre


  val genresByDickens = setOfGenreByAnAuthor(setOfBooks,"Dickens")
  val genresByJames = setOfGenreByAnAuthor(setOfBooks,"James")
  println(genresByDickens)
  println(genresByJames)

}