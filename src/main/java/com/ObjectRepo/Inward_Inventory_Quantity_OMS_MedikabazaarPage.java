package com.ObjectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Inward_Inventory_Quantity_OMS_MedikabazaarPage {

	@FindBy(xpath="//button[text()='Submit']") private WebElement submit_button;
	//@FindBy(xpath="(//th[contains (text(),'»')])[1])") private WebElement calendar_button;
	@FindBy(xpath="//input[@name=\"cf_3\"]") private WebElement exp_date_textField;

	public Inward_Inventory_Quantity_OMS_MedikabazaarPage(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}


	public WebElement getSubmit_button() {
		return submit_button;
	}
	public WebElement getExp_date_textField() {
		return exp_date_textField;
	}
//	public WebElement getCalendar_button() {
//		return calendar_button;
//	}


}
