package Unit_4.Chap_20
/*
  Always remember that in Scala, functions are 1st class citizens
  HOFs are  functions that accept other functions as parameters, or
  return functions or both

  HOFs helps create powerful abstractions that reduce code duplication
  and increase re-usability of programs


 */
object HOFs101 extends App{

  // length of string
  def lengthOfString(s:String): String = s"Length of $s is ${s.length}"

  // letters
  def lettersInString(s:String): String =
    s"The number of letters in $s is ${s.count(_.isLetter)}"

  // digits
  def digitsInString(s:String): String =
    s"The number of digits in $s is ${s.count(_.isDigit)}"

  val ex1 = "Elvis020"
//  println(digitsInString(ex1))
//  println(lettersInString(ex1))
//  println(lengthOfString(ex1))

  // This initial solution is not suitable, since there
  // is so much code duplication. In case we want to add new
  // functionality, we will have te define another function


  /* Implementing using HOF */

  // A method that counts

  def stats(s:String, predicate:Char=>Boolean): Int = s.count(predicate)

  def size(s:String): Int = stats(s, _=> true)
  def countLetters(s:String): Int = stats(s, _.isLetter)
  def countDigits(s:String): Int = stats(s, _.isDigit)

  println(size(ex1))
  println(countDigits(ex1))
  println(countLetters(ex1))

// You can also support other statistics that you have not explicitly defined
  val text = "This is a Text Example"
  println(stats(text, _.isUpper))
  println(stats(text, _ == 'x'))


  // Foo's HOF implementation
  def foo(f:Int=>Double):Double=f(42)+2



  // Functions as return values - using the Predicate Selector
  sealed trait Mode // This allows us to define a finite set of operations
  case object Length extends Mode
  case object Letters extends Mode
  case object Digits extends Mode
  case object WhiteSpace extends Mode

  // This method illustrates function as return values
  def predicateSelector(mode:Mode):Char=>Boolean = mode match{
    case Length => _ => true
    case Letters => _.isLetter
    case Digits => _.isDigit
    case WhiteSpace => _.isWhitespace
  }

  val text_2 = "This is a beautiful COUNTER, EH?"
  println(s"The length of \"$text_2\" is " + stats(text_2,predicateSelector(Length)))
  println(s"Is digits? " + stats(text_2,predicateSelector(Digits)))
  println(s"Is letters? " + stats(text_2,predicateSelector(Letters)))
  println(s"Number of white space =>  " + stats(text_2,predicateSelector(WhiteSpace)))



  // Final example
  def operateWitFallback(n:Int,operation:Int=>Int, fallBack:Int):Int = operation(n) match{
    case (j:Int) if j>0 => j
    case _ =>fallBack
  }

  def ops(j:Int):Int = j-90

  println(operateWitFallback(23, ops, 1))





}