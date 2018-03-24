package mobileAutomation;

//package <set your test package>;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.TakesScreenshot;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

public class Ebay {
	private String reportDirectory = "reports";
	private String reportFormat = "xml";
	private String testName = "Untitled";
	String folder_name;
	DateFormat df;
	ExtentReports extent;
	ExtentTest logger;
	
	protected AndroidDriver<AndroidElement> driver = null;

	DesiredCapabilities dc = new DesiredCapabilities();
	
	@BeforeMethod
	public void setUp() throws MalformedURLException {
		dc.setCapability("reportDirectory", reportDirectory);
		dc.setCapability("reportFormat", reportFormat);
		dc.setCapability("testName", testName);
		dc.setCapability("unicodeKeyboard", "true");                                     
		dc.setCapability("resetKeyboard", "true");
		dc.setCapability(MobileCapabilityType.UDID, "ZY22232ZB2");
		driver = new AndroidDriver<>(new URL("http://localhost:4723/wd/hub"), dc);
	}
	
	@BeforeTest
	public void startTest(){
		extent = new ExtentReports(System.getProperty("user.dir")+"/test-output/MobileAutomationReport.html",true);
		extent.addSystemInfo("Host Name", "Mobile Automation")
		.addSystemInfo("Environment","Andriod")
		.addSystemInfo("User Name", "Arup Acharya");

		extent.loadConfig(new File(System.getProperty("user.dir")+"\\extent-config.xml"));
	}
	
	@Test
	public void BuyPhone() throws Exception {
		
		
		File file = new File(System.getProperty("user.dir")+"\\src\\com\\config\\OR.properties");
		  
		FileInputStream fileInput = null;
		try {
			fileInput = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Properties prop = new Properties();
		
		//load properties file
		try {
			prop.load(fileInput);
		} catch (IOException e) {
			e.printStackTrace();
		}
				
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.context(prop.getProperty("AppType"));

		driver.findElement(By.xpath(prop.getProperty("AppDescription"))).click();

		driver.executeScript(prop.getProperty("ScrollDown"));

		driver.findElement(By.xpath(prop.getProperty("ebayIcon"))).click();

		driver.findElement(By.xpath(prop.getProperty("SignInButton"))).click();
		captureScreenShots();
		//Handling Mobile Screen Rotation. Changing it to landscape
		driver.rotate(ScreenOrientation.LANDSCAPE);
		
		new WebDriverWait(driver, 30).until(ExpectedConditions.presenceOfElementLocated(By.xpath(prop.getProperty("username"))));
		driver.findElement(By.xpath(prop.getProperty("username"))).clear();
		driver.findElement(By.xpath(prop.getProperty("username"))).sendKeys(prop.getProperty("usernameValue"));
		
		new WebDriverWait(driver, 30).until(ExpectedConditions.presenceOfElementLocated(By.xpath(prop.getProperty("password"))));
		driver.findElement(By.xpath(prop.getProperty("password"))).sendKeys(prop.getProperty("passwordValue"));
		
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath(prop.getProperty("SignInButton2"))));
		driver.findElement(By.xpath(prop.getProperty("SignInButton2"))).click();
		captureScreenShots();
		
		new WebDriverWait(driver, 30).until(ExpectedConditions.presenceOfElementLocated(By.xpath(prop.getProperty("NoThanks"))));
		driver.findElement(By.xpath(prop.getProperty("NoThanks"))).click();
		captureScreenShots();
		
		//Handling Mobile Screen Rotation. Changing it to Portrait again
		driver.rotate(ScreenOrientation.PORTRAIT);
		
		new WebDriverWait(driver, 30).until(ExpectedConditions.presenceOfElementLocated(By.xpath(prop.getProperty("SearchBox"))));
		driver.findElement(By.xpath(prop.getProperty("SearchBox"))).click();

		new WebDriverWait(driver, 30).until(ExpectedConditions.presenceOfElementLocated(By.xpath(prop.getProperty("SearchBoxText"))));
		driver.findElement(By.xpath(prop.getProperty("SearchBoxText"))).sendKeys(prop.getProperty("ModelName"));
		captureScreenShots();
		driver.getKeyboard().sendKeys("{ENTER}");

		new WebDriverWait(driver, 30).until(ExpectedConditions.presenceOfElementLocated(By.xpath(prop.getProperty("PhoneName"))));
		driver.findElement(By.xpath(prop.getProperty("PhoneName"))).click();
		captureScreenShots();
		
		new WebDriverWait(driver, 30).until(ExpectedConditions.presenceOfElementLocated(By.xpath(prop.getProperty("BuyNowButton"))));
		driver.findElement(By.xpath(prop.getProperty("BuyNowButton"))).click();
		captureScreenShots();
		//Till buy it now screen
		
		logger = extent.startTest("BuyPhone");
		Assert.assertTrue(true);
		logger.log(LogStatus.PASS, "Test Case passed");
		extent.endTest(logger);
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	public void captureScreenShots() throws IOException {
        folder_name="screenshot";
        File f=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        //Date format for screenshot file name
        df=new  SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa");
        //create directory with given folder name
        new File(folder_name).mkdir();
        //Setting file name
        String file_name=df.format(new Date())+".png";
        //copy screenshot file into screenshot folder.
        FileUtils.copyFile(f, new File(folder_name + "/" + file_name));
    }
	
	@AfterTest
	 public void endReport(){
	                extent.flush();
	                extent.close();
	    }
}