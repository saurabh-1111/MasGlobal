package masglobaltestapi.azurewebsites.net.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import masglobaltestapi.azurewebsites.net.dao.EmployeeDao;
import masglobaltestapi.azurewebsites.net.dto.Employee;

@Service
public class EmployeeService {
    
    private final EmployeeDao employeeDao;
    
    @Autowired
    public EmployeeService(@Qualifier("Emp") EmployeeDao employeeDao) {
        System.out.println("EmployeeService called.");
        this.employeeDao = employeeDao;
    }
    
    public List<Employee> getAllEmployees(){
        System.out.println("EmployeeService getAllEmployees called.");
        return employeeDao.getAllEmployees();
    }
    
    public Optional<Employee> getEmployeeById(int id){
        System.out.println("EmployeeService getEmployeeById called.");
    return employeeDao.getEmployeeById(id);
    }
    
    public int getAnualSalary(int salary) {
        System.out.println("EmployeeService getAnualSalary called.");
        return employeeDao.getAnualSalary(salary);
    }
    
    public int addEmployee(Employee employee) {
        return employeeDao.addEmployee(employee);
    }
    
}
