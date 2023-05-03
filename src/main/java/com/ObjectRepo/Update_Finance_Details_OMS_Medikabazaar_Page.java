package com.ObjectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Update_Finance_Details_OMS_Medikabazaar_Page {
	
	@FindBy(id="invoiceNumber") private WebElement Invoice_Number;
	@FindBy(id="invoiceDate") private WebElement Invoice_Date;
	@FindBy(xpath="//input[@id='invoiceAttachment']") private WebElement Invoice_Attachmente;
	@FindBy(id="ewayBillNo") private WebElement eway_Bill_No;
	@FindBy(id="update_tally_details_form_btn") private WebElement Save;
	@FindBy(xpath="/html/body/div[2]/div/div[1]/a/button") private WebElement Back;


		public Update_Finance_Details_OMS_Medikabazaar_Page(WebDriver driver)
		{
			PageFactory.initElements(driver,this);
		}

		public WebElement getInvoice_Number_Label() {
			return Invoice_Number;
		}
		public WebElement getInvoice_Date_label() {
			return Invoice_Date;
		}
		public WebElement getInvoice_Attachmente_label() {
			return Invoice_Attachmente;
		}
		
		public WebElement geteway_Bill_No_label() {
			return eway_Bill_No;
		}
		public WebElement getSave_button() {
			return Save;
		}
		public WebElement getBack_button() {
			return Back;
		}

}
