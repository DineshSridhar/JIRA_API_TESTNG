package org.test.jira.LoginAPI;

import static io.restassured.RestAssured.given;

import java.io.IOException;


import org.Logintojira.pojo.LoginAuth;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jira.utilis.APIConfigResp;
import org.jira.utilis.CommonMethod;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.filter.session.SessionFilter;
import io.restassured.http.ContentType;

public class LoginToJira {

	String endpoint="/rest/auth/1/session";
	 public SessionFilter sessionFilter;
	String response;
	Logger logger;
	
	@Test
	public void LoginAuthAPI() throws IOException
	{
		sessionFilter = new SessionFilter();
		logger=LogManager.getLogger(LoginToJira.class);
		
		LoginAuth loginAuth = new LoginAuth();
		loginAuth.setUsername(APIConfigResp.getGlobalValue("username"));
		loginAuth.setPassword(APIConfigResp.getGlobalValue("password"));
		
		response=  given()
		           .baseUri(APIConfigResp.getGlobalValue("baseURI"))
		           .contentType(ContentType.JSON)
		           .body(loginAuth)
		           .log().all()
		           .filter(sessionFilter)
		    .when()
		           .post(endpoint)
		    .then()
		           .statusCode(APIConfigResp.SUCCESS_RESPONSE_CODE)
		           .log().all()
		           .extract().asString();	
		
		CommonMethod cm=new CommonMethod(response);
   	 Assert.assertFalse(cm.checkNullAndEmpty("session.name"));
   	 Assert.assertFalse(cm.checkNullAndEmpty("session.value"));
		
   	logger.info("Successfully Executed LoginAPI");
   	 
	}
	public SessionFilter getSessionDetails()
    {
    	return sessionFilter;
    }
}
