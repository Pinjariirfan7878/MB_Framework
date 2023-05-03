package com.ObjectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Indent_Configuration_PROTA_Medikabazaar_Page {
	
	@FindBy(id="raise_indent_button") private WebElement raise_indent_button_for_vpo;
	@FindBy(id="non_vpo_raise_indent_button") private WebElement raise_indent_button_for_non_vpo;
	@FindBy(id="sku_level_tab") private WebElement sku_level_tab;
	
	
	public Indent_Configuration_PROTA_Medikabazaar_Page(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}
	public WebElement getRaise_indent_button_for_vpo() {
		return raise_indent_button_for_vpo;
	}
	public WebElement getRaise_indent_button_for_non_vpo() {
		return raise_indent_button_for_non_vpo;
	}
	public WebElement getSku_level_tab() {
		return sku_level_tab;
	}
	
	
}
