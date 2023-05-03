package com.ObjectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Edit_Indent_PROTA_Medikabazaar_Page {

	@FindBy(xpath="(//input[@class=\"selectAll\"])[1]") private WebElement selectAll_checkbox;
	@FindBy(xpath="//button[text()=\"Create PO\"]")private WebElement create_po_button;
	@FindBy(xpath="//button[@class=\"trash deletesingleitem\"]")private WebElement delete_button;
	@FindBy(xpath="//button[@class=\"save updatesingleitem\"]")private WebElement save_update_button;

	@FindBy(xpath="//div[@class=\"dataTables_scrollBody\"]//tr//td[12]") WebElement primary_stock;
	@FindBy(xpath="//div[@class=\"dataTables_scrollBody\"]//tr//td[14]") WebElement safety_stock;
	@FindBy(xpath="//div[@class=\"dataTables_scrollBody\"]//tr//td[15]") WebElement reorder_level;;
	@FindBy(xpath="//div[@class=\"dataTables_scrollBody\"]//tr//td[16]") WebElement EOQ;
	@FindBy(xpath="//div[@class=\"dataTables_scrollBody\"]//tr//td[17]//input") private WebElement indent_Qty;
	@FindBy(xpath="//button[@class=\"btn back-btn\"]") private WebElement back_button;

	public WebElement getBack_button() {
		return back_button;
	}
	public Edit_Indent_PROTA_Medikabazaar_Page(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}
	public WebElement getCreate_po_button() {
		return create_po_button;
	}
	public WebElement getIndent_Qty() {
		return indent_Qty;
	}
	public WebElement getDelete_button() {
		return delete_button;
	}
	public WebElement getSave_update_button() {
		return save_update_button;
	}
	public WebElement getSelectAll_checkbox() {
		return selectAll_checkbox;
	}
	public WebElement getPrimary_stock() {
		return primary_stock;
	}
	public WebElement getSafety_stock() {
		return safety_stock;
	}
	public WebElement getReorder_level() {
		return reorder_level;
	}
	public WebElement getEOQ() {
		return EOQ;
	}
}
