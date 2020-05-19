package org.test.jira.IssueAPI;
import static io.restassured.RestAssured.*;

import java.io.IOException;

import org.Logintojira.pojo.LoginAuth;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jira.Base.BaseTest;
import org.jira.resource.TestData;
import org.jira.utilis.APIConfigResp;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.specification.ResponseSpecification;

public class CreateIssue extends BaseTest{

	String endpoint="/rest/api/2/issue";
	TestData data=new TestData();
	String response;
	String keyvalue="AP";
	String name="Bug";
	static String issueId;
	static String issueKey;
	static String issuelink;
	Logger logger;
	
	@Test
	  public void CreateIssueTest() throws IOException
	  {
		logger=LogManager.getLogger(CreateIssue.class);
		
	response=
	         given()
	         .spec(requestSpec)
					.header("Content-Type","application/json")
					.body(data.CreateIssuePayload(keyvalue, name))
			.when()
		       		.post(endpoint)
		    .then()
		    .spec(responseSpec)
		    		.statusCode(APIConfigResp.CREATED_RESPONSE_CODE)
		    		.extract().asString();
	    
	   
	     JsonPath js=new JsonPath(response);
	     
	     
	     issueId =js.getString("id");
	     issueKey =js.getString("key");
	     issuelink=js.getString("self");
	     
	     
	     Assert.assertTrue( !issueId.isEmpty() && issueId!=null );
	     Assert.assertTrue(issueKey.contains((keyvalue)+"-"));
	     Assert.assertEquals(issuelink,APIConfigResp.getGlobalValue("baseURI")+endpoint+"/"+issueId);
	     
	     logger.info("Successfully completed CreateIssue Request");
	  }
}
