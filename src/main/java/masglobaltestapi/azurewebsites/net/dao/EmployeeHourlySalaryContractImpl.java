package masglobaltestapi.azurewebsites.net.dao;

import org.springframework.stereotype.Repository;

/** 
* 
* @author Saurabh Gupta
* This class implements EmployeeContractFactory
* Provides implementation of getAnnualSalary method. 
*  
*/
@Repository("HourlySalaryContract")
public class EmployeeHourlySalaryContractImpl implements EmployeeContractFactory {
    
    /*
     * This method calculates annual salary based on hourly salary
     * @param hourlySalary
     * Complexity - O(1)
     */
    @Override
    public int getAnnualSalary(int hourlySalary) {
        
        int annualSalary = 120 * hourlySalary * 12;
        return annualSalary;
  }
    
}
