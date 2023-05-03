package com.oms.GenericLib;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import com.google.common.io.Files;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseUtilityClass implements IAutoconstant {

	public static ExtentSparkReporter reporter;
	public static ExtentReports reports;
	public static ExtentTest test;

	public static SoftAssert softassert = new SoftAssert();
	public static Faker fake = new Faker(new Locale("en-IN"));
	public static FakeValuesService faker = new FakeValuesService(new Locale("en-GB"), new RandomService());

	public String concatenate = ".";

	private static WebDriver driver;
	private static WebdriverActionclass webaction;
	private static CommonActionClass commonAction;
	private static HashMap<String, String> globalData = new HashMap<String, String>();
	private static FileLibClass fileClass;
	private static HelperClass helperClass;

	public static boolean isSuccess = true;
	private String firstLoginUserName = "";
	private TakesScreenshot takeScreenShort;
	public static String FlowNAME = "";
	public static int globalTime = 5;
	public static String WebEnvironment = "";

	// ##==================##TestNg Methods##==================##

	/**
	 * Here We Initialize the Object of Classes , which can use in Testing
	 * Flow.<br/>
	 * [ WebdriverActionclass , CommonActionClass ]
	 */
	@BeforeSuite
	public void initializationOFObjects() throws Exception {
		webaction = getWebdriverAction();
		commonAction = getCommonAction();
		WebEnvironment = getFileLibClass().readPropertyData(PROP_PATH, "Environment");
	}

	/**
	 * Here We check About SoftAssert , <br/>
	 * if anywhere in TestCase Flow SoftAssert failed then add <b>Satuas.FAIL</b> in
	 * Report.<br/>
	 * and make <b>Report FAIL at the END </b>.
	 */
	@AfterSuite
	public void disconnectToDB() {
		System.out.println("After Suite");
		System.out.println("============>Softassert<========");
		try {
			softassert.assertAll();
		} catch (Error e) {
			isSuccess = false;
			test.log(Status.FAIL,getHelperClass().getFailSatatement(e.getMessage()));
		}

		// driver.close();
		try {
			//getWebDriver().quit();
		} catch (Exception e) {
			System.out.println("Last Step .....");
		}
	}

	/**
	 * Here We Open URL in Browser <br/>
	 * Maximize the Window
	 */
	@BeforeClass
	public void launchBrowser() {
		LaunchedBrowser();
		CommonActionClass.userLoginCount = 0;
		String url = "";
		try {
			String urlKey = "URL" + WebEnvironment;
			url = getFileLibClass().readPropertyData(PROP_PATH, urlKey);
			getWebdriverAction().url(url);
			getWebdriverAction().maximumWindow();
			getWebdriverAction().implicitlywait();
			firstLoginUserName = getCommonAction().userLoginPage("user", "User Login");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Here User LogOut <br/>
	 * and Close the Browser.
	 */
	@AfterClass
	public void close() {
		System.out.println("After Class ,Close the browser......");
//		getCommonAction().userLogOut();
//		getWebDriver().close();
	}
	// ##==================##Browser Util Method##==================##

	/**
	 * Here We Initialize the Browser.
	 */
	private void LaunchedBrowser() {
		System.out.println(".............Browser is Launched.........");
		String browser = "";

		try {
			browser = getFileLibClass().readPropertyData(PROP_PATH, "Browser");

			if (browser.equalsIgnoreCase("chrome")) {
				WebDriverManager.chromedriver().avoidBrowserDetection().setup();
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--remote-allow-origins=*");
				driver = new ChromeDriver(options);

			} else if (browser.equalsIgnoreCase("Firefox")) {
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
			} else {
				System.out.println("Please Enter Valid Browser Name");
			}

		} catch (Exception e) {
			System.out.println("Exception=" + e);
		}
	}

	public static int testCasesCount = 0;

	/**
	 * Here We Set TestCase Name and TestCase Description<br/>
	 * This Both data used in <b>TestReport</b> <br/>
	 * This method must call at <b>Beginning</b> in TestClass.
	 */
	public void setTestCaseAndDescriptionInReport(String flowName, String testCase, String description) {
		FlowNAME = flowName;
		MylistenerClass.MethodName = testCase;
		reports.removeTest("onTestStart");
		test = reports.createTest(testCase,
				"<span name=\"testDescription" + testCasesCount + "\">" + description + "</span>");
		testCasesCount++;
		Reporter.log(testCase, true);
		test.info(getStrongText("Environment =>" + WebEnvironment));
		try {
			test.info(getStrongText("URL => " + getFileLibClass().readPropertyData(PROP_PATH, "URL" + WebEnvironment)));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		test.info("User Login by <strong>" + firstLoginUserName + "</strong>");
	}



	public String getOnlyId(String msg, String startWith) {
		String arr[] = msg.split(" ");
		String r = "";

		for (String s : arr) {
			if (s.startsWith(startWith) && (s.length() > 5) && !s.equals("")) {
				r = s;
				break;
			} else if (s.matches("[0-9]+") && (s.length() > 5)) {
				// System.out.println("=>" + s);
				r = s;
			}
		}

		System.out.println("Order ID satrt with" + startWith + " =>" + r);
		return r;
	}

	public String getName(String str) {
		String arr[] = str.split("xpath");
		String xpath = "xpath" + arr[1];
		return xpath;
	}

	public String getName(WebElement ele) {
		String name = "";
		try {
			name = (ele.getAccessibleName().isBlank() || ele.getAccessibleName().isBlank()) ? getName(ele.toString())
					: ele.getAccessibleName();
		} catch (Exception e) {
			name = getName(ele.toString());
		}
		return name;
	}

	public String getXpath(String str) {
		String arr[] = str.split("xpath:");
		arr[1] = arr[1].substring(0, (arr[1].length() - 1)).trim();
		return arr[1];
	}
	// ##==================##Initalization of Object##==================##

	public TakesScreenshot getTakesScreenshot() {
		if (takeScreenShort == null) {
			takeScreenShort = (TakesScreenshot) getWebDriver();
		}
		return takeScreenShort;
	}

	public WebDriver getWebDriver() {
		return driver;
	}

	public void setWebDriver(WebDriver webDriver) {
		driver = webDriver;
	}

	public WebdriverActionclass getWebdriverAction() {
		if (webaction == null) {
			webaction = new WebdriverActionclass();
		}
		return webaction;
	}

	public CommonActionClass getCommonAction() {
		if (commonAction == null) {
			commonAction = new CommonActionClass();
		}
		return commonAction;
	}

	public FileLibClass getFileLibClass() {
		if (fileClass == null) {
			fileClass = new FileLibClass();
		}
		return fileClass;
	}

	public HelperClass getHelperClass() {
		if (helperClass == null) {
			helperClass = new HelperClass();
		}
		return helperClass;
	}

	// ##==================##Global Data##==================##
	public void addGlobalData(String key, String value) {
		globalData.put(key, value);
	}

	public String getGloabalData(String key) {
		String value = "";
		for (Map.Entry m : globalData.entrySet()) {
			if (m.getKey().equals(key)) {
				value = (String) m.getValue();
			}
		}
		return value;
	}

	public void showAllGlobalData() {
		for (Map.Entry m : globalData.entrySet()) {
			System.out.println("Key=" + m.getKey() + ", Value= " + m.getValue());
		}
	}

	// ##==================##TestCaseFlow Util Methods##==================##

	public void takeScreenShort(String msg, String reporterLogMsg) {

		String path = IAutoconstant.Screenshot_PATH + FlowNAME + "/";
		;
		File imgDirectoty = new File(path);

		if (!imgDirectoty.exists()) {
			imgDirectoty.mkdir();
		}

		TakesScreenshot ts = (TakesScreenshot) getWebDriver();
		File scr = ts.getScreenshotAs(OutputType.FILE);
		path = path + FlowNAME + "_" + getWebdriverAction().currentDateTime() + ".png";

		File dsn = new File(path);
		try {
			Files.copy(scr, dsn);
		} catch (IOException e) {
			e.printStackTrace();
		}

		path = concatenate + path;
		test.info(msg, MediaEntityBuilder.createScreenCaptureFromPath(path).build());
		Reporter.log(reporterLogMsg, true);
	}

	// ##==================##WebElement Related Methods##==================##

	public void waitForVisibleElement(WebElement ele, int timeOut) {
		try {
			WebDriverWait wait = new WebDriverWait(getWebDriver(), Duration.ofSeconds(timeOut));
			wait.until(ExpectedConditions.visibilityOf(ele));
		} catch (Exception e1) {
			System.out.println("Exception Occure in waitForVisibleElement = " + e1);
		}
	}

	public WebElement waitForVisibleElementXpath(WebElement ele, int timeOut) {
		WebDriverWait wait = new WebDriverWait(getWebDriver(), Duration.ofSeconds(timeOut));

		System.out.println("Xpath =" + getXpath(ele.toString()));
		WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(getXpath(ele.toString()))));

		if (e == null) {
			e = ele;
		}
		return e;
	}

	public void waitForClickableElement(WebElement element, int timeOut) {
		try {
			WebDriverWait wait = new WebDriverWait(getWebDriver(), Duration.ofSeconds(timeOut));
			wait.until(ExpectedConditions.elementToBeClickable(element));
		} catch (Exception e1) {
			System.out.println("Exception Occure in waitForClickableElement = " + e1);
		}
	}

	public void highLight(WebElement ele) {
		try {
			JavascriptExecutor jsExecutor = (JavascriptExecutor) getWebDriver();
			jsExecutor.executeScript("arguments[0].setAttribute('style', 'border:2px solid yellow')", ele);
		} catch (Exception e) {

		}
	}

	public void scrollIntoView(WebElement ele) {
		((JavascriptExecutor) getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", ele);
	}

	public void customClick(WebElement ele) {
		try {
			waitForVisibleElement(ele, globalTime);
			scrollIntoView(ele);
			highLight(ele);
			System.out.println("CustomClick On Element =>" + getName(ele));
			waitForClickableElement(ele, globalTime);
			ele.click();

		} catch (Exception e) {
			try {
				System.out.println("Javascript CustomClick On Element =>" + getName(ele));
				JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
				js.executeScript("arguments[0].click();", ele);
			} catch (Exception e1) {
				System.out.println("Exception Occure in CustomClick = " + e1);
				throw(e);
			}
		}
	}

	public void customSendText(WebElement ele, String text) {
		try {
			waitForVisibleElement(ele, 20);
			scrollIntoView(ele);
			highLight(ele);
			System.out.println("customSendText =>" + text + " ,in Element =>" + getName(ele));
			waitForClickableElement(ele, 15);
			customClearText(ele);
			ele.sendKeys(text.trim());
		} catch (Exception e) {
			try {
				System.out.println("Javascript CustomSendText =>" + text + " ,in Element =>" + getName(ele));
				JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
				js.executeScript("arguments[0].value='" + text.trim() + "';", ele);
			} catch (Exception e1) {
				System.out.println("Exception Occure in customSendText = " + e1);
				throw(e1);
			}
		}
	}

	public void customClearText(WebElement ele) {
		try {
			waitForVisibleElement(ele, 20);
			scrollIntoView(ele);
			highLight(ele);
			System.out.println("customClearText in Element =>" + getName(ele));
			waitForClickableElement(ele, 15);
			ele.clear();
		} catch (Exception e) {
			try {
				System.out.println("Javascript CustomClearText in Element =>" + getName(ele));
				String del = Keys.chord(Keys.CONTROL, "a") + Keys.DELETE;
				ele.sendKeys(del);
			} catch (Exception e1) {
				System.out.println("Exception Occure in customClearText = " + e1);
				throw(e1);
			}
		}
	}

	/**
	 * This is code for menu and subMenu. Wait for some time to visible of
	 * subMenu.<br/>
	 * 1. if subMenu isn't Visible than, wait for timeOut seconds[Thread] <br/>
	 * 2. clickOn menu. <br/>
	 * 3. clickOn subMenu <br/>
	 * 4. it repeat in Loop 10 time , totalTime = timeOut*10 .
	 */
	public void clickOnSubMenu(WebElement menu, WebElement subMenu, int timeOut) {
		int count = 0;

		try {
			if (!subMenu.isDisplayed()) {
				while (!subMenu.isDisplayed()) {
					count++;
					System.out.println("Count =" + count);
					waitUsingThread(timeOut);
					customClick(menu);
					if (count >= 10 || subMenu.isDisplayed()) {
						break;
					}
				}
			}
		} catch (Exception e) {
			customClick(menu);
		}
		customClick(subMenu);
	}

	public void waitUsingThread(int second) {
		try {
			Thread.sleep((second * 1000));
		} catch (Exception e) {
			System.out.println("Exception in thread=" + e);
		}
	}

	public String getStrongText(String text) {
		text = "<strong>" + text + "</strong>";
		return text;
	}
	
	public boolean checkWebElementPrsernt(WebElement ele) {
		boolean isPresent = false ;
		List<WebElement> elements = getWebDriver().findElements(By.xpath(getXpath(ele.toString())));

		if (elements.size() > 0) {
		    System.out.println("Element exists");
		    isPresent = true;
		}
		return isPresent;
	}
}