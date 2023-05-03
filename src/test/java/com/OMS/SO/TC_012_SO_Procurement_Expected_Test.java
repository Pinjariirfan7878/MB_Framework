package com.OMS.SO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ObjectRepo.Sales_Order_Shipment_OMS_Medikabazaar_Page;
import com.oms.GenericLib.BaseUtilityClass;
import com.oms.GenericLib.IAutoconstant;
import com.oms.GenericLib.RetryanalyserClass;

@Listeners(com.oms.GenericLib.MylistenerClass.class)
public class TC_012_SO_Procurement_Expected_Test extends BaseUtilityClass {

	String testCaseDescription = "Testing Flow of SO Procurement Expected";

	@Test(retryAnalyzer = RetryanalyserClass.class)
	public void SO_Procurement_Expected_Test() throws Exception {

		String currentClassName = this.getClass().getSimpleName();
		setTestCaseAndDescriptionInReport("SO", currentClassName, testCaseDescription);

		String Customer_Email_ID = getFileLibClass().getDataFromExcell("SO_Data", "Customer_Email_ID",
				IAutoconstant.EXCEL_TestData);
		String SKU_Qty = getFileLibClass().getDataFromExcell("SO_Data", "SKU_Qty", IAutoconstant.EXCEL_TestData);
		String Shipping_Price = getFileLibClass().getDataFromExcell("SO_Data", "Shipping_Price",
				IAutoconstant.EXCEL_TestData);
		getCommonAction().product_sku = getFileLibClass().getDataFromExcell("SO_Data", "SKU_Name",
				IAutoconstant.EXCEL_TestData);
		getCommonAction().cdcName = getFileLibClass().getDataFromExcell("SO_Data", "CDC", IAutoconstant.EXCEL_TestData);

		// Step 1: Create Sales Order
		getCommonAction().get_Create_SO(Customer_Email_ID, getCommonAction().product_sku, SKU_Qty, Shipping_Price,
				false);
		getCommonAction().userLogOut();

		// Step 2: Procure Approval
		getCommonAction().procureApproveProcess("SO", "approve"); // approve , reject
		getCommonAction().userLogOut();

		// Step 3: Finance Approval
		getCommonAction().financeApproveProcess("SO", "approve");
		getCommonAction().userLogOut();

		// Step 4: Procurement Expected
		String Email_to_Notify = getFileLibClass().getDataFromExcell("SO_Data", "Email_Notification_To",
				IAutoconstant.EXCEL_TestData);
		String Qty_To_Process = getFileLibClass().getDataFromExcell("SO_Data", "Qty_to_process",
				IAutoconstant.EXCEL_TestData);

		getCommonAction().userLoginPage("user", "User Login For Procurement Expected");
		getCommonAction().getSearch_SO(getGloabalData("SO_ID"));
		procurement_expected(Email_to_Notify, Qty_To_Process);

		// Assertion
		String s1 = getGloabalData("Procurement Expected");
		String s2 = "Procurement Expected   ";
		System.out.println(s1 + s2);
		getCommonAction().checkAssertString(s1, s2, "SO_Procurement_Expected_Test");

	}

	// *************************************Test script is Over
	// Here**************************************************

	// Common Functions

	public void procurement_expected(String Email_to_Notify, String Qty_To_Process) {
		Sales_Order_Shipment_OMS_Medikabazaar_Page Sales_Order_Shipment = new Sales_Order_Shipment_OMS_Medikabazaar_Page(
				getWebDriver());

		customClick(Sales_Order_Shipment.getLogistic_details());
		customClick(Sales_Order_Shipment.getprocurement_expected_radio_button());

		String date = getHelperClass().getDate(120, "yyyy-MM-dd");
		System.out.println("Date="+date);
	
		customSendText(Sales_Order_Shipment.getExpectedDeliveryDate(), date);
		customClick(Sales_Order_Shipment.getoperation_team_member_label());

		// Dynamic Xpath for operations team name
		String Operation_Team_Member = getFileLibClass().getDataFromExcell("SO_Data", "Operation_Team_Member",
				IAutoconstant.EXCEL_TestData);

		WebElement ope_team_member_name = getWebDriver()
				.findElement(By.xpath("//input[@title='" + Operation_Team_Member + "']"));
		customClick(ope_team_member_name);

		customSendText(Sales_Order_Shipment.getpro_expected_email_to_notify(), Email_to_Notify);
		customClick(Sales_Order_Shipment.getcheck_box_all());

		customClearText(Sales_Order_Shipment.getQty_to_process_Pro_Expected());
		customSendText(Sales_Order_Shipment.getQty_to_process_Pro_Expected(), Qty_To_Process);
		takeScreenShort("Details Entered For " + getStrongText("Procurement Expected"), "procurement expected");
		test.info("Email Notification Send To : " + getStrongText(Email_to_Notify));
		customClick(Sales_Order_Shipment.getsubmit_button());
		
	
		getCommonAction().getSearch_SO(getGloabalData("SO_ID"));
		
		customClick(Sales_Order_Shipment.getactivity_summary_tab());
		clickOnSubMenu(Sales_Order_Shipment.getactivity_summary_tab(), Sales_Order_Shipment.getPending_Shipments_heading(), 10);
		customClick(Sales_Order_Shipment.getprocurement_expected_text_Activity_Summary());

//		customClick(Sales_Order_Shipment.getactivity_summary_tab());
	//	getWebdriverAction().refresh();
		//customClick(Sales_Order_Shipment.getactivity_summary_tab());
		//getWebdriverAction().scrollIntoView(Sales_Order_Shipment.getPending_Shipments_heading());
		customClick(Sales_Order_Shipment.getPending_Shipments_heading());
		String procurement_expected_text = Sales_Order_Shipment.getprocurement_expected_text_Activity_Summary()
				.getText();
		System.out.println(procurement_expected_text);
		String onlyText = procurement_expected_text.replaceAll("[0-9]", "");
		String onlyText1 = onlyText.replaceAll("[^a-zA-Z0-9]", " ");
		addGlobalData("Procurement Expected", onlyText1);

		// Dynamic xpath for SKU Quantity
		getCommonAction().product_sku = getFileLibClass().getDataFromExcell("SO_Data", "SKU_Name",
				IAutoconstant.EXCEL_TestData);

		WebElement skuQny = getWebDriver().findElement(By
				.xpath("(//td[contains(text(),'" + getCommonAction().product_sku + "')]/following-sibling::td)[2]"));

		String qty_to_proc1 = skuQny.getText();
		System.out.println("Qty to process is >>>>>>>>>>" + qty_to_proc1);
		takeScreenShort("Status In Activity Summary: " + getStrongText(procurement_expected_text),
				"Procurement Expected");
		test.info("Pending for Procurement quantity: " + getStrongText(qty_to_proc1));
	}

}