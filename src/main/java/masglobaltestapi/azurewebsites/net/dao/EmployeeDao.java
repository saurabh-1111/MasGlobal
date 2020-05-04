package masglobaltestapi.azurewebsites.net.dao;

import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

/** 
* 
* @author Saurabh Gupta
* This class is a data repository for all employees
* Connects to MasGlobal api and consumes employees information 
*  
*/
public class EmployeeDao {
    
    // this contains info for all employees in json array
    private static JSONArray employeeList = null;
    
    /*
     * Consume employee info from external url
     * Returns an array if employee information exists
     * else returns null
     * Complexity - O(1)
     */
     public static JSONArray consumeEmployees() {

        final String uri = "http://masglobaltestapi.azurewebsites.net/api/employees";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);      
       
        try {
            JSONArray employeeList = new JSONArray(result);
              
            // returns null if no employee information found
            if(employeeList.length() == 0)
                return null;
            else
                return employeeList;
            
        } catch (JSONException e) {
        e.printStackTrace();
        }
       return employeeList;
    }
    
}
