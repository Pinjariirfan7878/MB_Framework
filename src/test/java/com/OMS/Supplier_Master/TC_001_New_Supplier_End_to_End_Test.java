package com.OMS.Supplier_Master;

import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ObjectRepo.Add_Seller_OMS_Medikabazaar_Page;
import com.ObjectRepo.Getting_Started_OMS_MedikabazaarPage;
import com.ObjectRepo.Supplier_Details_OMS_Medikabazaar_Page;
import com.ObjectRepo.Supplier_Master_OMS_Medikabazaar_Page;
import com.ObjectRepo.loginPage;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.oms.GenericLib.BaseUtilityClass;
import com.oms.GenericLib.FileLibClass;
import com.oms.GenericLib.IAutoconstant;
import com.oms.GenericLib.RetryanalyserClass;

@Listeners(com.oms.GenericLib.MylistenerClass.class)
public class TC_001_New_Supplier_End_to_End_Test extends BaseUtilityClass {
	String testCaseDescription = "Testing flow of Add Domestic Supplier";
	@Test//(retryAnalyzer = RetryanalyserClass.class)
	public void New_Supplier_End_to_End_Test() throws Exception {
		
		Getting_Started_OMS_MedikabazaarPage getting_started = new Getting_Started_OMS_MedikabazaarPage(getWebDriver());
		
		FileLibClass filclass = new FileLibClass();
		Supplier_Master_OMS_Medikabazaar_Page Supplier_Master = new Supplier_Master_OMS_Medikabazaar_Page(getWebDriver());
		Add_Seller_OMS_Medikabazaar_Page Add_Seller = new Add_Seller_OMS_Medikabazaar_Page(getWebDriver());
		Supplier_Details_OMS_Medikabazaar_Page Supplier_Details = new Supplier_Details_OMS_Medikabazaar_Page(getWebDriver());
		loginPage login = new loginPage(getWebDriver());
		
		String currentClassName = this.getClass().getSimpleName();
		setTestCaseAndDescriptionInReport("Supplier",currentClassName, testCaseDescription);
		
		getting_started.getSuppliers_tab().click();
		getting_started.getSupplier_Master_tab().click();
		Supplier_Master.getAdd_Supplier_button().click();
		
		Add_Seller.getSupplier_Type_Checkbox().click();	
		String Supplier_Name = fake.name().firstName();
		Add_Seller.getSupplier_Name_Label().sendKeys(Supplier_Name);
		Thread.sleep(2000);
		String Supplier_Email = fake.internet().safeEmailAddress();
		Add_Seller.getSupplier_Email_Label().sendKeys(Supplier_Email);
		String mobile = faker.regexify("[6-9]{10}");
		Add_Seller.getSupplier_Mobile_Label().sendKeys(mobile);
		String Supplier_Password = fake.internet().password();
		Add_Seller.getPassword_Label().sendKeys(Supplier_Password);
		getWebdriverAction().select_dd_by_value(Add_Seller.getCountry_Label(), "IN");
		getWebdriverAction().select_dd_by_value(Add_Seller.getSRM_Label(), "1768");
		Add_Seller.getSubmit_button().click();
		test.info("Supplier Details "+" Supplier Name : "+getStrongText(Supplier_Name)+" Supplier Email : "+ getStrongText(Supplier_Email));
//Billing Address
		Thread.sleep(5000);
		getWebdriverAction().scrollBy_value("0", "400");
		Thread.sleep(2000);
		String Street_Line1 = fake.address().streetAddress();
		Supplier_Details.getStreet_Line1_Label().sendKeys(Street_Line1);
		String pincode = faker.regexify("[2-5]{6}");
		Supplier_Details.getPincode_Label().sendKeys(pincode);
		getWebdriverAction().select_dd_by_value(Supplier_Details.getCountry_Label(), "IN");
		getWebdriverAction().select_dd_by_value(Supplier_Details.getState_Label(), "505");
		String City = fake.address().city();
		
		Supplier_Details.getCity_Label().sendKeys(City);
		
//Other Details
		customClick(Supplier_Details.getPayment_Terms_Label());
		//custome(Supplier_Details.getPayment_Terms_Label());
		getWebdriverAction().select_dd_by_value(Supplier_Details.getPayment_Terms_Label(), "50% Advance, Balance immediate on Delivery");
		
		Supplier_Details.getSubmit_button().click();
		test.info("Supplier Billing Address Details Added");
		String status1 = Supplier_Details.getStatus_msg_kyc().getText();
		System.out.println("Status after supplier added"+status1 );
		Reporter.log(status1, true);
		if(Supplier_Details.getStatus_msg_kyc().isDisplayed())
		{
			String path=concatenate+getWebdriverAction().screenshot_Supplier_Master("New Supplier Added");
			test.info("Status message is displayed as: "+status1,MediaEntityBuilder.createScreenCaptureFromPath(path).build());
			Reporter.log("Step:1 New Supplier Added Successfully and Verification Passed",true);
		}
		else
		{
			String path=concatenate+getWebdriverAction().screenshot_Supplier_Master("New Supplier Not Added");
			test.info("Status message is not displayed ",MediaEntityBuilder.createScreenCaptureFromPath(path).build());
			Reporter.log("Step:1 New Supplier Not Added and Verification Failed",true);
		}	
//KYC Details
		getWebdriverAction().explicitlywait_elementtobeclickable(Supplier_Details.getKYC_Details_tab());
		Supplier_Details.getKYC_Details_tab().click();
		Thread.sleep(2000);
		getWebdriverAction().scrollBy_value("0", "200");
		Supplier_Details.getCompany_Name().sendKeys(Supplier_Name+"Pvt Ltd");
		String pdf=IAutoconstant.Pdf_dummy;
		Supplier_Details.getGST_declaration_attachment().sendKeys(pdf);
		//String GST_Num = filclass.readData_TestData("ADD_SUPPLIER_Data",1,2);
		//Supplier_Details.getGST_num_Label().sendKeys(GST_Num);
		String PAN_Num = filclass.readData_TestData("ADD_SUPPLIER_Data",2,2);
		Supplier_Details.getPAN_num_Label().sendKeys(PAN_Num);
		Supplier_Details.getContact_Person_Label().sendKeys(Supplier_Name);
		Supplier_Details.getAlt_Contact_Num_Label().sendKeys(mobile);
		
		String Credit_Period = filclass.readData_TestData("ADD_SUPPLIER_Data",3,2);
		
		Supplier_Details.getCredit_Period_Label().sendKeys(Credit_Period);
		Supplier_Details.getPanimage_upload().sendKeys(pdf);
		getWebdriverAction().scrollBy_bottom();
		Thread.sleep(2000);
		customSendText(Supplier_Details.getBank_name(), "BOB");
		getWebdriverAction().explicitlywait_elementtobeclickable(Supplier_Details.getKYC_button());
		Supplier_Details.getKYC_button().click();
		test.info("Supplier KYC Details Added");
		Supplier_Details.getVerification_Status_tab().click();
		Thread.sleep(2000);
		if(Supplier_Details.getVerify_button().isDisplayed())
		{
			String path=concatenate+getWebdriverAction().screenshot_Supplier_Master("New Supplier KYC Details Added");
			test.info("New Supplier KYC Details Added",MediaEntityBuilder.createScreenCaptureFromPath(path).build());
			Reporter.log("Step:2 New Supplier KYC Details Added Successfully and Verification Passed",true);
		}
		else
		{
			String path=concatenate+getWebdriverAction().screenshot_Supplier_Master("New Supplier KYC Details Not Added");
			test.info("New Supplier KYC Details Not Added",MediaEntityBuilder.createScreenCaptureFromPath(path).build());
			Reporter.log("Step:2 New Supplier KYC Details Not Added and Verification Failed",true);
		}
//First Level Approval
		Supplier_Details.getCustomer_Status_dd().click();
		getWebdriverAction().select_dd_by_value(Supplier_Details.getCustomer_Status_dd(), "1");
		String Comment1 = filclass.readData_TestData("ADD_SUPPLIER_Data",4,2);
		Supplier_Details.getComment().sendKeys(Comment1);
		Supplier_Details.getVerification_Submit().click();
		test.info("Supplier First Level Approved");
		Supplier_Details.getVerification_Status_tab().click();
		getWebdriverAction().scrollBy_bottom();
		Thread.sleep(2000);
		Supplier_Details.getApproval_Validation1().getText();
		if(Supplier_Details.getApproval_Validation1().isDisplayed())
		{
			String path=concatenate+getWebdriverAction().screenshot_Supplier_Master("New Supplier First Level Approved");
			test.info("New Supplier First Level Approved",MediaEntityBuilder.createScreenCaptureFromPath(path).build());
			Reporter.log("Step:3 First Level Approved Successfully and Verification Passed",true);
		}
		else
		{
			String path=concatenate+getWebdriverAction().screenshot_Supplier_Master("New Supplier First Level Not Approved");
			test.info("New Supplier First Level Not Approved",MediaEntityBuilder.createScreenCaptureFromPath(path).build());
			Reporter.log("Step:3 First Level Not Approved and Verification Failed",true);
		}
		getWebdriverAction().explicitlywait_elementtobeclickable(getting_started.getUser_logo());
		getting_started.getUser_logo().click();
		getting_started.getLogout_button().click();
		Thread.sleep(3000);
		
//Second Level Approval
		login.getLogin_button().click();
		login.getLogin_app(filclass.readPropertyData(PROP_PATH, "finance_usernameProtaDev"),filclass.readPropertyData(PROP_PATH, "finance_passwordProtaDev"));
		getWebdriverAction().explicitlywait_elementtobeclickable(getting_started.getSuppliers_tab());
		getting_started.getSuppliers_tab().click();
		getting_started.getSupplier_Master_tab().click();
		Supplier_Master.getSupplier_Name_Label().sendKeys(Supplier_Name);
		Supplier_Master.getSearch_button().click();
		Supplier_Master.getSupplier_ID().click();
		Supplier_Details.getVerification_Status_tab().click();
		Supplier_Details.getCustomer_Status_dd().click();
		getWebdriverAction().select_dd_by_value(Supplier_Details.getCustomer_Status_dd(), "1");
		String Comment2 = filclass.readData_TestData("ADD_SUPPLIER_Data",5,2);
		Supplier_Details.getComment().sendKeys(Comment2);
		getWebdriverAction().explicitlywait_elementtobeclickable(Supplier_Details.getSubmit2());
		Supplier_Details.getSubmit2().click();
		test.info("Supplier Second Level Approved");
		getWebdriverAction().explicitlywait_elementtobeclickable(Supplier_Details.getVerification_Status_tab());
		Supplier_Details.getVerification_Status_tab().click();
		getWebdriverAction().scrollBy_bottom();
		Thread.sleep(2000);
		Supplier_Details.getApproval_Validation2().getText();
		if(Supplier_Details.getApproval_Validation2().isDisplayed())
		{
			String path=concatenate+getWebdriverAction().screenshot_Supplier_Master("New Supplier Second Level Approved");
			test.info("New Supplier Second Level Approved",MediaEntityBuilder.createScreenCaptureFromPath(path).build());
			Reporter.log("Step:4 Second Level Approved Successfully and Verification Passed",true);
		}
		else
		{
			String path=concatenate+getWebdriverAction().screenshot_Supplier_Master("New Supplier Second Level Not Approved");
			test.info("New Supplier Second Level Not Approved",MediaEntityBuilder.createScreenCaptureFromPath(path).build());
			Reporter.log("Step:4 Second Level Not Approved and Verification Failed",true);
		}
		
		Reporter.log("Supplier Name: "+Supplier_Name,true);
		Reporter.log("Supplier Email ID: "+Supplier_Email,true);
		Reporter.log("Supplier Contact Number: "+mobile,true);
		String approved_text="Approved";
		takeScreenShort(getStrongText("New Supplier added with Both approved")," New Supplier added");
		getCommonAction().checkAssertString(Supplier_Details.getL2_approval_text().getText(), approved_text, " New_Supplier_End_to_End_Test");

//Inactive supplier 
		inactive_Supplier_test(Supplier_Name, Supplier_Email);
		
	}
	public void inactive_Supplier_test(String supplier_name,String supplier_email) {
		Getting_Started_OMS_MedikabazaarPage getting_started = new Getting_Started_OMS_MedikabazaarPage(getWebDriver());
		Supplier_Master_OMS_Medikabazaar_Page Supplier_Master = new Supplier_Master_OMS_Medikabazaar_Page(getWebDriver());
		Supplier_Details_OMS_Medikabazaar_Page Supplier_Details = new Supplier_Details_OMS_Medikabazaar_Page(getWebDriver());

		customClick(getting_started.getSuppliers_tab());
		customClick(getting_started.getSupplier_Master_tab());
		customSendText(Supplier_Master.getSupplier_Name_Label(), supplier_name);	
		customSendText(Supplier_Master.getSupplier_email_text_field(), supplier_email);
		customClick(Supplier_Master.getSearch_button());
		customClick(Supplier_Master.getSupplier_ID());
		customClick(Supplier_Details.getStatus_button());
		customClick(Supplier_Master.getBack_button());
		customSendText(Supplier_Master.getSupplier_Name_Label(), supplier_name);	
		customSendText(Supplier_Master.getSupplier_email_text_field(), supplier_email);
		customClick(Supplier_Master.getSearch_button());
		
		waitForVisibleElement(Supplier_Master.getSupplier_status_text(), globalTime);
		scrollIntoView(Supplier_Master.getSupplier_status_text());
		highLight(Supplier_Master.getSupplier_status_text());
		String supplier_status = Supplier_Master.getSupplier_status_text().getText();
		takeScreenShort(getStrongText("Supplier status")," Supplier status");
		System.out.println(supplier_status);
		String expected_status = "Inactive";
		getCommonAction().checkAssertString(supplier_status,expected_status , "inactive_Supplier_test");
	
		
	}
}