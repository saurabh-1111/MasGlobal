package masglobaltestapi.azurewebsites.net.dao;

/** 
* 
* @author Saurabh Gupta
* This interface has one method to get annual salary
* EmployeeHourlySalaryContractImpl and EmployeeMonthlySalaryContractImpl has the implementation.  
*  
*/
public interface EmployeeContractFactory {
    
   int getAnnualSalary(int salary);
 
}
