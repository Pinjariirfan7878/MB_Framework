package com.oms.GenericLib;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.ObjectRepo.Create_PO_OMS_MedikabazaarPage;
import com.ObjectRepo.Create_Quote_and_Order_OMS_Medikabazaar_Page;
import com.ObjectRepo.Customer_Orders_OMS_Medikabazaar_Page;
import com.ObjectRepo.Edit_Stock_OMS_Medikabazaar;
import com.ObjectRepo.Getting_Started_OMS_MedikabazaarPage;
import com.ObjectRepo.Inward_Inventory_Quantity_OMS_MedikabazaarPage;
import com.ObjectRepo.PO_MasterModule_OMS_MedikabazaarPage;
import com.ObjectRepo.PO_OMS_MedikabazaarPage;
import com.ObjectRepo.Pick_And_Pack_OMS_Medikabazaar_Page;
import com.ObjectRepo.Pick_And_Pack_View_OMS_Medikabazaar_Page;
import com.ObjectRepo.Purchase_Order_OMS_MedikabazaarPage;
import com.ObjectRepo.Sales_Order_Shipment_OMS_Medikabazaar_Page;
import com.ObjectRepo.Shipment_Status_OMS_Medikabazaar_Page;
import com.ObjectRepo.Stock_Management_OMS_Medikabazaar_Page;
import com.ObjectRepo.Success_OMS_Medikabazaar_Page;
import com.ObjectRepo.Update_Finance_Details_OMS_Medikabazaar_Page;
import com.ObjectRepo.loginPage;
import com.aventstack.extentreports.MediaEntityBuilder;

public class CommonActionClass extends BaseUtilityClass {

	// ===============Common Methods=================

	public String getCDStockCount(String Product, String cdcName) {
		Stock_Management_OMS_Medikabazaar_Page stock_mgmt = new Stock_Management_OMS_Medikabazaar_Page(getWebDriver());
		Getting_Started_OMS_MedikabazaarPage getting_started = new Getting_Started_OMS_MedikabazaarPage(getWebDriver());

		customClick(getting_started.get_Operations_button());
		try {
			customClick(getting_started.get_Stock_Management_button());
		} catch (Exception e) {
			customClick(getting_started.get_Operations_button());
			customClick(getting_started.get_Stock_Management_button());
		}

		customClick(stock_mgmt.get_Warehouse_Name());
		getWebdriverAction().select_dd_by_visibletext(cdcName, stock_mgmt.get_Warehouse_Name());
		customSendText(stock_mgmt.get_sku_field(), Product);

		getWebdriverAction().thread_sleep(500);
		customClick(stock_mgmt.get_search_button());
		getWebdriverAction().thread_sleep(3000);
		String Product_count = "";
		// System.out.println("...........");
		try {
			WebElement ele = getWebDriver()
					.findElement(By.xpath("(//a[contains(text(),'" + Product + "')]/../following-sibling::td)[2]"));
			highLight(ele);
			customClick(ele);
			Product_count = ele.getText();
			addGlobalData(Product_count, "Product_count");
			takeScreenShort(
					"Stock Management , In Special Stock Update Product Stock = " + getStrongText(Product_count),
					"Stock Management , In Special Stock Update Product Stock = " + Product_count);
		} catch (Exception e) {
			System.out.println("E=" + e);

		}
		return Product_count;
	}

	public void userLogOut() {
		Getting_Started_OMS_MedikabazaarPage homePage = new Getting_Started_OMS_MedikabazaarPage(getWebDriver());
		customClick(homePage.getUser_logo());
		customClick(homePage.getLogout_button());
	}

	public static int userLoginCount = 0;

