package com.ObjectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Getting_Started_OMS_MedikabazaarPage {

	@FindBy (xpath="//a[text()=' Purchases ']") private WebElement purchase_tab ;
	@FindBy (xpath="//a[text()=' Purchase Orders ']") private WebElement purchase_order_option;
	@FindBy (xpath="//a[text()=' Customers ']") private WebElement customers_tab ;
	@FindBy (xpath="//a[text()=' Customer Master ']") private WebElement customers_masters_tab ;
	@FindBy(xpath="//a[@id=\"navbarDropdown\"]") private WebElement user_logo;
	@FindBy(xpath="(//button[@class=\"dropdown-item\"])[2]") private WebElement logout_button;
	@FindBy(xpath="//a[contains(text(),' Return to Supplier ')]") private WebElement Return_to_supplier_button;
	@FindBy(xpath="//a[text()=' Orders ']") private WebElement Order_tab;
	@FindBy(xpath="//*[@id=\"main_nav\"]/ul/li[2]/ul/li[3]/a") private WebElement Interbranch_tab;
	@FindBy(xpath="//a[text()=' Sales Orders ']") private WebElement Sales_Order_button;
	@FindBy(xpath="//a[contains(text(),' MIB Orders ')]") private WebElement MIB_Order;
	@FindBy(xpath="//a[contains(text(),' Operations ')]") private WebElement Operations_tab;
	@FindBy(xpath="//a[contains(text(),' Stock Management ')]") private WebElement stock_management;
	@FindBy(xpath="//a[contains(text(),' Suppliers ')]") private WebElement Suppliers;
	@FindBy(xpath="//a[contains(text(),' Supplier Master ')]") private WebElement Supplier_Master;
	
	@FindBy(xpath="//a[contains(text(),' Bulk GRN Creation ')]") private WebElement  bulk_GRN_creation_menu;
    @FindBy(xpath="//a[text()=' Indent Configuration ']") private WebElement indent_configuration_tab;
    @FindBy(xpath="//a[text()=' Indent Module ']") private WebElement indent_module_tab;
    
    
    
	public WebElement getIndent_module_tab() {
		return indent_module_tab;
	}
	public WebElement getIndent_configuration_tab() {
		return indent_configuration_tab;
	}
	public Getting_Started_OMS_MedikabazaarPage(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}
	public WebElement getBulk_GRN_creation_menu() {
		return bulk_GRN_creation_menu;
	}
	public WebElement getPurchase_tab() {
		return purchase_tab;
	}
    public WebElement getPurchase_order_option() {
		return purchase_order_option;
	}
	public WebElement getUser_logo() {
		return user_logo;
	}
	public WebElement getLogout_button() {
		return logout_button;
	}
	public WebElement getReturn_to_supplier_button() {
		return Return_to_supplier_button;
	}
	public WebElement getOrder_button() {
		return Order_tab;
	}
	public WebElement getinterbranch_tab() {
		return Interbranch_tab;
	}

	public WebElement getSalesorder_button() {
		return Sales_Order_button;
	}

	public WebElement getCustomers_button() {
		return customers_tab;
	}
	public WebElement getCustomerMaster_tab() {
		return customers_masters_tab;
	}
	public WebElement getMIB_Order_button() {
		return MIB_Order;
	}
	public WebElement getSuppliers_tab() {
		return Suppliers;
	}

	public WebElement getSupplier_Master_tab() {
		return Supplier_Master;
	}


	public WebElement get_Operations_button() {
		return Operations_tab;
	}
	public WebElement get_Stock_Management_button() {
		return stock_management;
	}
}
