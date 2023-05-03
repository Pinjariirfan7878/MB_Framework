package com.OMS.SO;


import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.oms.GenericLib.BaseUtilityClass;
import com.oms.GenericLib.IAutoconstant;
import com.oms.GenericLib.RetryanalyserClass;

@Listeners(com.oms.GenericLib.MylistenerClass.class)
public class TC_004_SO_RejectedByProcurement_Test extends BaseUtilityClass {
	
	String testCaseDescription = "Testing flow of Sales Order Procure Reject.";
	@Test(retryAnalyzer = RetryanalyserClass.class)
	public void so_rejected_by_procurement_test() throws Exception
	{
		String currentClassName = this.getClass().getSimpleName();
		setTestCaseAndDescriptionInReport("SO" ,currentClassName, testCaseDescription);
	
		
		String Customer_Email_ID = getFileLibClass().getDataFromExcell("SO_Data", "Customer_Email_ID",IAutoconstant.EXCEL_TestData);
		String SKU_Qty = getFileLibClass().getDataFromExcell("SO_Data", "SKU_Qty",IAutoconstant.EXCEL_TestData);
		String Shipping_Price = getFileLibClass().getDataFromExcell("SO_Data", "Shipping_Price",IAutoconstant.EXCEL_TestData);
		getCommonAction().product_sku = getFileLibClass().getDataFromExcell("SO_Data", "SKU_Name",IAutoconstant.EXCEL_TestData);
		getCommonAction().cdcName = getFileLibClass().getDataFromExcell("SO_Data", "CDC", IAutoconstant.EXCEL_TestData);
		
		//Step 1: Create Sales Order
		getCommonAction().get_Create_SO(Customer_Email_ID, getCommonAction().product_sku, SKU_Qty, Shipping_Price ,false);
		getCommonAction().userLogOut();
		
		//Step 2: Procure Reject
		getCommonAction().procureApproveProcess("SO", "reject"); //approve , reject
		
		//Validation of Test Case
		String Rejected_By_PO_Supervisor=getGloabalData("procureSO_Rejected");
		getCommonAction().checkAssertString(Rejected_By_PO_Supervisor,"Rejected By Procurement" ,"Validation of SO_Rejected_by_procurement_Test ");
	}
  }
