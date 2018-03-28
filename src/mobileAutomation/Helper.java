package mobileAutomation;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.text.DateFormat;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Helper {

	public static Properties prop;
	public static void captureScreenShots() throws IOException {
		String folder_name="screenshot";
		File f=((TakesScreenshot)DriverWrapper.driver).getScreenshotAs(OutputType.FILE);
		//Date format for screenshot file name
		DateFormat df=new SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa");
		//create directory with given folder name
		new File(folder_name).mkdir();
		//Setting file name
		String file_name=df.format(new Date())+".png";
		//copy screenshot file into screenshot folder.
		FileUtils.copyFile(f, new File(folder_name + "/" + file_name));
	}
	public static void loadObject()
	{
		File file = new File(System.getProperty("user.dir")+"\\src\\com\\config\\OR.properties");
		FileInputStream fileInput;
		try {
			fileInput = new FileInputStream(file);	
			prop = new Properties();
			prop.load(fileInput);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
