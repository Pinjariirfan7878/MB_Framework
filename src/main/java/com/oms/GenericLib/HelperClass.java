package com.oms.GenericLib;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.ITestListener;

import com.ObjectRepo.Create_PO_OMS_MedikabazaarPage;

public class HelperClass extends BaseUtilityClass implements ITestListener {

	public void fillPO_EditShippingAndBillingAdresses() {
		// Update Shipping, Billing Adresses
		WebElement editSupplierAddress = getWebDriver()
				.findElement(By.xpath("//button[contains(text(),'Supplier Address')]/../following-sibling::button"));
		WebElement firstName = getWebDriver().findElement(By.xpath("//input[@id='bfname']"));
		WebElement lastName = getWebDriver().findElement(By.xpath("//input[@id='blname']"));
		WebElement mobiNumber = getWebDriver().findElement(By.xpath("//input[@id='btelephone']"));
		WebElement city = getWebDriver().findElement(By.xpath("//input[@id='bcity']"));
		WebElement streetAddress = getWebDriver().findElement(By.xpath("//textarea[@id='baddrees']"));
		WebElement pinCode = getWebDriver().findElement(By.xpath("//input[@id='bpostcode']"));
		WebElement country = getWebDriver().findElement(By.xpath("//select[@id='bcountry']")); // India
		WebElement state = getWebDriver().findElement(By.xpath("//select[@id='bstate_select']")); // Gujarat
		WebElement checkAsDefault = getWebDriver().findElement(By.xpath("//input[@id='flexCheckDefault']"));
		WebElement updateAddress = getWebDriver().findElement(By.xpath("//button[contains(text(),'Update Address')]"));

		customSendText(editSupplierAddress, "Supplier Address");
		customSendText(firstName, "Supplier firstName");
		customSendText(lastName, "Supplier lastName");
		customSendText(mobiNumber, "9000000000");
		customSendText(city, "Mumbai");
		customSendText(streetAddress, "Supplier streetAddress1, streetAddress2");
		customSendText(pinCode, "400086");

		getWebdriverAction().select_dd_by_visibletext("India", country);
		getWebdriverAction().select_dd_by_visibletext("Gujarat", state);

		customClick(checkAsDefault);
		customClick(checkAsDefault);
		customClick(updateAddress);
	}

	public String getDecimalNumber(String number) {
		String num = "";
		char[] ch = number.toCharArray();
		boolean isDigitChar = false;
		
		for (char c : ch) {
			
			if(Character.isDigit(c)) {
				isDigitChar = true;
			}
			
			if ((Character.isDigit(c) || c == '.') && isDigitChar) {
				num = num + c;
			}
		}
		return num;
	}

	public double getRoundofTwoDecimalNumber(String num) {
		num = String.format("%.2f", Double.valueOf(num));
		return Double.valueOf(num);
	}

	public void calculationOfDiscount_PO(Create_PO_OMS_MedikabazaarPage create_PO_page, String PO_Qty,
			String flatDiscount, String skuDisPer, String shipping_charge, String taxPer) {

		String skuPrice = create_PO_page.getSkuSupplierPrice().getAttribute("value");
		String omsSubtotal = getDecimalNumber(create_PO_page.getSubtotalText().getText());
		String omsDiscountAmt = getDecimalNumber(create_PO_page.getDiscountAmtText().getText());
		String omsGstAmt = getDecimalNumber(create_PO_page.getGstText().getText());
		String omsGrandTotal = getDecimalNumber(create_PO_page.getGrandTotalText().getText());

		// 1. Calculate , Subtotal = skyQnt* SupplerPrice
		double calSubTotal = Double.valueOf(PO_Qty) * Double.valueOf(skuPrice);
		test.info("SKU Price = " + getStrongText(skuPrice) + ", SKU Quantity = " + getStrongText(PO_Qty));

		getCommonAction().checkAssertDouble(Double.valueOf(omsSubtotal), calSubTotal, "Sub Total ");

		if (flatDiscount.trim().equals("0")) {
			double calDiscountAmount = ((calSubTotal * Double.valueOf(skuDisPer)) / 100.00d);
			test.info("Price = " + getStrongText(calSubTotal + "") + ", SKU DisCount(%) = " + getStrongText(skuDisPer));
			calDiscountAmount = getRoundofTwoDecimalNumber(calDiscountAmount + "");
			getCommonAction().checkAssertDouble(Double.valueOf(omsDiscountAmt), calDiscountAmount, "Discount Amount ");

			// Amount after Discount
			calSubTotal = calSubTotal - calDiscountAmount;
			test.info("After Discount of " + getStrongText("" + calDiscountAmount) + ", Price = "
					+ getStrongText("" + calSubTotal));

			// After adding shipping charges
			calSubTotal = calSubTotal + Double.valueOf(shipping_charge);
			test.info("After Adding Shipment amount " + getStrongText(shipping_charge) + " Price = "
					+ getStrongText("" + calSubTotal));
		}

		// Tax charges calculation
		double calTaxAmt = getRoundofTwoDecimalNumber(((calSubTotal * Double.valueOf(taxPer)) / 100.00d) + "");
		test.info("Price = " + getStrongText(calSubTotal + "") + ", Tax(%) = " + getStrongText(taxPer));
		getCommonAction().checkAssertDouble(Double.valueOf(omsGstAmt), calTaxAmt, "Tax Amount ");

		// After adding Tax charges
		calSubTotal = getRoundofTwoDecimalNumber((calSubTotal + calTaxAmt) + "");
		test.info("After Adding Tax amount " + getStrongText("" + calTaxAmt) + ", Price = "
				+ getStrongText("" + calSubTotal));

		if (!(flatDiscount.trim().equals("0"))) {

			calSubTotal = getRoundofTwoDecimalNumber((calSubTotal - Double.valueOf(flatDiscount)) + "");
			test.info("After Flat Discount of " + getStrongText(flatDiscount) + ", Price = "
					+ getStrongText("" + calSubTotal));
		}

		getCommonAction().checkAssertDouble(Double.valueOf(omsGrandTotal), calSubTotal, "Grand Total");
	}

	public String getWebElementXpath(WebElement ele) {
		String s = ele.toString();
		String arr[] = s.split(" ");

		int index = 0;
		for (String str : arr) {
			if (str.contains("xpath")) {
				break;
			}
			index++;
		}
		String str = arr[index + 1].trim();
		str = str.substring(0, str.length() - 1);
		return str;
	}

	/**
	 * This method is use to get fail statement for of SoftAssert
	 */
	public String getFailSatatement(String msg) {
		String[] result = msg.split("],");

		String outPut = "";

		for (String str : result) {
			outPut = outPut + "<strong>" + str + "]</strong></br>";
		}

		return outPut;
	}

	public void clickOnSubmitButton(WebElement ele) {
		if (WebEnvironment.equals("ProtaDev")) {
			WebElement submitBtn = getWebDriver().findElement(By.xpath("(//button[contains(text(),'Submit')])[1]"));
			scrollIntoView(submitBtn);
			customClick(submitBtn);
		} else if (WebEnvironment.equals("Oms")) {
			//customClick(PO_master_page.getSumbit_button());
			customClick(ele);
		}
	}
	
	public String getDate(int days, String formate) { // "yyyy-MM-dd"

		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat format1 = new SimpleDateFormat(formate);

		calendar.add(Calendar.DATE, days);
		String jDate = format1.format(calendar.getTime());
		System.out.println("After " + days + " days : ===>" + jDate);
		return jDate;
	}
}