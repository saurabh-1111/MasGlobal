package masglobaltestapi.azurewebsites.net.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/** 
* 
* @author Saurabh Gupta
* This is the dto class which has all attributes of an employee. 
*  
*/
public class Employee {
    
    private final int id;
    private final String name;
    private final String contractTypeName ;
    private final int roleId ;
    private final String roleName ;
    private final String roleDescription;
    private final int hourlySalary ;
    private final int monthlySalary ;
    private int annualSalary;
   
    public Employee(@JsonProperty("id") int id, @JsonProperty("name") String name, 
            @JsonProperty("contractTypeName") String contractTypeName, @JsonProperty("roleId") int roleId, 
            @JsonProperty("roleName") String roleName, @JsonProperty("roleDescription") String roleDescription,
            @JsonProperty("hourlySalary") int hourlySalary, @JsonProperty("monthlySalary") int monthlySalary) {
        super();
        this.id = id;
        this.name = name;
        this.contractTypeName = contractTypeName;
        this.roleId = roleId;
        this.roleName = roleName;
        this.roleDescription = roleDescription;
        this.hourlySalary = hourlySalary;
        this.monthlySalary = monthlySalary;
        
    }


    public int getId() {
        return id;
    }



    public String getName() {
        return name;
    }



    public String getContractTypeName() {
        return contractTypeName;
    }



    public int getRoleId() {
        return roleId;
    }



    public String getRoleName() {
        return roleName;
    }



    public String getRoleDescription() {
        return roleDescription;
    }



    public int getHourlySalary() {
        return hourlySalary;
    }



    public int getMonthlySalary() {
        return monthlySalary;
    }



    public int getAnnualSalary() {
        return annualSalary;
    }



    public void setAnnualSalary(int annualSalary) {
        this.annualSalary = annualSalary;
    }
    
    @Override
    public String toString() {
        return "Employee [id=" + id + ", name=" + name + ", contractTypeName=" + contractTypeName + ", roleId=" + roleId
                + ", roleName=" + roleName + ", roleDescription=" + roleDescription + ", hourlySalary=" + hourlySalary
                + ", monthlySalary=" + monthlySalary + ", anualSalary=" + annualSalary + "]";
    }
}
