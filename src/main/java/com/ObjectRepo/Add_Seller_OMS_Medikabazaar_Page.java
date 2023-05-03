package com.ObjectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Add_Seller_OMS_Medikabazaar_Page {
	
@FindBy(xpath="//label[contains(text(),'Manufacturer')]") private WebElement Supplier_Type;
@FindBy(xpath="//input[@id='name']") private WebElement Supplier_Name;
@FindBy(xpath="//input[@id='email']") private WebElement Supplier_Email;
@FindBy(xpath="//input[@id='telephone']") private WebElement Supplier_Mobile;
@FindBy(xpath="//input[@id='password']") private WebElement Password;
@FindBy(xpath="//select[@id='country_id']") private WebElement Country;
@FindBy(xpath="//select[@id='bde_id']") private WebElement SRM;
@FindBy(xpath="//button[@id='submitbtn']") private WebElement Submit;




	public Add_Seller_OMS_Medikabazaar_Page(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}

	public WebElement getSupplier_Type_Checkbox() {
		return Supplier_Type;
	}
	public WebElement getSupplier_Name_Label() {
		return Supplier_Name;
	}
	public WebElement getSupplier_Email_Label() {
		return Supplier_Email;
	}
	public WebElement getSupplier_Mobile_Label() {
		return Supplier_Mobile;
	}
	public WebElement getPassword_Label() {
		return Password;
	}
	public WebElement getCountry_Label() {
		return Country;
	}
	public WebElement getSRM_Label() {
		return SRM;
	}
	public WebElement getSubmit_button() {
		return Submit;
	}
	
	

}
