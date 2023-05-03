package com.ObjectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Indent_List_PROTA_Medikabazaar_Page {
	
	@FindBy(xpath="//input[@id=\"increment_id_indent\"]")private WebElement indent_id_text_field;
	@FindBy(xpath="//button[text()='Search']")private WebElement search_button;
	@FindBy(xpath="//div[@class=\"dataTables_scrollBody\"]//tr//td[2]//a") private WebElement indent_id;
	@FindBy(xpath="//div[@class=\"col-sm-12\"]//tr//td[20]")private WebElement raised_by;
	@FindBy(xpath="//div[@class=\"col-sm-12\"]//tr//td[16]")private WebElement Indent_status;
	@FindBy(xpath="//div[@class=\"col-sm-12\"]//tr//td[17]")private WebElement PO_Number;
	@FindBy(xpath="//div[@class=\"col-sm-12\"]//tr//td[14]")private WebElement indent_Qty;
	

	public Indent_List_PROTA_Medikabazaar_Page(WebDriver drive) 
	{
		PageFactory.initElements(drive,this);
	}
	public WebElement getIndent_id_text_field() {
		return indent_id_text_field;
	}
	public WebElement getSearch_button() {
		return search_button;
	}
	public WebElement getIndent_id() {
		return indent_id;
	}
	public WebElement getIndent_status() {
		return Indent_status;
	}
	public WebElement getPO_Number() {
		return PO_Number;
	}
	public WebElement getRaised_by() {
		return raised_by;
	}
	public WebElement getIndent_Qty() {
		return indent_Qty;
	}
}
