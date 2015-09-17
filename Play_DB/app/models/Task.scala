package models

import play.api.Play.current
import scala.slick.driver.MySQLDriver.simple._
import play.api.db.DB

case class Task(id: Int ,label: String) 
object Task{
  
  def all: List[Task] = Nil
  
  def create(label: String) {
    println("INside Create...")
    
    val conn = DB.getConnection()
    
    try {
      val stmt = conn.createStatement
      println(s"Insert into task values(1,$label)")
      stmt.executeUpdate(s"Insert into task values(1,'$label')")
      
    } finally {
      conn.close()
    }
    println("Added..")
    //Ok("outString")
  }
    
    
    //println(conn)
    
  //DB.withConnection {

      //val task: TableQuery[Task] = TableQuery[Task]
      
      //implicit c => c.createStatement("insert into task (label) values ({label})")
      
      
    /*  
    implicit c => SQL("insert into task (label) values ({label})").on(
      'label -> label
    ).executeUpdate()
    */
    
  //}
 
    /*
  DB.withConnection("play") { conn =>
    println("Success")
  }
  *
  */
  
  
 // }
  
  def delete (id: Int) {}
  
  
  

}