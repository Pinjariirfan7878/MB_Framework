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

//@Listeners(com.oms.GenericLib.MylistenerClass.class)
public class TC_004_PO_RejectByProcurement_Test extends BaseUtilityClass {

	String testCaseDescription = "Testing flow of Purchase order, Create Purchase order, Rejected By PO Supervisor( Procure ). ";

	@Test(retryAnalyzer = RetryanalyserClass.class)
	public void create_po_RejectedBy_procurement_Test() throws Exception {
		Getting_Started_OMS_MedikabazaarPage homepage = new Getting_Started_OMS_MedikabazaarPage(getWebDriver());
		Purchase_Order_OMS_MedikabazaarPage puchase_order = new Purchase_Order_OMS_MedikabazaarPage(getWebDriver());
		Create_PO_OMS_MedikabazaarPage create_PO_page = new Create_PO_OMS_MedikabazaarPage(getWebDriver());
		PO_OMS_MedikabazaarPage PO_created_page = new PO_OMS_MedikabazaarPage(getWebDriver());
		PO_MasterModule_OMS_MedikabazaarPage PO_master_page = new PO_MasterModule_OMS_MedikabazaarPage(getWebDriver());

		String currentClassName = this.getClass().getSimpleName();
		setTestCaseAndDescriptionInReport("PO", currentClassName, testCaseDescription);

		test.info("Home page is opened");

		getCommonAction().product_sku = getFileLibClass().getDataFromExcell("PO_Data", "Product SKU",
				IAutoconstant.EXCEL_TestData);
		getCommonAction().cdcName = getFileLibClass().getDataFromExcell("PO_Data", "CDC", IAutoconstant.EXCEL_TestData);

		// Step 1 => Creating Purchase Order.
		getCommonAction().createPurchaseOrder(homepage, puchase_order, create_PO_page, PO_created_page, false);
		getCommonAction().userLogOut();
		// Step 2 => =============Approval From Procure [approve , reject]
		getCommonAction().procureApproveProcess("PO", "reject");

		// Validation of Test Case
		String Rejected_By_PO_Supervisor = getGloabalData("procurePO_Rejected");
		getCommonAction().checkAssertString(Rejected_By_PO_Supervisor, "Rejected By PO Supervisor",
				"Validation of create_po_RejectedBy_procurement_Test ");

		getCommonAction().userLogOut();

		getCommonAction().userLoginPage("user", "Login for Canceling Order");
		String verifyCancelText = getCommonAction().poFullCancel(PO_master_page);
		System.out.println("Cancel text captured is " + verifyCancelText);
		getCommonAction().checkAssertString(verifyCancelText, "Canceled", "Validation of cancel_po_fully_Test");
	}
}
