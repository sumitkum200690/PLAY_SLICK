object MyTest {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  
  val list1 = List(1,2,789)                       //> list1  : List[Int] = List(1, 2, 789)
  
  list1.foreach(println)                          //> 1
                                                  //| 2
                                                  //| 789
  list1.filter(_ > 2)                             //> res0: List[Int] = List(789)

val list2 = List((1,2,789))                       //> list2  : List[(Int, Int, Int)] = List((1,2,789))
  //list2.filter(_(0)._2 > 2)
  list2.filter(e => e._2 > 1)                     //> res1: List[(Int, Int, Int)] = List((1,2,789))
}