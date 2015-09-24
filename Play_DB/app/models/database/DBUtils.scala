package models.database

import scala.slick.driver.MySQLDriver.simple._
import models.DepartmentModel
import models.EmployeeModel
import scala.slick.lifted.{ ProvenShape, ForeignKeyQuery }
import play.api.db.DB
import play.api.Play.current

//case class Employee(id: Option[Long], name: String, dept_id: Long, manager_id: Long, isManager: Boolean)

// DEPARTMENT TABLE CREATION 
class Departments(tag: Tag) extends Table[(Long, String)](tag, "DEPARTMENT") {

  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def name = column[String]("name")

  def * = (id, name)
}

// Employees TABLE CREATION with foreignKey 
class Employees(tag: Tag) extends Table[(Long, String, Long, Boolean)](tag, "EMPLOYEE") {

  println("Inside Employees class....")

  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def name = column[String]("name")
  def dept_id = column[Long]("dept_id")
  def isManager = column[Boolean]("isManager")
  def * = (id, name, dept_id, isManager)

  def departments = foreignKey("DEPT_FK", dept_id, TableQuery[Departments])(_.id)
}

// Manager Table creation with foreignKeys(as 's' is coming so multiple :P )

//case class Manager(id: Option[Long], emp_id: Long, dept_id: Long, reportee_id: Long)

class Managers(tag: Tag) extends Table[(Long, Long, Long)](tag, "MANAGER") {

  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def emp_id = column[Long]("emp_id")
  def reportee_id = column[Long]("reportee_id")
  //def * = (id.?, emp_id, dept_id, reportee_id) <> (Manager.tupled, Manager.unapply _) previous version of slick

  def * = (id, emp_id, reportee_id)

  def employees_emp_id = foreignKey("EMP-ID_FK", emp_id, TableQuery[Employees])(_.id)
  def employees_dept_id = foreignKey("DEPT-ID_FK", reportee_id, TableQuery[Employees])(_.id)
}

object DBUtils {

  def setup {

    val department = TableQuery[Departments]
    val employee = TableQuery[Employees]
    val manager = TableQuery[Managers]

    Database.forURL("jdbc:mysql://localhost/play", "root", "admin", driver = "com.mysql.jdbc.Driver") withSession {
      implicit session =>
        (department.ddl).create
        (employee.ddl).create
        (manager.ddl).create
    }
  }

  def getAllDepartments: List[models.DepartmentModel] = {
    Database.forURL("jdbc:mysql://localhost/play", "root", "admin", driver = "com.mysql.jdbc.Driver") withSession {
      implicit session =>

        //       val departments = TableQuery[Departments]
        //        println("\n####### DEPARTMENTS #######")
        //        departments foreach {
        //          case (id, name) =>
        //            println("Id: " + id + " Name: " + name)
        //        }

        val departments = TableQuery[Departments]
        val departmentDetails = departments.run.toList.map(dept => new DepartmentModel(dept._1, dept._2))
        departmentDetails
    }
  }

  def getAllEmployees: List[models.EmployeeModel] = {

    Database.forURL("jdbc:mysql://localhost/play", "root", "admin", driver = "com.mysql.jdbc.Driver") withSession {
      implicit session =>
        val employees = TableQuery[Employees]

        val departments = TableQuery[Departments]
      // val selectedDept =  departments.filter(d=>d.id inSet (Set(deptId.toLong))).run
       val selectedDept =  departments.run.toList
       println(selectedDept(2)._2)
        
      val allDepartments = departments.run.toList



        
        val employeeDetails = employees.run.toList.map(emp => new models.EmployeeModel(emp._1, emp._2, emp._3, emp._4,allDepartments.filter(_._1 == emp._3)(0)._2))
        employeeDetails
        
        
        
//        employees foreach {
//          case (id, name, dept_id, isManager) =>
//            println("Id: " + id + " Name: " + name + " dept_id:" + dept_id + " isManager:" + isManager)
//        }
    }
  }
  def getAllManagers: List[models.EmployeeModel] = {

    Database.forURL("jdbc:mysql://localhost/play", "root", "admin", driver = "com.mysql.jdbc.Driver") withSession {
      implicit session =>
        val managers = TableQuery[Managers]
        val employees = TableQuery[Employees]
        println("\n\n####### Managers #######")

        val allManagers = managers.run.toList
        val managerDetails = employees.filter(_.id inSet (allManagers.map(m => m._2))).run.toList.map(emp => new models.EmployeeModel(emp._1, emp._2, emp._3, emp._4,"depat ka name next version me dalenge boss"))
        managerDetails

      // managers.foreach(m => if(m._4) println(m._2 +" "+ m._4))
    }
  }

