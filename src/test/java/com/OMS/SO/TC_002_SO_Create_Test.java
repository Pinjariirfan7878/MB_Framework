package com.OMS.SO;


import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.oms.GenericLib.BaseUtilityClass;
import com.oms.GenericLib.IAutoconstant;
import com.oms.GenericLib.RetryanalyserClass;


@Listeners(com.oms.GenericLib.MylistenerClass.class)
public class TC_002_SO_Create_Test extends BaseUtilityClass {

	String testCaseDescription = "Creating Sales Order.";

	@Test//(retryAnalyzer = RetryanalyserClass.class)
	public void Create_sales_order_Test() {
		String currentClassName = this.getClass().getSimpleName();
		setTestCaseAndDescriptionInReport("SO", currentClassName, testCaseDescription);
		
		// Create Sales Order
		String Customer_Email_ID = getFileLibClass().getDataFromExcell("SO_Data", "Customer_Email_ID",
				IAutoconstant.EXCEL_TestData);
		String SKU_Qty = getFileLibClass().getDataFromExcell("SO_Data", "SKU_Qty", IAutoconstant.EXCEL_TestData);
		String Shipping_Price = getFileLibClass().getDataFromExcell("SO_Data", "Shipping_Price",
				IAutoconstant.EXCEL_TestData);
		getCommonAction().product_sku = getFileLibClass().getDataFromExcell("SO_Data", "SKU_Name",
				IAutoconstant.EXCEL_TestData);
		getCommonAction().cdcName = getFileLibClass().getDataFromExcell("SO_Data", "CDC", IAutoconstant.EXCEL_TestData);
		
		//Create Sales Order
		getCommonAction().get_Create_SO(Customer_Email_ID, getCommonAction().product_sku, SKU_Qty, Shipping_Price , false);
		
		//Validation of test case
		String SO_Number = getGloabalData("SO_ID");
		getCommonAction().checkAssertString_Empty(SO_Number, "Validation of Create_sales_order_Test");
	}
}
