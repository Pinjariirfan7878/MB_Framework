package com.OMS.Supplier_Master;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ObjectRepo.Getting_Started_OMS_MedikabazaarPage;
import com.ObjectRepo.Supplier_Details_OMS_Medikabazaar_Page;
import com.ObjectRepo.Supplier_Master_OMS_Medikabazaar_Page;
import com.oms.GenericLib.BaseUtilityClass;
@Listeners(com.oms.GenericLib.MylistenerClass.class)
public class inactive extends BaseUtilityClass{

	@Test
	public void run() {
		inactive_Supplier_test("Callie","callie.olson@example.com");
	}
	
	public void inactive_Supplier_test(String supplier_name,String supplier_email) {
		Getting_Started_OMS_MedikabazaarPage getting_started = new Getting_Started_OMS_MedikabazaarPage(getWebDriver());
		Supplier_Master_OMS_Medikabazaar_Page Supplier_Master = new Supplier_Master_OMS_Medikabazaar_Page(getWebDriver());
		Supplier_Details_OMS_Medikabazaar_Page Supplier_Details = new Supplier_Details_OMS_Medikabazaar_Page(getWebDriver());

		customClick(getting_started.getSuppliers_tab());
		customClick(getting_started.getSupplier_Master_tab());
		customSendText(Supplier_Master.getSupplier_Name_Label(), supplier_name);	
		customSendText(Supplier_Master.getSupplier_email_text_field(), supplier_email);
		customClick(Supplier_Master.getSearch_button());
		customClick(Supplier_Master.getSupplier_ID());
		customClick(Supplier_Details.getStatus_button());
		customClick(Supplier_Master.getBack_button());
		customSendText(Supplier_Master.getSupplier_Name_Label(), supplier_name);	
		customSendText(Supplier_Master.getSupplier_email_text_field(), supplier_email);
		customClick(Supplier_Master.getSearch_button());
		
		waitForVisibleElement(Supplier_Master.getSupplier_status_text(), globalTime);
		scrollIntoView(Supplier_Master.getSupplier_status_text());
		highLight(Supplier_Master.getSupplier_status_text());
		String supplier_status = Supplier_Master.getSupplier_status_text().getText();
		takeScreenShort(getStrongText("Supplier status")," Supplier status");
		System.out.println(supplier_status);
		String expected_status = "Inactive";
		getCommonAction().checkAssertString(supplier_status,expected_status , "inactive_Supplier_test");
	
		
	}
}
