package com.ObjectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Success_OMS_Medikabazaar_Page {
	
	@FindBy(xpath="//*[contains(text(),'is created successfully')]") private WebElement Success_message;
	@FindBy(xpath="//*[contains(text(), 'Thank You!')]") private WebElement Thank_you_message;
	@FindBy(xpath="//div[@class='toast toast-error']") private WebElement kyc_pending_error_message; 
	
	 public Success_OMS_Medikabazaar_Page(WebDriver driver)
	    {
	    	PageFactory.initElements(driver,this);
	    }

		public WebElement getSuccess_message() {
			return Success_message;
		}
		public WebElement getKyc_pending_error_message() {
			return kyc_pending_error_message;
		}

		public WebElement getThankyou_message() {
			return Thank_you_message;
		}

}
