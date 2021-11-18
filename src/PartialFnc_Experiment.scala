object PartialFnc_Experiment extends App{
//  val map_1: Map[Int, Int] = Map(1->1)
//  println(map_1)
//  val map_2: PartialFunction[Int, Int] = map_1 orElse(Map(2->4,3->9))
//  val map_3 = map_2
////  for{
////    i <- 1 to 3
////  } yield
//  for(i <- 1 to 3){
//    println(map_2(i))
//  }
//  println(map_2(1))
//  println(map_2(2))
////  map_2.unapply(1)
//  println(map_2(1))

  def sum(a:Int,b:Int): Int = a+b
  val sum3 = sum _
  val summy:PartialFunction[(Int,Int),Int] = {
    case (f:Int,d:Int) => f+d
    case _ => 0
  }
  println(sum3(4, 5))
  println(summy(3, 4))
}