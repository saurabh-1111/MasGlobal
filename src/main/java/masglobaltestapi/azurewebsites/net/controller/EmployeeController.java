package masglobaltestapi.azurewebsites.net.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import masglobaltestapi.azurewebsites.net.dto.Employee;
import masglobaltestapi.azurewebsites.net.service.EmployeeService;

@RequestMapping("masglobaltestapi.azurewebsites.net/api/employees")
@RestController
public class EmployeeController {
    
    private final EmployeeService employeeService; 
    
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    
    @GetMapping
    public List<Employee> getAllEmployees(){
        return employeeService.getAllEmployees();
    }
    
    @GetMapping(path="{id}")
    public Optional<Employee> getEmployeeById(@PathVariable("id") int id){
        return employeeService.getEmployeeById(id);
    }
    
    
    
}
