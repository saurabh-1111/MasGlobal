package masglobaltestapi.azurewebsites.net.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import masglobaltestapi.azurewebsites.net.dao.EmployeeContractDao;
import masglobaltestapi.azurewebsites.net.dao.EmployeeDao;
import masglobaltestapi.azurewebsites.net.dao.EmployeeHourlySalaryContract;
import masglobaltestapi.azurewebsites.net.dao.EmployeeMonthlySalaryContract;
import masglobaltestapi.azurewebsites.net.dto.Employee;

@Service
public class EmployeeService {
    
    private EmployeeContractDao employeeContractDao;
    private boolean isEmployeeDataRetrieved = false; 
    
    private static List<Employee> employeeList = null;
    private static JSONArray employeeJsonArray = null;
    
    @Autowired
    public EmployeeService(@Qualifier("HourlySalaryContract") EmployeeContractDao employeeContractDao) {
        System.out.println("EmployeeService called.");
        this.employeeContractDao = employeeContractDao;
    }
    
       
    public List<Employee> getAllEmployees(){
        System.out.println("EmployeeService getAllEmployees called.");
        
        /*
        if(!isFirstTime && (null  == EmployeeDao.employeeList || EmployeeDao.employeeList.length() == 0))
            return null;
            */
        
        
        // Consume all data first time only, no need to consume every time
        if(!isEmployeeDataRetrieved) {
            retrieveAllEmployeesInfo();
        
        if(null == employeeList || employeeList.size() == 0)
            return null;
        
        calculateAnnualSalaryForAllEmployees();
        }
        
        
        System.out.println("employeeList: " + employeeList.toString().toString()) ;
        return employeeList;
        
        
    }
    
    private void calculateAnnualSalaryForAllEmployees() {
        
        Employee employee;
        
        
        System.out.println("calculateAnnualSalaryForAllEmployees called.");
        
        for(int i = 0; i  < employeeList.size(); i++) {
            
            
            
            employee = employeeList.get(i);
            
            // Annual salary already calculated
            if(employee.getAnnualSalary() != 0)
                continue;
            
            calculateSalaryforOneEmployee(employee);
            
            
            
        }
        
        
    }


    private void calculateSalaryforOneEmployee(Employee employee) {
        int annualSalary = 0;

        if(employee.getContractTypeName().equalsIgnoreCase("HourlySalaryEmployee")) {
            employeeContractDao = new EmployeeHourlySalaryContract();
            //employeeContractDao  =  EmployeeHourlySalaryContract :: getAnnualSalary(employee.getHourlySalary());
            annualSalary = employeeContractDao.getAnnualSalary(employee.getHourlySalary());
            employee.setAnnualSalary(annualSalary);
            
            System.out.println("HourlySalaryEmployee: " + annualSalary);
            
            
        }
        else if(employee.getContractTypeName().equalsIgnoreCase("MonthlySalaryEmployee")) {
            employeeContractDao = new EmployeeMonthlySalaryContract();
            annualSalary = employeeContractDao.getAnnualSalary(employee.getMonthlySalary());
            employee.setAnnualSalary(annualSalary);
            
            System.out.println("MonthlySalaryEmployee: " + annualSalary);
            
            
        }
        
    }


    private void retrieveAllEmployeesInfo() {
        
        employeeJsonArray = EmployeeDao.consumeEmployees();
        isEmployeeDataRetrieved = true;
        
        System.out.println("retrieveAllEmployeesInfo called. employeeJsonArray: " +employeeJsonArray.length());
        if(null != employeeJsonArray)
            getEmployeeList(employeeJsonArray);
        
    }


    private void getEmployeeList(JSONArray employeeJsonArray) {
        
        JSONObject employee;  
        
        employeeList = new ArrayList<>();
        
        for (int i = 0; i < employeeJsonArray.length(); i++) {
            
            employee = (JSONObject) employeeJsonArray.get(i);
            
            
          
            employeeList.add(new Employee(employee.getInt("id"), employee.opt("name").toString(), 
                    employee.opt("contractTypeName").toString(), employee.getInt("roleId"), 
                    employee.opt("roleName").toString(),employee.opt("roleDescription").toString(), 
                    employee.getInt("hourlySalary"), employee.getInt("monthlySalary")));
       }
        
        System.out.println("getEmployeeList called. employeeList: " + employeeList.size());
        
    
    }


    public Optional<Employee> getEmployeeById(int id){
        System.out.println("EmployeeService getEmployeeById called.");
        
     // Consume all data first time only, no need to consume every time
        if(!isEmployeeDataRetrieved) 
            retrieveAllEmployeesInfo();
        
        if(null == employeeList || employeeList.size() == 0)
            return null;
        
        Optional<Employee> employee1 = employeeList.stream().filter(employee-> employee.getId() == id).findFirst();
        
        calculateSalaryforOneEmployee(employee1.get());
        
        return employee1;
        
       
        
       // return employeeList.stream().filter(employee-> employee.getId() == id).findFirst();
    }
    
    public int getAnnualSalary(int salary) {
        System.out.println("EmployeeService getAnualSalary called.");
        return employeeContractDao.getAnnualSalary(salary);
    }
    
    public int addEmployee(Employee employee) {
        
        if(null == employeeList)
            employeeList = new ArrayList<>();
        
        employeeList.add(new Employee(employee.getId(), employee.getName(), employee.getContractTypeName(), employee.getRoleId(), employee.getRoleName(), employee.getRoleDescription(), employee.getHourlySalary(), employee.getMonthlySalary()));
        
        //calculate annual salary based on contract type and setting in the object
        if(employee.getContractTypeName().equalsIgnoreCase("HourlySalaryEmployee")) {
            employeeContractDao = new EmployeeHourlySalaryContract();
            employee.setAnnualSalary(employeeContractDao.getAnnualSalary(employee.getHourlySalary()));
        }
        else if(employee.getContractTypeName().equalsIgnoreCase("MonthlySalaryEmployee")) {
            employeeContractDao = new EmployeeMonthlySalaryContract();
            employee.setAnnualSalary(employeeContractDao.getAnnualSalary(employee.getMonthlySalary()));
        }
        
        return employeeList.size();
    }
    
}
