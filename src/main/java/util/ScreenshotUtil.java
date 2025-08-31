package util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.util.Date;
import org.apache.commons.io.FileUtils;

public class ScreenshotUtil {
	
	// 🔹 This will be assigned from BaseTest
  //  public static WebDriver driver;
	//public static WebDriver driver;

	public static String captureScreenshot(WebDriver driver, String testName) {
        String destPath = null;
        try {
            if (driver != null) {
                File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                destPath = System.getProperty("user.dir") + "/screenshots/" + testName + "_" + timestamp + ".png";
                File dest = new File(destPath);
                FileUtils.copyFile(src, dest);
            } else {
                // fallback name when driver is null
                destPath = System.getProperty("user.dir") + "/screenshots/" + testName + "_DriverIsNull.png";
            }
        } catch (IOException e) {
            e.printStackTrace();
            // fallback if screenshot fails
            destPath = System.getProperty("user.dir") + "/screenshots/" + testName + "_ScreenshotFailed.png";
        }
        return destPath;
    }
}


