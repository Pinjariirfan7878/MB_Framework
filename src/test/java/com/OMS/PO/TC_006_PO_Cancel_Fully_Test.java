package com.OMS.PO;

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

@Listeners(com.oms.GenericLib.MylistenerClass.class)
public class TC_006_PO_Cancel_Fully_Test extends BaseUtilityClass{

	String testCaseDescription = "Testing flow of Cancelling Purchase order fully";

	@Test(retryAnalyzer = RetryanalyserClass.class)
	public void cancel_po_fully_Test() throws Exception{
		
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

		//getCommonAction().userLogOut();

		//getCommonAction().userLoginPage("user", "Login for Canceling Order");
		String verifyCancelText = getCommonAction().poFullCancel(PO_master_page);
		System.out.println("Cancel text captured is " + verifyCancelText);
		getCommonAction().checkAssertString(verifyCancelText, "Canceled", "Validation of cancel_po_fully_Test");
		
		/*
		//Step 2 => Canceling the PO fully
		poFullCancel(PO_master_page);

		//Step 3 => Verifying the cancel message in comments section
		String verifyCancelText = verifyCancelCommentPO(PO_master_page);
		System.out.println("Cancel text captured is "+ verifyCancelText );
		getCommonAction().checkAssertString(verifyCancelText, "Canceled", "Validation of cancel_po_fully_Test");*/
	}
	//Main test methods is over Here
	// Common Method for PO canceled flow
	//Start
	/*private void poFullCancel(PO_MasterModule_OMS_MedikabazaarPage PO_master_page)
	{	
		//getSearch_SO(getGloabalData("SO_ID"));
		//customClick(PO_master_page.getView_purchase_order_button()) ;
		customClick(PO_master_page.getCancel_po_button());
	
		customClick(PO_master_page.getPo_qty_selectAll());
		customClearText(PO_master_page.getPo_cancel_comment());
		customSendText(PO_master_page.getPo_cancel_comment(),"Fully cancelling PO");
		//customClick(PO_master_page.getSumbit_button());
		getHelperClass().clickOnSubmitButton(PO_master_page.getSumbit_button());		
	}

	public String verifyCancelCommentPO(PO_MasterModule_OMS_MedikabazaarPage PO_master_page)
	{
		System.out.println("Control under verify cancel text function");
		
		waitForVisibleElement(PO_master_page.getPO_status_and_comment_bucket(),40);
		System.out.println("Visible of element");
		customClick(PO_master_page.getPO_status_and_comment_bucket());
		System.out.println("click on status_and_comment_bucket");
		clickOnSubMenu(PO_master_page.getPO_status_and_comment_bucket(), PO_master_page.getCanceled_text(), 10);
		System.out.println("Menu click on status_and_comment_bucket");
		
		//waitForVisibleElement(PO_master_page.getCanceled_text(), 40);
		
		takeScreenShort("PO Status After Canceled ","PO Status After Canceled");
		String cancelText = PO_master_page.getCanceled_text().getText();
		return cancelText;
	}*/
}  //END