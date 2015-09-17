package models.database

import scala.slick.driver.MySQLDriver.simple._

case class Employee(id: Option[Long], name: String, dept_id: Long, manager_id: Long, isManager: Boolean)

class Employees(tag: Tag) extends Table[Employee](tag, "EMPLOYEE") {
  
  println("Inside Employees class....")
  
  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def name = column[String]("name")
  def dept_id = column[Long]("dept_id")
  def manager_id = column[Long]("manager_id")
  def isManager = column[Boolean]("isManager")
  def * = (id.?, name, dept_id, manager_id, isManager) <> (Employee.tupled, Employee.unapply _)

} 

