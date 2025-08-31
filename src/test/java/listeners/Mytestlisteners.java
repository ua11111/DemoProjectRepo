package listeners;

import java.io.IOException;


import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import util.ExtentReporter;
import util.ScreenshotUtil;
import tests.BaseTest;

public class Mytestlisteners implements ITestListener{
	
	ExtentReports extent= ExtentReporter.getExtentReport();
	ExtentTest test;

	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
	}

	public void onTestSuccess(ITestResult result) {
		
		test.log(Status.PASS ,result.getMethod().getMethodName() + " test case passed");
		
	}

	public void onTestFailure(ITestResult result) {
		

		test.log(Status.FAIL ,result.getMethod().getMethodName() + " test case failed");
		
		String screenshot = ScreenshotUtil.captureScreenshot(BaseTest.driver,result.getMethod().getMethodName());
		test.addScreenCaptureFromPath(screenshot);
		
			}

	public void onTestSkipped(ITestResult result) {
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}

	public void onTestFailedWithTimeout(ITestResult result) {
	
	}

	public void onStart(ITestContext context) {
		
	}

	public void onFinish(ITestContext context) {
		
		extent.flush();
		
	}

}
