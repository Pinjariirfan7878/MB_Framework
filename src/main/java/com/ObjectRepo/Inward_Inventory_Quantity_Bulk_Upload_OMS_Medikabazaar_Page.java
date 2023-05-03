package com.ObjectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Inward_Inventory_Quantity_Bulk_Upload_OMS_Medikabazaar_Page {
	
	@FindBy(xpath="//input[@id=\"inward_inventory_quantity_csv\"]") private WebElement upload_csv_field;
    @FindBy(xpath="//input[@id=\"invoice_attachment\"]") private WebElement upload_invoice_field;
    @FindBy(xpath="//button[text()='Upload']") private WebElement upload_button;
	@FindBy(xpath="//input[@id=\"qa_check\"]") private WebElement qa_check_box;
	@FindBy(xpath="//input[@id=\"invoice_number\"]") private WebElement invoice_text_field;
	@FindBy(xpath="//input[@id=\"invoice_amount\"]") private WebElement invoice_amount_field;
	@FindBy(xpath="//button[contains(text(),'Confirm')]") private WebElement confirm_button;
	@FindBy(xpath="(//strong)[1]") private WebElement grn_generated_Message;

    public WebElement getGrn_generated_Message() {
		return grn_generated_Message;
	}
	public Inward_Inventory_Quantity_Bulk_Upload_OMS_Medikabazaar_Page(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}
	public WebElement getUpload_csv_field() {
		return upload_csv_field;
	}
	public WebElement getUpload_invoice_field() {
		return upload_invoice_field;
	}
	public WebElement getUpload_button() {
		return upload_button;
	}
	public WebElement getQa_check_box() {
		return qa_check_box;
	}
	public WebElement getInvoice_text_field() {
		return invoice_text_field;
	}
	public WebElement getInvoice_amount_field() {
		return invoice_amount_field;
	}
	public WebElement getConfirm_button() {
		return confirm_button;
	}
    
   
}
