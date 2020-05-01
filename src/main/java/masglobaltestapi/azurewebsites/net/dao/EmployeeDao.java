package masglobaltestapi.azurewebsites.net.dao;

import java.util.List;

import masglobaltestapi.azurewebsites.net.dto.Employee;

public interface EmployeeDao {
    
    List<Employee> getAllEmployees();
    Employee getEmployeeById(int id);
    int getAnualSalary(int salary);
    
    
}
