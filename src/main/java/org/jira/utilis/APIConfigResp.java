package org.jira.utilis;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class APIConfigResp {
	
	public static String getGlobalValue(String key) throws IOException
	{
		Properties prop = new Properties();
		FileInputStream fileInputStream = new FileInputStream("C:\\Users\\UTIS LAPTOP 54\\Eclispe_Worspace3_Jira\\JiraAPI\\src\\main\\java\\org\\jira\\configproperties\\Global.properties");
		prop.load(fileInputStream);
		return prop.getProperty(key);
		
	}
	
	    public static int SUCCESS_RESPONSE_CODE=200;
	    public static int CREATED_RESPONSE_CODE=201;
	    public static int DELETE_RESPONSE_CODE=204;
	    
}
