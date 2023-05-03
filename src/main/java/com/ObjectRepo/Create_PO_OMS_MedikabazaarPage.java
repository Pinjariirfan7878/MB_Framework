package com.ObjectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Create_PO_OMS_MedikabazaarPage {

	@FindBy(xpath = "//input[@type=\"email\"]")
	private WebElement supplier_email_textfield;
	@FindBy(xpath = "//button[text()='Submit']")
	private WebElement submit_button;
	@FindBy(xpath = "//select[@id=\"payment_terms\"]")
	private WebElement payment_terms_DD;
	@FindBy(xpath = "//input[@id=\"shipped_by\"]")
	private WebElement product_sku_textfield;
	@FindBy(xpath = "//input[@id=\"qty\"]")
	private WebElement quantity_textfield;
	@FindBy(xpath = "//button[text()='+ Add product']")
	private WebElement add_product_button;
	@FindBy(xpath = "//input[contains(@class,'numbers hsn_class')]")
	private WebElement HSN_Code_textfield;
	@FindBy(xpath = "//input[@name=\"updateitemlist[]\"]")
	private WebElement firstIteam_check_box;
	@FindBy(xpath = "//button[@id=\"btnplaneorder\"]")
	private WebElement create_purchase_order_button;
	@FindBy(xpath = "//button[text()='Update Cart']")
	private WebElement update_cart_button;
	@FindBy(xpath = "//input[@name=\"shipping_price\"]")
	private WebElement shipping_charge_textfield;
	@FindBy(xpath = "//button[text()='UPDATE']")
	private WebElement shipping_update_button;
	@FindBy(xpath = "//input[@id='price1']")
	private WebElement skuSupplierPrice;
	
	@FindBy(xpath = "//span[contains(text(),'Grand Total')]/following-sibling::span")
	private WebElement grandTotalText;
	@FindBy(xpath = "//span[contains(text(),'Subtotal')]/following-sibling::span")
	private WebElement subtotalText;
    @FindBy(xpath = "(//span[contains(text(),'Discount Amt')]/following-sibling::span)[1]")
	private WebElement discountAmtText;
    @FindBy(xpath = "//span[contains(text(),'GST')]/following-sibling::span")
	private WebElement gstText; 
    @FindBy(xpath="//input[@id=\"baseprice\"]")
    private WebElement 	Supplier_Base_Price;
    @FindBy(xpath="//input[@id=\"mrp\"]")
    private WebElement 	MRP_text_field;
    
    public WebElement getMRP_text_field() {
		return MRP_text_field;
	}
	public WebElement getGrandTotalText() {
		return grandTotalText;
	}
    public WebElement getSubtotalText() {
		return subtotalText;
	}
    public WebElement getSupplier_Base_Price() {
		return Supplier_Base_Price;
	}
	public WebElement getDiscountAmtText() {
		return discountAmtText;
	}
    public WebElement getGstText() {
		return gstText;
	}

	public WebElement getSkuSupplierPrice() {
		return skuSupplierPrice;
	}

	public WebElement getShipping_charge_textfield() {
		return shipping_charge_textfield;
	}

	public WebElement getShipping_update_button() {
		return shipping_update_button;
	}

	public WebElement getUpdate_cart_button() {
		return update_cart_button;
	}

	public Create_PO_OMS_MedikabazaarPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public WebElement getSupplier_email_textfield() {
		return supplier_email_textfield;
	}

	public WebElement getSubmit_button() {
		return submit_button;
	}

	public WebElement getPayment_terms_DD() {
		return payment_terms_DD;
	}

	public WebElement getProduct_sku_textfield() {
		return product_sku_textfield;
	}

	public WebElement getQuantity_textfield() {
		return quantity_textfield;
	}

	public WebElement getAdd_product_button() {
		return add_product_button;
	}

	public WebElement getHSN_Code_textfield() {
		return HSN_Code_textfield;
	}

	public WebElement getFirstIteam_check_box() {
		return firstIteam_check_box;
	}

	public WebElement getCreate_purchase_order_button() {
		return create_purchase_order_button;
	}

}
