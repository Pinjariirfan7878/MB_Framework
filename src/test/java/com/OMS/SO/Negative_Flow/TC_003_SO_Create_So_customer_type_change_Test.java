package com.OMS.SO.Negative_Flow;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ObjectRepo.Create_Quote_and_Order_OMS_Medikabazaar_Page;
import com.ObjectRepo.Customer_OMS_Medikabazaar_Page;
import com.ObjectRepo.Customer_Orders_OMS_Medikabazaar_Page;
import com.ObjectRepo.Getting_Started_OMS_MedikabazaarPage;
import com.ObjectRepo.Success_OMS_Medikabazaar_Page;
import com.oms.GenericLib.BaseUtilityClass;
import com.oms.GenericLib.FileLibClass;
import com.oms.GenericLib.IAutoconstant;

@Listeners(com.oms.GenericLib.MylistenerClass.class)
public class TC_003_SO_Create_So_customer_type_change_Test extends BaseUtilityClass{
	
	String testCaseDescription = "Create_So_customer_type_change ";
	@Test
	public void Create_So_customer_type_change_Test()
	{
		String currentClassName = this.getClass().getSimpleName();
		setTestCaseAndDescriptionInReport("SO Negative", currentClassName, testCaseDescription);
		
		String SKU_Name=getFileLibClass().getDataFromExcell("Create_So_customer_type_change", "SKU_Name", IAutoconstant.EXCEL_TestData);
		String SKU_QTY=getFileLibClass().getDataFromExcell("Create_So_customer_type_change", "SKU_Qty", IAutoconstant.EXCEL_TestData);
		String Shipping_price=getFileLibClass().getDataFromExcell("Create_So_customer_type_change", "Shipping_price", IAutoconstant.EXCEL_TestData);
		String customer_name=getFileLibClass().getDataFromExcell("Create_So_customer_type_change", "customer_name", IAutoconstant.EXCEL_TestData);
		String customer_email=getFileLibClass().getDataFromExcell("Create_So_customer_type_change", "customer_email", IAutoconstant.EXCEL_TestData);

		Getting_Started_OMS_MedikabazaarPage getting_started = new Getting_Started_OMS_MedikabazaarPage(getWebDriver());
		Customer_OMS_Medikabazaar_Page custpage = new Customer_OMS_Medikabazaar_Page(getWebDriver());
		FileLibClass filclass = new FileLibClass();
		
		test.info(" Customer name : " +getStrongText(customer_name) + ", customer Email = " + getStrongText(customer_email));
		
		customClick(getting_started.getCustomers_button());
		clickOnSubMenu(getting_started.getCustomers_button(), getting_started.getCustomerMaster_tab(), globalTime);
		customSendText(custpage.getCustomer_name_text_field(), customer_name);
		customSendText(custpage.getCustomet_email_text_field(),customer_email);
		customClick(custpage.getSearch_button());
		customClick(custpage.getCustomer_id());
		getWebdriverAction().thread_sleep(1000);
		String before_cutomer_type = custpage.getType_of_Customer().getText();
		test.info("Initial customer type : "+ getStrongText(before_cutomer_type));
		customClick(custpage.getVerification_Status_Comments_tab());
		getWebdriverAction().thread_sleep(1000);
		highLight(custpage.getL1_approved_text());
		String actual_L1_approved_text = custpage.getL1_approved_text().getText();
		takeScreenShort(getStrongText("Initial Customer L1 status "), "customer with L1 status ");
		test.info("Initial L1 status : "+ getStrongText(actual_L1_approved_text));
		String expected_L1_approved_text = "Approved";
		getCommonAction().checkAssertString(actual_L1_approved_text, expected_L1_approved_text, "customer found with L1 ");
		
		customClick(custpage.getDetails_tab());
		customClick(custpage.getChange_customer_type_button());
		getWebdriverAction().select_dd_by_value(custpage.getChange_Customer_type_dd(), "11");
		getWebdriverAction().thread_sleep(1000);
		String after_cutomer_type = custpage.getType_of_Customer().getText();
		test.info("after change customer type : "+ getStrongText(after_cutomer_type));
		customClick(custpage.getVerification_Status_Comments_tab());
		getWebdriverAction().thread_sleep(1000);
		highLight(custpage.getL1_approved_text());
		String after_actual_L1_approved_text = custpage.getL1_approved_text().getText();
		takeScreenShort("customer L1 status after change customer type : "+getStrongText(after_actual_L1_approved_text), "customer with L1 status ");
		if(!expected_L1_approved_text.equalsIgnoreCase(after_actual_L1_approved_text))
		{
			test.info(getStrongText("Customer Type changed so approval is revoked."));
		}
		else {
			test.info(getStrongText("Customer Type changed but approval is not revoked."));
		}
//create So without L1 approve
		get_Create_SO(customer_email, SKU_Name, SKU_QTY,Shipping_price,false );
		
//default setting for customer
		customClick(getting_started.getCustomers_button());
		clickOnSubMenu(getting_started.getCustomers_button(), getting_started.getCustomerMaster_tab(), globalTime);
		customSendText(custpage.getCustomer_name_text_field(), customer_name);
		customSendText(custpage.getCustomet_email_text_field(),customer_email);
		customClick(custpage.getSearch_button());
		customClick(custpage.getCustomer_id());
		getWebdriverAction().thread_sleep(1000);
		customClick(custpage.getChange_customer_type_button());
		getWebdriverAction().select_dd_by_value(custpage.getChange_Customer_type_dd(), "6");
		getWebdriverAction().thread_sleep(1000);
		customClick(custpage.getVerification_Status_Comments_tab());
		getWebdriverAction().thread_sleep(1000);
		getWebdriverAction().select_dd_by_value(custpage.getStatus_dd(), "1");
		String Comment1 = filclass.readData_TestData("ADD_SUPPLIER_Data",4,2);
		custpage.getComment().sendKeys(Comment1);
		customClick(custpage.getSubmit_btn());	
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//*************************************common Functions*******************************************************************************************************
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
				takeScreenShort(getStrongText("Sales Order is not Create due to L1 pending "), "Sales Order is not Created due to L1 pending");
				softassert.assertEquals(isSuccess, true, "Test case" + " is successful passed");
				String msgStatus = "Assertion of " + getStrongText("Sales Order is not Create due to L1 pending ") + " is passed " ;
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