  def getAllReportee(managerId: String): List[models.EmployeeModel] = {

    Database.forURL("jdbc:mysql://localhost/play", "root", "admin", driver = "com.mysql.jdbc.Driver") withSession {
      implicit session =>

        //val employee_who_is_reporting=TableQuery[Employees]
        println("\n\n####### Managers ki reportee ID #######")
        // managers.foreach(m => if(m._2==2) println(m._3 +"<-----This is Reportee ID who is reporting manager woth ID =2"))
        //managers.foreach(m => if(m._2==2) employee_who_is_reporting.foreach(e => if(m._3 == e._1) println("The reporties are->>"+e._2) ))  nOt totally fuctional style
        val managers = TableQuery[Managers]
        val employees = TableQuery[Employees]

        val allReportees: List[(Long, Long, Long)] = managers.filter(_.emp_id inSet (Set(managerId.toLong))).run.toList

        val reporteesDetails = employees.filter(_.id inSet (allReportees.map(r => r._3))).run.toList.map(emp => new EmployeeModel(emp._1, emp._2, emp._3, emp._4,"depat ka name next version me dalenge boss"))

        reporteesDetails

    }
  }
  
  
  def getManagerByDepartment(deptName: String, deptId: String): List[models.EmployeeModel] = {

    Database.forURL("jdbc:mysql://localhost/play", "root", "admin", driver = "com.mysql.jdbc.Driver") withSession {
      implicit session =>

        val managers = TableQuery[Managers]
        val employees = TableQuery[Employees]
        
       /* val departments = TableQuery[Departments]
       val selectedDept =  departments.filter(d=>d.id inSet (Set(deptId.toLong))).run*/

        val managersByDept = employees.filter(e => (e.dept_id inSet Set(deptId.toLong))).filter(_.isManager).run.toList.map(emp => new EmployeeModel(emp._1, emp._2, emp._3, emp._4,"depat ka name next version me dalenge boss"))

        managersByDept
    }
  }
  
  

 /* def getEmployeeById(empName: String, empId: String): List[models.EmployeeModel] = {

    Database.forURL("jdbc:mysql://localhost/play", "root", "admin", driver = "com.mysql.jdbc.Driver") withSession {
      implicit session =>

        val dept = TableQuery[Departments]
        val employees = TableQuery[Employees]

        var empById = employees.filter(e => (e.id inSet Set(empId.toLong))).run.toList.map(emp => new EmployeeModel(emp._1, emp._2, emp._3, emp._4))
       empById
    }
  }*/
  
  
  def getEmployeeDetails(empId: String, deptId:String): List[models.EmployeeModel] = {
    Database.forURL("jdbc:mysql://localhost/play", "root", "admin", driver = "com.mysql.jdbc.Driver") withSession {
      implicit session =>
        
        println("empId:"+empId+", length:"+empId.length())
        
        val employees = TableQuery[Employees]
        val departments = TableQuery[Departments]
       val selectedDept =  departments.filter(d=>d.id inSet (Set(deptId.toLong))).run.toList
       println("DEPT : "+selectedDept(0)._2)
       val empDeptDetail = employees.filter( e =>  (e.id inSet Set(empId.toLong))).run.toList.map(emp => new EmployeeModel(emp._1, emp._2, emp._3, emp._4, selectedDept(0)._2))
       println(empDeptDetail)
       empDeptDetail
    }
  }

  
  

}