object MyTest {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(59); 
  println("Welcome to the Scala worksheet");$skip(34); 
  
  
  val list1 = List(1,2,789);System.out.println("""list1  : List[Int] = """ + $show(list1 ));$skip(28); 
  
  list1.foreach(println);$skip(22); val res$0 = 
  list1.filter(_ > 2);System.out.println("""res0: List[Int] = """ + $show(res$0));$skip(29); 

val list2 = List((1,2,789));System.out.println("""list2  : List[(Int, Int, Int)] = """ + $show(list2 ));$skip(60); val res$1 = 
  //list2.filter(_(0)._2 > 2)
  list2.filter(e => e._2 > 1);System.out.println("""res1: List[(Int, Int, Int)] = """ + $show(res$1))}
}
