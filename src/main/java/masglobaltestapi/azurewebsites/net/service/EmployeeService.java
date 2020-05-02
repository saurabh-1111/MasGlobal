package masglobaltestapi.azurewebsites.net.service;

import java.util.ArrayList;
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
    
    private static List<Employee> employeeList = new ArrayList<>();
    
    @Autowired
    public EmployeeService(@Qualifier("HourlySalaryContract") EmployeeDao employeeDao) {
        System.out.println("EmployeeService called.");
        this.employeeDao = employeeDao;
    }
    
       
    public List<Employee> getAllEmployees(){
        System.out.println("EmployeeService getAllEmployees called.");
        return employeeList;
    }
    
    public Optional<Employee> getEmployeeById(int id){
        System.out.println("EmployeeService getEmployeeById called.");
        return employeeList.stream().filter(employee-> employee.getId() == id).findFirst();
    }
    
    public int getAnualSalary(int salary) {
        System.out.println("EmployeeService getAnualSalary called.");
        return employeeDao.getAnualSalary(salary);
    }
    
    public int addEmployee(Employee employee) {
        employeeList.add(new Employee(employee.getId(), employee.getName(), employee.getContractTypeName(), employee.getRoleId(), employee.getRoleName(), employee.getRoleDescription(), employee.getHourlySalary(), employee.getMonthlySalary()));
        
        return 1;
    }
    
}
