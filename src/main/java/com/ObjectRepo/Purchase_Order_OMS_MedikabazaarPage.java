package com.ObjectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Purchase_Order_OMS_MedikabazaarPage {


	@FindBy (xpath="//a[contains(text(),'+ Create Purchase')]") private WebElement Create_Purchase_Order_button;
	@FindBy(xpath="//button[contains(text(),'Master Item')]") private WebElement PO_master_item_bucket;
	@FindBy(xpath="//select[@id=\"prepared_by\"]") private WebElement prepared_by_dd;
	@FindBy(xpath="//button[@id=\"btnFiterSubmitSearch\"]") private WebElement search_button;
	@FindBy(xpath="//table//tr[1]//td[1]") private WebElement first_PO_Id;
	@FindBy(xpath="//input[@id='increment_id']") private WebElement poIdTextField;
	

	public Purchase_Order_OMS_MedikabazaarPage(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}
	public WebElement getCreate_Purchase_Order_button() {
		return Create_Purchase_Order_button;
	}
	public WebElement getPO_master_item_bucket() {
		return PO_master_item_bucket;
	}
	public WebElement getPrepared_by_dd() {
		return prepared_by_dd;
	}
	public WebElement getSearch_button() {
		return search_button;
	}
	public WebElement getFirst_PO_Id() {
		return first_PO_Id;
	}
	
	public WebElement getPOidTextField() {
		return poIdTextField;
	}

}
