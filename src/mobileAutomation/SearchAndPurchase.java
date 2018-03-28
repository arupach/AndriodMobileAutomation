package mobileAutomation;

import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class SearchAndPurchase {

	public static AndroidDriver<AndroidElement> driver = null;
	ExtentTest logger = null;
	public SearchAndPurchase() {
		driver = DriverWrapper.driver;
		logger = Reporter.logger;
	}
	public void searchandpurchase(){

		Properties prop = Helper.prop;
		
		try {
			driver.rotate(ScreenOrientation.PORTRAIT);
			new WebDriverWait(driver, 60).until(ExpectedConditions.presenceOfElementLocated(By.xpath(prop.getProperty("SearchBox"))));
			driver.findElement(By.xpath(Helper.prop.getProperty("SearchBox"))).click();
			logger.log(LogStatus.PASS, "Find "+prop.getProperty("SearchBox")+" Sreach box");

			new WebDriverWait(driver, 60).until(ExpectedConditions.presenceOfElementLocated(By.xpath(prop.getProperty("SearchBoxText"))));
			driver.findElement(By.xpath(prop.getProperty("SearchBoxText"))).sendKeys(prop.getProperty("ModelName"));
			logger.log(LogStatus.PASS, "Find "+prop.getProperty("SearchBoxText")+" and enter text");
			Helper.captureScreenShots();
			driver.getKeyboard().sendKeys("{ENTER}");

			new WebDriverWait(driver, 60).until(ExpectedConditions.presenceOfElementLocated(By.xpath(prop.getProperty("PhoneName"))));
			driver.findElement(By.xpath(prop.getProperty("PhoneName"))).click();
			logger.log(LogStatus.PASS, "Search "+prop.getProperty("PhoneName")+" Phone");
			Helper.captureScreenShots();

			new WebDriverWait(driver, 60).until(ExpectedConditions.presenceOfElementLocated(By.xpath(prop.getProperty("BuyNowButton"))));
			driver.findElement(By.xpath(prop.getProperty("BuyNowButton"))).click();
			logger.log(LogStatus.PASS, "Click  Buy Now button");
			Helper.captureScreenShots();
			//Till buy it now screen
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
