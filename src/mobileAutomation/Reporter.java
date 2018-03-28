package mobileAutomation;

import java.io.File;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class Reporter {	
	public static ExtentReports extent;
	public static ExtentTest logger;
	
  public static void report(){
	  
  }
  
  public static void initiateExtendReport(){
	  extent = new ExtentReports(System.getProperty("user.dir")+"/test-output/MobileAutomationReport.html",true);
		extent.addSystemInfo("Host Name", "Mobile Automation")
		.addSystemInfo("Environment","Andriod")
		.addSystemInfo("User Name", "Arup Acharya");
		extent.loadConfig(new File(System.getProperty("user.dir")+"\\extent-config.xml"));
  }
}
