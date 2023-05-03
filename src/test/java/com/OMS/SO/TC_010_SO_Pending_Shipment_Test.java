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
public class TC_010_SO_Pending_Shipment_Test extends BaseUtilityClass {

	String testCaseDescription = "Testing Flow of SO Pending Shipment";

	@Test(retryAnalyzer = RetryanalyserClass.class)
	public void SO_Pending_Shipment_Test()  {
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

		//Step 1: Create Sales Order
		getCommonAction().get_Create_SO(Customer_Email_ID, getCommonAction().product_sku, SKU_Qty, Shipping_Price,false);
		getCommonAction().userLogOut();
		
		//Step 2: Procure Approval
		getCommonAction().procureApproveProcess("SO", "approve"); // approve , reject
		getCommonAction().userLogOut();
		
		//Step 3: Finance Approval
		getCommonAction().financeApproveProcess("SO", "approve");
		getCommonAction().userLogOut();
		
		//Step 4: Shipment
		String Email_to_Notify = getFileLibClass().getDataFromExcell("SO_Data", "Email_Notification_To",
				IAutoconstant.EXCEL_TestData);
		String Qty_to_process = getFileLibClass().getDataFromExcell("SO_Data", "Qty_to_process",
				IAutoconstant.EXCEL_TestData);
		
		getCommonAction().userLoginPage("user", "User Login For Pending Shipment");
		getCommonAction().getSearch_SO(getGloabalData("SO_ID"));
		pendingShipment_count(Email_to_Notify, Qty_to_process);
		
		//Assertion
		String s1=getGloabalData("Pending for Procurement");
		String s2 = "Pending for Procurement";
		getCommonAction().checkAssertString(s1,s2,"SO_Procurement_Completed_Test");
		
	}
	//*************************************Test script is Over Here**************************************************
	
	//Common Functions
	public void pendingShipment_count(String Email_to_Notify, String Qty_to_process) {
		Sales_Order_Shipment_OMS_Medikabazaar_Page Sales_Order_Shipment = new Sales_Order_Shipment_OMS_Medikabazaar_Page(
				getWebDriver());
		
		customClick(Sales_Order_Shipment.getLogistic_details());
		customClick(Sales_Order_Shipment.getpending_shipment_radio_button());
		
		customClick(Sales_Order_Shipment.getpending_shipment_reason_text());
		getWebdriverAction().alert_accept();
		//customClick(Sales_Order_Shipment.getprocurement_team_member());
		String Procure_Team_Member = getFileLibClass().getDataFromExcell("SO_Data", "Operation_Team_Member",
				IAutoconstant.EXCEL_TestData);
		WebElement pro_team_mem = getWebDriver().findElement(By.xpath("(//option[contains(text(),'"+Procure_Team_Member+"')])[2]"));
		customClick(pro_team_mem);
		
		customSendText(Sales_Order_Shipment.getemail_to_notify(), Email_to_Notify);
		customClick(Sales_Order_Shipment.getcheck_box_all());
		customClearText(Sales_Order_Shipment.getQty_to_process_field_pending_Shipment());
		customSendText(Sales_Order_Shipment.getQty_to_process_field_pending_Shipment(), Qty_to_process);
		takeScreenShort("Details Entered For " + getStrongText("Pending Shipment With Pending Procurement "),"pending shipment");
		test.info("Email Notification Send To : " +getStrongText(Email_to_Notify));
		getWebdriverAction().scrollBy_bottom();
		customClick(Sales_Order_Shipment.getsubmit_button());
		
		getCommonAction().getSearch_SO(getGloabalData("SO_ID"));
		
		customClick(Sales_Order_Shipment.getactivity_summary_tab());
		clickOnSubMenu(Sales_Order_Shipment.getactivity_summary_tab(), Sales_Order_Shipment.getPending_Shipments_heading(), 10);
		customClick(Sales_Order_Shipment.getpending_procurement_text_Activity_Summary());
		
		String pending_proc_text = Sales_Order_Shipment.getpending_procurement_text_Activity_Summary().getText();
		System.out.println(pending_proc_text);
		addGlobalData("Pending for Procurement",pending_proc_text);
		
		//Dynamic xpath for SKU Quantity
		getCommonAction().product_sku = getFileLibClass().getDataFromExcell("SO_Data", "SKU_Name",IAutoconstant.EXCEL_TestData);
			
		WebElement skuQny = getWebDriver().findElement(By.xpath("(//td[contains(text(),'"+getCommonAction().product_sku+"')]/following-sibling::td)[2]"));
				
		String qty_to_proc = skuQny.getAttribute("textContent").trim();
		
		System.out.println("Qty to process is >>>>>>>>>>" +qty_to_proc);
		takeScreenShort("Status In Activity Summary: " + getStrongText(pending_proc_text),"pending procurement");
		test.info("Pending for Procurement quantity: " +getStrongText(qty_to_proc));
	}
}
