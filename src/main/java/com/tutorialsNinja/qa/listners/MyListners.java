package com.tutorialsNinja.qa.listners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tutorialsNinja.qa.utils.ExtentReporter;
import com.tutorialsNinja.qa.utils.Utils;

public class MyListners implements ITestListener{
	ExtentReports extentReport;
	ExtentTest extentTest;
	String testName;

	@Override
	public void onStart(ITestContext context) {
		//System.out.println("Execution of project Test starts");
		extentReport = ExtentReporter.generateExtentReport();
		
		
	}
	
	@Override
	public void onTestStart(ITestResult result) {
		testName = result.getName();
		extentTest = extentReport.createTest(testName);
		extentTest.log(Status.INFO, testName + " Started Executing ");
		//System.out.println(testName + " Started Executing ");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		extentTest.log(Status.PASS,testName+ " got susseccfully executed ");
		//System.out.println(testName + " got susseccfully executed ");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		//System.out.println("ScreenShot taken");
		
		WebDriver driver= null;
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		File srcScreenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//		String destinationScreenShotPath = System.getProperty("user.dir")+"\\ScreenShots\\" + testName + ".png";
//		try {
//			FileHandler.copy(srcScreenshot, new File(destinationScreenShotPath));
//		}catch (IOException e) {
//			e.printStackTrace();
//		}
		
		String destinationScreenShotPath =  Utils.captureScreenShots(driver, testName);
//		
		extentTest.addScreenCaptureFromPath(destinationScreenShotPath);
		extentTest.log(Status.INFO,result.getThrowable());
		extentTest.log(Status.FAIL,testName+"Got Failed");
		
	}

	
	@Override
	public void onTestSkipped(ITestResult result) {
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.SKIP, testName+"Got Skipped");
//		System.out.println(testName + " got skipped");
//		System.out.println(result.getThrowable());
	}


	@Override
	public void onFinish(ITestContext context) {
		System.out.println("Finished executing project tests");
		extentReport.flush();
		
		String pathOfExtentReport = System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html";
		File extentReport = new File(pathOfExtentReport);
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
