package com.OMS.PO;

import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ObjectRepo.Create_PO_OMS_MedikabazaarPage;
import com.ObjectRepo.Getting_Started_OMS_MedikabazaarPage;
import com.ObjectRepo.Inward_Inventory_Quantity_OMS_MedikabazaarPage;
import com.ObjectRepo.PO_MasterModule_OMS_MedikabazaarPage;
import com.ObjectRepo.PO_OMS_MedikabazaarPage;
import com.ObjectRepo.Purchase_Order_OMS_MedikabazaarPage;
import com.oms.GenericLib.BaseUtilityClass;
import com.oms.GenericLib.IAutoconstant;
import com.oms.GenericLib.RetryanalyserClass;

@Listeners(com.oms.GenericLib.MylistenerClass.class)
public class TC_002_PO_End_to_End_Test extends BaseUtilityClass {

	String testCaseDescription = "Testing flow of Purchase order, 1)Create Purchase Order, 2) Verification from Procure and Finance, 3) Inward Stock, 4) RollbackGRN, 5) Calculation of Discount";

	/**
	 * @throws Throwable
	 */
	@Test(retryAnalyzer = RetryanalyserClass.class)
	public void purchase_order_End_to_End_Test() throws Throwable {

		Getting_Started_OMS_MedikabazaarPage homepage = new Getting_Started_OMS_MedikabazaarPage(getWebDriver());
		Purchase_Order_OMS_MedikabazaarPage puchase_order = new Purchase_Order_OMS_MedikabazaarPage(getWebDriver());
		Create_PO_OMS_MedikabazaarPage create_PO_page = new Create_PO_OMS_MedikabazaarPage(getWebDriver());
		PO_OMS_MedikabazaarPage PO_created_page = new PO_OMS_MedikabazaarPage(getWebDriver());
		PO_MasterModule_OMS_MedikabazaarPage PO_master_page = new PO_MasterModule_OMS_MedikabazaarPage(getWebDriver());

		Inward_Inventory_Quantity_OMS_MedikabazaarPage inward_Qty = new Inward_Inventory_Quantity_OMS_MedikabazaarPage(
				getWebDriver());
		String currentClassName = this.getClass().getSimpleName();
		setTestCaseAndDescriptionInReport("PO", currentClassName, testCaseDescription);

		test.info("Home page is opened");
		getCommonAction().product_sku = getFileLibClass().getDataFromExcell("PO_Data", "Product SKU",
				IAutoconstant.EXCEL_TestData);
		getCommonAction().cdcName = getFileLibClass().getDataFromExcell("PO_Data", "CDC", IAutoconstant.EXCEL_TestData);

		// Step 1 => Get Initial Count of Stock in StockManagement
		String InitialCount = getCommonAction().getCDStockCount(getCommonAction().product_sku,
				getCommonAction().cdcName);
		getCommonAction().initialCDCcount = Integer.parseInt(InitialCount);
		Reporter.log("CDC initially stock is " + getCommonAction().initialCDCcount, true);
		test.info("CDC initially stock is " + getCommonAction().initialCDCcount);

		// Step 2 => Creating Purchase Order.
		getCommonAction().createPurchaseOrder(homepage, puchase_order, create_PO_page, PO_created_page, true);
		getCommonAction().userLogOut();

		// Step 3 => =============Approval From Procure [approve , reject]
		String procureApproveStatus = getFileLibClass().getDataFromExcell("PO_Data", "procureApproveStatus",
				IAutoconstant.EXCEL_TestData);
		if (procureApproveStatus.equals("approve")) {
			getCommonAction().procureApproveProcess("PO", "approve");
		} else if (procureApproveStatus.equals("reject")) {
			getCommonAction().procureApproveProcess("PO", "reject");
		}
		getCommonAction().userLogOut();
		// Step 4 => ============Approval From Finance [approve , reject]
		String financeApproveStatus = getFileLibClass().getDataFromExcell("PO_Data", "financeApproveStatus",
				IAutoconstant.EXCEL_TestData);
		if (procureApproveStatus.equals("approve") && financeApproveStatus.equals("approve")) {
			getCommonAction().financeApproveProcess("PO", "approve");
		} else if (financeApproveStatus.equals("reject") && procureApproveStatus.equals("approve")) {
			getCommonAction().financeApproveProcess("PO", "reject");
		}
		getCommonAction().userLogOut();
		// Step 5 => User Inward Order
		String userInwardOrderStatus = getFileLibClass().getDataFromExcell("PO_Data", "userInwardOrderStatus",
				IAutoconstant.EXCEL_TestData);

		if (userInwardOrderStatus.equals("true") && procureApproveStatus.equals("approve")
				&& financeApproveStatus.equals("approve")) {
			getCommonAction().userInwardOrder(PO_master_page, inward_Qty);
		}
		// Step 6 => Count after Inward
		String afterInwardCount = getCommonAction().getCDStockCount(getCommonAction().product_sku,
				getCommonAction().cdcName);
		test.info("After Inwarded Count is  " + getStrongText(afterInwardCount));
		System.out.println("*******" + afterInwardCount + "*******");

		// Step 7 => GRN RollBack
		getCommonAction().searchByPO_ID(getGloabalData("PO_ID"));
		PO_master_page.getPO_inward_history_bucket().click();
		String executeRollbackGRN = getFileLibClass().getDataFromExcell("PO_Data", "executeRollbackGRN",
				IAutoconstant.EXCEL_TestData);
		if (executeRollbackGRN.equals("true") & procureApproveStatus.equals("approve")
				&& financeApproveStatus.equals("approve") && userInwardOrderStatus.equals("true")) {
			getCommonAction().rollBackGRN(PO_master_page, executeRollbackGRN);
		}
		// Step 8 => Count after RollBack
		String afterRollBack_count = getCommonAction().getCDStockCount(getCommonAction().product_sku,
				getCommonAction().cdcName);
		test.info("After RollBack Count is  " + getStrongText(afterRollBack_count));
		System.out.println("*******" + afterRollBack_count + "*******");

		// Validation of Test Case , Approval of Procure
		getCommonAction().checkAssertString_Empty(getGloabalData("procurePO_Approved"),
				"Validation for Procure Approval");

		// Validation of Test Case , Approval of Finance
		getCommonAction().checkAssertString_Empty(getGloabalData("financePO_Approved"),
				"Validation for Finance Approval");

		// Step 9 => Validation of Test Case
		String FinalCount = afterRollBack_count;
		getCommonAction().checkAssertString(FinalCount, InitialCount, "Validation of Purchase_Order_End_to_End_Test ");
		
		String verifyCancelText = getCommonAction().poFullCancel(PO_master_page);
		System.out.println("Cancel text captured is " + verifyCancelText);
		getCommonAction().checkAssertString(verifyCancelText, "Canceled", "Validation of cancel_po_fully_Test");
	}
}