package mobileAutomation;

import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class LoginPage {
	// Webelemet for Login Page
	WebDriver driver  = null;
	ExtentTest logger = null;

	public LoginPage() {
		driver = DriverWrapper.driver;
		logger = Reporter.logger;
	}
	public void login(){
		Properties prop = Helper.prop;
		try {
			new WebDriverWait(driver, 60).until(ExpectedConditions.presenceOfElementLocated(By.xpath(prop.getProperty("username"))));
			driver.findElement(By.xpath(prop.getProperty("username"))).clear();
			driver.findElement(By.xpath(prop.getProperty("username"))).sendKeys(prop.getProperty("usernameValue"));
			logger.log(LogStatus.PASS, "Username "+prop.getProperty("usernameValue")+" Entered");

			new WebDriverWait(driver, 60).until(ExpectedConditions.presenceOfElementLocated(By.xpath(prop.getProperty("password"))));
			driver.findElement(By.xpath(prop.getProperty("password"))).sendKeys(prop.getProperty("passwordValue"));
			logger.log(LogStatus.PASS, "Password ********* Entered");

			new WebDriverWait(driver, 60).until(ExpectedConditions.presenceOfElementLocated(By.xpath(prop.getProperty("SignInButton2"))));
			driver.findElement(By.xpath(prop.getProperty("SignInButton2"))).click();
			logger.log(LogStatus.PASS, "SingIn Button "+prop.getProperty("usernameValue")+" Clicked");
			Helper.captureScreenShots();

			new WebDriverWait(driver, 30).until(ExpectedConditions.presenceOfElementLocated(By.xpath(prop.getProperty("NoThanks"))));
			driver.findElement(By.xpath(prop.getProperty("NoThanks"))).click();
			Helper.captureScreenShots();

		} catch (NoSuchElementException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
