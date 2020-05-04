package masglobaltestapi.azurewebsites.net.service;

import masglobaltestapi.azurewebsites.net.dao.EmployeeDao;
import masglobaltestapi.azurewebsites.net.dto.Employee;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;



@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeServiceTests {
    
    @Autowired
    private EmployeeService employeeService;
    
    @MockBean
    private EmployeeDao employeeDao;
    
    @Test
    public void getAllEmployees() throws JSONException {
        String result = "[{hourlySalary:10000,monthlySalary:20000,roleId:1,name:Juan,roleName:Administrator,id:1,contractTypeName:HourlySalaryEmployee,roleDescription:null},{hourlySalary:10000,monthlySalary:20000,roleId:2,name:Sebastian,roleName:Contractor,id:2,contractTypeName:MonthlySalaryEmployee,roleDescription:null}]";
        
        JSONArray employeeList1 = new JSONArray(result);
        
        when(employeeDao.consumeEmployees()).thenReturn(employeeList1);
            
        List<Employee> list = employeeService.getAllEmployees();
            
        assertEquals(2, list.size());
        
        }
        
        
    
}
