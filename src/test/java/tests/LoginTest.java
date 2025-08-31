package tests;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageobject.LoginPage;
import util.ControlSheetUtil;
import util.ExcelUtil;

public class LoginTest extends BaseTest {
   
    @DataProvider(name = "LoginData")
    public Object[][] getData() throws Exception {
        String[][] rawData = ExcelUtil.getSheetData(
            "C:\\Users\\ashut\\OneDrive\\Documents\\Karanworkspace\\Sikhnahai\\testdata\\TestData.xlsx",
            "LoginData"
        );

        Object[][] data1 = new Object[rawData.length][1];  // 1 HashMap per row

        for (int i = 0; i < rawData.length; i++) {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("runmode", rawData[i][0]);
            map.put("browser", rawData[i][1]);
            map.put("username", rawData[i][2]);
            map.put("password", rawData[i][3]);
            map.put("expectedResult", rawData[i][4]);

            data1[i][0] = map; // Store HashMap in column 0
        }

        return data1;
    }

    @Test(dataProvider = "LoginData")
    public void loginTest(HashMap<String, String> hm1) throws Exception {
        HashMap<String, String> controlMap = ControlSheetUtil.getControlData(
            "C:\\Users\\ashut\\OneDrive\\Documents\\Karanworkspace\\Sikhnahai\\testdata\\TestCaseControl.xlsx",
            "Sheet1"
        );

        // Control sheet check
        if (!controlMap.get("LoginTest").equalsIgnoreCase("Y")) {
            throw new SkipException("Skipping LoginTest since Runmode=N in Control Sheet");
        }

        // Dataset check
        if (!hm1.get("runmode").equalsIgnoreCase("Y")) {
            throw new SkipException("Skipping dataset since Runmode=N");
        }

        // Launch browser
        driver = launchBrowser(hm1.get("browser"));
        LoginPage l= new LoginPage(driver);
        
            driver.get(prop.getProperty("url"));
            
           

            // Enter username and password
            l.supplyusername(hm1.get("username"));
           // Thread.sleep(10);
            l.supplypassword(hm1.get("password"));
            //Thread.sleep(10);
            l.clicksubmit();
          //  driver.findElement(By.id("id_email_login")).sendKeys(hm1.get("username"));
          //  driver.findElement(By.id("id_password")).sendKeys(hm1.get("password"));
           // driver.findElement(By.xpath("//*[@id=\"cndidate_login_widget\"]/form/ul[2]/li[4]/div/button")).click();

            // Check login result
            String actualResult = driver.getTitle().contains("Dashboard") ? "Success" : "Failure";
            Assert.assertEquals(actualResult, hm1.get("expectedResult"));
        
        }  } 