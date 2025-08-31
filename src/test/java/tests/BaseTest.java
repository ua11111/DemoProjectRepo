package tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
    
    public static WebDriver driver;
    public Properties prop;

    public WebDriver launchBrowser(String browserName) throws IOException {
        // Load properties
        prop = new Properties();
        File propfile = new File(System.getProperty("user.dir") + "\\src\\test\\resources\\datareading.properties");
        FileInputStream fis = new FileInputStream(propfile);
        prop.load(fis);

        // Select browser
        if (browserName.equalsIgnoreCase("Chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("Firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else if (browserName.equalsIgnoreCase("Edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        } else {
            throw new IllegalArgumentException("Invalid browser: " + browserName);
        }

        driver.manage().window().maximize();
        return driver;
    }
    @AfterMethod()
    public void quitBrowser() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
