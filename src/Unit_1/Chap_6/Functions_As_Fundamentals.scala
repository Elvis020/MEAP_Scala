package Unit_1.Chap_6

object Functions_As_Fundamentals extends App{
  /*
    There are a lot of functions types available in Scala including:
    abstract,pure,impure,anonymous and many more

    Functions are 1st class citizens in Scala
    When using the keyword def, a function must always have a name

    More on ???, check out this chapter if you want to read more
    def can have zero or more parameters

    If an implementation to a function is missing, the compiler cannot
    infer for you

    Compile => 2,6
   */

  // Function for even numbers
  def isEven(n:Int) = {
    if(n<0) throw new RuntimeException("Error!, number cannot be negative")
    else (n&1)==0
  }

  println(isEven(23))
//  println(isEven(-34))

  def myPow(exponent:Int,base:Int):Double = {
    Math.pow(base,exponent)
  }

  println(myPow(3, 2))

}
