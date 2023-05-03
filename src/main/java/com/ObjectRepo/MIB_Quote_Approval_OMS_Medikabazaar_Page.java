package com.ObjectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MIB_Quote_Approval_OMS_Medikabazaar_Page {
	
	@FindBy(xpath="//button[contains(text(),'Create MIB Order')]") private WebElement Create_MIB_Order;
	@FindBy(xpath="//input[@id='c_email']") private WebElement Customer_Email;
	@FindBy(xpath="//button[@id='btnFilterSubmitSearch']") private WebElement Email_search;
	@FindBy(xpath="//table//tr[1]//td[2]//a") private WebElement MIB_quote_ID;
	@FindBy(xpath="//select[@id='verification_status']") private WebElement Budget_Approval_Selection;
	@FindBy(xpath="//input[@id='comment']") private WebElement Budget_Approval_comment;
	@FindBy(xpath="//button[contains(text(),'Submit')]") private WebElement Budget_Approval_submit;
	@FindBy(xpath="//span[contains(text(),'Approved')]") private WebElement Approved_Green;
	@FindBy(xpath="(//span[text()='Rejected'])[1]") private WebElement Rejected_text;
	

	public MIB_Quote_Approval_OMS_Medikabazaar_Page(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}
	public WebElement getRejected_text() {
		return Rejected_text;
	}
	public WebElement getCreate_MIB_Order_button() {
		return Create_MIB_Order;
	}
	public WebElement getCustomer_Email_Label() {
		return Customer_Email;
	}
	public WebElement getEmail_search_button() {
		return Email_search;
	}
	public WebElement getMIB_quote_ID() {
		return MIB_quote_ID;
	}
	public WebElement getBudget_Approval_dd() {
		return Budget_Approval_Selection;
	}
	public WebElement getBudget_Approval_comment() {
		return Budget_Approval_comment;
	}
	public WebElement getBudget_Approval_submit_button() {
		return Budget_Approval_submit;
	}
	public WebElement getApproved_Green_Label() {
		return Approved_Green;
	}

}
