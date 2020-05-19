package org.jira.Base;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.Logintojira.pojo.LoginAuth;
import org.jira.utilis.APIConfigResp;
import org.test.jira.LoginAPI.LoginToJira;
import org.testng.annotations.BeforeMethod;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.filter.session.SessionFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class BaseTest {
	
	public RequestSpecification requestSpec;
	public ResponseSpecification responseSpec;
		
	@BeforeMethod
    public void setUp() throws IOException
    { 
   	 
   
   String authMode=APIConfigResp.getGlobalValue("auth_mode");  
   	 
   	 
   	 if(authMode.contains("cookie"))
   	 {
   		 
   		 LoginToJira loginToJira = new LoginToJira();
   		 SessionFilter session=new SessionFilter();
   		loginToJira.LoginAuthAPI();;
   		 session=loginToJira.getSessionDetails();
   		 
   		 
   		 requestSpec=new RequestSpecBuilder()
    		.setBaseUri(APIConfigResp.getGlobalValue("baseURI"))
    		.log(LogDetail.ALL)
    		.addFilter(session)
    		.build();	 
   	 }
   	  		
   	 else
   	 {
   		 try
   		 {   
				throw new Exception("Please provide API config file");
			  } 
   		 catch (Exception e) {
				
				e.printStackTrace();
			}
   	 }
   	   	 
   	
   	 responseSpec=new ResponseSpecBuilder()
   	.log(LogDetail.ALL)
   	.build();
   	 
    }
	
}
