package masglobaltestapi.azurewebsites.net.dao;

import java.util.List;
import java.util.Optional;

import masglobaltestapi.azurewebsites.net.dto.Employee;

public class EmployeeMonthlySalaryContract implements EmployeeDao{
    private static List<Employee> employeeList;

    @Override
    public List<Employee> getAllEmployees() {
        
        return employeeList;
    }

    @Override
    public Optional<Employee> getEmployeeById(int id) {
       
        return employeeList.stream()
                .filter(employee-> employee.getId() == id)
                .findFirst();
    }

    @Override
    public int getAnualSalary(int salary) {
        salary *= 12; 
        return salary;
    }
    
}
