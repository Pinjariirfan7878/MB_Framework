package com.OMS.SO;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.oms.GenericLib.BaseUtilityClass;
import com.oms.GenericLib.IAutoconstant;
import com.oms.GenericLib.RetryanalyserClass;

@Listeners(com.oms.GenericLib.MylistenerClass.class)
public class TC_005_SO_RejectedByFinance_Test extends BaseUtilityClass {

	String testCaseDescription = "Tesing flow of Sales Order Finance Reject.";

	@Test(retryAnalyzer = RetryanalyserClass.class)
	public void so_rejected_by_finance_test() throws Exception {
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

		//Step 1: Create Sales Order
		getCommonAction().get_Create_SO(Customer_Email_ID, getCommonAction().product_sku, SKU_Qty, Shipping_Price,false);
		getCommonAction().userLogOut();
		
		//Step 2: Procure Approval
		getCommonAction().procureApproveProcess("SO", "approve"); // approve , reject
		getCommonAction().userLogOut();
		
		//Step 3: Finance Reject
		getCommonAction().financeApproveProcess("SO", "reject");

		//validation part
		String Rejected_By_Finance=getGloabalData("financeSO_Rejected");
		getCommonAction().checkAssertString(Rejected_By_Finance,"Rejected By Finance" ,"Validation of SO_Rejected_by_Finance_Test ");
	}
}
