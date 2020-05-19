package org.test.jira.IssueAPI;

import static org.testng.Assert.assertEquals;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jira.Base.BaseTest;
import org.jira.utilis.APIConfigResp;
import org.jira.utilis.CommonMethod;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class GetIssueDetails extends BaseTest  {
 
	  String endpoint="/rest/api/2/issue/{IssueKey}";
	   String response;
	   String issueKey;
	  Logger logger;
	  CommonMethod cmd=new CommonMethod(response);
	  @Test
	  public void GetIssueTest()
	  {
		  
		  
		 logger=LogManager.getLogger(GetIssueDetails.class);
		  
		 
		  
		
		response=   given()
				           .spec(requestSpec)
				           .pathParam("IssueKey",CreateIssue.issueKey)
				           .queryParam("fields", "summary,description,comment")
				     .when()
				           .get(endpoint)
				    .then()
				           .spec(responseSpec)
				           .statusCode(APIConfigResp.SUCCESS_RESPONSE_CODE)
				            .extract().asString();
		
		
		   
		 logger.info("Details fetched Successfully for Issue");
}
}
