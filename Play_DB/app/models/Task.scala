package models

import play.api.Play.current
import play.api.db.DB
import scala.slick.lifted.TableQuery
import models.database.Employees
import models.database.Employees

case class Task(id: Int ,label: String) 
object Task{
  
  def all: List[Task] = Nil
  
 /* def create(label: String) {
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
    
    
      
    
   
  
  
  def delete (id: Int) {} */
  
  
  
  
  
  

}