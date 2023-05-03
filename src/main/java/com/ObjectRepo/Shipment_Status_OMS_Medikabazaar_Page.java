package com.ObjectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Shipment_Status_OMS_Medikabazaar_Page {
	
	@FindBy(xpath="//select[@id='order_status']") private WebElement Shipping_Status;
	@FindBy(xpath="//input[@id='datetimepicker']") private WebElement Date_Time_Picker;
	
	@FindBy(xpath="//textarea[@id='shipping_comment']") private WebElement shipping_comment1;
	@FindBy(xpath="//select[@id='comment_dropdown']") private WebElement shipping_comment2;
	@FindBy(xpath="//button[@id='checkBtn']") private WebElement submit;
	@FindBy(xpath="//strong[contains(text(),'Updated successfully!')]") private WebElement shipping_suc_msg;
	@FindBy(xpath="//button[contains(text(),'Pending')]") private WebElement pending;
	@FindBy(xpath="//a[contains(text(),'Update Finance Info')]/../following-sibling::td[1]/button") private WebElement pending1;
	@FindBy(xpath="(//td[contains(text(),'--- ---')])[1]/../td[6]/button") private WebElement pending2;
	
	@FindBy(xpath="//td[contains(text(),'GRN')]") private WebElement GRN_Number;
	//@FindBy(xpath="//button[@id='submit']") private WebElement submit_return;
	@FindBy(xpath="//td[contains(text(),'Delivered')]") private WebElement Delivered_text;
	@FindBy(xpath="//td[contains(text(),'Return')]") private WebElement Return_text;
	@FindBy(xpath="//div[@class='day active']") private WebElement current_date;
	//@FindBy(xpath="//button[contains(text(),'Success')]") private WebElement success_green_flag1;
	@FindBy(xpath="//a[contains(text(),' Credit Note attachment')]/../following-sibling::td[1]/button") private WebElement success_green_flag1;
	
	//@FindBy(xpath="(//button[contains(text(),'Success')])[2]") private WebElement success_green_flag2;
	@FindBy(xpath="(//a[contains(text(),' Credit Note attachment')])[2]/../following-sibling::td[1]/button") private WebElement success_green_flag2;
	@FindBy(xpath="//a[contains(text(),'Update Finance Info')]") private WebElement update_fin_info;
	
	@FindBy(xpath="//option[contains(text(),'Partial Return')]") private WebElement Shipping_Status_Partial_Return;
	@FindBy(xpath="//input[@id='orderitem']") private WebElement Check_box_PR;
	@FindBy(xpath="//input[@class='form-control productqtytoreturn']") private WebElement Enter_Qty_PR;
	@FindBy(xpath="//input[@class='form-control shipmentitem']") private WebElement Enter_SKU_Qty_PR;
	@FindBy(xpath="//select[@id='comment_dropdown_id']") private WebElement Shipping_Return_Reason; //for partial return-1
	@FindBy(xpath="//input[@id='datepicker_return']") private WebElement Date_PR;
	@FindBy(xpath="//td[contains(text(),'Partially return')]") private WebElement Partial_Return_text;
	
	@FindBy(xpath="//select[@name='shipping_comment']") private WebElement Shipping_Return_Reason2; //for partial return-2
	
	@FindBy(xpath="(//td[contains(text(),'GRN')])[2]") private WebElement GRN_Number2;
	
	//@FindBy(xpath="//select[@id='order_status']") private WebElement Shipping_Status;
	
	

	
	
	
		public Shipment_Status_OMS_Medikabazaar_Page(WebDriver driver)
		{
			PageFactory.initElements(driver,this);
		}

		public WebElement getShipping_Status_Label() {
			return Shipping_Status;
		}
		public WebElement getDate_Time_Picker_Label() {
			return Date_Time_Picker;
		}
		public WebElement getshipping_comment1_Label() {
			return shipping_comment1;
		}
		public WebElement getshipping_comment2_Label() {
			return shipping_comment2;
		}
		public WebElement getsubmit() {
			return submit;
		}
		public WebElement getshipping_suc_msg() {
			return shipping_suc_msg;
		}
		public WebElement getpending_button() {
			return pending;
		}
		public WebElement getpending1_button() {
			return pending1;
		}
		public WebElement getpending2_button() {
			return pending2;
		}
		
		
		public WebElement getGRN_Number_text() {
			return GRN_Number;
		}
		//public WebElement getsubmit_return() {
			//return submit_return;
	//	}
		public WebElement getDelivered_text() {
			return Delivered_text;
		}
		public WebElement getReturn_text() {
			return Return_text;
		}
		public WebElement getcurrent_date_picker() {
			return current_date;
		}
		public WebElement getsuccess_green_flag1() {
			return success_green_flag1;
		}
		public WebElement getsuccess_green_flag2() {
			return success_green_flag2;
		}
		public WebElement getupdate_fin_info() {
			return update_fin_info;
		}
		
		public WebElement getShipping_Status_Label_PR() {
			return Shipping_Status_Partial_Return;
		}
		
		public WebElement getCheck_box_PR() {
			return Check_box_PR;
		}
		public WebElement getEnter_Qty_PR() {
			return Enter_Qty_PR;
		}
		public WebElement getEnter_SKU_Qty_PR() {
			return Enter_SKU_Qty_PR;
		}
		public WebElement getShipping_Return_Reason_dd() {
			return Shipping_Return_Reason;
		}
		public WebElement getDate_PR() {
			return Date_PR;
		}
		public WebElement getPartial_Return_text() {
			return Partial_Return_text;
		}
		public WebElement getShipping_Return_Reason_dd2() {   //for partial return-2
			return Shipping_Return_Reason2;
		}
		
		public WebElement getGRN_Number2_text() {
			return GRN_Number2;
		}
		

}
