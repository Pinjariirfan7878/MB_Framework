package com.ObjectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Sales_Orders_Reports_OMS_Medikabazaar_Page {
	
	@FindBy(xpath="//button[contains(text(),'Select Operations Team Member')]") private WebElement Operations_Team_Member_dd;
	@FindBy(xpath="//input[@id='ms-opt-207']") private WebElement Operations_Team_Member_dd_text;
	@FindBy(xpath="//input[@id='additional_operations_email_text_box']") private WebElement email_to_notify;
	@FindBy(xpath="//button[@id='logistic_form_submit']") private WebElement submit_button;
	@FindBy(xpath="//input[@id='full']") private WebElement check_box_all;
	
	@FindBy(xpath="(//input[@class='form-control text-right'])[1]") private WebElement Qty_to_process;
	
	@FindBy(xpath="(//input[@type='text'])[1]") private WebElement Operations_Team_Member_search;		
	
		
	 public Sales_Orders_Reports_OMS_Medikabazaar_Page(WebDriver driver)
	    {
	    	PageFactory.initElements(driver,this);
	    }

		public WebElement getOperations_Team_Member_dd() {
			return Operations_Team_Member_dd;
		}
		public WebElement getOperations_Team_Member_dd_text() {
			return Operations_Team_Member_dd_text;
		}
		public WebElement getemail_to_notify() {
			return email_to_notify;
		}
		public WebElement getsubmit_button() {
			return submit_button;
		}
		public WebElement getcheck_box_all() {
			return check_box_all;
		}
		public WebElement getQty_to_process() {
			return Qty_to_process;
		}
		public WebElement getOperations_Team_Member_search() {
			return Operations_Team_Member_search;
		}
}
