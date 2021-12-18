package Unit_2.Chap_9




/*
  Note that you can also scope packages and imports. Scala automatically adds the packages
  scala, java.lang and scala.Predef

  Packages are tools used to organize an app composed of multiple files
 */

object Importing_Packages extends App{
  // Importing an using Aliases and scoping it
  import scala.io.{Source => Hey}
  import java.sql.{Date => MyD}


   def readFileIntoString(file:String): String = {
     Hey.fromFile(file)
         .getLines()
         .mkString("\n")


     // Using the date apis
     MyD.valueOf("22-10-1996").toString

   }

}
