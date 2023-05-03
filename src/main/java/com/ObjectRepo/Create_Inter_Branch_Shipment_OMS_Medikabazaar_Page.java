package com.ObjectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Create_Inter_Branch_Shipment_OMS_Medikabazaar_Page {
	
	@FindBy(id="from_location") private WebElement Source_Warehouse ;
	@FindBy(id="to_location") private WebElement Destination_Warehouse ;
	@FindBy(id="recipient_email") private WebElement Recipient_Email ;
	@FindBy(id="logistic_form_submit") private WebElement IB_submit ;
	@FindBy(xpath="//button[contains(text(),'Select from Product List')]") private WebElement Product_List ;
	@FindBy(xpath="//*[contains(text(),'Save Product')]") private WebElement IB_Save ;
	@FindBy(xpath="//button[text()='Create']") private WebElement IB_Create ;
	@FindBy(xpath="/html/body/div[2]/div/div[1]/div/a/button") private WebElement back ;
	@FindBy(xpath="//strong[text()='Information Updated Successfully!']") private WebElement Success_Message ;
	
		public Create_Inter_Branch_Shipment_OMS_Medikabazaar_Page(WebDriver driver)
		{
			PageFactory.initElements(driver,this);
		}

		public WebElement getSource_Warehouse_dd() {
			return Source_Warehouse;
		}
		public WebElement getDestination_Warehouse_dd() {
			return Destination_Warehouse;
		}
		public WebElement getRecipient_Email_Label() {
			return Recipient_Email;
		}
		public WebElement getIB_submit_button() {
			return IB_submit;
		}
		public WebElement getProduct_List_button() {
			return Product_List;
		}
		public WebElement getIB_Save_button() {
			return IB_Save;
		}
		public WebElement getIB_Create_button() {
			return IB_Create;
		}
		public WebElement getback_button() {
			return back;
		}
		public WebElement getSuccess_Message_Display() {
			return Success_Message;
		}
		

}
