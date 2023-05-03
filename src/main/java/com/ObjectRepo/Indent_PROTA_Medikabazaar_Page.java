package com.ObjectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Indent_PROTA_Medikabazaar_Page {

	
	@FindBy(xpath="//div[@class=\"orderSuccess-wrapper\"]//p[contains(text(),'Indent ID')]")
	private WebElement indent_id;
	@FindBy(xpath="//button[text()='View Indent Details']")private WebElement view_indent_button;
	
	public Indent_PROTA_Medikabazaar_Page(WebDriver driver) {
		PageFactory.initElements(driver,this);
	}
	public WebElement getView_indent_button() {
		return view_indent_button;
	}
	public WebElement getIndent_id() {
		return indent_id;
	}
}
