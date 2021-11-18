package Unit_6.Chap_37

import Unit_6.Chap_36.{ExamSession, StudentWithSession}

/**
 * Project: Scala_with_Daniela
 * Package: Unit_6.Chap_37
 *
 * User: elvis 😎😎
 * Date: 18/11/2021
 * Time: 06:15
 *
 * Happy Coding
 */
/*
  Retrieving values associated with a key is a constant time operation.
  Scala offers strategies to retrieve values from a Map
    1. Using get: For a Map[K,V], this returns an Option[V]
 */


object Working_with_Maps extends App{

  // Retrieving values for a given key
  def getStudentWithSession(registration:Map[ExamSession,List[StudentWithSession]],
                            session:ExamSession) =
    registration.getOrElse(session,Nil)


}