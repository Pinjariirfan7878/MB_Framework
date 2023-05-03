package com.OMS.SO;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.oms.GenericLib.BaseUtilityClass;
import com.oms.GenericLib.IAutoconstant;
import com.oms.GenericLib.RetryanalyserClass;

@Listeners(com.oms.GenericLib.MylistenerClass.class)
public class TC_007_SO_Partial_Return extends BaseUtilityClass {

	String testCaseDescription = "Testing flow of Sales Order Partial Return.";

	@Test(retryAnalyzer = RetryanalyserClass.class)
	public void sales_order_PartialReturn_Test() throws Throwable {
		String currentClassName = this.getClass().getSimpleName();
		setTestCaseAndDescriptionInReport("SO", currentClassName, testCaseDescription);

		String Customer_Email_ID = getFileLibClass().getDataFromExcell("SO_Data", "Customer_Email_ID",
				IAutoconstant.EXCEL_TestData);
		getCommonAction().skuQuantity = getFileLibClass().getDataFromExcell("SO_Data", "SKU_Qty", IAutoconstant.EXCEL_TestData);
		String Shipping_Price = getFileLibClass().getDataFromExcell("SO_Data", "Shipping_Price",
				IAutoconstant.EXCEL_TestData);
		getCommonAction().product_sku = getFileLibClass().getDataFromExcell("SO_Data", "SKU_Name",
				IAutoconstant.EXCEL_TestData);
		getCommonAction().cdcName = getFileLibClass().getDataFromExcell("SO_Data", "CDC", IAutoconstant.EXCEL_TestData);

		// Step 1 => Get Initial Count of Stock in StokeManagement
		String Before_PAP_CDC_Count = getCommonAction().getCDStockCount(getCommonAction().product_sku,
				getCommonAction().cdcName);
		int Before_PAP_initialCDCcount = Integer.parseInt(Before_PAP_CDC_Count);
		
		//Step 1: Create Sales Order
		getCommonAction().get_Create_SO(Customer_Email_ID, getCommonAction().product_sku, getCommonAction().skuQuantity, Shipping_Price,false);
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
		getCommonAction().getPickAndPackAction(getCommonAction().product_sku, getCommonAction().skuQuantity);
		
		//Step 5: Shipment
		getCommonAction().getPAP_ShippingAction();
		getCommonAction().getUpdate_Finance_Info();

		Before_PAP_CDC_Count = getCommonAction().getCDStockCount(getCommonAction().product_sku,
				getCommonAction().cdcName);

		//Step 6: Dispatch
		getCommonAction().getSearch_SO(getGloabalData("SO_ID"));
		getCommonAction().get_Dispatch_Action();
		
		//String returnType = "Full Return" ;
		//getCommonAction().getPartial_Return("1", "1", "1",returnType);
		
		//Step 7: Partial return
		String returnType = "Partial Return" ;//Full Return
		getCommonAction().getPartial_Return("1", "1", "1",returnType);
		
		String skuCount = getCommonAction().getCDStockCount(getCommonAction().product_sku,getCommonAction().cdcName);
		test.info("After Partial Return of " + getCommonAction().product_sku + " , Quanity = " +getStrongText(skuCount));
		getCommonAction().getSearch_SO(getGloabalData("SO_ID"));
		
		getCommonAction().getPartial_Return("2", "1", "1",returnType);
		skuCount = getCommonAction().getCDStockCount(getCommonAction().product_sku,getCommonAction().cdcName);
		test.info("After Partial Return of " + getCommonAction().product_sku + " , Quanity = " +getStrongText(skuCount));

		
		//Validation for Test Case for Demo need to add valid part
		String SO_Number = getGloabalData("SO_ID");
		getCommonAction().checkAssertString_Empty(SO_Number, "Validation of Sales_Order_PartialReturn_Test");
		
	}
}
