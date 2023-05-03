package com.ObjectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Customer_OMS_Medikabazaar_Page {

	
	@FindBy (xpath="//button[text()='Add Customer']") private WebElement btn_add_customer ;
	//@FindBy (xpath="//select[@id='group_id']") private WebElement customer_type ;
	@FindBy (xpath="//span[@id=\"select2-group_id-container\"]") private WebElement customer_type ;
	
	@FindBy (xpath="//input[@id='name']") private WebElement customer_name ;
	@FindBy (xpath="//input[@id='telephone']") private WebElement customer_phone ;
	@FindBy (xpath="//input[@id=\"email\"]") private WebElement customer_email ;
	@FindBy (xpath="//input[@id='password']") private WebElement customer_password ;
//	@FindBy (xpath="//select[@id='bde_id']") private WebElement customer_bdeid ;
	@FindBy (xpath="//span[@id=\"select2-bde_id-container\"]") private WebElement customer_bdeid ;
	
	@FindBy (css="input#street") private WebElement customer_address ;
	@FindBy (css="input#postcode") private WebElement customer_pincode ;
	@FindBy (xpath="//input[@id='city']") private WebElement customer_city ;
//@FindBy (xpath="//select[@id='country_id']") private WebElement customer_country ;
	@FindBy (xpath="//span[@id=\"select2-country_id-container\"]") private WebElement customer_country ;
//@FindBy (xpath="//select[@id='region_id']") private WebElement customer_state ;
	@FindBy (xpath="//span[@id=\"select2-region_id-container\"]") private WebElement customer_state ;
	
	@FindBy (xpath="//button[@id='submitbtn']") private WebElement btn_submit ;
	@FindBy (xpath="//button[text()='Make Customer to Supplier']") private WebElement btn_ConvertToSupplier; 
	@FindBy(xpath="//table[@class=\"table-list\"]//tr[2]//td[2]") private WebElement email_id;
	
	@FindBy(xpath="//label[@for=\"switch1\"]")private WebElement status_button;
	@FindBy(xpath="//button[@class=\"btn back-btn\"]")private WebElement back_button;
	@FindBy(xpath="//input[@id=\"c_name\"]") private WebElement customer_name_text_field;
	@FindBy(xpath="//input[@id=\"c_email\"]")private WebElement customet_email_text_field;
	@FindBy(xpath="//button[text()='Search']") private WebElement search_button;
    @FindBy(xpath="//table//tr//td[3]//button") private WebElement customer_status_text;
    
    @FindBy(xpath="//button[text()='Verification Status and Comments']")private WebElement Verification_Status_Comments_tab;
	@FindBy(xpath="//select[@id='customerstatus']") private WebElement status_dd;
	@FindBy(xpath="//textarea[@id='comment']") private WebElement Comment;
	@FindBy(xpath="//div[@id=\"save_order_view_div\"]//button[text()='Submit']") private WebElement submit_btn;
	@FindBy(xpath="(//*[@id=\"save_order_view_div\"]/button)[2]")private WebElement verify_btn;
	@FindBy(xpath="(//table[@class=\"table datatable\"]//tr//td)[1]")private WebElement L1_approved_text;
	@FindBy(xpath="(//table[@class=\"table datatable\"]//tr//td)[2]")private WebElement L2_approved_text;
	@FindBy(xpath="//*[@id=\"allproduct\"]/tbody/tr/td[1]/a")private WebElement customer_id;
	@FindBy(xpath="//button[text()='Details']")private WebElement details_tab;
	@FindBy(xpath="//table//tr[4]//td[2]//strong")private WebElement type_of_Customer;	
	@FindBy(xpath="//button[@onclick=\"show_customer_change()\"]")private WebElement change_customer_type_button;
	@FindBy(xpath="//select[@id=\"group_id\"]")private WebElement change_Customer_type_dd;
	
	public WebElement getDetails_tab() {
		return details_tab;
	}
	public WebElement getChange_Customer_type_dd() {
		return change_Customer_type_dd;
	}
	public WebElement getChange_customer_type_button() {
		return change_customer_type_button;
	}
	public WebElement getType_of_Customer() {
		return type_of_Customer;
	}
	public WebElement getCustomer_id() {
		return customer_id;
	}
	public WebElement getL1_approved_text() {
		return L1_approved_text;
	}
	public WebElement getL2_approved_text() {
		return L2_approved_text;
	}
	public WebElement getVerify_btn() {
		return verify_btn;
	}
	public WebElement getComment() {
		return Comment;
	}

	public WebElement getStatus_dd() {
		return status_dd;
	}

	public WebElement getSubmit_btn() {
		return submit_btn;
	}

	public WebElement getEmail_id() {
		return email_id;
	}

	public WebElement getVerification_Status_Comments_tab() {
		return Verification_Status_Comments_tab;
	}

	public WebElement getSearch_button() {
		return search_button;
	}

	public WebElement getCustomer_status_text() {
		return customer_status_text;
	}

	public WebElement getCustomer_name_text_field() {
		return customer_name_text_field;
	}

	public WebElement getCustomet_email_text_field() {
		return customet_email_text_field;
	}

	public Customer_OMS_Medikabazaar_Page (WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}
	
	public WebElement getAddNewCustomerButton() {
		return btn_add_customer;
	}
	public WebElement getCustomerTypeField() {
		return customer_type;
	}
	
	public WebElement getCustomerNameField() {
		return customer_name;
	}
	public WebElement getCustomerPhoneField() {
		return customer_phone;
	}
	public WebElement getCustomerEmailField() {
		return customer_email;
	}
	public WebElement getCustomerPasswordField() {
		return customer_password;
	}
	public WebElement getCustomerbdeidField() {
		return customer_bdeid;
	}
	public WebElement getCustomerAddressField() {
		return customer_address;
	}
	public WebElement getCustomerZipCodeField() {
		return customer_pincode;
	}
	
	public WebElement getCustomerCountryField() {
		return customer_country;
	}
	public WebElement getCustomerStateField() {
		return customer_state;
	}
	public WebElement getCustomerCityField() {
		return customer_city;
	}
	public WebElement getCustomerSubmitBtn() {
		return btn_submit;
	}
	public WebElement getConvertToSupplierBtn() {
		return btn_ConvertToSupplier;
	}
	public WebElement getStatus_button() {
		return status_button;
	}
	public WebElement getBack_button() {
		return back_button;
	}
}
