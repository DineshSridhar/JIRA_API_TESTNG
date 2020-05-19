package org.jira.utilis;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class CommonMethod {
 
	String response;
	 public  JsonPath js;
	 
	//Constructor
	public CommonMethod(String response)
	{
		this.response=response;
	}
	
	 public  JsonPath jsonParser()
    {
   	 js=new JsonPath(response);
   	 return js;
    }
    public  String getStringValue(String key)
    {
   	 return jsonParser().getString(key);
   	 
    }
    
     public  boolean checkNullAndEmpty(String property)
    {
   	 String value=getStringValue(property);
   	 if(value.isEmpty() || value==null)
   		 return true;
   	 else
   		 return false;
   	 
    }
}
