package masglobaltestapi.azurewebsites.net.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import masglobaltestapi.azurewebsites.net.dto.Employee;

@Repository("HourlySalaryContract")
public class EmployeeHourlySalaryContract implements EmployeeDao {
    
    /*
    private static List<Employee> employeeList = new ArrayList<>();

    @Override
    public List<Employee> getAllEmployees() {
        System.out.println("EmployeeHourlySalaryContract getAllEmployees called.");
        
        return employeeList;
    }

    @Override
    public Optional<Employee> getEmployeeById(int id) {
        System.out.println("EmployeeHourlySalaryContract getEmployeeById called.");
        
        return employeeList.stream()
               .filter(employee-> employee.getId() == id)
               .findFirst();
    }

   

    @Override
    public int addEmployee(Employee employee) {
        employeeList.add(new Employee(employee.getId(), employee.getName(), employee.getContractTypeName(), employee.getRoleId(), employee.getRoleName(), employee.getRoleDescription(), employee.getHourlySalary(), employee.getMonthlySalary()));
        
        
        return 1;
    }
    */
    
    @Override
    public int getAnnualSalary(int salary) {
        System.out.println("EmployeeHourlySalaryContract getAnualSalary called.");
        
        salary = 120 * salary * 12;
        
        return salary;

    }
    
}
