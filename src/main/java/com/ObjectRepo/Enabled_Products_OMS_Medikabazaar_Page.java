package com.ObjectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Enabled_Products_OMS_Medikabazaar_Page {
	
	@FindBy(xpath="/html/body/div[2]/div/div[1]/div[2]/div/input") private WebElement Product_List_Search ;
	@FindBy(xpath="//input[@id='86383']") private WebElement Check_Box ;
    @FindBy(xpath="//input[@name='product_86383']") private WebElement Qty_to_add ;	
    @FindBy(id="mass-add-to-cart") private WebElement add_to_IB ;
    @FindBy(xpath="(//input[@name=\"full[]\"])[1]") private WebElement first_check_box;
	
	
	


	public WebElement getFirst_check_box() {
		return first_check_box;
	}
	public Enabled_Products_OMS_Medikabazaar_Page(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}
	public WebElement getProduct_List_search_Label() {
		return Product_List_Search;
	}
	public WebElement getCheck_Box() {
		return Check_Box;
	}
	public WebElement getQty_to_add_Label() {
		return Qty_to_add;
	}
	public WebElement add_to_IB_button() {
		return add_to_IB;
	}

}
