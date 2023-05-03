package com.OMS.PO;


import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ObjectRepo.Create_PO_OMS_MedikabazaarPage;
import com.ObjectRepo.Getting_Started_OMS_MedikabazaarPage;
import com.ObjectRepo.PO_MasterModule_OMS_MedikabazaarPage;
import com.ObjectRepo.PO_OMS_MedikabazaarPage;
import com.ObjectRepo.Purchase_Order_OMS_MedikabazaarPage;
import com.oms.GenericLib.BaseUtilityClass;
import com.oms.GenericLib.IAutoconstant;
import com.oms.GenericLib.RetryanalyserClass;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

@Listeners(com.oms.GenericLib.MylistenerClass.class)
public class TC_001_PO_Create_Test extends BaseUtilityClass {

	String testCaseDescription = "Testing flow of Purchase order_Create Purchase Order";
	@Test(retryAnalyzer = RetryanalyserClass.class)
	public void create_po_Test() throws Exception{
		FlowNAME = "PO";
		Getting_Started_OMS_MedikabazaarPage homepage = new Getting_Started_OMS_MedikabazaarPage(getWebDriver());
		Purchase_Order_OMS_MedikabazaarPage puchase_order = new Purchase_Order_OMS_MedikabazaarPage(getWebDriver());
		Create_PO_OMS_MedikabazaarPage create_PO_page = new Create_PO_OMS_MedikabazaarPage(getWebDriver());
		PO_OMS_MedikabazaarPage PO_created_page = new PO_OMS_MedikabazaarPage(getWebDriver());
		PO_MasterModule_OMS_MedikabazaarPage PO_master_page = new PO_MasterModule_OMS_MedikabazaarPage(getWebDriver());

		String currentClassName = this.getClass().getSimpleName();
		setTestCaseAndDescriptionInReport("PO",currentClassName, testCaseDescription);
		test.info("Home page is opened");
		
		getCommonAction().product_sku = getFileLibClass().getDataFromExcell("PO_Data", "Product SKU",
				IAutoconstant.EXCEL_TestData);
		getCommonAction().cdcName = getFileLibClass().getDataFromExcell("PO_Data", "CDC", IAutoconstant.EXCEL_TestData);

		// Step 1 => Creating Purchase Order.
		getCommonAction().createPurchaseOrder(homepage, puchase_order, create_PO_page, PO_created_page,false);

		//Step 2: Validation of Test Case
		String PO_ID=getGloabalData("PO_ID");
		getCommonAction().checkAssertString_Empty(PO_ID,"Validation of Create PO test");
	
		String verifyCancelText = getCommonAction().poFullCancel(PO_master_page);
		System.out.println("Cancel text captured is " + verifyCancelText);
		getCommonAction().checkAssertString(verifyCancelText, "Canceled", "Validation of cancel_po_fully_Test");
	
	}
}