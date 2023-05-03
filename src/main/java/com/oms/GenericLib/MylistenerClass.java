package com.oms.GenericLib;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.model.Media;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MylistenerClass extends BaseUtilityClass implements ITestListener {

	/*
	 * ExtentSparkReporter reporter; ExtentReports reports; ExtentTest test;
	 */
	FileLibClass fil = new FileLibClass();
	public static String MethodName;
	WebdriverActionclass webaction;
	String concatenate = ".";

	@Override
	public void onTestStart(ITestResult result) {
		System.out.println(result.getMethod().getMethodName() + " is Start");
//		 test = reports.createTest("onTestStart");
//		 MethodName = result.getMethod().getMethodName();
		System.out.println("=================@@@#####onTestStart#####@@@====================");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		/*
		 * Reporter.log(result.getMethod().getMethodName() + " Test Case Skipped",
		 * true); test.log(Status.SKIP, result.getMethod().getMethodName() +
		 * " Got Skipped"); test.log(Status.WARNING, result.getMethod().getMethodName()
		 * + " is got Skipped  Kindly Check Flow ");
		 */
		Reporter.log(MethodName + " Test Case Skipped", true);
		test.log(Status.SKIP, MethodName + " Got Skipped");
		test.log(Status.WARNING, MethodName + " is got Skipped  Kindly Check Flow ");
		System.out.println("<================" + MethodName + " is got Skipped  Kindly Check Flow =============>");

		webaction = getWebdriverAction();
		String path = concatenate + webaction.screenshot_fail(result.getName());
		test.addScreenCaptureFromPath(path);
	}

	@Override
	public void onStart(ITestContext context) {

		System.out.println("=========onStart========onStart========><");
		webaction = getWebdriverAction();
		reporter = new ExtentSparkReporter(System.getProperty("user.dir") + IAutoconstant.Extendreport_PATH
				+ "ExtentReport" + "_" + webaction.currentDateTime() + ".html");
		reporter.config().setTheme(Theme.STANDARD);
		reporter.config().setDocumentTitle("OMS-DEMO");

		reports = new ExtentReports();
		reports.attachReporter(reporter);
		try {
			reports.setSystemInfo("Created By  ", fil.readPropertyData(PROP_PATH, "Report_creator"));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		try {
			reports.setSystemInfo("Tester Name  ", fil.readPropertyData(PROP_PATH, "Tester_name"));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		reports.setSystemInfo("Environment  ", "QA");
		try {
			reports.setSystemInfo("Browser : ", fil.readPropertyData(PROP_PATH, "Browser"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		test = reports.createTest("onTestStart");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("IsScucess =" + isSuccess);
		/*
		 * if(isSuccess) {
		 * Reporter.log(result.getMethod().getMethodName()+" is Pass",true);
		 * test.log(Status.PASS,result.getMethod().getMethodName()
		 * +" Got Pass at Sucess ItestListner"); } else {
		 * test.log(Status.FAIL,result.getMethod().getMethodName()+" Got Failed"); }
		 */
		System.out.println("=================@@@#####onTestSuccess#####@@@====================");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		isSuccess = false;
		String exceptionMsg = result.getThrowable().toString();
		Reporter.log(MethodName + " Got Failed", true);

		test.log(Status.WARNING, MethodName + " is got failed  Kindly Check Flow.");
		exceptionMsg = "<textarea rows='5' cols='50' style='background-color:red;' readonly>" + exceptionMsg
				+ "</textarea>";
		takeScreenShort(exceptionMsg, exceptionMsg);
		System.out.println("<=======" + MethodName + "is got Failed  Kindly Check Flow =============>");
	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println("Execution Finished.....last........");

		if (isSuccess) {
			Reporter.log(MethodName + " is Pass", true);
			test.log(Status.PASS, MethodName + " Got Pass at Sucess ItestListner");
		} else {
			test.log(Status.FAIL, MethodName + " Got Failed");
		}
		reports.flush();
		getWebdriverAction().thread_sleep(2000);

		// To open HTML Report
		//readHtmlReport();
		// getWebDriver().quit();
	}

	public static void main(String arg[]) {
		new MylistenerClass().readHtmlReport();
	}

	public void readHtmlReport() {
		String userReportDirectory = System.getProperty("user.dir");
		System.out.println(" Dir= " + userReportDirectory);
		userReportDirectory = userReportDirectory + "\\Reports";

		System.out.println("Report Dir= " + userReportDirectory);
		String newFileName = getFileLibClass().getTheNewestFile(userReportDirectory, "html");
		System.out.println("Html name =" + newFileName);
		String htmlReport = userReportDirectory + "\\" + newFileName;

		WebDriverManager.chromedriver().avoidBrowserDetection().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		options.addArguments("headless");
		ChromeDriver driver = new ChromeDriver(options);
		setWebDriver(driver);
		getWebDriver().get(htmlReport);
		getWebDriver().manage().window().maximize();
		System.out.println("Open Url for HTML Report");

		List<WebElement> testCases = getWebDriver().findElements(By.xpath("//ul[@class='test-list-item']//p[@class='name']"));
	
		WebElement nameTC = null;
		WebElement statusTC = null;
		WebElement statTimeTC = null;
		WebElement endTimeTC = null;
		WebElement totalTimeTC = null;
		WebElement descriptionTC = null;

		List<WebElement> testCaseRowData = null;
		String testCaseName = "";
		WebElement testCaseMenuHeader = null;

		System.out.println("\n==============>Test Cases Details<=================");
		System.out.println("Total Test Cases = " + testCases.size());

		for (int i = 0; i < testCases.size(); i++) {
			nameTC = getWebDriver()
					.findElement(By.xpath("(//ul[@class='test-list-item']//p[@class='name'])[" + (i + 1) + "]"));
			testCaseName = nameTC.getAttribute("textContent");
			System.out.println((i+1)+") Test Case name = " + testCaseName);
		}
		
		System.out.println("\n....... Each Test Cases Details..........");
		for (int i = 0; i < testCases.size(); i++) {

			nameTC = getWebDriver()
					.findElement(By.xpath("(//ul[@class='test-list-item']//p[@class='name'])[" + (i + 1) + "]"));
			testCaseName = nameTC.getAttribute("textContent");
			System.out.println((i+1)+") Test Case name = " + testCaseName);

			statusTC = getWebDriver().findElement(By.xpath("(//ul[@class='test-list-item']//p[@class='text-sm']/span[last()])[" + (i + 1) + "]"));
			System.out.println("Test Case status = " + statusTC.getAttribute("textContent"));

			statTimeTC = getWebDriver().findElement(By.xpath("((//*[contains(text(),'" + testCaseName + "')])[2]/following-sibling::span)[1]"));
			System.out.println("Test Case statTimeTC = " + statTimeTC.getAttribute("textContent"));

			endTimeTC = getWebDriver().findElement(By.xpath("((//*[contains(text(),'" + testCaseName + "')])[2]/following-sibling::span)[2]"));
			System.out.println("Test Case endTimeTC = " + endTimeTC.getAttribute("textContent"));

			totalTimeTC = getWebDriver().findElement(By.xpath("((//*[contains(text(),'" + testCaseName + "')])[2]/following-sibling::span)[3]"));
			System.out.println("Test Case totalTimeTC = " + totalTimeTC.getAttribute("textContent"));

			descriptionTC = getWebDriver().findElement(By.xpath("(//*[contains(text(),'" + testCaseName + "')])[2]/../following-sibling::div"));
			System.out.println("Test Case descriptionTC = " + descriptionTC.getAttribute("textContent"));

			testCaseMenuHeader = getWebDriver().findElement(By.xpath("//p[contains(text(),'" + testCaseName + "')]/.."));
			customClick(testCaseMenuHeader);
			
			testCaseRowData = getWebDriver().findElements(By.xpath("(//*[contains(text(),'" + testCaseName
					+ "')])[1]/../../../../../following-sibling::div//table//tr"));

			System.out.println("\nIn "+testCaseName+ " ,Total Rows =" + testCaseRowData.size()+"\n");
			String data = "";

			WebElement rowData = null;

			for (int j = 0; j < testCaseRowData.size(); j++) {
				rowData = getWebDriver().findElement(By.xpath("((//*[contains(text(),'" + testCaseName
						+ "')])[1]/../../../../../following-sibling::div//table//tr)[" + (j + 1) + "]"));
				data = rowData.getAttribute("textContent");
				data = getOneSpaceText(data);
				System.out.println("-->" + data);
			}
			System.out.println(" \n\n\n\n================");
		}
		getWebDriver().quit();
	}

	public String getOneSpaceText(String str) {
		String arr[] = str.split(" ");
		String newStr = "";

		for (String s : arr) {
			newStr = newStr.trim() + " " + s.trim();
		}
		return newStr;
	}
}
