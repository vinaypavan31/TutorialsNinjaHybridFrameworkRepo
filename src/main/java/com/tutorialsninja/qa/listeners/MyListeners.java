package com.tutorialsninja.qa.listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tutorialsninja.qa.utils.ExtentReporter;
import com.tutorialsninja.qa.utils.Utilities;

public class MyListeners implements ITestListener {
	ExtentReports extentReport;
	ExtentTest extentTest;

	@Override
	public void onStart(ITestContext context) {
		try {
			extentReport = ExtentReporter.generateExtentReporter();
		} catch (IOException e) {

			e.printStackTrace();
		}
		String folderPath = System.getProperty("user.dir") + "/Screenshots";
		File folder = new File(folderPath);

		if (folder.exists()) {
			File[] files = folder.listFiles();

			if (files != null) {
				for (File file : files) {
					file.delete();
				}
			}
			System.out.println("Screenshots cleaned before execution");
		} else {
			folder.mkdirs();
		}

	}

	@Override
	public void onTestStart(ITestResult result) {

		extentTest = extentReport.createTest(result.getName());
		extentTest.log(Status.INFO, " started executing");

	}

	@Override
	public void onTestSuccess(ITestResult result) {

		extentTest.log(Status.PASS, result.getName() + " got successfully executed");

	}

	@Override
	public void onTestFailure(ITestResult result) {

		WebDriver driver = null;

		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver")
					.get(result.getInstance());
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (driver != null) {

			// 📸 Base64 (for report)
			String base64 = Utilities.getBase64Screenshot(driver);
			extentTest.addScreenCaptureFromBase64String(base64, result.getName());

			// 📁 Save file (for project)
			String path = Utilities.saveScreenshot(driver, result.getName());
			System.out.println("Screenshot saved at: " + path);
		}

		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.FAIL, result.getName() + " got failed");
	}

	@Override
	public void onTestSkipped(ITestResult result) {

		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.SKIP, result.getName() + " get skipped");

	}

	@Override
	public void onFinish(ITestContext context) {

		extentReport.flush();
		String pathOfExtentReport = System.getProperty("user.dir") + "\\test-output\\ExtentReports\\extentReport.html";
		File extentReport = new File(pathOfExtentReport);
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

}
