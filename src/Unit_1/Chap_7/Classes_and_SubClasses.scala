package Unit_1.Chap_7

object Classes_and_SubClasses extends App{
  /*
    Classes, subclasses abstract classes enable you to group entities that have
    common shapes and behaviours

    Class: A representation of elements of the same kind from real world
    Subclass: A class that inherits behaviours from another class
    Abstract Class: A class in which the methods are abstract
    SuperClass: A class whose members/methods are inherited by one/two classes

    In Scala, a class can have only one superclass
    Named parameter helps with readability and prevents ambiguity


    Mistakes related to the order of the parameters cannot be detected by
    the compiler at compile time


   */

//  class Robot(name:String) // name is not accessible

// name is accessible
  abstract class Robot(val name:String="Unknown"){
    def welcome(n:String): String
}

  //abstract classes cant be instantiated
//  val robot_1: Robot =new Robot("Tommy")
//  val robot_2: Robot =new Robot("Arthur")
//  val robot_3: Robot =new Robot


  // Accessing the names of the robot
//  println(robot_1.name)
//  println(robot_2.name)
//  println(robot_3.name)

  // Supposed we want a robot for a specific language
  class ItalianRobot(name:String) extends Robot(name){
    override def welcome(n: String): String = s"Benvenuto $n"
  }
  class EnglishRobot(name:String,val country:String) extends Robot(name){
    override def welcome(n: String): String = s"Welcome $n from the country of $country"
  }

  val italian_robot: ItalianRobot = new ItalianRobot("Pierro")
  val english_robot: EnglishRobot = new EnglishRobot("Duke","England")
  println(italian_robot.welcome("Elvis"))
  println(english_robot.welcome("Spacey"))



  println()
  println()

  // Accessing the methods in the class
//  println(robot_1.welcome("Elvis"))
//  println(robot_3.welcome("Shelby"))


  class Coordinates(val latitude:Double, val longitude:Double)
  val c_1: Coordinates = new Coordinates(latitude = 24.24,longitude = 54.12)
  val c_2: Coordinates = new Coordinates(longitude = 54.12, latitude = 21.42)

  println()
  println()

  abstract class Person(val name:String, val age:Int=0){
    def presentYourself() = s"My name is $name and I am $age years old"
    def hello(newName:String):String
  }
  class Teacher(name:String,age:Int) extends Person(name,age){
//    override def presentYourself(): String = super.presentYourself()
    def hello(newName: String): String = s"Hello! My name is $newName"
  }
  class Student(name:String,age:Int,id:String) extends Person(name,age){
//    override def presentYourself(): String = s"$name is a student, with id: $id and age $age"
    override def hello(newName:String): String = s"Hello $newName, my $name is a student, with id: $id and age $age"
  }


//NB: Cant instantiate abstract classes
//  val person_1: Person = new Person(name = "Martin", age = 18)
//  val person_2: Person = new Person(age = 0, name="Bob")

  val teacher: Teacher = new Teacher("Akrobeto",33)
  val student: Student = new Student("Alice",12,id="W345")

//  println(person_1.presentYourself())
//  println(person_2.presentYourself())

  println(teacher.hello("Kukumbagia"))
  println(student.hello("Poland"))


  println()
  println()

  // The bar scenario
  abstract class Bar(beverage:String){
    def request():String
  }
  class ColdBevs(val beverage:String) extends Bar(beverage){
    override def request(): String = s"Add more ice to my cold $beverage"
  }
  class HotBevs(val beverage:String) extends Bar(beverage){
    override def request(): String = s"Can you please reheat the hot $beverage"
  }

  val cold_beverage: ColdBevs = new ColdBevs("Star")
  val hot_beverage = new HotBevs("Tea")

  println(cold_beverage.request())
  println(hot_beverage.request())

}
