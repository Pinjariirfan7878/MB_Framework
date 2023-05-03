package com.OMS.SO;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.oms.GenericLib.BaseUtilityClass;
import com.oms.GenericLib.IAutoconstant;
import com.oms.GenericLib.RetryanalyserClass;

@Listeners(com.oms.GenericLib.MylistenerClass.class)
public class TC_001_SO_End_to_End_Test extends BaseUtilityClass {

	String testCaseDescription = "Tesing flow of Sales Order End to End.";
	
	@Test(retryAnalyzer = RetryanalyserClass.class)
	public void sales_order_End_to_End_Test() throws Exception
	{
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
		String skuCount = getCommonAction().getCDStockCount(getCommonAction().product_sku,getCommonAction().cdcName);
		int initialSkuCount = Integer.parseInt(skuCount);
		
			//Step 1: Create Sales Order
		getCommonAction().get_Create_SO(Customer_Email_ID, getCommonAction().product_sku, SKU_Qty, Shipping_Price,true);
		getCommonAction().userLogOut();
		
		//Step 2: Procure Approval
		getCommonAction().procureApproveProcess("SO", "approve"); // approve , reject
		getCommonAction().userLogOut();
		
		//Step 3: Finance Approval
		getCommonAction().financeApproveProcess("SO", "approve");
		getCommonAction().userLogOut();
		
		//Step 4: Pick and Pack
		getCommonAction().userLoginPage("user", "User Login For to do Pick and Pack");
		getCommonAction().getSearch_SO(getGloabalData("SO_ID"));
		getCommonAction().getPickAndPackAction(getCommonAction().product_sku, SKU_Qty);
		
		//Step 5: Shipment
		getCommonAction().getPAP_ShippingAction();
		getCommonAction().getUpdate_Finance_Info();

		getCommonAction().getSearch_SO(getGloabalData("SO_ID"));
		
		//Step 6: Dispatch
		getCommonAction().get_Dispatch_Action();
		skuCount = getCommonAction().getCDStockCount(getCommonAction().product_sku,
				getCommonAction().cdcName);
		 
		test.info("After Sales Order Delivered of "+getCommonAction().product_sku+" is "+ getStrongText(skuCount));
		int afterDeliveredCount =  Integer.parseInt(skuCount) ;
		int skuQuantity =  Integer.parseInt(SKU_Qty) ;
		
		System.out.println("Before Delivered sku Count = "+initialSkuCount);
		System.out.println("Sku Count = "+SKU_Qty);
		System.out.println("After Delivered sku Count = "+afterDeliveredCount);
		
		if((afterDeliveredCount +skuQuantity) == initialSkuCount ) {
			softassert.assertEquals(isSuccess, true, "sales_order_End_to_End_Test" +" is successful passed");	
			String msgStatus ="Assertion of "+getStrongText("sales_order_End_to_End_Test")+" is Passed ,";
			test.info("<span style='background-color:#32CD32; color:white'>"+msgStatus+"</span>");
		}
		else
		{
			isSuccess = false ;
			softassert.assertEquals(isSuccess, true, "sales_order_End_to_End_Test" +" is Failed");
			String msgStatus = "Assertion of "+getStrongText("")+" is fail ";
			test.info("<span style='background-color:red; color:white'>"+msgStatus+"</span>");
		}
	}
}
