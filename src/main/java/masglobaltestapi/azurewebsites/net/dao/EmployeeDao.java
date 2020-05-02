package masglobaltestapi.azurewebsites.net.dao;

import javax.validation.Valid;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.client.RestTemplate;

import masglobaltestapi.azurewebsites.net.dto.Employee;

public class EmployeeDao {
    
    public static JSONArray employeeList = null;
    
    
    public static JSONArray consumeEmployees() {

        final String uri = "http://masglobaltestapi.azurewebsites.net/api/employees";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);      
        System.out.println(" result: " + result); 
        
        try {
            //JSONObject jsonResponse = new JSONObject(result);
            JSONArray employeeList = new JSONArray(result);
            
            if(employeeList.length() == 0)
                return null;
            else
                return employeeList;
            /*
            
            //Employee employee1 = (Employee) jsonResponse.get(0);
            
            JSONObject obj = (JSONObject) employeeList.get(0);
            obj.get("name");
            
            //Employee employee1 = (Employee) obj;
            
            
            String value = employeeList.get(0).toString() ;// ("name");  
            System.out.println(" value.toString(): " + obj.get("name"));
            */
        } catch (JSONException e) {
        e.printStackTrace();
        }
       return employeeList;
    }
    
}
