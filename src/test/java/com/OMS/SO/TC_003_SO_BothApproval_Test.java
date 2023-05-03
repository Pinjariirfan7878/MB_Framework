package com.OMS.SO;


import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.oms.GenericLib.BaseUtilityClass;
import com.oms.GenericLib.IAutoconstant;
import com.oms.GenericLib.RetryanalyserClass;


@Listeners(com.oms.GenericLib.MylistenerClass.class)
public class TC_003_SO_BothApproval_Test extends BaseUtilityClass {

	String testCaseDescription = "Tesing flow of Sales Order Both Approval.";

	@Test(retryAnalyzer = RetryanalyserClass.class)
	public void sales_order_Bothapproval_Test() throws Exception {
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
		
		// Validation of Test Case
		String approve_finance_text = getGloabalData("financeSO_Approved");
		getCommonAction().checkAssertString(approve_finance_text,"Approved By Finance"," validation of sales_order_Bothapproval_Test");
		
	}
}
