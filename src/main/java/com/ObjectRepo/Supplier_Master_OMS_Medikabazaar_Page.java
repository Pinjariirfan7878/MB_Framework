package com.ObjectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Supplier_Master_OMS_Medikabazaar_Page {


	@FindBy(xpath="//button[contains(text(),'Add Supplier')]") private WebElement Add_Supplier;
	@FindBy(xpath="//input[@id='c_name']") private WebElement Supplier_Name;
	@FindBy(xpath="//button[@id='btnFiterSubmitSearch']") private WebElement search;
	@FindBy(xpath="//*[@id=\"allproduct\"]/tbody/tr/td[1]/a") private WebElement Supplier_ID;
	@FindBy(xpath="//input[@id=\"c_email\"]") private WebElement supplier_email_text_field;
	@FindBy(xpath="//button[@class=\"btn back-btn\"]")private WebElement back_button;
	@FindBy(xpath="//table//tr//td[3]//button") private WebElement supplier_status_text;
	
	public Supplier_Master_OMS_Medikabazaar_Page(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}
	public WebElement getBack_button() {
		return back_button;
	}
	public WebElement getAdd_Supplier_button() {
		return Add_Supplier;
	}
	public WebElement getSupplier_status_text() {
		return supplier_status_text;
	}
	public WebElement getSupplier_Name_Label() {
		return Supplier_Name;
	}
	public WebElement getSearch_button() {
		return search;
	}
	public WebElement getSupplier_ID() {
		return Supplier_ID;
	}
	public WebElement getSupplier_email_text_field() {
		return supplier_email_text_field;
	}
	

}
