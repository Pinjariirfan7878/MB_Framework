package com.oms.GenericLib;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;

public class WebdriverActionclass extends BaseUtilityClass {

	public void maximumWindow() {
		getWebDriver().manage().window().maximize();
	}

	public void refresh() {
		getWebDriver().navigate().refresh();
	}

	public void url(String url) {
		getWebDriver().get(url);
	}

	public void implicitlywait() {
		getWebDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}

	public void explicitlywait_elementtobeclickable(WebElement element) {
		WebDriverWait wait = new WebDriverWait(getWebDriver(), Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void explicitlywait_visibilityofallelements(WebElement element) {
		WebDriverWait wait = new WebDriverWait(getWebDriver(), Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfAllElements(element));
	}

	public void moveToElement(WebElement element) {
		Actions ac = new Actions(getWebDriver());
		ac.moveToElement(element).perform();
	}

	public void select_dd_by_value(WebElement element, String value) {
		customClick(element);
		Select sc = new Select(element);
		sc.selectByValue(value);
	}

	/**
	 * 1) Parameter => Visible Text
	 * 2) WebElemnt => Select Tag
	 * */
	public void select_dd_by_visibletext(String visible_text, WebElement element) {
		scrollIntoView(element);
		customClick(element);
		Select sc = new Select(element);
		sc.selectByVisibleText(visible_text);
	}

	public void select_dd_by_index(WebElement element, int a) {
		Select sc = new Select(element);
		sc.selectByIndex(a);
	}

	public void scrolldown_By_element(WebElement element) {
		Point loc = element.getLocation();
		JavascriptExecutor jse = (JavascriptExecutor) getWebDriver();
		jse.executeScript("window.scrollBy" + loc);
	}

	public void scrollBy_value(String x, String y) {
		JavascriptExecutor jse = (JavascriptExecutor) getWebDriver();
		jse.executeScript("window.scrollBy(" + x + "," + y + ")");

	}

	public void scrollBy_bottom() {
		JavascriptExecutor jse = (JavascriptExecutor) getWebDriver();
		jse.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void scrollBy_top() {
		JavascriptExecutor jse = (JavascriptExecutor) getWebDriver();
		jse.executeScript("window.scrollBy(0,-document.body.scrollHeight)");
	}

	public void scrollBy_horizontally(WebElement element) {
		JavascriptExecutor jse = (JavascriptExecutor) getWebDriver();
		jse.executeScript("arguments[0].scrollIntoView();", element);
	}

	public void alert_accept() {
//
//		WebDriverWait wait = new WebDriverWait(getWebDriver(), Duration.ofSeconds(15));
//		wait.until(ExpectedConditions.alertIsPresent());
//		System.out.println("Alertr msg = " + getWebDriver().switchTo().alert().getText());
		getWebDriver().switchTo().alert().accept();
	}

	public void alert_dismiss() {
		getWebDriver().switchTo().alert().dismiss();
	}

	public String currentDateTime() {
		LocalDateTime obj = LocalDateTime.now();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yy");
		String value = dtf.format(obj);
		String value1 = value.replace(" ", "_");
		String value2 = value1.replaceAll(":", ".");
		return value2;
	}

	public String screenshot_fail(String methodName) // irfan
	{

		TakesScreenshot ts = (TakesScreenshot) getWebDriver();
		File scr = ts.getScreenshotAs(OutputType.FILE);
		String path = IAutoconstant.Screenshot_Failed_PATH + "_" + methodName + "_" + currentDateTime() + ".png";
		File dsn = new File(path);
		try {
			Files.copy(scr, dsn);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}

	// For PO flow
	public String screenshot_PO(String name) {
		TakesScreenshot ts = (TakesScreenshot) getWebDriver();
		;
		File scr = ts.getScreenshotAs(OutputType.FILE);
		String path = IAutoconstant.Screenshot_PATH_PO + name + "_" + currentDateTime() + ".png";

		File dsn = new File(path);
		try {
			Files.copy(scr, dsn);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}

	// For IB flow
	public String screenshot_IB(String name) {
		TakesScreenshot ts = (TakesScreenshot) getWebDriver();
		;
		File scr = ts.getScreenshotAs(OutputType.FILE);
		String path = IAutoconstant.Screenshot_PATH_IB + name + "_" + currentDateTime() + ".png";

		File dsn = new File(path);
		try {
			Files.copy(scr, dsn);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}

	// For RTS flow
	public String screenshot_RTS(String name) {
		TakesScreenshot ts = (TakesScreenshot) getWebDriver();
		File scr = ts.getScreenshotAs(OutputType.FILE);
		String path = IAutoconstant.Screenshot_PATH_RTS + name + "_" + currentDateTime() + ".png";

		File dsn = new File(path);
		try {
			Files.copy(scr, dsn);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}

	// For PO flow
	public String screenshot_SO(String name) {
		TakesScreenshot ts = (TakesScreenshot) getWebDriver();
		File scr = ts.getScreenshotAs(OutputType.FILE);
		String path = IAutoconstant.Screenshot_PATH_SO + name + "_" + currentDateTime() + ".png";

		File dsn = new File(path);
		try {
			Files.copy(scr, dsn);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}

	// For MIB flow
	public String screenshot_MIB(String name) {
		TakesScreenshot ts = (TakesScreenshot) getWebDriver();
		File scr = ts.getScreenshotAs(OutputType.FILE);
		String path = IAutoconstant.Screenshot_PATH_MIB + name + "_" + currentDateTime() + ".png";

		File dsn = new File(path);
		try {
			Files.copy(scr, dsn);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}

	// For Supplier Master flow
	public String screenshot_Supplier_Master(String name) {
		TakesScreenshot ts = (TakesScreenshot) getWebDriver();
		File scr = ts.getScreenshotAs(OutputType.FILE);
		String path = IAutoconstant.Screenshot_PATH_Supplier_Master + name + "_" + currentDateTime() + ".png";

		File dsn = new File(path);
		try {
			Files.copy(scr, dsn);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}

	public void thread_sleep(int value)  {
		try {
			Thread.sleep(value);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public String screenshot_URL(String name) {
		
		TakesScreenshot ts = (TakesScreenshot) getWebDriver();
		File scr = ts.getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + IAutoconstant.Screenshot_URL_PATH + name + "_"
				+ currentDateTime() + ".png";
		File dsn = new File(path);
		try {
			Files.copy(scr, dsn);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}

	public void getWindowHandle() {
		getWebDriver().getWindowHandle();
	}

	public void getWindowHandles() {
		String parent = getWebDriver().getWindowHandle();
		Set<String> s = getWebDriver().getWindowHandles();
		Iterator<String> I1 = s.iterator();
		while (I1.hasNext()) {
			String child_window = I1.next();
			if (!parent.equals(child_window)) {
				getWebDriver().switchTo().window(child_window);
			}
		}
	}

}
