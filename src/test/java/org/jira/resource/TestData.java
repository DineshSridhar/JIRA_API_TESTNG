package org.jira.resource;

import org.Createissue.pojo.CreateIssuePojo;
import org.Createissue.pojo.Fields;
import org.Createissue.pojo.IssueType;
import org.Createissue.pojo.Project;

public class TestData {

	public CreateIssuePojo CreateIssuePayload(String keyvalue,String name)
	{
		Fields fields = new Fields();
		Project proj = new Project();
		proj.setKey(keyvalue);;
		IssueType issue = new IssueType();
		issue.setName(name);
		CreateIssuePojo create= new CreateIssuePojo();
		create.setFields(fields);
		fields.setProject(proj);
		fields.setIssuetype(issue);
		fields.setDescription("Creating My bug");
		fields.setSummary("Payment issue");
		return create;
		
		
	}
	
	
}