	public String userLoginPage(String userRole, String msg) {
		loginPage log_page = new loginPage(getWebDriver());
		String userName = "";
		String userPassword = "";

		switch (userRole) {
		case "user":
			userName = "Username" + WebEnvironment;
			userPassword = "Password" + WebEnvironment;
			break;

		case "procure":
			userName = "procure_username" + WebEnvironment;
			userPassword = "procure_password" + WebEnvironment;
			break;

		case "finance":
			userName = "finance_username" + WebEnvironment;
			userPassword = "finance_password" + WebEnvironment;
			break;

		default:
			userName = "";
			userPassword = "";
		}

		String loginUserName = "";
		String loginUserPassword = "";
		if (!userName.equals("") && !userPassword.equals("")) {

			try {
				loginUserName = getFileLibClass().readPropertyData(PROP_PATH, userName);
				loginUserPassword = getFileLibClass().readPropertyData(PROP_PATH, userPassword);

				if (WebEnvironment.equals("ProtaDev")) {
					WebElement loginButton = getWebDriver().findElement(By.xpath("//button[contains(text(),'Login')]"));
					customClick(loginButton);
				}

				log_page.getLogin_app(loginUserName, loginUserPassword);

				if (userLoginCount > 0) {
					test.info(msg + " by " + getStrongText(loginUserName));
				}
				userLoginCount++;
				getWebdriverAction().maximumWindow();
				getWebdriverAction().implicitlywait();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return loginUserName;
	}

	public void procureApproveProcess(String flowName, String verificationStatus) {
		// PO => Purchase Order || SO => Sales Order [ approve , reject]
		PO_MasterModule_OMS_MedikabazaarPage PO_master_page = new PO_MasterModule_OMS_MedikabazaarPage(getWebDriver());
		Sales_Order_Shipment_OMS_Medikabazaar_Page Sales_Order_Shipment = new Sales_Order_Shipment_OMS_Medikabazaar_Page(
				getWebDriver());

		userLoginPage("procure", "Logged in with Procurement Member to Approve " + flowName + " at First Level");
		String comment_textfield = "";

		String verificationStatusDropDownText = "";
		if (flowName.equals("PO")) {

			verificationStatusDropDownText = (verificationStatus.equals("approve")) ? "Approved By PO Supervisor"
					: "Rejected By PO Supervisor";
			searchByPO_ID(getGloabalData("PO_ID"));
			getWebdriverAction().select_dd_by_visibletext(verificationStatusDropDownText,
					PO_master_page.getVerification_status_dd());
			comment_textfield = getFileLibClass().getDataFromExcell("PO_Data", "Comment Textfield",
					IAutoconstant.EXCEL_TestData);

			customSendText(PO_master_page.getComment_textfield(), comment_textfield);

			getHelperClass().clickOnSubmitButton(PO_master_page.getSumbit_button());

			customClick(PO_master_page.getPO_status_and_comment_bucket());

			if (verificationStatus.equals("approve")) {
				clickOnSubMenu(PO_master_page.getPO_status_and_comment_bucket(),
						PO_master_page.getProcure_approved_text(), 2);
				String statusText = PO_master_page.getProcure_approved_text().getText();
				addGlobalData("procurePO_Approved", statusText);
			} else {
				clickOnSubMenu(PO_master_page.getPO_status_and_comment_bucket(),
						PO_master_page.getRejected_By_PO_Supervisor_text(), 2);
				String statusText = PO_master_page.getRejected_By_PO_Supervisor_text().getText();
				addGlobalData("procurePO_Rejected", statusText);
			}

		} else if (flowName.equals("SO")) {
			verificationStatusDropDownText = (verificationStatus.equals("approve")) ? "Approved By Procurement"
					: "Rejected By Procurement";
			getSearch_SO(getGloabalData("SO_ID"));

			customClick(Sales_Order_Shipment.getLogistic_details());
			getWebdriverAction().select_dd_by_visibletext(verificationStatusDropDownText,
					PO_master_page.getVerification_status_dd());

			if (verificationStatusDropDownText.equals("Rejected By Procurement")) {
				comment_textfield = getFileLibClass().getDataFromExcell("SO_Data", "procureRejected_comment",
						IAutoconstant.EXCEL_TestData);
				customSendText(PO_master_page.getComment_textfield(), comment_textfield);
			}

			customClick(PO_master_page.getSumbit_button());
			customClick(Sales_Order_Shipment.getActivitySummaryHeader());

			if (verificationStatus.equals("approve")) {
				clickOnSubMenu(Sales_Order_Shipment.getActivitySummaryHeader(),
						Sales_Order_Shipment.getApproved_by_proc_text(), 2);
				customClick(Sales_Order_Shipment.getApproved_by_proc_text());
				String statusText = Sales_Order_Shipment.getApproved_by_proc_text().getText();
				addGlobalData("procureSO_Approved", statusText);
			} else {
				clickOnSubMenu(Sales_Order_Shipment.getActivitySummaryHeader(),
						Sales_Order_Shipment.getReject_Status_text(), 2);
				customClick(Sales_Order_Shipment.getReject_Status_text());
				String statusText = Sales_Order_Shipment.getReject_Status_text().getText();
				addGlobalData("procureSO_Rejected", statusText);
			}
		}

		if (verificationStatus.equals("approve")) {
			if (PO_master_page.getStatusWhilCommentedHeader().isDisplayed()) {
				takeScreenShort(getStrongText(FlowNAME + " Approved By Procurement"),
						"Step for " + flowName + "  Approved By Procurement And Verify Passed ");
			} else {
				takeScreenShort(getStrongText(FlowNAME + " is not Approved By Procurement"),
						"Step for " + flowName + "is Not Approve By Procurement And Verify Failed");
			}
		} else if (verificationStatus.equals("reject")) {
			if (PO_master_page.getStatusWhilCommentedHeader().isDisplayed()) {
				takeScreenShort(getStrongText("In " + flowName + " Rejected By Procurement"),
						"Step for " + flowName + "  Rejected By Procurement And Verify Passed ");
			} else {
				takeScreenShort(getStrongText("In " + flowName + " is not Rejected By Procurement"),
						"Step for " + flowName + "is Not Rejecte By Procurement And Verify Failed");
			}
		}
	}

	public void financeApproveProcess(String flowName, String verificationStatus) {
		PO_MasterModule_OMS_MedikabazaarPage PO_master_page = new PO_MasterModule_OMS_MedikabazaarPage(getWebDriver());
		Sales_Order_Shipment_OMS_Medikabazaar_Page Sales_Order_Shipment = new Sales_Order_Shipment_OMS_Medikabazaar_Page(
				getWebDriver());
		// [ approve , reject]
		userLoginPage("finance", "Logged in with Finance Member to Approve " + flowName + " at Second Level");
		String comment_textfield = "";
		String verificationStatusDropDownText = "";

		if (flowName.equals("PO")) {
			verificationStatusDropDownText = (verificationStatus.equals("approve")) ? "Approved By Finance"
					: "Rejected By Finance";

			searchByPO_ID(getGloabalData("PO_ID"));
			getWebdriverAction().select_dd_by_visibletext(verificationStatusDropDownText,
					PO_master_page.getVerification_status_dd());
			comment_textfield = getFileLibClass().getDataFromExcell("PO_Data", "Comment Textfield",
					IAutoconstant.EXCEL_TestData);
			customSendText(PO_master_page.getComment_textfield(), comment_textfield);

			getHelperClass().clickOnSubmitButton(PO_master_page.getSumbit_button());

			customClick(PO_master_page.getPO_status_and_comment_bucket());
			customClick(PO_master_page.getStatusWhilCommentedHeader());

			if (verificationStatus.equalsIgnoreCase("approve")) {
				clickOnSubMenu(PO_master_page.getStatusWhilCommentedHeader(), PO_master_page.getFinance_approve_text(),
						2);
				String approve_finance_text = PO_master_page.getFinance_approve_text().getText();
				addGlobalData("financePO_Approved", approve_finance_text);
				takeScreenShort(getStrongText(" Approved By Finance"),
						"Step:3 P.O Approved By Finance And Verify Passed");
			} else {
				clickOnSubMenu(PO_master_page.getStatusWhilCommentedHeader(),
						PO_master_page.getRejected_By_Finance_text(), 2);
				String Rejected_By_Finance = PO_master_page.getRejected_By_Finance_text().getText();
				addGlobalData("financePO_Rejected", Rejected_By_Finance);
				takeScreenShort(getStrongText("In " + "PO" + " Rejected By Finance"),
						"Step:3 P.O Rejected By Finance And Verify Passed");
			}

		} else if (flowName.equals("SO")) {
			verificationStatusDropDownText = (verificationStatus.equals("approve")) ? "Approved By Finance"
					: "Rejected By Finance";
			getSearch_SO(getGloabalData("SO_ID"));
			customClick(Sales_Order_Shipment.getLogistic_details());
			getWebdriverAction().select_dd_by_visibletext(verificationStatusDropDownText,
					PO_master_page.getVerification_status_dd());

			if (verificationStatusDropDownText.contains("Rejected By Finance")) {
				getWebdriverAction().select_dd_by_visibletext("Credit Terms not mentioned",
						getWebDriver().findElement(By.xpath("//select[@id='comment_rejected']")));

				comment_textfield = getFileLibClass().getDataFromExcell("SO_Data", "financeRejected_comment",
						IAutoconstant.EXCEL_TestData);
				customSendText(PO_master_page.getComment_textfield(), comment_textfield);
			}
			customClick(PO_master_page.getSumbit_button());

			clickOnSubMenu(PO_master_page.getActivitySummary(), PO_master_page.getStatusWhilCommentedHeader(), 5);
			if (verificationStatus.equals("approve")) {
				if (PO_master_page.getStatusWhilCommentedHeader().isDisplayed()) {

					getWebdriverAction().refresh();
					PO_master_page.getActivitySummary().click();
					// getWebdriverAction().thread_sleep(500);
					waitUsingThread(5);
					takeScreenShort(getStrongText(" Approved By Finance"),
							"Step:3 P.O Approved By Finance And Verify Passed");
				} else {
					takeScreenShort(getStrongText("In " + flowName + " is not Approved By Finance"),
							"Step:3 " + flowName + " is Not Approved By Finance And Verify Failed");
				}
				customClick(PO_master_page.getFinance_approve_text());
				String approve_finance_text = PO_master_page.getFinance_approve_text().getText();
				addGlobalData("financeSO_Approved", approve_finance_text);
			} else if (verificationStatus.equals("reject")) {
				if (PO_master_page.getStatusWhilCommentedHeader().isDisplayed()) {

					takeScreenShort(getStrongText("In " + flowName + " Rejected By Finance"),
							"Step:3 P.O Rejected By Finance And Verify Passed");
				} else {
					takeScreenShort(getStrongText("In " + flowName + " is not Rejected By Finance"),
							"Step:3 " + flowName + " is Not Rejected By Finance And Verify Failed");
				}
				customClick(PO_master_page.getRejected_By_Finance_text());
				String Rejected_By_Finance = PO_master_page.getRejected_By_Finance_text().getText();
				addGlobalData("financeSO_Rejected", Rejected_By_Finance);
			}
		}
	}

	public void toastMessage() {
		WebElement ele = getWebDriver().findElement(By.xpath("//div[@class='toast-message']"));
		System.out.println("Toast message For adding = " + ele.getText());
		customClick(ele);
	}
	// ==============PO==================

	public void searchByPO_ID(String pOId) {
		Purchase_Order_OMS_MedikabazaarPage purchase_order = new Purchase_Order_OMS_MedikabazaarPage(getWebDriver());
		Getting_Started_OMS_MedikabazaarPage homePage = new Getting_Started_OMS_MedikabazaarPage(getWebDriver());
		customClick(homePage.getPurchase_tab());
		customClick(homePage.getPurchase_order_option());
		customClick(purchase_order.getPO_master_item_bucket());

		customSendText(purchase_order.getPOidTextField(), pOId);
		customClick(purchase_order.getSearch_button());
		customClick(purchase_order.getFirst_PO_Id());
	}

	public int initialCDCcount = 0, Po_Qty = 0;
	public String PO_number = "", product_sku = "", cdcName = "", skuQuantity = "";

	/**
	 * checkAssertDouble(double actualValue , double expectedValue , String msg)
	 * <br/>
	 * this method have softAssert , which use for checking ActualValue and
	 * ExpectedValue <br/>
	 * 1) actualValue => this value come from WebSite <br/>
	 * 2) expectedValue => from DataFile or Calculation <br/>
	 * 3) msg => Message write into Report
	 */
	public void checkAssertDouble(double actualValue, double expectedValue, String msg) {
		if (actualValue == expectedValue) {
			softassert.assertEquals(isSuccess, true, msg + " is successful passed");
			String msgStatus = "Assertion of " + getStrongText(msg) + " is pass ," + " value = "
					+ getStrongText("" + actualValue);
			test.info("<span style='background-color:#32CD32; color:white'>" + msgStatus + "</span>");
		} else {
			isSuccess = false;
			softassert.assertEquals(isSuccess, true, msg + " is failed");
			String msgStatus = "Assertion of " + getStrongText(msg) + " is fail , " + " Actual value = "
					+ getStrongText("" + actualValue) + " , Expected Value = " + getStrongText("" + expectedValue);
			test.info("<span style='background-color:red; color:white'>" + msgStatus + "</span>");
		}
	}

	/**
	 * checkAssertString((String actualValue , String expectedValue , String msg)
	 * <br/>
	 * this method have softAssert , which use for checking ActualValue and
	 * ExpectedValue <br/>
	 * 1) actualValue => this value come from execution <br/>
	 * 2) expectedValue => hard coded expected value <br/>
	 * 3) msg => Message write into Report
	 */
	public void checkAssertString(String actualValue, String expectedValue, String msg) {
		if (actualValue.equalsIgnoreCase(expectedValue)) {
			softassert.assertEquals(isSuccess, true, msg + " is successful passed");
			String msgStatus = "Assertion of " + getStrongText(msg) + " is pass ," + " value = "
					+ getStrongText("" + actualValue);
			test.info("<span style='background-color:#32CD32; color:white'>" + msgStatus + "</span>");
		} else {
			isSuccess = false;
			softassert.assertEquals(isSuccess, true, msg + " is failed");
			String msgStatus = "Assertion of " + getStrongText(msg) + " is fail , " + " Actual value = "
					+ getStrongText("" + actualValue) + " , Expected Value=" + getStrongText("" + expectedValue);
			test.info("<span style='background-color:red; color:white'>" + msgStatus + "</span>");
		}
	}

	/**
	 * checkAssertString_Empty(String value) <br/>
	 * this method have softAssert , which use for checking Value is empty <br/>
	 * 1) value => this value come from execution <br/>
	 * 2) msg => Message write into Report
	 */
	public void checkAssertString_Empty(String value, String msg) {
		if (value.isEmpty()) {
			softassert.assertEquals(isSuccess, false, " is Failed");
			String msgStatus = "Assertion is " + msg + "Failed ," + " value = " + getStrongText("" + value);
			test.info("<span style='background-color:red; color:white'>" + msgStatus + "</span>");

		} else {
			isSuccess = true;
			softassert.assertEquals(isSuccess, true, " is Passed");
			String msgStatus = "Assertion is " + msg + "Passed , " + " Actual value = " + getStrongText("" + value);
			test.info("<span style='background-color:#32CD32; color:white'>" + msgStatus + "</span>");

		}
	}

	/**
	 * checkAssertint((int actualValue , int expectedValue , String msg) <br/>
	 * this method have softAssert , which use for checking ActualValue and
	 * ExpectedValue <br/>
	 * 1) actualValue => this value come from execution <br/>
	 * 2) expectedValue => hard coded expected value <br/>
	 * 3) msg => Message write into Report
	 */
	public void checkAssertint(int actualValue, int expectedValue, String msg) {
		if (actualValue == expectedValue) {
			softassert.assertEquals(isSuccess, true, msg + " is successful passed");
			String msgStatus = "Assertion of " + getStrongText(msg) + " is pass ," + " value = "
					+ getStrongText("ActualValue and expectedValue are same");
			test.info("<span style='background-color:#32CD32; color:white'>" + msgStatus + "</span>");
		} else {
			isSuccess = false;
			softassert.assertEquals(isSuccess, true, msg + " is failed");
			String msgStatus = "Assertion of " + getStrongText(msg) + " is fail , " + " Actual value = "
					+ getStrongText("" + actualValue) + " , Expected Value=" + getStrongText("" + expectedValue);
			test.info("<span style='background-color:red; color:white'>" + msgStatus + "</span>");
		}
	}

	public void createPurchaseOrder(Getting_Started_OMS_MedikabazaarPage homepage,
			Purchase_Order_OMS_MedikabazaarPage puchase_order, Create_PO_OMS_MedikabazaarPage create_PO_page,
			PO_OMS_MedikabazaarPage PO_created_page, boolean isCalculationDisplay) {

		customClick(homepage.getPurchase_tab());
		customClick(homepage.getPurchase_order_option());
		test.info("Purchase Order Listing page is opened");

		customClick(puchase_order.getCreate_Purchase_Order_button());

		test.info("Create Purchase Order page is opened");
		waitForVisibleElement(create_PO_page.getSupplier_email_textfield(), 15);
		customClearText(create_PO_page.getSupplier_email_textfield());
		String supplier_email = getFileLibClass().getDataFromExcell("PO_Data", "Supplier Email",
				IAutoconstant.EXCEL_TestData);

		String flatDiscount = getFileLibClass().getDataFromExcell("PO_Data", "flatDiscount",
				IAutoconstant.EXCEL_TestData);

		customSendText(create_PO_page.getSupplier_email_textfield(), supplier_email);
		customClick(create_PO_page.getSubmit_button());

		// dropdown
		waitForVisibleElement(create_PO_page.getPayment_terms_DD(), 15);
		getWebdriverAction().select_dd_by_index(create_PO_page.getPayment_terms_DD(), 3);

		WebElement relationshipManager = getWebDriver().findElement(By.xpath("//select[@id='bde']"));
		getWebdriverAction().select_dd_by_visibletext("Irfan Pinjari", relationshipManager);

		customSendText(create_PO_page.getProduct_sku_textfield(), product_sku);
		String PO_Qty = getFileLibClass().getDataFromExcell("PO_Data", "PO_Qty", IAutoconstant.EXCEL_TestData);
		Po_Qty = Integer.parseInt(PO_Qty);

		waitForVisibleElement(create_PO_page.getQuantity_textfield(), 15);
		customClearText(create_PO_page.getQuantity_textfield());
		customSendText(create_PO_page.getQuantity_textfield(), "" + PO_Qty);

		customClick(create_PO_page.getAdd_product_button());
		getWebdriverAction().scrolldown_By_element(create_PO_page.getProduct_sku_textfield());

		String supplierPrice = getFileLibClass().getDataFromExcell("PO_Data", "Supplier Price",
				IAutoconstant.EXCEL_TestData);

		customSendText(create_PO_page.getSkuSupplierPrice(), supplierPrice);

		// Here SKU Product details
		String HSN_Code = getFileLibClass().getDataFromExcell("PO_Data", "HSN Code", IAutoconstant.EXCEL_TestData);
		customSendText(create_PO_page.getHSN_Code_textfield(), HSN_Code);

		String skuDisPer = getFileLibClass().getDataFromExcell("PO_Data", "skuDiscountPercentage",
				IAutoconstant.EXCEL_TestData);
		// If flatDiscoiunt is not Equals to Zero than skuDisPer will be Zero
		if (!flatDiscount.trim().equals("0")) {
			skuDisPer = "0";
		}

		WebElement skuPer = getWebDriver().findElement(By.xpath("//input[@id='discount_percent']"));
		customClick(skuPer);
		customSendText(skuPer, skuDisPer);
		// Only this values are Present in TaxDorpDown 0,5,12,18,24,28

		String taxPer = getFileLibClass().getDataFromExcell("PO_Data", "taxPercentage", IAutoconstant.EXCEL_TestData);
		WebElement taxDropDown = getWebDriver().findElement(By.xpath("//select[@id='tax_percent']"));
		getWebdriverAction().select_dd_by_visibletext(taxPer, taxDropDown);

		customClick(create_PO_page.getFirstIteam_check_box());
		customClick(create_PO_page.getUpdate_cart_button());
		test.info("SKU Items Details, in the Cart is Updated");

		WebElement flatDiscountAmt = null;
		WebElement flatDiscountApply = null;

		// Here Flat Discount Or Shipping Charges will Apply
		String shipping_charge = getFileLibClass().getDataFromExcell("PO_Data", "Shipping Charge",
				IAutoconstant.EXCEL_TestData);
		if (!(flatDiscount.trim().equals("0"))) {
			flatDiscountAmt = getWebDriver().findElement(By.xpath("//input[@name='flat_discount']"));
			flatDiscountApply = getWebDriver().findElement(By.xpath("//button[contains(text(),'Apply')]"));

			customSendText(flatDiscountAmt, flatDiscount);
			customClick(flatDiscountApply);
		} else {

			// Shipping Charges
			customClearText(create_PO_page.getShipping_charge_textfield());
			customSendText(create_PO_page.getShipping_charge_textfield(), shipping_charge);
			customClick(create_PO_page.getShipping_update_button());
		}

		// Here Calculation Part1

		if (isCalculationDisplay) { // only Calculate when it is true
			// 1)Verify here for subTotal
			getHelperClass().calculationOfDiscount_PO(create_PO_page, PO_Qty, flatDiscount, skuDisPer, shipping_charge,
					taxPer);

		}

		scrollIntoView(create_PO_page.getSkuSupplierPrice());
		test.info("Grand Total = " + getStrongText(create_PO_page.getGrandTotalText().getText()));

		customClick(create_PO_page.getCreate_purchase_order_button());

		try {
			// Alert
			WebDriverWait w = new WebDriverWait(getWebDriver(), Duration.ofSeconds(5));
			if (w.until(ExpectedConditions.alertIsPresent()) == null) {
				customClick(create_PO_page.getCreate_purchase_order_button());
				System.out.println("Alert not exists");
			} else {
				getWebdriverAction().alert_accept();
				System.out.println("Alert exists");
			}
		} catch (Exception e) {
			System.out.println("Exception of Alert =" + e);
		}

		try {
			PO_number = PO_created_page.getPO_ID().getText();
		} catch (Exception e1) {
			WebElement msg = getWebDriver().findElement(
					By.xpath("//strong[contains(text(),'Please enter a Proper Shipping, Billing Adresses')]"));

			if (msg.isDisplayed()) {
				getHelperClass().fillPO_EditShippingAndBillingAdresses();
			}

			customClick(create_PO_page.getCreate_purchase_order_button());

			try {
				// Alert
				WebDriverWait w = new WebDriverWait(getWebDriver(), Duration.ofSeconds(5));
				if (w.until(ExpectedConditions.alertIsPresent()) == null) {
					customClick(create_PO_page.getCreate_purchase_order_button());
					System.out.println("Alert not exists");
				} else {
					getWebdriverAction().alert_accept();
					System.out.println("Alert exists");
				}
			} catch (Exception e) {
				System.out.println("Exception of Alert =" + e);
			}
			PO_number = PO_created_page.getPO_ID().getText();
		}

		Reporter.log(PO_number, true);
		if (PO_created_page.getConfirm_img().isDisplayed()) {
			test.info("OrderId = " + getStrongText(getOnlyId(PO_number, "PO")));
			takeScreenShort("Purchase order is created with number " + getStrongText(PO_number),
					"Step:1 P.O Created Successfully And Verification Passed ");
			addGlobalData("PO_ID", getOnlyId(PO_number, "PO"));
		} else {
			takeScreenShort(getStrongText("Purchase order is not created"),
					"Step:1 P.O is not created Verification Failed");
		}
	}

	public void userInwardOrder(PO_MasterModule_OMS_MedikabazaarPage PO_master_page,
			Inward_Inventory_Quantity_OMS_MedikabazaarPage inward_Qty) {
		getCommonAction().userLoginPage("user", "User Login For Inward Stock");
		getCommonAction().searchByPO_ID(getGloabalData("PO_ID"));
		getWebdriverAction().scrollBy_value("0", "500");
		String invoice_number = getFileLibClass().getDataFromExcell("PO_Data", "Invoice Number",
				IAutoconstant.EXCEL_TestData);
		String invoice_amount = getFileLibClass().getDataFromExcell("PO_Data", "Invoice Amount",
				IAutoconstant.EXCEL_TestData);

		customClick(PO_master_page.getQa_check_box());
		customSendText(PO_master_page.getInvoice_number_textfield(), invoice_number);
		customClearText(PO_master_page.getInvoice_amount_textfield());
		customSendText(PO_master_page.getInvoice_amount_textfield(), invoice_amount);

		String pdf = IAutoconstant.Pdf_dummy;
		PO_master_page.getInvoice_attachment_textfield().sendKeys(pdf);

		customClick(PO_master_page.getSelect_all_checkbox());

		// customClick(PO_master_page.getSumbit_button());
		getHelperClass().clickOnSubmitButton(PO_master_page.getSumbit_button());

		getWebdriverAction().alert_accept();

		if (WebEnvironment.equals("ProtaDev")) {
			try {
				WebElement dropDownRack = getWebDriver().findElement(By.xpath("//select[@name='mbrackshelfdata']"));
				getWebdriverAction().select_dd_by_index(dropDownRack, 1);
			} catch (Exception e) {
				System.out.println("Exception e" + e);
			}

			WebElement batch = getWebDriver().findElement(By.xpath("(//td[contains(text(),'"
					+ getCommonAction().product_sku + "')]/following-sibling::td//input)[5]"));
			customSendText(batch, "Batch1");

			WebElement expDate = getWebDriver().findElement(By.xpath("(//td[contains(text(),'"
					+ getCommonAction().product_sku + "')]/following-sibling::td//input)[6]"));
			customSendText(expDate, getHelperClass().getDate(220, "yyyy-MM-dd"));

			WebElement mfgDate = getWebDriver().findElement(By.xpath("(//td[contains(text(),'"
					+ getCommonAction().product_sku + "')]/following-sibling::td//input)[7]"));
			customSendText(mfgDate, getHelperClass().getDate(0, "yyyy-MM-dd"));

		} else if (WebEnvironment.equals("Oms")) {
			String date = getHelperClass().getDate(320, "yyyy-MM-dd");
			System.out.println("Date=" + date);
			customSendText(inward_Qty.getExp_date_textField(), date);
		}

		getHelperClass().clickOnSubmitButton(PO_master_page.getSumbit_button());
		// customClick(PO_master_page.getSumbit_button());

		customClick(PO_master_page.getPO_inward_history_bucket());

		customClick(PO_master_page.getAccepet_order_text());
		// String flowName ="PO" ;
		if (PO_master_page.getAccepet_order_text().isDisplayed()) {
			takeScreenShort(getStrongText("Stock Inwarded Successfully"),
					"Step:4 Stock Inwarded Successfully And Verify Passed ");
		} else {
			takeScreenShort(getStrongText("Stock is not Inward"), "Step:4 Stock Inward is Not Done and Verify Failed ");
		}
		String PO_Number = PO_master_page.getPO_number().getText();
		String GRN_Number = PO_master_page.getGRN_number().getText();
		test.info("GRN number = " + getStrongText(getOnlyId(GRN_Number, "GRN")));
		Reporter.log("PO ID : " + PO_Number + " GRN number : " + GRN_Number, true);
		test.info("After Inwarded details are : " + getStrongText(PO_Number + "_" + GRN_Number));
	}

	public void rollBackGRN(PO_MasterModule_OMS_MedikabazaarPage PO_master_page, String executeRollbackGRN) {

		PO_master_page.executeRollbackGRN(getWebDriver());
		WebElement successFullmsg = getWebDriver()
				.findElement(By.xpath("//div[contains(@class,'alert alert-success')]/strong"));

		getWebdriverAction().explicitlywait_elementtobeclickable(successFullmsg);
		String actualElementText = successFullmsg.getText();
		takeScreenShort(getStrongText(actualElementText), actualElementText);

		if (executeRollbackGRN.equals("true")) {
			Po_Qty = 0;
		}
	}

	/*
	 * public boolean getFinalCountOfStock() { String FinalCount =
	 * getCommonAction().getCDStockCount(product_sku, cdcName);
	 * 
	 * int finalCount = Integer.parseInt(FinalCount);
	 * 
	 * boolean success; System.out.println("initialCDCcount=" + initialCDCcount);
	 * System.out.println("Po_Qty=" + Po_Qty); System.out.println("Final Count=" +
	 * finalCount);
	 * 
	 * if ((initialCDCcount + Po_Qty) == finalCount) {
	 * Reporter.log("CDC Stock after Stock inward " + finalCount, true);
	 * test.info("CDC Stock after Stock inwarded " + getStrongText("" +
	 * finalCount)); success = true; } else {
	 * Reporter.log("CDC Stock is not updated after inwarded ", true);
	 * test.info("CDC Stock is not updated after inward "); success = false; }
	 * 
	 * return success; }
	 */
	// ===========So Methods================

	public void getSearch_SO(String SO_ID) {

		Customer_Orders_OMS_Medikabazaar_Page Customer_Orders = new Customer_Orders_OMS_Medikabazaar_Page(
				getWebDriver());
		Getting_Started_OMS_MedikabazaarPage homePage = new Getting_Started_OMS_MedikabazaarPage(getWebDriver());

		// waitForClickableElement(homePage.getOrder_button(),5);
		customClick(homePage.getOrder_button());
		try {
			customClick(homePage.getSalesorder_button());
		} catch (Exception e) {
			customClick(homePage.getOrder_button());
			customClick(homePage.getSalesorder_button());
		}
		customSendText(Customer_Orders.getso_order_id(), SO_ID);
		customClick(Customer_Orders.getso_search_button());
		customClick(Customer_Orders.getfirst_so_id());
	}

	public void get_Create_SO(String Customer_Email_ID, String SKU_Name, String SKU_Qty, String Shipping_Price,
			boolean isCalculationDisplay) {
		Getting_Started_OMS_MedikabazaarPage getting_started = new Getting_Started_OMS_MedikabazaarPage(getWebDriver());
		Customer_Orders_OMS_Medikabazaar_Page Customer_Orders = new Customer_Orders_OMS_Medikabazaar_Page(
				getWebDriver());
		Create_Quote_and_Order_OMS_Medikabazaar_Page Create_Quote_and_Order = new Create_Quote_and_Order_OMS_Medikabazaar_Page(
				getWebDriver());
		Success_OMS_Medikabazaar_Page Success = new Success_OMS_Medikabazaar_Page(getWebDriver());

		customClick(getting_started.getOrder_button());

		customClick(getting_started.getSalesorder_button());

		customClick(Customer_Orders.getcreate_sales_order_button());

		customSendText(Create_Quote_and_Order.getEnter_Customer_Email_ID(), Customer_Email_ID);

		customClick(Create_Quote_and_Order.getSubmit_button());

		test.info("Customer Email = " + getStrongText(Customer_Email_ID) + " is Added");

		// Clear Cart
		try {
			getWebdriverAction().scrollBy_bottom();
			if (checkWebElementPrsernt(Create_Quote_and_Order.getclear_cart_button())) {
				getWebdriverAction().scrollIntoView(Create_Quote_and_Order.gettotal_sku_added_count());
				Create_Quote_and_Order.gettotal_sku_added_count().isDisplayed();

				customClick(Create_Quote_and_Order.getclear_cart_button());
				customClick(Create_Quote_and_Order.getOK_to_clearcart_button());
				takeScreenShort("<strong>Cleared pre loaded SKU's from the Cart Before Inward CSV</strong>",
						"Cleared the cart with pre loaded SKU's Before Inward CSV");
			}
		} catch (Exception e) {
			System.out.println("E=" + e);
			System.out.println("There is NO pre loaded SKU's Before Inward CSV");
		}

		customSendText(Create_Quote_and_Order.getEnter_SKU(), SKU_Name);
		customClearText(Create_Quote_and_Order.getQuantity_Field());

		customSendText(Create_Quote_and_Order.getQuantity_Field(), SKU_Qty);
		customClick(Create_Quote_and_Order.getAdd_Product_button());

		test.info("Product Name = " + getStrongText(SKU_Name) + " , Quantity = " + getStrongText(SKU_Qty) + " Added");

		toastMessage();

		waitForClickableElement(Create_Quote_and_Order.getCheck_Box(), 20);

		String specialPriceSO = getFileLibClass().getDataFromExcell("SO_Data", "SpecialPrice",
				IAutoconstant.EXCEL_TestData);
		WebElement specialPrice = getWebDriver()
				.findElement(By.xpath("(//td[contains(text(),'" + SKU_Name + "')]/following-sibling::td)[3]//input"));
		try {
			customSendText(specialPrice, specialPriceSO);
		} catch (Exception e) {
			System.out.println("Exceptin = " + e);
		}
		String skuDiscountPerSO = getFileLibClass().getDataFromExcell("SO_Data", "SkuDiscountPercentage",
				IAutoconstant.EXCEL_TestData);

		WebElement discountPer = getWebDriver()
				.findElement(By.xpath("(//td[contains(text(),'" + SKU_Name + "')]/following-sibling::td)[6]//input"));
		customSendText(discountPer, skuDiscountPerSO);

		String skuTaxPerSO = getFileLibClass().getDataFromExcell("SO_Data", "SkuTaxPercentage",
				IAutoconstant.EXCEL_TestData);

		WebElement taxPer = getWebDriver()
				.findElement(By.xpath("(//td[contains(text(),'" + SKU_Name + "')]/following-sibling::td)[8]//select"));
		getWebdriverAction().select_dd_by_visibletext(skuTaxPerSO, taxPer);

		customClick(Create_Quote_and_Order.getCheck_Box());
		customClick(Create_Quote_and_Order.getUpdateCartButton());
		toastMessage();

		customSendText(Create_Quote_and_Order.getShipping_Price(), Shipping_Price);
		customClick(Create_Quote_and_Order.getShipping_Update_Button());
		toastMessage();

		// Calculation of Discount
		if (isCalculationDisplay) {

			WebElement subTotal = getWebDriver()
					.findElement(By.xpath("//span[contains(text(),'Subtotal')]/following-sibling::span"));
			String omsSubtotal = getHelperClass().getDecimalNumber(subTotal.getAttribute("textContent"));
			System.out.println("omsSubtotal=" + omsSubtotal);

			WebElement afterSkuDiscountPrice = getWebDriver().findElement(
					By.xpath("(//td[contains(text(),'" + SKU_Name + "')]/following-sibling::td)[10]//div"));
			String omsAfterSkuDiscountPrice = getHelperClass()
					.getDecimalNumber(afterSkuDiscountPrice.getAttribute("textContent"));
			System.out.println("omsAfterSkuDiscountPrice=" + omsAfterSkuDiscountPrice);

			WebElement totalDiscount = getWebDriver()
					.findElement(By.xpath("//span[contains(text(),'Discount')]/following-sibling::span"));
			String omsTotalDiscount = getHelperClass().getDecimalNumber(totalDiscount.getAttribute("textContent"));
			System.out.println("omsTotalDiscount=" + omsTotalDiscount);

			WebElement gst = getWebDriver()
					.findElement(By.xpath("//span[contains(text(),'GST')]/following-sibling::span"));
			String omsGst = getHelperClass().getDecimalNumber(gst.getAttribute("textContent"));
			System.out.println("omsGst=" + omsGst);

			WebElement grandTotal = getWebDriver()
					.findElement(By.xpath("//span[contains(text(),'Grand Total')]/following-sibling::span//strong"));
			String omsGrandTotal = getHelperClass().getDecimalNumber(grandTotal.getAttribute("textContent"));
			System.out.println("omsGrandTotal=" + omsGrandTotal);

			// this is by Calculation of Math

			double subTotalCal = (Double.valueOf(getHelperClass().getDecimalNumber(specialPriceSO))
					* Double.valueOf(getHelperClass().getDecimalNumber(SKU_Qty)));
			checkAssertDouble(Double.valueOf(omsSubtotal), subTotalCal, "Sub Total ");

			double disAmtCal = ((subTotalCal * Double.valueOf(getHelperClass().getDecimalNumber(skuDiscountPerSO)))
					/ 100);
			checkAssertDouble(Double.valueOf(omsTotalDiscount), disAmtCal, "Discount Amount");

			test.info(
					"Price = " + getStrongText(omsSubtotal) + ", SKU DisCount(%) = " + getStrongText(skuDiscountPerSO));

			double price = subTotalCal - disAmtCal;

			test.info("Discount of " + getStrongText(omsTotalDiscount) + ", Price = " + getStrongText("" + price));

			price = price + Double.valueOf(getHelperClass().getDecimalNumber(Shipping_Price));

			test.info("After Adding Shipment amount " + getStrongText(Shipping_Price) + " , Price = "
					+ getStrongText("" + price));
			test.info("Price = " + getStrongText("" + price) + ", Tax(%) = " + getStrongText(skuTaxPerSO));

			double gstCal = ((price * Double.valueOf(getHelperClass().getDecimalNumber(skuTaxPerSO))) / 100);
			checkAssertDouble(Double.valueOf(omsGst), gstCal, "GST ");

			double grandTotalCal = price + gstCal;

			test.info("After Adding Tax amount " + getStrongText(omsGst) + ", Price = " + getStrongText(omsGrandTotal));
			checkAssertDouble(Double.valueOf(omsGrandTotal), grandTotalCal, "Grand Total");
			test.info("Grand Total = " + getStrongText(omsGrandTotal));
		}

		// To create SO Order
		customClick(Create_Quote_and_Order.getPlace_Order_Button());

		String SO_ID = "";
		// Reporter.log(SO_ID, true);
		waitForVisibleElement(Success.getSuccess_message(), 10);

		if (Success.getSuccess_message().isDisplayed()) {
			SO_ID = Success.getSuccess_message().getText();
			test.info("OrderId = <strong>" + getOnlyId(SO_ID, "OM") + "</strong>");
			addGlobalData("SO_ID", getOnlyId(SO_ID, "OM"));

			String suc_message = Success.getSuccess_message().getText();
			Reporter.log(suc_message, true);
			System.out.println("Sales Order ID: OM" + SO_ID);

			takeScreenShort("<strong>Sales Order, " + SO_ID + "</strong>",
					"Step:1 Sales Order Created Successfully and Verification Passed with ID: " + SO_ID);

		} else {
			takeScreenShort("Sales Order is not Created", "Step:1 Sales Order is UNSUCCESSFUL and Verification Failed");
		}
	}

	public void getPickAndPackAction(String SkuName, String SkuQnt) {

		Sales_Order_Shipment_OMS_Medikabazaar_Page Sales_Order_Shipment = new Sales_Order_Shipment_OMS_Medikabazaar_Page(
				getWebDriver());
		Pick_And_Pack_OMS_Medikabazaar_Page Pick_And_Pack = new Pick_And_Pack_OMS_Medikabazaar_Page(getWebDriver());
		customClick(Sales_Order_Shipment.getLogistic_details());

		customClick(Sales_Order_Shipment.getpick_and_pack_button());
		getWebdriverAction().select_dd_by_visibletext(getCommonAction().cdcName,
				Sales_Order_Shipment.getwh_select_dd());// "Mumbai (CDC)"

		scrollIntoView(Sales_Order_Shipment.getcheck_box());
		customClick(Sales_Order_Shipment.getcheck_box());
		customClick(Sales_Order_Shipment.getApproval_submit());

		/*
		 * Here we, 1. get QNT which for PickAndPack 2. check where we get avalibale QNT
		 * eqaul or More. 3. send PickAndPack QNT to into it.
		 * 
		 */
		// String xpathQnt =
		// WebEnvironment.equals("Oms")?"":"((//td[contains(text(),'MBPHM09EINJMYLN000013')])[1]/following-sibling::td)[3]";
		WebElement pickAndPackQnt = getWebDriver().findElement(
				By.xpath("((//td[contains(text(),'" + SkuName + "')])[1]/following-sibling::td)[2]//label"));
		String pickAndPickQntNumber = pickAndPackQnt.getAttribute("textContent").trim();
		System.out.println("pickAndPickQntNumber =" + pickAndPickQntNumber);
		WebElement ele = null;
		List<WebElement> listEle = null;

		listEle = getWebDriver().findElements(By.xpath(
				"//input[contains(@name,'sku[" + SkuName + "][inward_quantity]')]/..//span[@class='availablestock']"));

		int c = 0;

		for (WebElement e : listEle) {
			c++;
			if (Integer.parseInt(e.getText()) >= Integer.parseInt(pickAndPickQntNumber)) {
				ele = getWebDriver().findElement(
						By.xpath("(//input[contains(@name, 'sku[" + SkuName + "][inward_quantity]')])[" + c + "]"));

				if (c == 2)
					break;
			}
		}
		System.out.println("Count=" + c);
		customSendText(ele, SkuQnt);
		customClick(Pick_And_Pack.getconfirm_button());

		WebDriverWait wait = new WebDriverWait(getWebDriver(), Duration.ofSeconds(15));
		wait.until(ExpectedConditions.alertIsPresent());

		getWebdriverAction().alert_accept();

		if (Sales_Order_Shipment.getpap_suc_msg().isDisplayed()) {
			takeScreenShort(getStrongText("Pick and Pack Created"),
					"Step:4 Pick and Pack Created Successfully and Verification Passed");
		} else {
			takeScreenShort(getStrongText("Pick and Pack is not Created"),
					"Step:4 Pick and Pack NOT Created and Verification Failed");
		}

		Reporter.log("The Total Quantity for Pick and Pack is: " + SkuQnt, true);
		test.info("The Total Quantity for Pick and Pack is: " + getStrongText(SkuQnt));
		getWebdriverAction().scrollBy_bottom();
		customClick(Sales_Order_Shipment.getview());
	}

	public void getPAP_ShippingAction() {
		String Insurance_Reference_Number = getFileLibClass().getDataFromExcell("SO_Data", "Insurance_Reference_Number",
				IAutoconstant.EXCEL_TestData);
		String Estimated_TAT = getFileLibClass().getDataFromExcell("SO_Data", "Estimated_TAT",
				IAutoconstant.EXCEL_TestData);
		String Distance = getFileLibClass().getDataFromExcell("SO_Data", "Distance", IAutoconstant.EXCEL_TestData);
		String Transporter_Name = getFileLibClass().getDataFromExcell("SO_Data", "Transporter_Name",
				IAutoconstant.EXCEL_TestData);
		String GST_Number = getFileLibClass().getDataFromExcell("SO_Data", "GST_Number", IAutoconstant.EXCEL_TestData);
		String Comment1 = getFileLibClass().getDataFromExcell("SO_Data", "Comment1", IAutoconstant.EXCEL_TestData);

		Pick_And_Pack_View_OMS_Medikabazaar_Page Pick_And_Pack_View = new Pick_And_Pack_View_OMS_Medikabazaar_Page(
				getWebDriver());

		getWebdriverAction().select_dd_by_visibletext("Bluedart", Pick_And_Pack_View.getcourier_type_dd());
		getWebdriverAction().select_dd_by_visibletext("Yes", Pick_And_Pack_View.getgst_available_dd());

		customSendText(Pick_And_Pack_View.getInsurance_Ref_Num(), Insurance_Reference_Number);
		customSendText(Pick_And_Pack_View.getEst_TAT(), Estimated_TAT);
		customSendText(Pick_And_Pack_View.getdistance(), Distance);
		customSendText(Pick_And_Pack_View.gettransporter_name(), Transporter_Name);
		customSendText(Pick_And_Pack_View.getgst_number(), GST_Number);
		customSendText(Pick_And_Pack_View.getcomment(), Comment1);
		customClick(Pick_And_Pack_View.getsave());
		test.info("Pick and Pack Courier Details updated");
		customClick(Pick_And_Pack_View.getGenerate_Invoice());
		customClick(Pick_And_Pack_View.getUpdate_Finance_Info());
	}

	public void getUpdate_Finance_Info() {
		String Invoice_Number = getFileLibClass().getDataFromExcell("SO_Data", "Invoice_Number",
				IAutoconstant.EXCEL_TestData);
		String eway_Bill_No = getFileLibClass().getDataFromExcell("SO_Data", "eway_Bill_No",
				IAutoconstant.EXCEL_TestData);
		String pdf = IAutoconstant.Pdf_dummy;

		Update_Finance_Details_OMS_Medikabazaar_Page Update_Finance_Details = new Update_Finance_Details_OMS_Medikabazaar_Page(
				getWebDriver());

		customSendText(Update_Finance_Details.getInvoice_Number_Label(), Invoice_Number);

		customClick(Update_Finance_Details.getInvoice_Date_label());
		customSendText(Update_Finance_Details.getInvoice_Date_label(), "2023-03-08");

		customSendText(Update_Finance_Details.getInvoice_Attachmente_label(), pdf);
		Update_Finance_Details.geteway_Bill_No_label().sendKeys(eway_Bill_No);
		customClick(Update_Finance_Details.getSave_button());

		customClick(Update_Finance_Details.getBack_button());
		getWebdriverAction().refresh();
		getWebdriverAction().scrollBy_value("0", "500");
	}

	public void get_Dispatch_Action() {

		String comment2 = getFileLibClass().getDataFromExcell("SO_Data", "comment2", IAutoconstant.EXCEL_TestData);

		Sales_Order_Shipment_OMS_Medikabazaar_Page Sales_Order_Shipment = new Sales_Order_Shipment_OMS_Medikabazaar_Page(
				getWebDriver());
		Pick_And_Pack_View_OMS_Medikabazaar_Page Pick_And_Pack_View = new Pick_And_Pack_View_OMS_Medikabazaar_Page(
				getWebDriver());
		Shipment_Status_OMS_Medikabazaar_Page Shipment_Status = new Shipment_Status_OMS_Medikabazaar_Page(
				getWebDriver());

		customClick(Sales_Order_Shipment.getactivity_summary_tab());
		customClick(Sales_Order_Shipment.getview());
		customClick(Pick_And_Pack_View.getFinal_Dispatch());

		waitForVisibleElement(Sales_Order_Shipment.getDispatched_text(), 10);
		if (Sales_Order_Shipment.getDispatched_text().isDisplayed()) {
			takeScreenShort("Order Dispatched", "Step:5 Dispatched Successfully and Verification Passed");
		} else {
			takeScreenShort("Order is not Dispatched", "Step:5 Dispatch Not done and Verification Failed");
		}

		customClick(Sales_Order_Shipment.getview2());

		getWebdriverAction().select_dd_by_value(Shipment_Status.getShipping_Status_Label(), "2");

		customSendText(Shipment_Status.getshipping_comment1_Label(), comment2);
		customClick(Shipment_Status.getDate_Time_Picker_Label());
		Shipment_Status.getDate_Time_Picker_Label().sendKeys(Keys.ENTER);
		if (Shipment_Status.getDelivered_text().isDisplayed()) {
			takeScreenShort("Sales Order Delivered", "Step:6 SO Delivery Done Successfully and Verification Passed");
		} else {
			takeScreenShort("Sales Order is not Delivered", "Step:6 SO Delivery NOT Done and Verification Failed");
		}
		customClick(getWebDriver().findElement(By.xpath("//button[@class='btn back-btn']")));
	}

	public void getRollback_PickAndPack(String Total_Qty_To_Rollback, String SKU_Qty_To_Rollback, String Comment_RB1,
			boolean isUpdateFinanceVisible) {

		Pick_And_Pack_View_OMS_Medikabazaar_Page Pick_And_Pack_View = new Pick_And_Pack_View_OMS_Medikabazaar_Page(
				getWebDriver());
		Sales_Order_Shipment_OMS_Medikabazaar_Page Sales_Order_Shipment = new Sales_Order_Shipment_OMS_Medikabazaar_Page(
				getWebDriver());

		customClick(Sales_Order_Shipment.getactivity_summary_tab());
		customClick(Sales_Order_Shipment.getview());

		customClick(Pick_And_Pack_View.getRollback_button());
		customClick(Pick_And_Pack_View.getCheck_box_RB());

		customClearText(Pick_And_Pack_View.getEnter_Total_Qty_to_RB_field());
		customSendText(Pick_And_Pack_View.getEnter_Total_Qty_to_RB_field(), Total_Qty_To_Rollback);

		customClearText(Pick_And_Pack_View.getEnter_SKU_Qty_to_RB_field());
		customSendText(Pick_And_Pack_View.getEnter_SKU_Qty_to_RB_field(), SKU_Qty_To_Rollback);
		customSendText(Pick_And_Pack_View.getComment_RB_field(), Comment_RB1);
		customClick(Pick_And_Pack_View.getRollback_submit_button());

		if (isUpdateFinanceVisible) {
			customClick(Pick_And_Pack_View.getUpdate_RB_Fin_Info_button());
			getCommonAction().getUpdate_Finance_Info();
		}

		test.info(getStrongText("Rollback Quantity is: " + Total_Qty_To_Rollback));

		String Count_After_RB1 = getCommonAction().getCDStockCount(getCommonAction().product_sku,
				getCommonAction().cdcName);
		int CDCcount_After_RB1 = Integer.parseInt(Count_After_RB1);
		Reporter.log(
				"Roll Back of Quantity = " + Total_Qty_To_Rollback + ", The CDC stock count is " + CDCcount_After_RB1,
				true);
		test.info("Roll Back of Quantity = " + Total_Qty_To_Rollback + ", The CDC stock count of  " + product_sku
				+ " is = " + getStrongText(Count_After_RB1));
		getCommonAction().getSearch_SO(getGloabalData("SO_ID"));
		addGlobalData(Count_After_RB1, "Count_After_RB1");
	}

	public void getPartial_Return(String count, String Total_Qty_To_Partial_Return, String SKU_Qty_To_Partial_Return,
			String returnType) {
		Shipment_Status_OMS_Medikabazaar_Page Shipment_Status = new Shipment_Status_OMS_Medikabazaar_Page(
				getWebDriver());
		Sales_Order_Shipment_OMS_Medikabazaar_Page Sales_Order_Shipment = new Sales_Order_Shipment_OMS_Medikabazaar_Page(
				getWebDriver());

		customClick(Sales_Order_Shipment.getactivity_summary_tab());
		customClick(Sales_Order_Shipment.getview2());

		getWebdriverAction().select_dd_by_visibletext(returnType, Shipment_Status.getShipping_Status_Label());

		WebElement date, shipmentReason, submitButton;
		String xpath = returnType.equals("Partial Return") ? "//input[@id='datepicker_return']"
				: "//input[@id='datetimepicker']";
		date = getWebDriver().findElement(By.xpath(xpath));

		xpath = returnType.equals("Partial Return") ? "Shipping Return Reason" : "Shipping Comment";
		shipmentReason = getWebDriver().findElement(By.xpath("//label[contains(text(),'" + xpath + "')]/..//select"));

		xpath = returnType.equals("Partial Return") ? "//button[@id='checkBtn']" : "//button[@id='submit']";
		submitButton = getWebDriver().findElement(By.xpath(xpath));

		if (returnType.equals("Partial Return")) {
			customClick(Shipment_Status.getCheck_box_PR());
			customSendText(Shipment_Status.getEnter_Qty_PR(), Total_Qty_To_Partial_Return);
			customClearText(Shipment_Status.getEnter_SKU_Qty_PR());
			customSendText(Shipment_Status.getEnter_SKU_Qty_PR(), SKU_Qty_To_Partial_Return);
		}

		customSendText(date, "2023-02-21 10:05:16");

		try {
			getWebdriverAction().select_dd_by_visibletext("The customer changed her/his mind", shipmentReason);
		} catch (Exception e) {
			getWebdriverAction().select_dd_by_visibletext("The product was different from the one that was ordered",
					shipmentReason);
		}
		customClick(submitButton);

		WebElement grnText = getWebDriver().findElement(By.xpath("(//td[contains(text(),'GRN')])[1]"));
		Total_Qty_To_Partial_Return = returnType.equals("Partial Return") ? Total_Qty_To_Partial_Return
				: getCommonAction().skuQuantity;

		if (grnText.isDisplayed()) {
			scrollIntoView(grnText);
			test.info("Sales Order, " + returnType + " of " + getCommonAction().product_sku + ", Quantity ="
					+ getStrongText(Total_Qty_To_Partial_Return));
			Reporter.log("Sales Order, " + returnType + " of " + getCommonAction().product_sku + ", Quantity ="
					+ Total_Qty_To_Partial_Return, true);
		} else {
			test.info("Sales Order,Failed " + returnType + " of " + getCommonAction().product_sku + ", Quantity ="
					+ getStrongText(Total_Qty_To_Partial_Return));
			Reporter.log("Sales Order,Failed " + returnType + " of " + getCommonAction().product_sku + ", Quantity ="
					+ Total_Qty_To_Partial_Return, true);
		}

		WebElement status = getWebDriver().findElement(By.xpath("//button[contains(text(),'Pending')]"));
		System.out.println("===>" + status.getText() + "<==");

		WebElement updateInfo = getWebDriver().findElement(By.xpath("//a[contains(text(),'Update Finance Info')]"));
		customClick(updateInfo);

		getWebdriverAction().getWindowHandles();
		getCommonAction().getUpdate_Finance_Info();
		status = getWebDriver().findElement(By.xpath("(//button[contains(text(),'Success')])[" + count + "]"));
		scrollIntoView(status);
		if (status.isDisplayed()) {
			scrollIntoView(status);
			test.info("Updated Finance Info for Sales Order Return Quantity");
			Reporter.log("Filled Updated Finance Info for SO Partial Return  Qty=" + count + " and Verification Passed",
					true);
		} else {
			test.info("Not Updated Finance Info for Sales Order Return Quantity");
			Reporter.log(
					"NOT Filled Updated Finance Info for SO Partial Return Qty" + count + " and Verification Failed",
					true);
		}

		status = getWebDriver().findElement(By.xpath("//h3[contains(text(),'Shipping Comment And Status')]"));
		scrollIntoView(status);
		customClick(status);
		if (status.isDisplayed()) {
			scrollIntoView(status);
			takeScreenShort(
					"Status of " + returnType + " of SKU = " + getStrongText(product_sku) + " , Quntity = "
							+ Total_Qty_To_Partial_Return,
					"Status of " + returnType + " of SKU =" + product_sku + " , Quntity = "
							+ Total_Qty_To_Partial_Return);
		} else {
			takeScreenShort(
					"Status is not visible of " + returnType + " of SKU =" + product_sku + " , Quntity = "
							+ Total_Qty_To_Partial_Return,
					"Status of " + returnType + " of SKU = " + product_sku + " , Quntity = "
							+ Total_Qty_To_Partial_Return);
		}

		System.out.println("===>" + status.getText() + "<==");
		getSearch_SO(getGloabalData("SO_ID"));
	}

	/*
	 * By calling this method we are going to get SKU Updated Details on Stock
	 * logger Page this method will return multiple Data in form of
	 * HashMap<String,Integer> By using of HashMap<String, Integer> SKU_Details =
	 * stockLogger_Details(CDC_Name, sku_name, transaction_type_name); we can fetch
	 * Data by SKU_Details.get("Value");
	 */
	public HashMap<String, Integer> stockLogger_Details(String CDC_Name, String sku_name,
			String transaction_type_name) {

		Stock_Management_OMS_Medikabazaar_Page stock_mgmt = new Stock_Management_OMS_Medikabazaar_Page(getWebDriver());
		Getting_Started_OMS_MedikabazaarPage getting_started = new Getting_Started_OMS_MedikabazaarPage(getWebDriver());

		customClick(getting_started.get_Operations_button());
		try {
			customClick(getting_started.get_Stock_Management_button());
		} catch (Exception e) {
			customClick(getting_started.get_Operations_button());
			customClick(getting_started.get_Stock_Management_button());
		}
		customClick(stock_mgmt.getStock_logger_tab());
		getWebdriverAction().select_dd_by_visibletext(CDC_Name, stock_mgmt.getWarehouse_dd());
		customSendText(stock_mgmt.getSku_textfield(), sku_name);
		getWebdriverAction().select_dd_by_visibletext(transaction_type_name, stock_mgmt.getTransaction_Sub_Type_dd());
		stock_mgmt.getSearch_button().click();
		getWebdriverAction().scrollBy_horizontally(stock_mgmt.getPrevious_Total_Stock_th_qty());

		String scheme_Qty = stock_mgmt.getScheme_Qty().getText();
		int scheme_qty = Integer.parseInt(scheme_Qty);

		String updated_qty = stock_mgmt.getQTY().getText();
		int updated_Qty = Integer.parseInt(updated_qty);

		String decrease_Stock = stock_mgmt.getDecrease_stock().getText();
		int decrease_stock = Integer.parseInt(decrease_Stock);

		String total_back_Qty = stock_mgmt.getTotal_Qty_back_to_stock().getText();
		int total_back_qty = Integer.parseInt(total_back_Qty);

		String current_total_Stock = stock_mgmt.getCurrent_total_stock().getText();
		int current_total_stock = Integer.parseInt(current_total_Stock);

		String previous_Stock = stock_mgmt.getPrevious_Total_Stock_th_qty().getText();
		int previous_stock = Integer.parseInt(previous_Stock);

		HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
		hashMap.put("scheme_qty", scheme_qty);
		hashMap.put("updated_Qty", updated_Qty);
		hashMap.put("decrease_stock", decrease_stock);
		hashMap.put("total_back_qty", total_back_qty);
		hashMap.put("current_total_stock", current_total_stock);
		hashMap.put("previous_stock", previous_stock);

		return hashMap;
	}

	// Method For SO Bulk
	public void get_SO_Bulk_Duplicate_CSV() throws Throwable {
		Getting_Started_OMS_MedikabazaarPage getting_started = new Getting_Started_OMS_MedikabazaarPage(getWebDriver());
		Customer_Orders_OMS_Medikabazaar_Page Customer_Orders = new Customer_Orders_OMS_Medikabazaar_Page(
				getWebDriver());
		Create_Quote_and_Order_OMS_Medikabazaar_Page Create_Quote_and_Order = new Create_Quote_and_Order_OMS_Medikabazaar_Page(
				getWebDriver());
		Success_OMS_Medikabazaar_Page Success = new Success_OMS_Medikabazaar_Page(getWebDriver());

		String Customer_Email_ID = " ";
		String Shipping_Price = " ";

		customClick(getting_started.getOrder_button());
		customClick(getting_started.getSalesorder_button());
		customClick(Customer_Orders.getcreate_sales_order_button());

		Customer_Email_ID = getFileLibClass().getDataFromExcell("SO_Data", "Customer_Email_ID",
				IAutoconstant.EXCEL_TestData);
		customSendText(Create_Quote_and_Order.getEnter_Customer_Email_ID(), Customer_Email_ID);
		customClick(Create_Quote_and_Order.getSubmit_button());
		test.info("Customer Email=" + getStrongText(Customer_Email_ID) + " is Added");

		// CHECK PRE LOADED SKU IN CART
		try {
			getWebdriverAction().scrollIntoView(Create_Quote_and_Order.gettotal_sku_added_count());
			Create_Quote_and_Order.gettotal_sku_added_count().isDisplayed();
			getWebdriverAction().scrollBy_bottom();
			customClick(Create_Quote_and_Order.getclear_cart_button());
			customClick(Create_Quote_and_Order.getOK_to_clearcart_button());
			takeScreenShort("<strong>Cleared pre loaded SKU's from the Cart</strong>",
					"Cleared the cart with pre loaded SKU's Before Inward CSV");
		} catch (Exception e) {
			System.out.println("E=" + e);
			System.out.println("There is NO pre loaded SKU's Before Inward CSV");
		}

		// BULK SKU THROUGH CSV FILE
		getWebdriverAction().scrollBy_top();
		customClick(Create_Quote_and_Order.getImport_From_CSV_Button());
		// String so_csv_file = IAutoconstant.SO_bulk_sku_553_modify;
		String so_csv_file = IAutoconstant.SO_bulk_sku_553_modify;
		try {
			takeScreenShort("<strong>Uploading Bulk SO CSV File</strong>", "Bulk SO CSV File is Uploaded");
			Create_Quote_and_Order.getcsv_file_upload().sendKeys(so_csv_file);
			customClick(Create_Quote_and_Order.getImport_button());
		} catch (Exception e) {
			System.out.println("E=" + e);
		}

		String csv_counnt_message = Create_Quote_and_Order.getduplicate_message().getText();
		System.out.println("Message Displayed on OMS Page:" + csv_counnt_message);
		takeScreenShort("<strong>Message Displayed for Imported CSV File</strong>",
				"Message displayed for successful imported CSV file");

		try {
			getWebdriverAction().scrollIntoView(Create_Quote_and_Order.getduplicate_message());
			customClick(Create_Quote_and_Order.getdownload_link());
			// String downloaded_file = IAutoconstant.SO_downloaded_file;
		} catch (Exception e) {
			System.out.println("E=" + e);
		}

		// Success and Failure SKU count
		scrollIntoView(Create_Quote_and_Order.gettotal_sku_added_count());
		String total_sku_added_count = Create_Quote_and_Order.gettotal_sku_added_count().getText();
		// tackScreenShort("<strong>Total Units Added Into Cart</strong>","Total Units
		// Displayed");

		String total_sku_added_into_cart = total_sku_added_count.replaceAll("[^0-9]", "");
		System.out.println("Total SKU's Added Into Cart Is: " + total_sku_added_into_cart); // Total SKU's Success
		String failure_part = csv_counnt_message.substring(42, csv_counnt_message.length());
		String total_sku_failed = failure_part.replaceAll("[^0-9]", "");
		System.out.println("Total SKU's Failed: " + total_sku_failed); // Total SKU's Failed
		takeScreenShort(
				"Total SKU's Added Into Cart Is: <strong>" + total_sku_added_into_cart
						+ "</strong> & Total SKU's Failed: <strong>" + total_sku_failed + "</strong>",
				"Total no. of success and failed SKUs Displayed");

		String downloadFilePath = System.getProperty("user.home");
		downloadFilePath = downloadFilePath + "\\Downloads";

		String csvFilePath = downloadFilePath;
		String newcsvfile = getFileLibClass().getTheNewestFile(csvFilePath, "csv");
		System.out.println("New File name=" + newcsvfile);
		csvFilePath = csvFilePath + "\\" + newcsvfile;
		List<String> priceData = getFileLibClass().readColumnFromCSVWithCondition(csvFilePath, "price", "Import Status",
				"Success");
		List<String> qtyData = getFileLibClass().readColumnFromCSVWithCondition(csvFilePath, "qty", "Import Status",
				"Success");
		// Here multiplication of each Row => finalPrice = priceDat*qtyData
		List<Double> finalPrice = new ArrayList<Double>();
		double cal = 0;
		for (int i = 0; i < priceData.size(); i++) {
			cal = (Double.valueOf(priceData.get(i)) * Double.valueOf(qtyData.get(i)));
			finalPrice.add(cal);
		}
		double sumOfPriceAndQnt = 0;
		for (double d : finalPrice) {
			sumOfPriceAndQnt = (sumOfPriceAndQnt + d);
		}
		System.out.println("Subtotal As Per Excel: " + sumOfPriceAndQnt);
		getWebdriverAction().scrollBy_bottom();
		String Actual_sub_total = Create_Quote_and_Order.getsub_total().getText();
		System.out.println("Subtotal As Per OMS: " + Actual_sub_total);

		// Subtotal in numbers
		DecimalFormat df = new DecimalFormat("#"); // "#.##"
		df.setRoundingMode(RoundingMode.CEILING);
		String Subtotal_excel = df.format(sumOfPriceAndQnt);
		int Subtotal_excel1 = Integer.parseInt(Subtotal_excel);
		System.out.println("Subtotal(Excel): " + Subtotal_excel1);
		String strNew = Actual_sub_total.replace(",", "");
		String strNew2 = strNew.replace("₹", "");
		double str1 = Double.parseDouble(strNew2);
		// DecimalFormat df = new DecimalFormat("#"); //"#.##"
		df.setRoundingMode(RoundingMode.CEILING);
		String Subtotal_oms = df.format(str1);
		int Subtotal_oms1 = Integer.parseInt(Subtotal_oms);
		System.out.println("Subtotal(OMS): " + Subtotal_oms1);
		test.info("Subtotal(OMS): <strong>" + Subtotal_oms1 + "</strong> & " + "Subtotal(Excel): <strong>"
				+ Subtotal_excel1 + "</strong>");

		if (Subtotal_excel1 == Subtotal_oms1) {
			System.out.println("The Subtotal is same in Excel and OMS page");
			test.info("Subtotal(Excel): <strong>" + Subtotal_excel1 + "</strong>" + " Is Verified With CSV File");
			customSendText(Create_Quote_and_Order.getShipping_Price(), Shipping_Price);
			customClick(Create_Quote_and_Order.getShipping_Update_Button());
			toastMessage();
			customClick(Create_Quote_and_Order.getPlace_Order_Button());

		} else {
			System.out.println("The Subtotal is NOT same in Excel and OMS page");
			test.info("The sub total" + Actual_sub_total + "Is <strong>" + "NOT" + "</strong> Verified With CSV File");
		}

		String SO_ID = "";
		Reporter.log(SO_ID, true);
		waitForVisibleElement(Success.getSuccess_message(), 10);

		if (Success.getSuccess_message().isDisplayed()) {
			SO_ID = Success.getSuccess_message().getText();
			test.info("OrderId = <strong>" + getOnlyId(SO_ID, "OM") + "</strong>");
			addGlobalData("SO_ID", getOnlyId(SO_ID, "OM"));

			String suc_message = Success.getSuccess_message().getText();
			Reporter.log(suc_message, true);
			System.out.println("Sales Order ID: OM" + SO_ID);

			takeScreenShort("<strong>Sales Order, " + SO_ID + "</strong>",
					"Step:1 Sales Order Created Successfully and Verification Passed with ID: " + SO_ID);

		} else {
			takeScreenShort("Sales Order is not Created", "Step:1 Sales Order is UNSUCCESSFUL and Verification Failed");
		}
	}

	// ************************
	public void get_SO_Bulk_Unique_CSV() throws Throwable {
		Getting_Started_OMS_MedikabazaarPage getting_started = new Getting_Started_OMS_MedikabazaarPage(getWebDriver());
		Customer_Orders_OMS_Medikabazaar_Page Customer_Orders = new Customer_Orders_OMS_Medikabazaar_Page(
				getWebDriver());
		Create_Quote_and_Order_OMS_Medikabazaar_Page Create_Quote_and_Order = new Create_Quote_and_Order_OMS_Medikabazaar_Page(
				getWebDriver());
		Success_OMS_Medikabazaar_Page Success = new Success_OMS_Medikabazaar_Page(getWebDriver());

		String Customer_Email_ID = " ";
		String Shipping_Price = " ";

		customClick(getting_started.getOrder_button());

		customClick(getting_started.getSalesorder_button());

		customClick(Customer_Orders.getcreate_sales_order_button());

		Customer_Email_ID = getFileLibClass().getDataFromExcell("SO_Data", "Customer_Email_ID",
				IAutoconstant.EXCEL_TestData);
		customSendText(Create_Quote_and_Order.getEnter_Customer_Email_ID(), Customer_Email_ID);

		customClick(Create_Quote_and_Order.getSubmit_button());

		test.info("Customer Email=" + getStrongText(Customer_Email_ID) + " is Added");

		// Clear cart
		try {
			getWebdriverAction().scrollIntoView(Create_Quote_and_Order.gettotal_sku_added_count());
			Create_Quote_and_Order.gettotal_sku_added_count().isDisplayed();
			getWebdriverAction().scrollBy_bottom();
			customClick(Create_Quote_and_Order.getclear_cart_button());
			customClick(Create_Quote_and_Order.getOK_to_clearcart_button());
			takeScreenShort("<strong>Cleared pre loaded SKU's from the Cart Before Inward CSV</strong>",
					"Cleared the cart with pre loaded SKU's Before Inward CSV");
		} catch (Exception e) {
			System.out.println("E=" + e);
			System.out.println("There is NO pre loaded SKU's Before Inward CSV");
		}

		// BULK SKU THROUGH CSV FILE
		getWebdriverAction().scrollBy_top();
		customClick(Create_Quote_and_Order.getImport_From_CSV_Button());
		String so_csv_file = IAutoconstant.SO_bulk_sku_modify;
		try {
			Create_Quote_and_Order.getcsv_file_upload().sendKeys(so_csv_file);
			customClick(Create_Quote_and_Order.getImport_button());
		} catch (Exception e) {
			System.out.println("E=" + e);
		}

		// Count message
		String csv_counnt_message = Create_Quote_and_Order.getmessage_csv_count().getText();
		takeScreenShort("<strong>Message Diaplyed For Imported CSV File</strong>",
				"Message displayed for imported CSV");
		System.out.println(csv_counnt_message);

		// SKU Count
		String total_sku_added_count = Create_Quote_and_Order.gettotal_sku_added_count().getText();
		String total_sku_added_into_cart = total_sku_added_count.replaceAll("[^0-9]", "");
		System.out.println("Total SKU's Added Into Cart Is: " + total_sku_added_into_cart);
		test.info("Total" + getStrongText(total_sku_added_into_cart)
				+ "SKU's Added Into Cart Successfully and Verified With CSV File");

		getWebdriverAction().scrollBy_bottom();
		String Actual_sub_total = Create_Quote_and_Order.getsub_total().getText();
		System.out.println("The sub total on OMS page: " + Actual_sub_total);

		List<String> priceData = getFileLibClass().readColumnFromCSV(so_csv_file, "price");
		List<String> qtyData = getFileLibClass().readColumnFromCSV(so_csv_file, "qty");

		List<Double> finalPrice = new ArrayList<Double>();
		double cal = 0;
		for (int i = 0; i < priceData.size(); i++) {
			cal = (Double.valueOf(priceData.get(i)) * Double.valueOf(qtyData.get(i)));
			finalPrice.add(cal);
		}
		double sumOfPriceAndQnt = 0;
		for (double d : finalPrice) {
			sumOfPriceAndQnt = (sumOfPriceAndQnt + d);
		}
		System.out.println("Subtotal As Per Excel: " + sumOfPriceAndQnt);
		getWebdriverAction().scrollBy_bottom();

		// Subtotal in numbers
		DecimalFormat df = new DecimalFormat("#"); // "#.##"
		df.setRoundingMode(RoundingMode.CEILING);
		String Subtotal_excel = df.format(sumOfPriceAndQnt);
		int Subtotal_excel_1 = Integer.parseInt(Subtotal_excel);
		System.out.println("Subtotal(Excel): " + Subtotal_excel_1);

		String strNew = Actual_sub_total.replace(",", "");
		String strNew2 = strNew.replace("₹", "");
		double str1 = Double.parseDouble(strNew2);
		df.setRoundingMode(RoundingMode.CEILING);
		String Subtotal_oms = df.format(str1);
		int Subtotal_oms_1 = Integer.parseInt(Subtotal_oms);
		System.out.println("Subtotal(OMS): " + Subtotal_oms_1);
		test.info("Subtotal(OMS): <strong>" + Subtotal_oms_1 + "</strong> & " + "Subtotal(Excel): <strong>"
				+ Subtotal_excel_1 + "</strong>");

		if (Subtotal_excel_1 == (Subtotal_oms_1)) {
			System.out.println("The Subtotal is same in Excel and OMS page");
			test.info("Subtotal(Excel): <strong>" + Subtotal_excel_1 + "</strong>" + " Is Verified With CSV File");
			customSendText(Create_Quote_and_Order.getShipping_Price(), Shipping_Price);
			customClick(Create_Quote_and_Order.getShipping_Update_Button());
			toastMessage();
			customClick(Create_Quote_and_Order.getPlace_Order_Button());
		} else {
			System.out.println("The Subtotal is NOT same in Excel and OMS page");
			test.info("Subtotal(Excel): <strong>" + Subtotal_excel_1 + "</strong>" + " Is NOT Verified With CSV File");
		}

		String SO_ID = "";
		Reporter.log(SO_ID, true);
		waitForVisibleElement(Success.getSuccess_message(), 10);

		if (Success.getSuccess_message().isDisplayed()) {
			SO_ID = Success.getSuccess_message().getText();
			test.info("OrderId = <strong>" + getOnlyId(SO_ID, "OM") + "</strong>");
			addGlobalData("SO_ID", getOnlyId(SO_ID, "OM"));

			String suc_message = Success.getSuccess_message().getText();
			Reporter.log(suc_message, true);
			System.out.println("Sales Order ID: OM" + SO_ID);

			takeScreenShort("<strong>Sales Order, " + SO_ID + "</strong>",
					"Step:1 Sales Order Created Successfully and Verification Passed with ID: " + SO_ID);

		} else {
			takeScreenShort("Sales Order is not Created", "Step:1 Sales Order is UNSUCCESSFUL and Verification Failed");
		}
	}

	// *****************************
	public int SpecialStockUpdate_ManualEnrty(String CDC_Name, String SKU_Name, String SKU_Qty) {

		Stock_Management_OMS_Medikabazaar_Page stock_mgmt = new Stock_Management_OMS_Medikabazaar_Page(getWebDriver());
		Getting_Started_OMS_MedikabazaarPage getting_started = new Getting_Started_OMS_MedikabazaarPage(getWebDriver());
		Edit_Stock_OMS_Medikabazaar edit_stock = new Edit_Stock_OMS_Medikabazaar(getWebDriver());

		int sku_Qty = Integer.parseInt(SKU_Qty);
		String comment_text = getFileLibClass().getDataFromExcell("Stock_management_special_stock", "comment text",
				IAutoconstant.EXCEL_TestData);

		String Batch_number = fake.letterify("A?12???B");
		System.out.println("Batch number : " + Batch_number);
		customClick(getting_started.get_Operations_button());
		try {
			customClick(getting_started.get_Stock_Management_button());
		} catch (Exception e) {
			customClick(getting_started.get_Operations_button());
			customClick(getting_started.get_Stock_Management_button());
		}
		customClick(stock_mgmt.get_Warehouse_Name());
		getWebdriverAction().select_dd_by_visibletext(CDC_Name, stock_mgmt.get_Warehouse_Name());
		customSendText(stock_mgmt.get_sku_field(), SKU_Name);
		customClick(stock_mgmt.get_search_button());
		getWebdriverAction().thread_sleep(1000);
		customClick(stock_mgmt.getSku_column());
		getWebdriverAction().scrollBy_bottom();
		customClick(edit_stock.getAdd_more_button());
		customSendText(edit_stock.getBatch_no_textfield(), Batch_number);
		customClick(edit_stock.getExp_date_textfiled());
		customClick(edit_stock.getDate_picker_testfield());
		getWebdriverAction().scrollBy_horizontally(edit_stock.getDelete_button());
		customSendText(edit_stock.getPo_Qty_textfield(), SKU_Qty);

		try {
			edit_stock.getRack_shelf_floor_dd().click();
			getWebdriverAction().select_dd_by_index(edit_stock.getRack_shelf_floor_dd(), 1);
		} catch (Exception e) {
			customSendText(edit_stock.getComment_test_filed(), comment_text);
		}
		test.info("Warehouse Name : " + getStrongText(CDC_Name) + " , SKU_Name : " + getStrongText(SKU_Name)
				+ " , SKU_Qty : " + getStrongText(SKU_Qty) + " , Batch_Number : " + getStrongText(Batch_number));
		customClick(edit_stock.getSubmit_button());
		takeScreenShort(getStrongText("Special stock updated "), " Special Stock Updated");
		getWebdriverAction().thread_sleep(200);
		return sku_Qty;
	}
	
	public void onlyVisibleLocatorClick(String xpath) {
		List<WebElement> elements = getWebDriver().findElements(By.xpath(xpath));
		
		for(WebElement ele : elements ) {
			if(ele.isDisplayed() && ele.isEnabled()) {
				customClick(ele);
				break;
			}
		}	
	}
	
	public String poFullCancel(PO_MasterModule_OMS_MedikabazaarPage PO_master_page)
	{	
		searchByPO_ID(getGloabalData("PO_ID"));
		customClick(PO_master_page.getCancel_po_button());
		
		WebElement selectAll = getWebDriver().findElement(By.xpath("//th[text()='Product']/preceding-sibling::th//input"));
		customClick(selectAll);
		customClearText(PO_master_page.getPo_cancel_comment());
		customSendText(PO_master_page.getPo_cancel_comment(),"Fully cancelling PO");
		onlyVisibleLocatorClick("//button[contains(text(),'Submit')]");
		
		String verificationText = verifyCancelCommentPO(PO_master_page) ;
		return verificationText ;
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
		
		takeScreenShort("PO Status After Canceled ","PO Status After Canceled");
		String cancelText = PO_master_page.getCanceled_text().getText();
		return cancelText;
	}
	
	public void createPurchaseOrder(String supplier_email,String sku ,String SKU_PO_Qty,boolean isCalculationDisplay) {

		Getting_Started_OMS_MedikabazaarPage homepage=new Getting_Started_OMS_MedikabazaarPage(getWebDriver());
		Purchase_Order_OMS_MedikabazaarPage puchase_order=new Purchase_Order_OMS_MedikabazaarPage(getWebDriver());
		PO_OMS_MedikabazaarPage PO_created_page=new PO_OMS_MedikabazaarPage(getWebDriver());
		 Create_PO_OMS_MedikabazaarPage create_PO_page=new Create_PO_OMS_MedikabazaarPage(getWebDriver());
		
		customClick(homepage.getPurchase_tab());
		customClick(homepage.getPurchase_order_option());
		test.info("Purchase Order Listing page is opened");

		customClick(puchase_order.getCreate_Purchase_Order_button());

		test.info("Create Purchase Order page is opened");
		waitForVisibleElement(create_PO_page.getSupplier_email_textfield(), 15);
		customClearText(create_PO_page.getSupplier_email_textfield());

		String flatDiscount = getFileLibClass().getDataFromExcell("PO_Data", "flatDiscount",
				IAutoconstant.EXCEL_TestData);

		customSendText(create_PO_page.getSupplier_email_textfield(), supplier_email);
		customClick(create_PO_page.getSubmit_button());

		// dropdown
		waitForVisibleElement(create_PO_page.getPayment_terms_DD(), 15);
		getWebdriverAction().select_dd_by_index(create_PO_page.getPayment_terms_DD(), 3);

		WebElement relationshipManager = getWebDriver().findElement(By.xpath("//select[@id='bde']"));
		getWebdriverAction().select_dd_by_visibletext("Irfan Pinjari", relationshipManager);

		customSendText(create_PO_page.getProduct_sku_textfield(), product_sku);
		
		Po_Qty = Integer.parseInt(SKU_PO_Qty);

		waitForVisibleElement(create_PO_page.getQuantity_textfield(), 15);
		customClearText(create_PO_page.getQuantity_textfield());
		customSendText(create_PO_page.getQuantity_textfield(), "" + SKU_PO_Qty);

		customClick(create_PO_page.getAdd_product_button());
		getWebdriverAction().scrolldown_By_element(create_PO_page.getProduct_sku_textfield());

		String supplierPrice = getFileLibClass().getDataFromExcell("PO_Data", "Supplier Price",
				IAutoconstant.EXCEL_TestData);

		customSendText(create_PO_page.getSkuSupplierPrice(), supplierPrice);

		// Here SKU Product details
		String HSN_Code = getFileLibClass().getDataFromExcell("PO_Data", "HSN Code", IAutoconstant.EXCEL_TestData);
		customSendText(create_PO_page.getHSN_Code_textfield(), HSN_Code);

		String skuDisPer = getFileLibClass().getDataFromExcell("PO_Data", "skuDiscountPercentage",
				IAutoconstant.EXCEL_TestData);
		// If flatDiscoiunt is not Equals to Zero than skuDisPer will be Zero
		if (!flatDiscount.trim().equals("0")) {
			skuDisPer = "0";
		}

		WebElement skuPer = getWebDriver().findElement(By.xpath("//input[@id='discount_percent']"));
		customClick(skuPer);
		customSendText(skuPer, skuDisPer);
		// Only this values are Present in TaxDorpDown 0,5,12,18,24,28

		String taxPer = getFileLibClass().getDataFromExcell("PO_Data", "taxPercentage", IAutoconstant.EXCEL_TestData);
		WebElement taxDropDown = getWebDriver().findElement(By.xpath("//select[@id='tax_percent']"));
		getWebdriverAction().select_dd_by_visibletext(taxPer, taxDropDown);

		customClick(create_PO_page.getFirstIteam_check_box());
		customClick(create_PO_page.getUpdate_cart_button());
		test.info("SKU Items Details, in the Cart is Updated");

		WebElement flatDiscountAmt = null;
		WebElement flatDiscountApply = null;

		// Here Flat Discount Or Shipping Charges will Apply
		String shipping_charge = getFileLibClass().getDataFromExcell("PO_Data", "Shipping Charge",
				IAutoconstant.EXCEL_TestData);
		if (!(flatDiscount.trim().equals("0"))) {
			flatDiscountAmt = getWebDriver().findElement(By.xpath("//input[@name='flat_discount']"));
			flatDiscountApply = getWebDriver().findElement(By.xpath("//button[contains(text(),'Apply')]"));

			customSendText(flatDiscountAmt, flatDiscount);
			customClick(flatDiscountApply);
		} else {

			// Shipping Charges
			customClearText(create_PO_page.getShipping_charge_textfield());
			customSendText(create_PO_page.getShipping_charge_textfield(), shipping_charge);
			customClick(create_PO_page.getShipping_update_button());
		}

		// Here Calculation Part1

		if (isCalculationDisplay) { // only Calculate when it is true
			// 1)Verify here for subTotal
			getHelperClass().calculationOfDiscount_PO(create_PO_page, SKU_PO_Qty, flatDiscount, skuDisPer, shipping_charge,
					taxPer);

		}

		scrollIntoView(create_PO_page.getSkuSupplierPrice());
		test.info("Grand Total = " + getStrongText(create_PO_page.getGrandTotalText().getText()));

		customClick(create_PO_page.getCreate_purchase_order_button());

		try {
			// Alert
			WebDriverWait w = new WebDriverWait(getWebDriver(), Duration.ofSeconds(5));
			if (w.until(ExpectedConditions.alertIsPresent()) == null) {
				customClick(create_PO_page.getCreate_purchase_order_button());
				System.out.println("Alert not exists");
			} else {
				getWebdriverAction().alert_accept();
				System.out.println("Alert exists");
			}
		} catch (Exception e) {
			System.out.println("Exception of Alert =" + e);
		}

		try {
			PO_number = PO_created_page.getPO_ID().getText();
		} catch (Exception e1) {
			WebElement msg = getWebDriver().findElement(
					By.xpath("//strong[contains(text(),'Please enter a Proper Shipping, Billing Adresses')]"));

			if (msg.isDisplayed()) {
				getHelperClass().fillPO_EditShippingAndBillingAdresses();
			}

			customClick(create_PO_page.getCreate_purchase_order_button());

			try {
				// Alert
				WebDriverWait w = new WebDriverWait(getWebDriver(), Duration.ofSeconds(5));
				if (w.until(ExpectedConditions.alertIsPresent()) == null) {
					customClick(create_PO_page.getCreate_purchase_order_button());
					System.out.println("Alert not exists");
				} else {
					getWebdriverAction().alert_accept();
					System.out.println("Alert exists");
				}
			} catch (Exception e) {
				System.out.println("Exception of Alert =" + e);
			}
			PO_number = PO_created_page.getPO_ID().getText();
		}

		Reporter.log(PO_number, true);
		if (PO_created_page.getConfirm_img().isDisplayed()) {
			test.info("OrderId = " + getStrongText(getOnlyId(PO_number, "PO")));
			takeScreenShort("Purchase order is created with number " + getStrongText(PO_number),
					"Step:1 P.O Created Successfully And Verification Passed ");
			addGlobalData("PO_ID", getOnlyId(PO_number, "PO"));
		} else {
			takeScreenShort(getStrongText("Purchase order is not created"),
					"Step:1 P.O is not created Verification Failed");
		}
	}
	
}