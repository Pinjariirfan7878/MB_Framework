package com.OMS.SO.Negative_Flow;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ObjectRepo.Create_Quote_and_Order_OMS_Medikabazaar_Page;
import com.ObjectRepo.Customer_Orders_OMS_Medikabazaar_Page;
import com.ObjectRepo.Getting_Started_OMS_MedikabazaarPage;
import com.ObjectRepo.Success_OMS_Medikabazaar_Page;
import com.oms.GenericLib.BaseUtilityClass;
import com.oms.GenericLib.IAutoconstant;

@Listeners(com.oms.GenericLib.MylistenerClass.class)
public class TC_001_SO_Create_So_with_L1_without_KYC_Test extends BaseUtilityClass{

	String testCaseDescription = "Create_So_with_L1_without_KYC ";
	@Test
	public void Create_So_with_L1_without_KYC_Test()
	{
		String currentClassName = this.getClass().getSimpleName();
		setTestCaseAndDescriptionInReport("SO Negative", currentClassName, testCaseDescription);

		String SKU_Name=getFileLibClass().getDataFromExcell("Create_So_with_L1_without_KYC", "SKU_Name", IAutoconstant.EXCEL_TestData);
		String SKU_QTY=getFileLibClass().getDataFromExcell("Create_So_with_L1_without_KYC", "SKU_Qty", IAutoconstant.EXCEL_TestData);
		String Shipping_price=getFileLibClass().getDataFromExcell("Create_So_with_L1_without_KYC", "Shipping_price", IAutoconstant.EXCEL_TestData);
		String customer_name=getFileLibClass().getDataFromExcell("Create_So_with_L1_without_KYC", "cutomer name", IAutoconstant.EXCEL_TestData);
		String customer_email=getFileLibClass().getDataFromExcell("Create_So_with_L1_without_KYC", "customer email", IAutoconstant.EXCEL_TestData);

		test.info(" Customer name : " +getStrongText(customer_name) + ", customer Email = " + getStrongText(customer_email));

		//create SO 		
		get_Create_SO(customer_email, SKU_Name, SKU_QTY,Shipping_price,false );
	}



























	//****************************Common Functions*************************************************************************************************************

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
				takeScreenShort("<strong>Cleared pre loaded SKU'S</strong>",
						"Cleared the cart with pre loaded SKU'S");
			}
		} catch (Exception e) {
			System.out.println("E=" + e);
			System.out.println("There is NO pre loaded SKU'S");
		}

		customSendText(Create_Quote_and_Order.getEnter_SKU(), SKU_Name);
		customClearText(Create_Quote_and_Order.getQuantity_Field());

		customSendText(Create_Quote_and_Order.getQuantity_Field(), SKU_Qty);
		customClick(Create_Quote_and_Order.getAdd_Product_button());

		test.info("Product Name = " + getStrongText(SKU_Name) + " , Quantity = " + getStrongText(SKU_Qty) + " Added");

		getCommonAction().toastMessage();

		getWebdriverAction().scrollBy_value("0", "-150");
		waitForClickableElement(Create_Quote_and_Order.getCheck_Box(), 20);

		String specialPriceSO = getFileLibClass().getDataFromExcell("SO_Data", "SpecialPrice",
				IAutoconstant.EXCEL_TestData);
		WebElement specialPrice = getWebDriver()
				.findElement(By.xpath("(//td[contains(text(),'" + SKU_Name + "')]/following-sibling::td)[3]//input"));
		customSendText(specialPrice, specialPriceSO);

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
		getCommonAction().toastMessage();

		customSendText(Create_Quote_and_Order.getShipping_Price(), Shipping_Price);
		customClick(Create_Quote_and_Order.getShipping_Update_Button());
		getCommonAction().toastMessage();

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
			getCommonAction().checkAssertDouble(Double.valueOf(omsSubtotal), subTotalCal, "Sub Total ");

			double disAmtCal = ((subTotalCal * Double.valueOf(getHelperClass().getDecimalNumber(skuDiscountPerSO)))
					/ 100);
			getCommonAction().checkAssertDouble(Double.valueOf(omsTotalDiscount), disAmtCal, "Discount Amount");

			test.info(
					"Price = " + getStrongText(omsSubtotal) + ", SKU DisCount(%) = " + getStrongText(skuDiscountPerSO));

			double price = subTotalCal - disAmtCal;

			test.info("Discount of " + getStrongText(omsTotalDiscount) + ", Price = " + getStrongText("" + price));

			price = price + Double.valueOf(getHelperClass().getDecimalNumber(Shipping_Price));

			test.info("After Adding Shipment amount " + getStrongText(Shipping_Price) + " , Price = "
					+ getStrongText("" + price));
			test.info("Price = " + getStrongText("" + price) + ", Tax(%) = " + getStrongText(skuTaxPerSO));

			double gstCal = ((price * Double.valueOf(getHelperClass().getDecimalNumber(skuTaxPerSO))) / 100);
			getCommonAction().checkAssertDouble(Double.valueOf(omsGst), gstCal, "GST ");

			double grandTotalCal = price + gstCal;

			test.info("After Adding Tax amount " + getStrongText(omsGst) + ", Price = " + getStrongText(omsGrandTotal));
			getCommonAction().checkAssertDouble(Double.valueOf(omsGrandTotal), grandTotalCal, "Grand Total");
			test.info("Grand Total = " + getStrongText(omsGrandTotal));
		}

		// To create SO Order
		customClick(Create_Quote_and_Order.getPlace_Order_Button());
		getWebdriverAction().thread_sleep(500);

		if (Success.getKyc_pending_error_message().isDisplayed()) {
			takeScreenShort(getStrongText("Sales Order is not Create due to KYC pending "), "Sales Order is not Created due to KYC pending");
			softassert.assertEquals(isSuccess, true, "Test case" + " is successful passed");
			String msgStatus = "Assertion of " + getStrongText("Sales Order is not Create due to KYC pending") + " is passed " ;
			test.info("<span style='background-color:#32CD32; color:white'>" + msgStatus + "</span>");
		}
		else
		{
			isSuccess = false;
			softassert.assertEquals(isSuccess, true, "Test case" + " is failed");
			String msgStatus = "Assertion of " + getStrongText("Test case") + " is failed ";
			test.info("<span style='background-color:red; color:white'>" + msgStatus + "</span>");
			takeScreenShort(getStrongText("Sales Order is Create even KYC pending "), "Sales Order is  Created even KYC pending");
		}	
	}
}


