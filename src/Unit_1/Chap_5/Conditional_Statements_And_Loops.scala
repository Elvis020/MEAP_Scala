package Unit_1.Chap_5
/*
  This chapter illustrates examples of using conditional statements(if..else)
  Note that Scala uses curly braces to identify one or more expressions


  While loops are no part of the Scala language and are considered as anti-pattern
  While loops operate on mutability and they are rarely used. While loops are
  replaced with foreach,map etc in Scala


  The main difference btn for-loop and while-loop is, you are repeating a condition
  over finite period of times unlike while where unless a condition is met, it continues


 */
object Conditional_Statements_And_Loops extends App{
  // Function to categorize days of the week in terms of numbers
  def categorizeDaysOfWeek(n:Int): String =
    if(n.equals(1) | n.equals(7)) "Weekend"
    else if(List.range(2,6).contains(n)) "Weekday"
    else "Unknown"

  println(categorizeDaysOfWeek(3)) // weekday

  // Labelling a number
  def labelNumber(n:Int):String =
    if(n==0) "neutral"
    else if(n<0) "negative"
    else "positive"

  println(labelNumber(43)) // positive
  println(labelNumber(-43)) // negative


  // A function that checks whether a number is even or odd
  def evenOrOdd(n:Int): String =
    if(((n&1) ==0)) "Even"
    else "Odd"

  println(evenOrOdd(90))
  println(evenOrOdd(91))
  println(evenOrOdd(33))



  // Trying to write while loops
  def echo(message:String,limit:Int=5) = {
    var i=0
    while(i<limit){
      print(message+" ")
      i+=1
    }
  }
  echo("Hello")
  println()


  // Print all numbers using a while loop
  def count(limit:Int=10): Unit ={
    var i=1
    while(i<=limit){
      print(i+" ")
      i+=1
    }
  }
  count()
  println()

  // For loops
  // Using for loop to achieve the echo functionality
  def echoWithFor(limit:Int=5) =for(i <- 0 until limit) print(i+" ")

  echoWithFor()
  println()
  for(a <- "hello") print(a+" ")
  println()


  // A program to apply discount to a given price
  def discounter(price:Int): Int = {
    var finalPrice = 0
    if(price<50)finalPrice=price
    else if((price>=50) & (price<100)) finalPrice=price + (price*0.1).toInt
    else finalPrice=price + (price*0.15).toInt

    finalPrice
  }

  println(discounter(24))
  println(discounter(140))
}
