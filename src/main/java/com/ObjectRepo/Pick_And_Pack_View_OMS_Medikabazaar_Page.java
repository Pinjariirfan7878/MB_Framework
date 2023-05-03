package com.ObjectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Pick_And_Pack_View_OMS_Medikabazaar_Page {
	
@FindBy(xpath="//select[@id='courier_type']")private WebElement courier_type;
@FindBy(xpath="//input[@class='select2-search__field']")private WebElement courier_type_search;
@FindBy(xpath="//select[@id='gst']")private WebElement gst_available;
@FindBy(xpath="//input[@class='select2-search__field']")private WebElement gst_search;
@FindBy(xpath="//input[@id=\"insurance_reference_number\"]")private WebElement Insurance_Ref_Num;
@FindBy(xpath="//input[@id=\"es_tat\"]")private WebElement Est_TAT;
@FindBy(xpath="//input[@id=\"distance\"]")private WebElement distance;
@FindBy(xpath="//input[@id='transporter_name']")private WebElement transporter_name;
@FindBy(xpath="//input[@id='gstno']")private WebElement gst_number;
@FindBy(xpath="//input[@id=\"comment\"]")private WebElement comment;
@FindBy(xpath="//a[contains(text(),'Save')]")private WebElement save;
@FindBy(xpath="//a[contains(text(),'Generate Invoice')]")private WebElement Generate_Invoice;
@FindBy(xpath="//a[contains(text(),'Update Finance Info')]")private WebElement Update_Finance_Info;
@FindBy(xpath="//a[contains(text(),'Final Dispatch')]")private WebElement Final_Dispatch;
@FindBy(xpath="//button[contains(text(),'Partial Rollback')]")private WebElement Rollback;
@FindBy(xpath="//input[@name='rollback_pickandpack_item_id[]']")private WebElement Check_box_RB;
@FindBy(xpath="//input[@name='rollback_pickandpack_qty[]']")private WebElement Enter_Total_Qty_to_RB;
@FindBy(xpath="//input[@name='rollback_pickandpack_qty_item[]']")private WebElement Enter_SKU_Qty_to_RB;
@FindBy(xpath="//textarea[@id='partial_comment']")private WebElement Comment_RB;
@FindBy(xpath="//button[@class='btn btn-primary partial_rollback_button']")private WebElement Rollback_submit;
@FindBy(xpath="//a[contains(text(),'Update Rollback Finance Info')]")private WebElement Update_RB_Fin_Info;
@FindBy(xpath="//button[@id='complete_rollback']")private WebElement Complete_Rollback1;
@FindBy(xpath="//td[contains(text(),'Complete Rollback')]")private WebElement Complete_Rollback_text;






	public Pick_And_Pack_View_OMS_Medikabazaar_Page(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}
	public WebElement getcourier_type_dd() {
		return courier_type;
	}
	public WebElement getcourier_type_search() {
		return courier_type_search;
	}
	public WebElement getgst_available_dd() {
		return gst_available;
	}
	public WebElement getgst_search() {
		return gst_search;
	}
	public WebElement getInsurance_Ref_Num() {
		return Insurance_Ref_Num;
	}
	public WebElement getEst_TAT() {
		return Est_TAT;
	}
	public WebElement getdistance() {
		return distance;
	}
	public WebElement gettransporter_name() {
		return transporter_name;
	}
	public WebElement getgst_number() {
		return gst_number;
	}
	public WebElement getcomment() {
		return comment;
	}
	public WebElement getsave() {
		return save;
	}
	public WebElement getGenerate_Invoice() {
		return Generate_Invoice;
	}
	public WebElement getUpdate_Finance_Info() {
		return Update_Finance_Info;
	}
	public WebElement getFinal_Dispatch() {
		return Final_Dispatch;
	}
	public WebElement getRollback_button() {
		return Rollback;
	}
	public WebElement getCheck_box_RB() {
		return Check_box_RB;
	}
	public WebElement getEnter_Total_Qty_to_RB_field() {
		return Enter_Total_Qty_to_RB;
	}
	public WebElement getEnter_SKU_Qty_to_RB_field() {
		return Enter_SKU_Qty_to_RB;
	}
	public WebElement getComment_RB_field() {
		return Comment_RB;
	}
	public WebElement getRollback_submit_button() {
		return Rollback_submit;
	}
	public WebElement getUpdate_RB_Fin_Info_button() {
		return Update_RB_Fin_Info;
	}
	public WebElement getComplete_Rollback_button1() {
		return Complete_Rollback1;
	}
	public WebElement getComplete_Rollback_text() {
		return Complete_Rollback_text;
	}

}
