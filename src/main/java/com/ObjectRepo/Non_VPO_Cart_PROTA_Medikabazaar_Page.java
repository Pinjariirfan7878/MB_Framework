package com.ObjectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Non_VPO_Cart_PROTA_Medikabazaar_Page {

	
	@FindBy(xpath="//input[@name=\"sku\"]") private WebElement product_sku_text_field;
	@FindBy(xpath="//input[@name=\"qty\"]") private WebElement sku_qty_text_field;
	@FindBy(xpath="//span[@id=\"select2-warehouse_id_values-container\"]") private WebElement warehouse_dd;
	@FindBy(xpath="//input[@class=\"select2-search__field\"]") private WebElement search_bar_warehouse_dd;
	@FindBy(xpath="//span[@id=\"select2-srm_id-container\"]") private WebElement SRM_dd;
	@FindBy(xpath="//input[@class=\"select2-search__field\"]") private WebElement search_bar_SRM_dd;
	@FindBy(xpath="//button[text()='+ Add product']") private WebElement add_product_button;
	@FindBy(xpath="(//input[@class=\"selectAll\"])[1]") private WebElement selectAll_checkbox;
	@FindBy(xpath="//button[text()='Raise Indent']") private WebElement raise_indent_button;
	@FindBy(xpath="//button[text()='Clear Cart']")private WebElement clear_cart_button;
	
	
	public Non_VPO_Cart_PROTA_Medikabazaar_Page(WebDriver driver) 
	{
		PageFactory.initElements(driver,this);
	}
	
	public WebElement getProduct_sku_text_field() {
		return product_sku_text_field;
	}
	public WebElement getSku_qty_text_field() {
		return sku_qty_text_field;
	}
	public WebElement getWarehouse_dd() {
		return warehouse_dd;
	}
	public WebElement getSearch_bar_warehouse_dd() {
		return search_bar_warehouse_dd;
	}
	public WebElement getSRM_dd() {
		return SRM_dd;
	}
	public WebElement getSearch_bar_SRM_dd() {
		return search_bar_SRM_dd;
	}
	public WebElement getAdd_product_button() {
		return add_product_button;
	}
	public WebElement getSelectAll_checkbox() {
		return selectAll_checkbox;
	}

	public WebElement getRaise_indent_button() {
		return raise_indent_button;
	}

	public WebElement getClear_cart_button() {
		return clear_cart_button;
	}
	
	
	
	
}
