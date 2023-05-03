package com.ObjectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Create_Quote_and_Order_OMS_Medikabazaar_Page {

	@FindBy (id="customer_email") private WebElement Enter_Customer_Email_ID_Label;
	@FindBy(id="change_email") private WebElement submit_button;
	@FindBy(id="shipped_by") private WebElement Enter_Product_SKU_Label;
	@FindBy(id="qty") private WebElement Quantity_Label;
	@FindBy(id="skusubmit") private WebElement Add_Product_button;
	@FindBy(xpath="(//input[@class='selectAll'])[1]") private WebElement Check_Box; 
	@FindBy(className="dataTables_scrollBody") private WebElement Item_Details_Scroll_Bar;
	@FindBy(xpath="//*[@id=\"quotetable\"]/tbody/tr/td[11]/input") private WebElement Disc_Label;
	@FindBy(className="form-select") private WebElement Tax_Label;
	@FindBy(xpath="//input[@name='shipping_price']")private WebElement Shipping_Price_Label;
	@FindBy(xpath="//button[text()='UPDATE']")private WebElement Shipping_Update;

	@FindBy(xpath="//*[contains(text(),'Place Order')]") private WebElement Place_Order;
	@FindBy(xpath="//button[contains(text(),'+ Import from CSV')]") private WebElement Import_From_CSV;
	@FindBy(xpath="//input[@id='image']") private WebElement csv_file_upload;
	@FindBy(xpath="(//button[contains(text(),'Import')])[2]") private WebElement Import_button;
	
	@FindBy(xpath="//strong[contains(text(),' File uploaded')]") private WebElement message_csv_count;
	@FindBy(xpath="//span[contains(text(),'Total Units:')]") private WebElement total_sku_added_count;
	@FindBy(xpath="(//span[@class='col-md-4 amount-col'])[1]") private WebElement sub_total;
	@FindBy(xpath="//strong[contains(text(),'Success Records')]") private WebElement duplicate_message;
	@FindBy(xpath="//button[contains(text(),'Clear Cart')]") private WebElement clear_cart;
	
	@FindBy(xpath="//button[contains(text(),'OK')]") private WebElement OK_to_clearcart;
	@FindBy(xpath="//a[contains(text(),'click here')]") private WebElement download;

	@FindBy(xpath="//button[contains(text(),'Update Cart')]") private WebElement updateCartButton;


	public Create_Quote_and_Order_OMS_Medikabazaar_Page(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}
	public WebElement getEnter_Customer_Email_ID() {
		return Enter_Customer_Email_ID_Label;
	}
	public WebElement getSubmit_button() {
		return submit_button;
	}
	public WebElement getEnter_SKU() {
		return Enter_Product_SKU_Label;
	}
	public WebElement getQuantity_Field() {
		return Quantity_Label;
	}
	public WebElement getAdd_Product_button() {
		return Add_Product_button;
	}
	public WebElement getCheck_Box() {
		return Check_Box;
	}
	public WebElement getDisc_Label() {
		return Disc_Label;
	}
	public WebElement getScroll_Bar() {
		return Item_Details_Scroll_Bar;
	}
	public WebElement getTax_Field() {
		return Tax_Label;
	}
	public WebElement getShipping_Price() {
		return Shipping_Price_Label;
	}
	public WebElement getShipping_Update_Button() {
		return Shipping_Update;
	}
	public WebElement getPlace_Order_Button() {
		return Place_Order;
	}
	public WebElement getImport_From_CSV_Button() {
		return Import_From_CSV;
	}
	public WebElement getcsv_file_upload() {
		return csv_file_upload;
	}
	public WebElement getImport_button() {
		return Import_button;
	}
	public WebElement getmessage_csv_count() {
		return message_csv_count;
	}
	public WebElement getsub_total() {
		return sub_total;
	}
	public WebElement gettotal_sku_added_count() {
		return total_sku_added_count;
	}
	public WebElement getduplicate_message() {
		return duplicate_message;
	}
	public WebElement getclear_cart_button() {
		return clear_cart;
	}
	public WebElement getOK_to_clearcart_button() {
		return OK_to_clearcart;
	}
	public WebElement getdownload_link() {
		return download;
	}
	public WebElement getUpdateCartButton() {
		return updateCartButton;
    }
}
