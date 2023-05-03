package com.ObjectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Product_List_OMS_Medikabazaar_RTS_Page {
	
	@FindBy(xpath="//input[@name='mcifd_id[]' and @value='86731']") private WebElement Select_Product;
	@FindBy(xpath="//input[@name='product_data[86731][inward_qty]']") private WebElement pro_qty;
	@FindBy(id="add_to_cart") private WebElement add_to_RTS_cart;
	
	
	public Product_List_OMS_Medikabazaar_RTS_Page(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}

	public WebElement getSelect_Product_Checkbox() {
		return Select_Product;
	}
	
	public WebElement getpro_qty_Field() {
		return pro_qty;
	}
	public WebElement getadd_to_RTS_cart() {
		return add_to_RTS_cart;
	}

}
