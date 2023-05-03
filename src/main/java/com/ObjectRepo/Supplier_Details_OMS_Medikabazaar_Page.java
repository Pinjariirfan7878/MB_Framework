package com.ObjectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Supplier_Details_OMS_Medikabazaar_Page {

	@FindBy(xpath="//input[@id='line1']") private WebElement Street_Line1;
	@FindBy(xpath="//input[@id='pincode']") private WebElement Pincode;	
	@FindBy(xpath="//select[@id='country']") private WebElement Country;	
	@FindBy(xpath="//select[@id='state_id']") private WebElement State;
	@FindBy(xpath="//input[@id='city']") private WebElement City;
	@FindBy(xpath="//input[@id='medika_seller_name']") private WebElement Medika_Supplier_Name;
	@FindBy(xpath="//input[@id='contact_number'][@class='form-control valid']") private WebElement Contact_Number;
	@FindBy(xpath="//select[@id='payment_terms']") private WebElement Payment_Terms;
	@FindBy(xpath="//button[contains(text(),'Submit')]") private WebElement Submit;
	@FindBy(xpath="//strong[contains(text(),'KYC updated successfully')]") private WebElement Status_msg_kyc;
	@FindBy(xpath="//input[@name='kyc[establishmentcompanyname][value]']") private WebElement Company_Name;
	@FindBy(xpath="//input[@name='gstdeclaration']") private WebElement GST_declaration;
	@FindBy(xpath="//input[@name='kyc[gstnno][value]']") private WebElement GST_num;
	@FindBy(xpath="//input[@name='kyc[pancardno][value]']") private WebElement PAN_num;
	@FindBy(xpath="//input[@name='kyc[contactperson][value]']") private WebElement Contact_Person;
	@FindBy(xpath="//input[@name='kyc[alternatecontactnumber][value]']") private WebElement Alt_Contact_Num;
	@FindBy(xpath="//input[@name='kyc[creditperiod][value]']") private WebElement Credit_Period;
	@FindBy(xpath="//button[@id='kyc_btn']") private WebElement KYC_button;
	@FindBy(xpath="//button[contains(text(),'KYC Details')]") private WebElement KYC_Details;
	@FindBy(xpath="//strong[contains(text(),'KYC updated successfully')]") private WebElement KYC_Details_status_msg;
	@FindBy(xpath="//button[contains(text(),'Verification Status and Comments')]") private WebElement Verification_Status;

	@FindBy(xpath="//*[@id=\"verificationStatus\"]/div[1]/div/div[2]/div/table/tbody/tr/td[4]/div/button") private WebElement View_Quicko_Data;
	@FindBy(xpath="//div[@id=\"save_order_view_div\"]//button") private WebElement verify_button;

	//div[@id="save_order_view_div"]//button
	@FindBy(xpath="//select[@id='customerstatus']") private WebElement Customer_Status;
	@FindBy(xpath="//textarea[@id='comment']") private WebElement Comment;
	@FindBy(xpath="//button[@id='first_step_verification_submit']") private WebElement Verification_Submit;
	@FindBy(xpath="//button[text()='Verify']") private WebElement Submit2;
	@FindBy(xpath="//td[contains(text(),'First Level Approved')]") private WebElement Approval_Validation1;
	@FindBy(xpath="//td[contains(text(),'Second Level Approved')]") private WebElement Approval_Validation2;
	@FindBy(xpath="//option[contains(text(),'GST number not Valid, please mention correct GST number')]") private WebElement Reject_Reason;
	@FindBy(xpath="//td[contains(text(),'GST number not Valid, please mention correct GST number')]") private WebElement Reject_Validation1;

	@FindBy(xpath="//table[@class=\"table datatable\"]//td[3]") private WebElement L2_approval_text;
	@FindBy(xpath="//table[@class=\"table datatable\"]//td[2]") private WebElement L1_approval_text;
	@FindBy(xpath="//input[@name=\"kyc[bankname][value]\"]") private WebElement bank_name;

	@FindBy(xpath="//input[@name=\"panimage\"]")private WebElement panimage_upload;
	@FindBy(xpath="//label[@for=\"switch1\"]") private WebElement status_button;




	public WebElement getStatus_button() {
		return status_button;
	}

	public Supplier_Details_OMS_Medikabazaar_Page(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}

	public WebElement getL2_approval_text() {
		return L2_approval_text;
	}

	public WebElement getL1_approval_text() {
		return L1_approval_text;
	}

	public WebElement getStreet_Line1_Label() {
		return Street_Line1;
	}
	public WebElement getPincode_Label() {
		return Pincode;
	}
	public WebElement getCountry_Label() {
		return Country;
	}
	public WebElement getState_Label() {
		return State;
	}
	public WebElement getCity_Label() {
		return City;
	}
	public WebElement getMedika_Supplier_Name_Label() {
		return Medika_Supplier_Name;
	}
	public WebElement getContact_Number_Label() {
		return Contact_Number;
	}
	public WebElement getPayment_Terms_Label() {
		return Payment_Terms;
	}
	public WebElement getSubmit_button() {
		return Submit;
	}
	public WebElement getStatus_msg_kyc() {
		return Status_msg_kyc;
	}
	public WebElement getCompany_Name() {
		return Company_Name;
	}
	public WebElement getGST_declaration_attachment() {
		return GST_declaration;
	}
	public WebElement getGST_num_Label() {
		return GST_num;
	}
	public WebElement getPAN_num_Label() {
		return PAN_num;
	}
	public WebElement getContact_Person_Label() {
		return Contact_Person;
	}
	public WebElement getAlt_Contact_Num_Label() {
		return Alt_Contact_Num;
	}
	public WebElement getCredit_Period_Label() {
		return Credit_Period;
	}
	public WebElement getKYC_button() {
		return KYC_button;
	}
	public WebElement getKYC_Details_tab() {
		return KYC_Details;
	}
	public WebElement getKYC_Details_status_msg() {
		return KYC_Details_status_msg;
	}
	public WebElement getVerification_Status_tab() {
		return Verification_Status;
	}
	public WebElement getView_Quicko_Data_button() {
		return View_Quicko_Data;
	}
	public WebElement getCustomer_Status_dd() {
		return Customer_Status;
	}
	public WebElement getComment() {
		return Comment;
	}
	public WebElement getVerification_Submit() {
		return Verification_Submit;
	}
	public WebElement getSubmit2() {
		return Submit2;
	}
	public WebElement getApproval_Validation1() {
		return Approval_Validation1;
	}
	public WebElement getApproval_Validation2() {
		return Approval_Validation2;
	}
	public WebElement getReject_Reason() {
		return Reject_Reason;
	}
	public WebElement getReject_Validation1() {
		return Reject_Validation1;
	}
	public WebElement getVerify_button() {
		return verify_button;
	}

	public WebElement getBank_name() {
		return bank_name;
	}
	public WebElement getPanimage_upload() {
		return panimage_upload;
	}
}
