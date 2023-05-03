package com.OMS.SO;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.oms.GenericLib.BaseUtilityClass;
import com.oms.GenericLib.IAutoconstant;
import com.oms.GenericLib.RetryanalyserClass;

@Listeners(com.oms.GenericLib.MylistenerClass.class)
public class TC_006_SO_Rollback_PickAndPack extends BaseUtilityClass {
	String testCaseDescription = "Tesing flow of Sales Order PickAndPack Rollback.";

	@Test(retryAnalyzer = RetryanalyserClass.class)
	public void sales_order_rollBack_pickAndPack_test () throws Throwable {
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

		// Step 1 => Get Initial Count of Stock in StokeManagement
		String skuCount = getCommonAction().getCDStockCount(getCommonAction().product_sku,
				getCommonAction().cdcName);
		int initialCDCcount = Integer.parseInt(skuCount);
		test.info(" Initial, The CDC stock count of " + getCommonAction().product_sku + " is "+ getStrongText(""+initialCDCcount));
		
		//Step 2: Create Sales Order
		getCommonAction().get_Create_SO(Customer_Email_ID, getCommonAction().product_sku, SKU_Qty, Shipping_Price,false);
		getCommonAction().userLogOut();
		
		//Step 3: Procure Approval
		getCommonAction().procureApproveProcess("SO", "approve"); // approve , reject
		getCommonAction().userLogOut();
		
		//Step 4: Finance Approval
		getCommonAction().financeApproveProcess("SO", "approve");
		getCommonAction().userLogOut();
		
		//Step 5: Pick and Pack
		getCommonAction().userLoginPage("user", "User Login For to do Pick and Pack");
		getCommonAction().getSearch_SO(getGloabalData("SO_ID"));
		getCommonAction().getPickAndPackAction(getCommonAction().product_sku, SKU_Qty);
		
		//Step 6: Shipment
		getCommonAction().getPAP_ShippingAction();
		getCommonAction().getUpdate_Finance_Info();

		skuCount = getCommonAction().getCDStockCount(getCommonAction().product_sku,
				getCommonAction().cdcName);
		
		test.info("After Pick and Pack "+getCommonAction().product_sku +" is , "+getStrongText(skuCount)) ;
		
		getCommonAction().getSearch_SO(getGloabalData("SO_ID"));

		// Step 7:Rollback
		String Total_Qty_To_Rollback = getFileLibClass().getDataFromExcell("SO_Data", "Total_Qty_To_Rollback",
				IAutoconstant.EXCEL_TestData);
		String SKU_Qty_To_Rollback = getFileLibClass().getDataFromExcell("SO_Data", "SKU_Qty_To_Rollback",
				IAutoconstant.EXCEL_TestData);
		String Comment_RB1 = getFileLibClass().getDataFromExcell("SO_Data", "Comment_RB1",
				IAutoconstant.EXCEL_TestData);
		
		// Here 1st time ROllBack UpdateFinance is Visible , so 1st time pass true
		getCommonAction().getRollback_PickAndPack(Total_Qty_To_Rollback, SKU_Qty_To_Rollback, Comment_RB1, true);
		getCommonAction().getRollback_PickAndPack("1", "1", "ROllBack 2", false);
		
		//Validation Test Case
		String RollBackAction = getCommonAction().getGloabalData("SO_ID");
		System.out.println("**********---"+RollBackAction+"---*********");
		getCommonAction().checkAssertString_Empty(RollBackAction, "Validation of sales_order_rollBack_pickAndPack_Test ");
	}
}
