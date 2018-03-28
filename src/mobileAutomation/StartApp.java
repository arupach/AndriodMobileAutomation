package mobileAutomation;


import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.ScreenOrientation;
import com.relevantcodes.extentreports.ExtentTest;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class StartApp {

	public static AndroidDriver<AndroidElement> driver = null;
	ExtentTest logger = null;
	public StartApp() {
		driver = DriverWrapper.driver;
		logger = Reporter.logger;
	}
	public void StartEbayApp(){
		Properties prop = Helper.prop;
		try {
			//driver.context(prop.getProperty("AppType"));
			driver.findElement(By.xpath(prop.getProperty("AppDescription"))).click();
			driver.executeScript(prop.getProperty("ScrollDown"));
			driver.findElement(By.xpath(prop.getProperty("ebayIcon"))).click();
			driver.findElement(By.xpath(prop.getProperty("SignInButton"))).click();
			Helper.captureScreenShots();
			
			//Handling Mobile Screen Rotation. Changing it to landscape
			driver.rotate(ScreenOrientation.LANDSCAPE);			
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
