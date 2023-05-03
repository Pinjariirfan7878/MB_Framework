package com.OMS.Customer;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ObjectRepo.Customer_OMS_Medikabazaar_Page;
import com.ObjectRepo.Getting_Started_OMS_MedikabazaarPage;
import com.oms.GenericLib.BaseUtilityClass;
import com.oms.GenericLib.FileLibClass;
import com.oms.GenericLib.RetryanalyserClass;
import com.oms.GenericLib.WebdriverActionclass;

	
@Listeners(com.oms.GenericLib.MylistenerClass.class)
public class TC_001_Add_New_Domestic_Customer extends BaseUtilityClass {
	String testCaseDescription = "Testing flow of Add Domestic Customer";
	@Test//(retryAnalyzer = RetryanalyserClass.class)
	public void addNewCustomer()
	{
		Getting_Started_OMS_MedikabazaarPage getting_started = new Getting_Started_OMS_MedikabazaarPage(getWebDriver());
		Customer_OMS_Medikabazaar_Page custpage = new Customer_OMS_Medikabazaar_Page(getWebDriver());
		FileLibClass filclass = new FileLibClass();
		
		String currentClassName = this.getClass().getSimpleName();
		setTestCaseAndDescriptionInReport("Customer",currentClassName, testCaseDescription);
		String mobile = faker.regexify("[8-9]{10}");
		String pincode = faker.regexify("[4-5]{6}");
		System.out.println("Faker phone is" + mobile);
		
		getWebdriverAction().explicitlywait_elementtobeclickable(getting_started.getCustomers_button());
		getting_started.getCustomers_button().click();
		test.info("Customers Button is clicked on main page");
		
		getWebdriverAction().explicitlywait_elementtobeclickable(getting_started.getCustomerMaster_tab());
		getting_started.getCustomerMaster_tab().click();		
		test.info("Customer Masters Button is clicked and Customer listing page is opened");
		
		getWebdriverAction().explicitlywait_elementtobeclickable(custpage.getAddNewCustomerButton());
		custpage.getAddNewCustomerButton().click();		
		test.info("Add Customer Page is opened");
		
		getWebdriverAction().explicitlywait_elementtobeclickable(custpage.getCustomerTypeField());
		
		customClick(custpage.getCustomerTypeField());
		getWebDriver().findElement(By.xpath("//input[@type=\"search\"]")).sendKeys("Doctor",Keys.ENTER);
		getWebdriverAction().thread_sleep(500);

		
		//getWebdriverAction().select_dd_by_value(custpage.getCustomerTypeField(),"10");
		test.info("Customer Type is chosen as Doctor");
		
		getWebdriverAction().explicitlywait_elementtobeclickable(custpage.getCustomerNameField());
		String custName = fake.name().firstName();
		getWebdriverAction().moveToElement(custpage.getCustomerNameField());
		custpage.getCustomerNameField().sendKeys(custName);
		test.info("Customer Name is "+ custName);
		
		
		getWebdriverAction().explicitlywait_elementtobeclickable(custpage.getCustomerPhoneField());
		getWebdriverAction().moveToElement(custpage.getCustomerPhoneField());
		custpage.getCustomerPhoneField().sendKeys(mobile);
		test.info("Customer Phone Number is "+ mobile);
		
		
		getWebdriverAction().explicitlywait_elementtobeclickable(custpage.getCustomerEmailField());
		String custEmail = fake.internet().safeEmailAddress();
		custpage.getCustomerEmailField().sendKeys(custEmail);
		test.info("Customer Email is "+ custEmail);
		
		
		getWebdriverAction().explicitlywait_elementtobeclickable(custpage.getCustomerPasswordField());
		String custPassword = fake.internet().password();
		custpage.getCustomerPasswordField().sendKeys(custPassword);
		test.info("Customer Password is "+ custPassword);
		
		getWebdriverAction().explicitlywait_elementtobeclickable(custpage.getCustomerbdeidField());
		customClick(custpage.getCustomerbdeidField());
		getWebDriver().findElement(By.xpath("//input[@type=\"search\"]")).sendKeys("irfan",Keys.ENTER);
		//getWebdriverAction().select_dd_by_value(custpage.getCustomerbdeidField(),"380");
		test.info("BDE is chosen while creating Customer");
		
			
		getWebdriverAction().implicitlywait();
		
		getWebdriverAction().scrollBy_bottom();
		
		getWebdriverAction().explicitlywait_elementtobeclickable(custpage.getCustomerAddressField());
		String custAddress = fake.address().streetAddress();
		custpage.getCustomerAddressField().sendKeys(custAddress);
		test.info("Customer Address is "+ custAddress);
		
		
		getWebdriverAction().explicitlywait_elementtobeclickable(custpage.getCustomerZipCodeField());
		custpage.getCustomerZipCodeField().sendKeys(pincode);
		test.info("Customer Zip code is "+ pincode);
		
		
		getWebdriverAction().explicitlywait_elementtobeclickable(custpage.getCustomerCountryField());
		custpage.getCustomerCountryField().click();
		getWebDriver().findElement(By.xpath("//input[@type=\"search\"]")).sendKeys("India",Keys.ENTER);
		//getWebdriverAction().select_dd_by_value(custpage.getCustomerCountryField(),"IN");
		test.info("Customer Country is chosen as India");
		
		
		getWebdriverAction().explicitlywait_elementtobeclickable(custpage.getCustomerStateField());
		customClick(custpage.getCustomerStateField());
		getWebDriver().findElement(By.xpath("//input[@type=\"search\"]")).sendKeys("Maharashtra",Keys.ENTER);
		//getWebdriverAction().select_dd_by_value(custpage.getCustomerStateField(),"505");
		test.info("Customer State is chosen as Maharashtra");
		
	
		getWebdriverAction().explicitlywait_elementtobeclickable(custpage.getCustomerCityField());
		String custCity = fake.address().city();
		custpage.getCustomerCityField().sendKeys(custCity);
		test.info("Customer City is chosen as "+ custCity);
		takeScreenShort(getStrongText("Customer Address Updated")," Customer Address Updated");
		
		
		getWebdriverAction().explicitlywait_elementtobeclickable(custpage.getCustomerSubmitBtn());
		custpage.getCustomerSubmitBtn().click();
		
		getWebdriverAction().implicitlywait();

		getWebdriverAction().explicitlywait_elementtobeclickable(custpage.getConvertToSupplierBtn());
		String added_email = custpage.getEmail_id().getText();
		takeScreenShort(getStrongText("New Customer added")," New Customer added");
		getCommonAction().checkAssertString(added_email, custEmail, "Add New Customer Test");
		
//Procure approval 
		customClick(custpage.getVerification_Status_Comments_tab());
		getWebdriverAction().select_dd_by_value(custpage.getStatus_dd(), "1");
		String Comment1 = filclass.readData_TestData("ADD_SUPPLIER_Data",4,2);
		custpage.getComment().sendKeys(Comment1);
		customClick(custpage.getSubmit_btn());
		takeScreenShort(getStrongText("Customer First Level Approval")," Customer First Level Approval");
		test.info("Customer First Level Approved");
		customClick(custpage.getVerification_Status_Comments_tab());
		getWebdriverAction().explicitlywait_visibilityofallelements(custpage.getL1_approved_text());
		takeScreenShort(getStrongText("Customer First Level Approved")," Customer First Level Approved");

		String actual_status = custpage.getL1_approved_text().getText();
		System.out.println(actual_status);
		String expected_status="Approved";
	    getCommonAction().checkAssertString(actual_status, expected_status, " L1 Appoved ");
		getCommonAction().userLogOut();
		
		
//Finance approval	
		getCommonAction().userLoginPage("finance", "  Login for customer L2 approval ");
		customClick(getting_started.getCustomers_button());
		clickOnSubMenu(getting_started.getCustomers_button(), getting_started.getCustomerMaster_tab(), globalTime);
		customSendText(custpage.getCustomer_name_text_field(), custName);
		customSendText(custpage.getCustomet_email_text_field(),custEmail);
		customClick(custpage.getSearch_button());
		customClick(custpage.getCustomer_id());
		customClick(custpage.getVerification_Status_Comments_tab());
		getWebdriverAction().select_dd_by_value(custpage.getStatus_dd(), "1");
		String Comment2 = filclass.readData_TestData("ADD_SUPPLIER_Data",5,2);
		custpage.getComment().sendKeys(Comment2);
		customClick(custpage.getVerify_btn());
		takeScreenShort(getStrongText("Customer Second Level Approval")," Customer Second Level Approval");
		test.info("Customer Second Level Approved");
		customClick(custpage.getVerification_Status_Comments_tab());
		getWebdriverAction().explicitlywait_visibilityofallelements(custpage.getL2_approved_text());
		takeScreenShort(getStrongText("Customer Second Level Approved")," Customer Second Level Approved");
		
		String actual_status2 = custpage.getL2_approved_text().getText();
		System.out.println(actual_status2);
		String expected_status2="Approved";
	    getCommonAction().checkAssertString(actual_status, expected_status2, " L2 Appoved ");
		getCommonAction().userLogOut();
		
//for Inactive Customer 
		getCommonAction().userLoginPage("user", "  Login to do customer Inactive ");
		customClick(getting_started.getCustomers_button());
		clickOnSubMenu(getting_started.getCustomers_button(), getting_started.getCustomerMaster_tab(), globalTime);
		customSendText(custpage.getCustomer_name_text_field(), custName);
		customSendText(custpage.getCustomet_email_text_field(),custEmail);
		customClick(custpage.getSearch_button());
		customClick(custpage.getCustomer_id());
		getWebdriverAction().thread_sleep(1000);
		customClick(custpage.getStatus_button());
		customClick(custpage.getBack_button());
		customSendText(custpage.getCustomer_name_text_field(),custName );
		customSendText(custpage.getCustomet_email_text_field(),custEmail );
		customClick(custpage.getSearch_button());
		waitForVisibleElement(custpage.getCustomer_status_text(), globalTime);
		scrollIntoView(custpage.getCustomer_status_text());
		highLight(custpage.getCustomer_status_text());
		String customer_status = custpage.getCustomer_status_text().getText();
		takeScreenShort(getStrongText("Customer status")," Customer status");
		System.out.println(customer_status);
		String expected_status3 = "Inactive";
		getCommonAction().checkAssertString(customer_status,expected_status3 , "add_New_Customer Test ");
		
	}
}
