package com.ObjectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.oms.GenericLib.BaseUtilityClass;
import com.oms.GenericLib.WebdriverActionclass;

public class Inter_Branch_Orders_OMS_Medikabazaar_Page {
	
	@FindBy(xpath="//button[contains(text(),'Create Inter Branch Order')]") private WebElement Create_IB_Order_Button ;
	@FindBy(xpath="//input[@name='c_email_recipient']") private WebElement Recipient_Email ;
	@FindBy(id="btnFiterSubmitSearch") private WebElement submit ;
	@FindBy(xpath="//*[@id=\"example1\"]/tbody/tr[1]/td[1]/a") private WebElement IB_ID ;
	@FindBy(xpath="//strong[text()='Order created successfully!']") private WebElement IB_Created_Success_Msg ;
	

	public Inter_Branch_Orders_OMS_Medikabazaar_Page(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}

	public WebElement getPurchase_tab() {
		return Create_IB_Order_Button;
	}
	public WebElement getRecipient_Email_Label() {
		return Recipient_Email;
	}
	public WebElement getSubmit_button() {
		return submit;
	}
	public WebElement getIB_ID_field() {
		return IB_ID;
	}
	public WebElement getIB_Created_Success_Msg() {
		return IB_Created_Success_Msg;
	}
	

}
