package com.ObjectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Logistic_Detail_OMS_Medikabazaar_Page {

	@FindBy(id="courier_type") private WebElement Courier_Type ;
	@FindBy(id="gst_available") private WebElement GST_Available ;
	@FindBy(id="es_tat") private WebElement Est_TAT ;
	@FindBy(id="distance_in_km") private WebElement Distance;
	@FindBy(id="datepicker") private WebElement Datepicker;
	@FindBy(xpath="//div[@class='day active today']") private WebElement currentDate;
	@FindBy(name="order_status") private WebElement Order_Status;
	@FindBy(id="comment") private WebElement comment;
	@FindBy(id="transporter_gst_no") private WebElement GST_Invoice_No;
	@FindBy(id="transporter_name") private WebElement Transporter_Name;
	@FindBy(id="logistic_form_submit") private WebElement Logistic_submit;
	@FindBy(xpath="//a[contains(text(),'Update Finance Info')]") private WebElement update_finance_info;
	@FindBy(id="product_id_select_all") private WebElement Select_All;
	@FindBy(id="inward_stock_submit") private WebElement Inward_Stock;
	@FindBy(xpath="//strong[text()='Stock Inwarded Successfully']") private WebElement Inwarded_Successfully;
	@FindBy(xpath="//strong[contains(text(),'Status change successfully!')]") private WebElement Logistic_suc_msg;
	@FindBy(xpath="//td[text()='Pick and Pack']") private WebElement pick_and_pack_text;
    @FindBy(xpath="//td[text()='Stock Inwarded']") private WebElement Stock_inwarded_text;
	@FindBy(xpath="//table//td[contains(text(),'GRN')]") private WebElement GRN_Number_text;
	
	
	public WebElement getGRN_Number_text() {
		return GRN_Number_text;
	}

	public WebElement getStock_inwarded_text() {
		return Stock_inwarded_text;
	}

	public WebElement getPick_and_pack_text() {
		return pick_and_pack_text;
	}

	public Logistic_Detail_OMS_Medikabazaar_Page(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}

	public WebElement getCourier_Type_dd() {
		return Courier_Type;
	}
	public WebElement getGST_Available_dd() {
		return GST_Available;
	}
	public WebElement getEst_TAT_Label() {
		return Est_TAT;
	}
	public WebElement getDistance_Label() {
		return Distance;
	}
	public WebElement getDatepicker_Label() {
		return Datepicker;
	}
	public WebElement getOrder_Status_dd() {
		return Order_Status;
	}
	public WebElement getComment_Label() {
		return comment;
	}
	public WebElement getGST_Invoice_No_Label() {
		return GST_Invoice_No;
	}
	public WebElement getTransporter_Name_Label() {
		return Transporter_Name;
	}
	public WebElement getLogistic_submit_button() {
		return Logistic_submit;
	}
	public WebElement getupdate_finance_info_button() {
		return update_finance_info;
	}
	public WebElement getSelect_All() {
		return Select_All;
	}
	public WebElement getInward_Stock_button() {
		return Inward_Stock;
	}
	public WebElement getInwarded_Successfully_Message() {
		return Inwarded_Successfully;
	}
	public WebElement getLogistic_suc_msg() {
		return Logistic_suc_msg;
	}
	public WebElement currentDate()
	{
		return currentDate;
	}

}
