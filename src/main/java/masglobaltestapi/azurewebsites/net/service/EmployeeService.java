package masglobaltestapi.azurewebsites.net.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import masglobaltestapi.azurewebsites.net.dao.EmployeeContractFactory;
import masglobaltestapi.azurewebsites.net.dao.EmployeeDao;
import masglobaltestapi.azurewebsites.net.dao.EmployeeHourlySalaryContractImpl;
import masglobaltestapi.azurewebsites.net.dao.EmployeeMonthlySalaryContractImpl;
import masglobaltestapi.azurewebsites.net.dto.Employee;

/** 
* 
* @author Saurabh Gupta
* This class has the main business logic 
* Talks to DAO layer for getting employees information
* Calculates annual salary of employee(s) based on contract type
*  
*/
@Service
public class EmployeeService {
    
    private EmployeeContractFactory employeeContractFactory;
    
    private boolean isEmployeeDataRetrieved = false; 
    
    private static List<Employee> employeeList = null;
    private static JSONArray employeeJsonArray = null;
    
    @Autowired
    public EmployeeService(@Qualifier("HourlySalaryContract") EmployeeContractFactory employeeContractFactory) {
        
        this.employeeContractFactory = employeeContractFactory;
     
    }
    
    /*
     * Retrieves employees info and calculates their salary
     * Complexity - O(1)
     * @ReturnType List<Employee>
     */
    public List<Employee> getAllEmployees() throws JSONException{
        
        // Retrieve all employee info first time only, no need to consume every time
        if(!isEmployeeDataRetrieved) {
            retrieveAllEmployeesInfo();
        
        if(null == employeeList || employeeList.size() == 0)
            return null;
        
        calculateAnnualSalaryForAllEmployees();
        
        }
        
        return employeeList;
        
        
    }
    
    /* This method calls DAO layer to get info for all the employees
     * Complexity - O(1)
     */
    private void retrieveAllEmployeesInfo() throws JSONException {
        
        employeeJsonArray = EmployeeDao.consumeEmployees();
        isEmployeeDataRetrieved = true;
        
        if(null != employeeJsonArray)
            getEmployeeList(employeeJsonArray);
        
    }
    
    /* This method calculates salary for all the employees
     * Complexity - O(n), where n is number of employees
     */
    private void calculateAnnualSalaryForAllEmployees() {

        employeeList.stream()
        .filter(employee-> employee.getAnnualSalary() == 0)
        .forEach(employee-> calculateSalaryforOneEmployee(employee));
     
    }


    /* This method calculates salary for single employee based on contract type
     * Complexity - O(1)
     * @Param Employee
     */
    private void calculateSalaryforOneEmployee(Employee employee) {
        int annualSalary = 0;

        if(employee.getContractTypeName().equalsIgnoreCase("HourlySalaryEmployee")) {
            employeeContractFactory = new EmployeeHourlySalaryContractImpl();
            annualSalary = employeeContractFactory.getAnnualSalary(employee.getHourlySalary());
            employee.setAnnualSalary(annualSalary);
        }
        else if(employee.getContractTypeName().equalsIgnoreCase("MonthlySalaryEmployee")) {
            employeeContractFactory = new EmployeeMonthlySalaryContractImpl();
            annualSalary = employeeContractFactory.getAnnualSalary(employee.getMonthlySalary());
            employee.setAnnualSalary(annualSalary);
        }
        
    }


    /* This method retrieves employees info from json array and add into list
     * Complexity - O(n), where n is number is employees
     * @param employeeJsonArray
     */
    private void getEmployeeList(JSONArray employeeJsonArray) throws JSONException {
        
        JSONObject employee = null;  
        
        employeeList = new ArrayList<Employee>();
        
        for (int i = 0; i < employeeJsonArray.length(); i++) {            
            employee = (JSONObject) employeeJsonArray.get(i);
        
            
            // creating dto object and adding to list
            employeeList.add(new Employee(employee.getInt("id"), employee.opt("name").toString(), 
                    employee.opt("contractTypeName").toString(), employee.getInt("roleId"), 
                    employee.opt("roleName").toString(),employee.opt("roleDescription").toString(), 
                    employee.getInt("hourlySalary"), employee.getInt("monthlySalary")));
              
       }
    }


    /*
     * This method returns employee based on id.
     * Complexity - O(1), if the given employee is the first element of the list
     * Complexity - O(n) in worst case, where the employee with given id is at the end of list
     * @param id
     */
    public Optional<Employee> getEmployeeById(int id) throws JSONException{
        // Consume all data first time only, no need to consume every time
        if(!isEmployeeDataRetrieved) 
            retrieveAllEmployeesInfo();
        
        if(null == employeeList || employeeList.size() == 0)
            return null;
        
        System.out.println("id: " + id);
        
        Optional<Employee> employeeWithGivenId = employeeList.stream()
                                                .filter(employee-> employee.getId() == id)
                                                .findFirst();
        
        // if employee not present with given id return 
        if(!employeeWithGivenId.isPresent())
            return null;
        
        calculateSalaryforOneEmployee(employeeWithGivenId.get());
        
        return employeeWithGivenId;
        
    }
    
}
