package com.ObjectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PO_OMS_MedikabazaarPage {
	
	@FindBy(xpath=("//div[@class=\"orderSuccess-wrapper\"]")) private WebElement confirm_img;
	@FindBy(xpath="//div[@class=\"orderSuccess-wrapper\"]//p") private WebElement PO_ID;
	@FindBy(xpath="//button[text()='View PO Details']") private WebElement view_PO_details;
	
	 public PO_OMS_MedikabazaarPage(WebDriver driver)
	    {
	    	PageFactory.initElements(driver,this);
	    }

		public WebElement getConfirm_img() {
			return confirm_img;
		}

		public WebElement getPO_ID() {
			return PO_ID;
		}

		public WebElement getView_PO_details() {
			return view_PO_details;
		}

}
