package com.ObjectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Create_MIB_Quote_and_Order_OMS_Medikabazaar_Page {
	

@FindBy(xpath="//input[@id='customer_email']") private WebElement Customer_Email;
@FindBy(xpath="//button[contains(text(),'Submit')]") private WebElement Email_submit;
@FindBy(xpath="//input[@name='updateitemlist[]']") private WebElement check_box;
@FindBy(xpath="//i[@class='fa fa-trash']") private WebElement trash;



@FindBy(xpath="//input[@name='sku']") private WebElement SKU_Name;
@FindBy(xpath="//input[@name='qty']") private WebElement SKU_Qty;
@FindBy(xpath="//button[@id='skusubmit']") private WebElement SKU_submit;
@FindBy(xpath="//input[@name='total_warranty_period_year']") private WebElement warranty_period;
@FindBy(xpath="//button[contains(text(),' Save')]") private WebElement Save;
@FindBy(xpath="//button[@class='btn btn-primary px-5 mr-3']") private WebElement Request_to_verify_budget;
@FindBy(xpath="//strong[contains(text(),'Quote Budget Sent Successfully!')]") private WebElement Budget_approval_status_message;
@FindBy(xpath="//button[contains(text(),'Place Order')]") private WebElement Place_Order;

@FindBy(xpath="//h4[contains(text(),'Order Items List')]") private WebElement Order_Items_List;
@FindBy(xpath="//button[contains(text(),'Update Cart')]") private WebElement Update_Cart;



	public Create_MIB_Quote_and_Order_OMS_Medikabazaar_Page(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}

	public WebElement getOrder_Items_List_Heading() {
        return Order_Items_List;
    }

    public WebElement getUpdate_Cart_button() {
        return Update_Cart;
    }
	public WebElement getCustomer_Email_Label() {
		return Customer_Email;
	}
	
	public WebElement getcheck_box() {
		return check_box;
	}
	
	public WebElement gettrash() {
		return trash;
	}
	
	public WebElement getSKU_Name_Label() {
		return SKU_Name;
	}
	
	public WebElement getSKU_Qty_Label() {
		return SKU_Qty;
	}
	
	public WebElement getSKU_submit_button() {
		return SKU_submit;
	}
	
	public WebElement getwarranty_period_Label() {
		return warranty_period;
	}
	
	public WebElement getSave_button() {
		return Save;
	}
	
	public WebElement getEmail_submit_button() {
		return Email_submit;
	}
	
	public WebElement getRequest_to_verify_budget_button() {
		return Request_to_verify_budget;
	}
	
	public WebElement getBudget_approval_status_message() {
		return Budget_approval_status_message;
	}
	
	public WebElement getPlace_Order_button() {
		return Place_Order;
	}
}
