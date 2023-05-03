package com.ObjectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Pick_And_Pack_OMS_Medikabazaar_Page {
	
@FindBy(xpath="//input[@name='sku[MedO743][inward_quantity][86829]']")private WebElement qty_for_outward;
@FindBy(xpath="//input[@name='sku[MB170321TruthBiomed026][inward_quantity][2769]']")private WebElement MIB_qty_for_outward;

//input[@name='sku[MedO741][inward_quantity][85647]']

@FindBy(xpath="//button[contains(text(),'Confirm')]")private WebElement confirm;

	
	
	

	public Pick_And_Pack_OMS_Medikabazaar_Page(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}
	public WebElement getqty_for_outward_Field() {
		return qty_for_outward;
	}
	public WebElement getMIB_qty_for_outward() {
		return MIB_qty_for_outward;
	}
	public WebElement getconfirm_button() {
		return confirm;
	}
	

}
