package controllers

import models.database.DBUtils
import play.api.data.Form
import play.api.data.Forms.nonEmptyText
import play.api.mvc.Action
import play.api.mvc.Controller
import play.api.Logger
import models.EmployeeModel
import models.DepartmentModel

object Application extends Controller {

  def index = Action {
    Ok(views.html.index())
  }
                                         
  def allManagers = Action { 
                                          
      println("In allManagers..")
      
     val managers= DBUtils.getAllManagers           
      
     //Logger.debug(managers.mkString("\n"))

     
     println("After setup")
    
     Ok(views.html.managerPage(managers: List[models.EmployeeModel]))
    }
  
  def allDepartments = Action {
    
    println("In allDepartments ")
    
    //val deptList=DBUtils.getAllDepartments
    val deptList= DBUtils.getAllDepartments
     deptList.foreach(d => println(d.id+" "+d.departmentName))
    
     
    Logger.debug(deptList.mkString("\n"))
    //Ok(views.html.query(deptList))
     
    Ok(views.html.allDepartmentPage(deptList: List[models.DepartmentModel]))
  }
  
  def allReportee(managerId: String) = Action{
    
    val repoteeList = DBUtils.getAllReportee(managerId)
    
    
    
    Ok(views.html.manageWiseReportees(repoteeList : List[models.EmployeeModel]))
  }
  
  
  def managerByDepartment(departmentName: String, id:String)= Action{
    
         val mbydept= DBUtils.getManagerByDepartment(departmentName, id)
    
    Ok(views.html.managerPage(mbydept:List[models.EmployeeModel]))
  }
  
  def employeeById(id:String, deptId:String) = Action{
    
    val empDesc = DBUtils.getEmployeeDetails(id, deptId)
    
    Ok(views.html.employeeByIdDescription(empDesc: List[models.EmployeeModel]))
  }
  
  
  
  val taskForm = Form("label" -> nonEmptyText)
                                           // )
  
  def allEmployees = Action {
    
     val emp= DBUtils.getAllEmployees
     
     Ok(views.html.employeeByIdDescription(emp: List[models.EmployeeModel]))
    
  }
  
  
 
  
  
  
  
}
