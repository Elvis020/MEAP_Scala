package Unit_1.Chap_4

object Values_and_Variables extends App {
  // Adding extra information so that we compute the avg score of all the exams marks
  var markSum = 0
  var markCount = 0


  // A function to mark exams
  def markExams(a:Double,b:Double,c:Double): Long = {
    var highest =0.0
    var lowest=a


    val avgScore = (a+b+c)/3
    val scaledScore = (10 * avgScore)/3
    val mark = Math.round(scaledScore).toInt

    // Determining highest mark
    if((a>0.0) & (a>b) & (a>c)) highest = a
    else if((b>a) & (b>c)) highest=b
    else if((c>b) & (c>a)) highest =c

    // Determining lowest mark
    if((b<a) & (b<c)) lowest = b
    else if((c<a) & (c<b)) lowest=c

    markSum += mark
    markCount += 1
    println("Highest =>> " + highest)
    println("Lowest =>> " + lowest)
    mark
  }
  /*
    Values are immutable so they cant be used to track the state of the anything
    Variables are used to track the states

    You don't need to provide a type for a variable, the compiler will infer for you
    Mutable structures in your code may be more performant compared to immutable but are
    prone to errors and concurrency issues such as resource starvation, deadlocks etc

    You can use case classes to get rid of var
    += and -= cannot be used with vals because they are immutable
    You can use a += with a var String but you cant use a -=, because String dont have
    method -


   */

  println(markExams(23, 54, 87))

}
