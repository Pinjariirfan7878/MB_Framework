package com.genericTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.oms.GenericLib.FileLibClass;
import com.oms.GenericLib.WebdriverActionclass;

import io.github.bonigarcia.wdm.WebDriverManager;

public class URL_Smoke  {

	@Test
	public void url_temper() throws Exception {
		
		
		WebDriverManager.chromedriver().setup();		
		WebDriver driver=new ChromeDriver();
		WebdriverActionclass webaction=new WebdriverActionclass(driver);
		driver.manage().window().maximize();
		
		driver.get("https://omsdemo.medikabazaar.com/login");
		driver.findElement(By.name("email")).sendKeys("milin.bhatt@medikabazaar.com");
		driver.findElement(By.name("password")).sendKeys("Hello@1212");
		driver.findElement(By.xpath("//button")).click();
		
		for(int i=44;i<=45;i++)
		{
			FileLibClass filib=new FileLibClass();
			String value=filib.readExcelData("Url", i, 0);
			System.out.println(value);
			driver.get(value);
			webaction.screenshot_URL(""+i+"");
			//webaction.screenshot_alone(value);
			String current_tittle = driver.getTitle();
			Reporter.log((i+1)+". "+value+" ===> "+current_tittle,true);		
		}
		driver.close();
	}
}