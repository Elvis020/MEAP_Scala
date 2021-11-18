package Unit_4.Chap_19
/*
  Case object is a regular singleton object for which the
  compiler overrides some useful methods
 */

sealed trait Currency
case object USD extends Currency
case object EUR extends Currency
case object GBP extends Currency
case object CAD extends Currency


case class Author(forename:String,surname:String)

sealed trait Genre
case object DRAMA extends Genre
case object ROMANTIC extends Genre
case object HORROR extends Genre

case class Book(title:String,author: Author,genre: Genre)

object CaseObjects101 extends App {
  println(USD.toString)
  println(CAD.toString)
  println(EUR.toString)
  println(GBP.toString)

}