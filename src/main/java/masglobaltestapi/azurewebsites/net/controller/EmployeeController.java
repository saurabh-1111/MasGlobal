package masglobaltestapi.azurewebsites.net.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import masglobaltestapi.azurewebsites.net.dto.Employee;
import masglobaltestapi.azurewebsites.net.service.EmployeeService;

/** 
* 
* @author Saurabh Gupta
* This is the controller class to get requests.
* Sends corresponding response. 
*  
*/
@CrossOrigin
@RequestMapping("/api/employee")
@RestController
public class EmployeeController {
    
    private final EmployeeService employeeService; 
    
    // dependency injection, avoiding EmployeeService instantiation
    @Autowired  
    public EmployeeController(EmployeeService employeeService) {
        
        this.employeeService = employeeService;
    }
    
    
    // get info for all the employees by calling /api/employee
    @GetMapping
    public List<Employee> getAllEmployees(){

        return employeeService.getAllEmployees();
    }
    
      
    // get info for given employee id by calling /api/employee/{id}
    @GetMapping(path="{id}")
    public Employee getEmployeeById(@PathVariable("id") int id){
        
        return employeeService.getEmployeeById(id).get();
    }
    
}
