package com.OMS.SO.Negative_Flow;


import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ObjectRepo.Create_Quote_and_Order_OMS_Medikabazaar_Page;
import com.ObjectRepo.Customer_OMS_Medikabazaar_Page;

import com.ObjectRepo.Getting_Started_OMS_MedikabazaarPage;
import com.ObjectRepo.Success_OMS_Medikabazaar_Page;
import com.oms.GenericLib.BaseUtilityClass;
import com.oms.GenericLib.IAutoconstant;

@Listeners(com.oms.GenericLib.MylistenerClass.class)
public class TC_004_SO_Create_So_customer_type_change_Test extends BaseUtilityClass {

	public void searchCustomer(String customer_name, String customer_email) {
		Getting_Started_OMS_MedikabazaarPage getting_started = new Getting_Started_OMS_MedikabazaarPage(getWebDriver());
		Customer_OMS_Medikabazaar_Page custpage = new Customer_OMS_Medikabazaar_Page(getWebDriver());

		test.info(" Customer name : " + getStrongText(customer_name) + ", customer Email = "
				+ getStrongText(customer_email));

		customClick(getting_started.getCustomers_button());
		clickOnSubMenu(getting_started.getCustomers_button(), getting_started.getCustomerMaster_tab(), globalTime);
		customSendText(custpage.getCustomet_email_text_field(), customer_email);
		customClick(custpage.getSearch_button());
		customClick(custpage.getCustomer_id());
	}

	public void checkCustomerApprovalStatus(Customer_OMS_Medikabazaar_Page custpage) {
		String l1Status, l2Status;
		if (custpage.getL1_approved_text().getAttribute("textContent").trim().equals("Approved")
				&& custpage.getL2_approved_text().getAttribute("textContent").trim().equals("Approved")) {
			l1Status = "Approved";
			l2Status = "Approved";
		} else if (custpage.getL1_approved_text().getAttribute("textContent").trim().equals("Approved")) {
			l1Status = "Approved";
			l2Status = "Not Approved";
		} else if (custpage.getL2_approved_text().getAttribute("textContent").trim().equals("Approved")) {
			l1Status = "Not Approved";
			l2Status = "Approved";
		} else {
			l1Status = "Not Approved";
			l2Status = "Not Approved";
		}

		highLight(custpage.getL1_approved_text());
		highLight(custpage.getL2_approved_text());

		takeScreenShort(
				"Customer L1 status => " + getStrongText("" + l1Status) + " , Customer L2 status => "
						+ getStrongText("" + l2Status),
				"Customer L1 status => " + getStrongText("" + l1Status) + " , Customer L2 status => "
						+ getStrongText("" + l2Status));
	}

	String testCaseDescription = "Create_So_customer_type_change ";

	@Test
	public void Create_So_customer_type_change_Test() {
		String currentClassName = this.getClass().getSimpleName();
		setTestCaseAndDescriptionInReport("SO Negative", currentClassName, testCaseDescription);

		String SKU_Name = getFileLibClass().getDataFromExcell("Create_So_customer_type_change", "SKU_Name",
				IAutoconstant.EXCEL_TestData);
		String SKU_QTY = getFileLibClass().getDataFromExcell("Create_So_customer_type_change", "SKU_Qty",
				IAutoconstant.EXCEL_TestData);
		String Shipping_price = getFileLibClass().getDataFromExcell("Create_So_customer_type_change", "Shipping_price",
				IAutoconstant.EXCEL_TestData);
		String customer_name = getFileLibClass().getDataFromExcell("Create_So_customer_type_change", "customer_name",
				IAutoconstant.EXCEL_TestData);
		String customer_email = getFileLibClass().getDataFromExcell("Create_So_customer_type_change", "customer_email",
				IAutoconstant.EXCEL_TestData);

		Getting_Started_OMS_MedikabazaarPage getting_started = new Getting_Started_OMS_MedikabazaarPage(getWebDriver());
		Customer_OMS_Medikabazaar_Page custpage = new Customer_OMS_Medikabazaar_Page(getWebDriver());

		// ================================================================Start=================

		TC_004_SO_Create_So_customer_type_change_Test ob = new TC_004_SO_Create_So_customer_type_change_Test();
		ob.searchCustomer(customer_name, customer_email);
		customClick(custpage.getVerification_Status_Comments_tab());
		
		String before_cutomer_type = custpage.getType_of_Customer().getAttribute("textContent").trim();
		test.info("Initial customer type : " + getStrongText(before_cutomer_type));
		customClick(custpage.getVerification_Status_Comments_tab());
		// getWebdriverAction().thread_sleep(1000);
		highLight(custpage.getL1_approved_text());
		String actual_L1_approved_text = custpage.getL1_approved_text().getAttribute("textContent").trim();
		// ============================================END===========

		ob.checkCustomerApprovalStatus(custpage);
		String expected_L1_approved_text = "Approved";
		getCommonAction().checkAssertString(actual_L1_approved_text, expected_L1_approved_text,
				"customer found with L1 ");

		customClick(custpage.getDetails_tab());
		customClick(custpage.getChange_customer_type_button());
		getWebdriverAction().select_dd_by_value(custpage.getChange_Customer_type_dd(), "11");
//		getWebdriverAction().thread_sleep(1000);
		String after_cutomer_type = custpage.getType_of_Customer().getText();
		test.info("after change customer type : " + getStrongText(after_cutomer_type));
		customClick(custpage.getVerification_Status_Comments_tab());
		// getWebdriverAction().thread_sleep(1000);

		ob.checkCustomerApprovalStatus(custpage);
		String after_actual_L1_approved_text = custpage.getL1_approved_text().getText();
//		takeScreenShort(
//				"customer L1 status after change customer type : " + getStrongText(after_actual_L1_approved_text),
//				"customer with L1 status ");

		if (!expected_L1_approved_text.equalsIgnoreCase(after_actual_L1_approved_text)) {
			test.info(getStrongText("Customer Type changed so approval is revoked."));
		} else {
			test.info(getStrongText("Customer Type changed but approval is not revoked."));
		}
//create So without L1 approve
		getCommonAction().get_Create_SO(customer_email, SKU_Name, SKU_QTY, Shipping_price, false);

		Create_Quote_and_Order_OMS_Medikabazaar_Page Create_Quote_and_Order = new Create_Quote_and_Order_OMS_Medikabazaar_Page(
				getWebDriver());
		Success_OMS_Medikabazaar_Page Success = new Success_OMS_Medikabazaar_Page(getWebDriver());
		// To create SO Order
		customClick(Create_Quote_and_Order.getPlace_Order_Button());
		getWebdriverAction().thread_sleep(500);

		if (Success.getKyc_pending_error_message().isDisplayed()) {
			takeScreenShort(getStrongText("Sales Order is not Create due to L1 pending "),
					"Sales Order is not Created due to L1 pending");
			softassert.assertEquals(isSuccess, true, "Test case" + " is successful passed");
			String msgStatus = "Assertion of " + getStrongText("Sales Order is not Create due to L1 pending ")
					+ " is passed ";
			test.info("<span style='background-color:#32CD32; color:white'>" + msgStatus + "</span>");
		} else {
			isSuccess = false;
			softassert.assertEquals(isSuccess, true, "Test case" + " is failed");
			String msgStatus = "Assertion of " + getStrongText("Test case") + " is failed ";
			test.info("<span style='background-color:red; color:white'>" + msgStatus + "</span>");
			takeScreenShort(getStrongText("Sales Order is Create even KYC pending "),
					"Sales Order is  Created even KYC pending");
		}
	}
}