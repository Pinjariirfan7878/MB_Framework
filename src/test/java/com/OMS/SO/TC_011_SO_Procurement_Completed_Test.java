package com.OMS.SO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ObjectRepo.Sales_Order_Shipment_OMS_Medikabazaar_Page;
import com.ObjectRepo.Sales_Orders_Reports_OMS_Medikabazaar_Page;
import com.oms.GenericLib.BaseUtilityClass;
import com.oms.GenericLib.IAutoconstant;
import com.oms.GenericLib.RetryanalyserClass;

@Listeners(com.oms.GenericLib.MylistenerClass.class)
public class TC_011_SO_Procurement_Completed_Test extends BaseUtilityClass {

	String testCaseDescription = "Testing Flow SO Procurement Complete";

	@Test(retryAnalyzer = RetryanalyserClass.class)
	public void SO_Procurement_Completed_Test() throws Exception {
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
		String Qty_To_Process = getFileLibClass().getDataFromExcell("SO_Data", "Qty_to_process",
				IAutoconstant.EXCEL_TestData);
		
		getCommonAction().userLoginPage("user", "User Login For Procurement Completed");
		getCommonAction().getSearch_SO(getGloabalData("SO_ID"));
		procurementCompleted_count(Email_to_Notify, Qty_To_Process);
		
		//Assertion
		String s1=getGloabalData("Procurement Complete");
		String s2 = "Procurement Complete";
		getCommonAction().checkAssertString(s1,s2,"SO_Procurement_Completed_Test");
	}

	//*************************************Test script is Over Here**************************************************

	//Common Functions

	public void procurementCompleted_count(String Email_to_Notify, String Qty_To_Process) {
		Sales_Order_Shipment_OMS_Medikabazaar_Page Sales_Order_Shipment = new Sales_Order_Shipment_OMS_Medikabazaar_Page(
				getWebDriver());
		Sales_Orders_Reports_OMS_Medikabazaar_Page Sales_Orders_Reports = new Sales_Orders_Reports_OMS_Medikabazaar_Page(getWebDriver());

		customClick(Sales_Order_Shipment.getLogistic_details());
		customClick(Sales_Order_Shipment.getprocurement_completed_radio_button());
		
		customClick(Sales_Order_Shipment.getsubmit_procurement_button());
	
		customClick(Sales_Orders_Reports.getOperations_Team_Member_dd());
		String Operation_Team_Member = getFileLibClass().getDataFromExcell("SO_Data", "Operation_Team_Member",
				IAutoconstant.EXCEL_TestData);
		
		customSendText(Sales_Orders_Reports.getOperations_Team_Member_search(), Operation_Team_Member);
		
		WebElement checkbox = getWebDriver().findElement(By.xpath("//input[@title='"+Operation_Team_Member+"']"));
		customClick(checkbox);
	
		customSendText(Sales_Orders_Reports.getemail_to_notify(), Email_to_Notify);
		customClick(Sales_Orders_Reports.getcheck_box_all());
		customClearText(Sales_Orders_Reports.getQty_to_process());
		customSendText(Sales_Orders_Reports.getQty_to_process(), Qty_To_Process);
		takeScreenShort("Details Entered For " + getStrongText("Procurement Completed"),"procurement completed");
		test.info("Email Notification Send To : " +getStrongText(Email_to_Notify));
		customClick(Sales_Orders_Reports.getsubmit_button());	
		
		getCommonAction().getSearch_SO(getGloabalData("SO_ID"));
		
		customClick(Sales_Order_Shipment.getactivity_summary_tab());
		clickOnSubMenu(Sales_Order_Shipment.getactivity_summary_tab(),Sales_Order_Shipment.getPending_Shipments_heading(),2);
		//getWebdriverAction().scrollIntoView(Sales_Order_Shipment.getPending_Shipments_heading());
		customClick(Sales_Order_Shipment.getPending_Shipments_heading());
		String procurement_completed_text = Sales_Order_Shipment.getprocurement_completed_text_Activity_Summary().getText();
		
		System.out.println(procurement_completed_text);
		addGlobalData("Procurement Complete",procurement_completed_text);
		
		//Dynamic xpath for SKU Quantity
		getCommonAction().product_sku = getFileLibClass().getDataFromExcell("SO_Data", "SKU_Name",
				IAutoconstant.EXCEL_TestData);
	
		WebElement skuQny = getWebDriver().findElement(By.xpath("(//td[contains(text(),'"+getCommonAction().product_sku+"')]/following-sibling::td)[2]"));
		
		String qty_to_proc1 = skuQny.getText();
		System.out.println("Qty to process is >>>>>>>>>>" +qty_to_proc1);
		takeScreenShort("Status In Activity Summary: " + getStrongText(procurement_completed_text),"Procurement Completed");
		test.info("Pending for Procurement quantity: " +getStrongText(qty_to_proc1));
	}
}
