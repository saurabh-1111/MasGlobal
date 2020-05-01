package masglobaltestapi.azurewebsites.net.dao;

import java.util.List;
import java.util.Optional;

import masglobaltestapi.azurewebsites.net.dto.Employee;

public interface EmployeeDao {
    
    List<Employee> getAllEmployees();
    Optional<Employee> getEmployeeById(int id);
    int getAnualSalary(int salary);
    
    
}