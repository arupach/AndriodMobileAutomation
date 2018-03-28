package mobileAutomation;

import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;

public class DriverWrapper {
	 public static AndroidDriver<AndroidElement> driver = null;
	 public static String reportDirectory = "reports";
	 public static String reportFormat = "xml";
	 public static String testName = "Untitled";
		//static 
	public static void initiateDriver(){
		try{
			Properties prop = Helper.prop;
			DesiredCapabilities dc = new DesiredCapabilities();	
			dc.setCapability("automationName",prop.getProperty("automator"));	
			dc.setCapability("reportDirectory", reportDirectory);
			dc.setCapability("reportFormat", reportFormat);
			dc.setCapability("testName", testName);
			dc.setCapability("unicodeKeyboard", "true");                                     
			dc.setCapability("resetKeyboard", "true");
			dc.setCapability(MobileCapabilityType.UDID, prop.getProperty("DeviceId"));
			driver = new AndroidDriver<>(new URL(prop.getProperty("Deviceurl")), dc);
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		}catch(Exception e){

		}
	}
}
