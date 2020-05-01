package masglobaltestapi.azurewebsites.net.service;

import java.util.List;
import java.util.Optional;

import masglobaltestapi.azurewebsites.net.dao.EmployeeDao;
import masglobaltestapi.azurewebsites.net.dto.Employee;

public class EmployeeService {
    
    private final EmployeeDao employeeDao;
    
    public EmployeeService(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }
    
    public List<Employee> getAllEmployees(){
        return employeeDao.getAllEmployees();
    }
    
    public Optional<Employee> getEmployeeById(int id){
    return employeeDao.getEmployeeById(id);
    }
    
    public int getAnualSalary(int salary) {
        return employeeDao.getAnualSalary(salary);
    }
    
}
