package com.restassured.listeners;



import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.restassured.reports.ExtentManager;
import com.restassured.reports.ExtentReport;
import com.restassured.reports.LogStatus;


/*
 * Listener class which is implementing ITestListener and hence we can use this to dynamically create reports, write logs.
 */
public class ListenerClass implements ITestListener{
	
	private static String TestcaseName;

	

	public static String getTestcaseName() {
		return TestcaseName;
	}

	public static void setTestcaseName(String testcaseName) {
		TestcaseName = testcaseName;
	}

	public void onTestStart(ITestResult result) {
		TestcaseName =result.getMethod().getDescription();
		setTestcaseName(TestcaseName);
		ExtentManager.setExtentTest(ExtentReport.report.startTest(TestcaseName));
		LogStatus.pass(TestcaseName+ " is started successfully");
		
	}

	public void onTestSuccess(ITestResult result) {
		/*if((ReadPropertyFile.get("RunMode").equalsIgnoreCase("Remote"))&&ReadPropertyFile.get("RemoteMode").equalsIgnoreCase("Zalenium")) {
			Cookie cookie = new Cookie("zaleniumTestPassed", "true");
		    DriverManager.getDriver().manage().addCookie(cookie);
		}*/
		LogStatus.pass(result.getMethod().getDescription()+ " test case is passed");
		ExtentReport.report.endTest(ExtentManager.getExtTest());
	}

	public void onTestFailure(ITestResult result) {
		/*if((ReadPropertyFile.get("RunMode").equalsIgnoreCase("Remote"))&&ReadPropertyFile.get("RemoteMode").equalsIgnoreCase("Zalenium")) {
			Cookie cookie = new Cookie("zaleniumTestPassed", "false");
		    DriverManager.getDriver().manage().addCookie(cookie);
		}*/
		LogStatus.fail(result.getMethod().getDescription()+ " is failed");
		LogStatus.fail(result.getThrowable().toString());
		//LogStatus.fail("Failed",TestUtils.pullScreenshotPath());
		ExtentReport.report.endTest(ExtentManager.getExtTest());
		
	}

	public void onTestSkipped(ITestResult result) {
		
		LogStatus.skip(result.getMethod().getDescription()+ " is skipped");
		
		ExtentReport.report.endTest(ExtentManager.getExtTest());
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ExtentReport.report.endTest(ExtentManager.getExtTest());
	}

	public void onStart(ITestContext context) {
	
		
	}

	public void onFinish(ITestContext context) {
		ExtentReport.report.endTest(ExtentManager.getExtTest());
		
	}



}
