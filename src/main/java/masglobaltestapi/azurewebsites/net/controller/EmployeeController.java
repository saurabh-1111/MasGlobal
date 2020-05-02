package masglobaltestapi.azurewebsites.net.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;



import masglobaltestapi.azurewebsites.net.dto.Employee;
import masglobaltestapi.azurewebsites.net.service.EmployeeService;

@RequestMapping("/api/employee")
//("http://masglobaltestapi.azurewebsites.net/api/employees")
@RestController
public class EmployeeController {
    
    private final EmployeeService employeeService; 
    
    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        System.out.println("EmployeeController called.");
        this.employeeService = employeeService;
    }
    
    @GetMapping
    public List<Employee> getAllEmployees(){
        System.out.println("EmployeeController getAllEmployees called.");
        return employeeService.getAllEmployees();
    }
    
    @GetMapping(path="{id}")
    public Optional<Employee> getEmployeeById(@PathVariable("id") int id){
        System.out.println("EmployeeController getEmployeeById called.");
        return employeeService.getEmployeeById(id);
    }
    
    //@PostMapping
    public int addEmployee(@Valid @NonNull @RequestBody Employee employee) {
        return employeeService.addEmployee(employee);
    }
    
    @PostMapping
    private void getUsers(@Valid @NonNull @RequestBody Employee employee) {

        final String uri = "http://masglobaltestapi.azurewebsites.net/api/employees";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);      
        System.out.println(" result: " + result); 
        
        try {
            //JSONObject jsonResponse = new JSONObject(result);
            JSONArray employeeList = new JSONArray(result);
            
            //Employee employee1 = (Employee) jsonResponse.get(0);
            
            JSONObject obj = (JSONObject) employeeList.get(0);
            obj.get("name");
            
            //Employee employee1 = (Employee) obj;
            
            
            String value = employeeList.get(0).toString() ;// ("name");  
            System.out.println(" value.toString(): " + obj.get("name")); 
        } catch (JSONException e) {
        e.printStackTrace();
        }
        
        
   }
    
    
}
