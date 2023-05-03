package com.OMS.SO;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.oms.GenericLib.BaseUtilityClass;
import com.oms.GenericLib.IAutoconstant;
import com.oms.GenericLib.RetryanalyserClass;

@Listeners(com.oms.GenericLib.MylistenerClass.class)
public class TC_009_SO_Bulk_Unique_SKU_CSV extends BaseUtilityClass {

	String testCaseDescription = "Testing flow of Sales Order with bulk SKU's are adding into the cart";

	@Test(retryAnalyzer = RetryanalyserClass.class)
	public void 	SO_Bulk_Unique_SKU_CSV_Test() throws Throwable {
		String currentClassName = this.getClass().getSimpleName();
		setTestCaseAndDescriptionInReport("SO", currentClassName, testCaseDescription);
		
		getFileLibClass().getDataFromExcell("SO_Data", "Customer_Email_ID",IAutoconstant.EXCEL_TestData);
		getFileLibClass().getDataFromExcell("SO_Data", "Shipping_Price",IAutoconstant.EXCEL_TestData);
		getCommonAction().cdcName = getFileLibClass().getDataFromExcell("SO_Data", "CDC", IAutoconstant.EXCEL_TestData);

		//Step 1: Create Sales Order
		getCommonAction().get_SO_Bulk_Unique_CSV();
		getCommonAction().userLogOut();
		
		//Step 2: Procure Approval
		getCommonAction().procureApproveProcess("SO", "approve"); // approve , reject
		getCommonAction().userLogOut();
		
		//Step 3: Finance Approval
		getCommonAction().financeApproveProcess("SO", "approve");
		
		//Assert condition
		String SO_Number = getGloabalData("SO_ID");
		getCommonAction().checkAssertString_Empty(SO_Number, "Validation of SO_Bulk_Unique_SKU_CSV_Test");
		
	}
}