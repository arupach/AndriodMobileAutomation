package mobileAutomation;

//package <set your test package>;

import org.testng.annotations.*;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import java.text.DateFormat;
import java.io.FileInputStream;
import java.io.IOException;

public class MobileAutomationTest {
	String folder_name;
	DateFormat df;
	FileInputStream fileInput = null;
	WebDriver driver = null;

	@BeforeTest
	public void testClassSetUp(){
		Reporter.initiateExtendReport();
		Helper.loadObject();
	}

	@BeforeMethod
	public void testCasesetup(){
		DriverWrapper.initiateDriver();
		driver = DriverWrapper.driver;
		Reporter.logger = Reporter.extent.startTest("Buying a phone from ebay ");
	}

	@Test (description="To verify whether app is able to place order or not",priority=1)
	public void ebayAppPlaceOrder() throws IOException {

		StartApp SA =  new StartApp();
		SA.StartEbayApp();

		LoginPage LP = new LoginPage();
		LP.login();

		SearchAndPurchase SaP = new SearchAndPurchase();
		SaP.searchandpurchase();

	}

	@AfterTest
	public void testClassTearDown(){
		System.out.println(" I am in tear Down");
		Reporter.extent.endTest(Reporter.logger);		
		Reporter.extent.flush();
		Reporter.extent.close();
	}

	@AfterMethod
	public void testCaseTearDown() {
		DriverWrapper.driver.quit();
	}

}
