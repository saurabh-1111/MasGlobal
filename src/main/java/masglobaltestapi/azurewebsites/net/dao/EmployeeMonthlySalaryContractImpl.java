package masglobaltestapi.azurewebsites.net.dao;

import org.springframework.stereotype.Repository;

/** 
* 
* @author Saurabh Gupta
* This class implements EmployeeContractFactory
* Provides implementation of getAnnualSalary method. 
*  
*/
@Repository("MonthlySalaryContract")
public class EmployeeMonthlySalaryContractImpl implements EmployeeContractFactory{
    
    /*
     * This method calculates annual salary based on monthly salary
     * @param hourlySalary
     * Complexity - O(1)
     */
    @Override
    public int getAnnualSalary(int monthlySalary) {
        
        int annualSalary = monthlySalary * 12; 
        return annualSalary;
    }
    
}
