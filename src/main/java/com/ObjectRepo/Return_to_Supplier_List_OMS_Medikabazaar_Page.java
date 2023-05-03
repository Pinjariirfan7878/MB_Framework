package com.ObjectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Return_to_Supplier_List_OMS_Medikabazaar_Page {
	
	@FindBy(xpath="//button[contains(text(),'Create Return To Supplier')]") private WebElement Create_RTS;
	@FindBy(id="btnFilterSubmitSearch") private WebElement Search_RTS_ID;
	@FindBy(xpath="//table//tr[1]//td[1]/a") private WebElement select_RTS_ID;
	@FindBy(xpath="//strong[contains(text(),'Return to Supplier created successfully.')]") private WebElement rts_created_suc_msg;

		public Return_to_Supplier_List_OMS_Medikabazaar_Page(WebDriver driver)
		{
			PageFactory.initElements(driver,this);
		}

		public WebElement getCreate_RTS_button() {
			return Create_RTS;
		}
		
		public WebElement getSearch_RTS_ID_button() {
			return Search_RTS_ID;
		}
		
		public WebElement getselect_RTS_ID() {
			return select_RTS_ID;
		}
		public WebElement getRTS_created_suc_message() {
			return rts_created_suc_msg;
		}

	}
