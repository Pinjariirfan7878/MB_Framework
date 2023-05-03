package com.OMS.Interbranch;

import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ObjectRepo.Create_Inter_Branch_Shipment_OMS_Medikabazaar_Page;
import com.ObjectRepo.Enabled_Products_OMS_Medikabazaar_Page;
import com.ObjectRepo.Getting_Started_OMS_MedikabazaarPage;
import com.ObjectRepo.Inter_Branch_Orders_OMS_Medikabazaar_Page;
import com.ObjectRepo.Logistic_Detail_OMS_Medikabazaar_Page;
import com.ObjectRepo.Update_Finance_Details_OMS_Medikabazaar_Page;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.oms.GenericLib.BaseUtilityClass;
import com.oms.GenericLib.CommonActionClass;
import com.oms.GenericLib.FileLibClass;
import com.oms.GenericLib.IAutoconstant;
import com.oms.GenericLib.RetryanalyserClass;
import com.oms.GenericLib.WebdriverActionclass;

//@Listeners(com.oms.GenericLib.MylistenerClass.class)
public class TC_001_IB_End_To_End_Test extends BaseUtilityClass{

	@Test(retryAnalyzer = RetryanalyserClass.class)
	public void Interbranch__end_to_end_Test() throws Throwable
	{
		Getting_Started_OMS_MedikabazaarPage getting_started = new Getting_Started_OMS_MedikabazaarPage(driver);
		Inter_Branch_Orders_OMS_Medikabazaar_Page inter_branch_orders = new Inter_Branch_Orders_OMS_Medikabazaar_Page(driver);
		Create_Inter_Branch_Shipment_OMS_Medikabazaar_Page create_inter_branch_shipment = new Create_Inter_Branch_Shipment_OMS_Medikabazaar_Page(driver);
		Enabled_Products_OMS_Medikabazaar_Page enabled_products = new Enabled_Products_OMS_Medikabazaar_Page(driver);
		Logistic_Detail_OMS_Medikabazaar_Page logistic_detail = new Logistic_Detail_OMS_Medikabazaar_Page(driver);
		Update_Finance_Details_OMS_Medikabazaar_Page update_finance_details = new Update_Finance_Details_OMS_Medikabazaar_Page(driver);
		WebdriverActionclass webaction=new WebdriverActionclass(driver);
		FileLibClass filclass=new FileLibClass();

		Reporter.log("Test Case ID:0001",true);
		test.info("Home page is opened");
		String Product_SKU=filclass.readData_TestData("IB_Data",2,2);	
		String mumbaiCount = CommonActionClass.getCDCStockCount(Product_SKU,"32",driver);

		int initialFromCount = Integer.parseInt(mumbaiCount);
		System.out.println("Mumbai initially stock is "+mumbaiCount);
		
		String ahmedabadInitialStock = CommonActionClass.getCDCStockCount(Product_SKU,"17",driver);
		int initialDestinationCount = Integer.parseInt(ahmedabadInitialStock);
		test.info("Ahmedabad Stock before Stock Inward "+ahmedabadInitialStock);
		Reporter.log("Ahmedabad Stock before Stock Inward "+ahmedabadInitialStock,true);
	
		test.info("Stock of the SKU before Inter Branch Transfer in Mumbai CDC "+ mumbaiCount);
		Reporter.log("Stock of the SKU before Inter Branch Transfer in Mumbai CDC "+ mumbaiCount,true);

		getting_started.getOrder_button().click();
		webaction.explicitlywait_elementtobeclickable(getting_started.getinterbranch_tab());
		getting_started.getinterbranch_tab().click();
		inter_branch_orders.getPurchase_tab().click();
		test.info("Interbranch create page is opened");
		create_inter_branch_shipment.getSource_Warehouse_dd().click();
		webaction.select_dd_by_value(create_inter_branch_shipment.getSource_Warehouse_dd(),"32");
		create_inter_branch_shipment.getDestination_Warehouse_dd().click();
		webaction.select_dd_by_value(create_inter_branch_shipment.getDestination_Warehouse_dd(),"17");
		webaction.explicitlywait_elementtobeclickable(create_inter_branch_shipment.getRecipient_Email_Label());
		Thread.sleep(3000);
		
		create_inter_branch_shipment.getRecipient_Email_Label().clear();
		String Receipient_Email=filclass.readData_TestData("IB_Data",1,2);
		create_inter_branch_shipment.getRecipient_Email_Label().sendKeys(Receipient_Email);
		webaction.explicitlywait_elementtobeclickable(create_inter_branch_shipment.getIB_submit_button());
		create_inter_branch_shipment.getIB_submit_button().click();
		try {
		webaction.alert_accept();
		}
		catch (NoAlertPresentException Ex )
		{
			System.out.println("Exception occured is"+ Ex);
		}
		Thread.sleep(2000);
		if(create_inter_branch_shipment.getSuccess_Message_Display().isDisplayed())
		{
			String path=concatenate+webaction.screenshot_IB("Interbranch order info updated");
			test.info("Interbranch Order Information Updated",MediaEntityBuilder.createScreenCaptureFromPath(path).build());
			Reporter.log("Step:1 Inter Branch Order Information Updated Successfully and Verification Passed",true);
		}
		else
		{
			String path=concatenate+webaction.screenshot_IB("Interbranch order info is not updated");
			test.info("Interbranch Order Information is not Updated",MediaEntityBuilder.createScreenCaptureFromPath(path).build());
			Reporter.log("Step:1 Inter Branch Order Information  Not Updated and Verification Failed",true);
		}
		create_inter_branch_shipment.getProduct_List_button().click();
		
		enabled_products.getProduct_List_search_Label().sendKeys(Product_SKU);
		Thread.sleep(15000);
		webaction.explicitlywait_elementtobeclickable(enabled_products.getCheck_Box());
		enabled_products.getCheck_Box().click();
		enabled_products.getQty_to_add_Label().clear();
		String SKU_Qty=filclass.readData_TestData("IB_Data",3,2);
		enabled_products.getQty_to_add_Label().sendKeys(SKU_Qty);
		webaction.scrollBy_top();
		Thread.sleep(1000);
		webaction.explicitlywait_elementtobeclickable(enabled_products.add_to_IB_button());
		enabled_products.add_to_IB_button().click();
		Thread.sleep(10000);
		test.info("Products added");
		webaction.scrollBy_bottom();
		//Save
		webaction.explicitlywait_elementtobeclickable(create_inter_branch_shipment.getIB_Save_button());
		Thread.sleep(2000);
		create_inter_branch_shipment.getIB_Save_button().click();
		Thread.sleep(5000);
		webaction.scrollBy_bottom();
		//Create
		webaction.explicitlywait_elementtobeclickable(create_inter_branch_shipment.getIB_Create_button());
		create_inter_branch_shipment.getIB_Create_button().click();
		inter_branch_orders.getRecipient_Email_Label().sendKeys(Receipient_Email);
		webaction.explicitlywait_elementtobeclickable(inter_branch_orders.getSubmit_button());
		inter_branch_orders.getSubmit_button().click();
		String OrderID = inter_branch_orders.getIB_ID_field().getText();
		inter_branch_orders.getIB_ID_field().click();
		webaction.scrollBy_bottom();
		webaction.explicitlywait_visibilityofallelements(logistic_detail.getPick_and_pack_text());
		if(logistic_detail.getPick_and_pack_text().isDisplayed())
		{
			String path=concatenate+webaction.screenshot_IB("Interbranch order created");
			test.info("Inter Branch Order Created Successfully",MediaEntityBuilder.createScreenCaptureFromPath(path).build());
			Reporter.log("Step:2 Inter Branch Order Created Successfully and Verification Passed",true);
		}
		else
		{
			String path=concatenate+webaction.screenshot_IB("Interbranch order is not created");
			test.info("Inter Branch Order is not Created",MediaEntityBuilder.createScreenCaptureFromPath(path).build());
			Reporter.log("Step:2 Inter Branch Order Not Created and Verification Failed",true);
		}
		test.info("Interbranch Order ID: "+OrderID);
		Reporter.log("-------Interbranch Order ID: "+OrderID,true);
        webaction.scrollBy_top();
        Thread.sleep(1500);
		logistic_detail.getCourier_Type_dd().click();
		webaction.select_dd_by_value(logistic_detail.getCourier_Type_dd(),"2");
		logistic_detail.getGST_Available_dd().click();
		webaction.select_dd_by_value(logistic_detail.getGST_Available_dd(),"Yes");
		String Estimated_TAT_In_Days=filclass.readData_TestData("IB_Data",4,2);
		logistic_detail.getEst_TAT_Label().sendKeys(Estimated_TAT_In_Days);
		String Distance_In_KM=filclass.readData_TestData("IB_Data",5,2);
		logistic_detail.getDistance_Label().sendKeys(Distance_In_KM);
		
		logistic_detail.getDatepicker_Label().click();
		logistic_detail.currentDate().click();
		logistic_detail.getOrder_Status_dd().click();
		webaction.select_dd_by_value(logistic_detail.getOrder_Status_dd(),"22");
		String Comment1=filclass.readData_TestData("IB_Data",6,2);
		logistic_detail.getComment_Label().sendKeys(Comment1);
		String Transporter_GST_No=filclass.readData_TestData("IB_Data",7,2);
		logistic_detail.getGST_Invoice_No_Label().sendKeys(Transporter_GST_No);
		String Transporter_Name=filclass.readData_TestData("IB_Data",8,2);
		logistic_detail.getTransporter_Name_Label().sendKeys(Transporter_Name);
		webaction.explicitlywait_elementtobeclickable(logistic_detail.getLogistic_submit_button());
		logistic_detail.getLogistic_submit_button().click();
		webaction.explicitlywait_elementtobeclickable(logistic_detail.getupdate_finance_info_button());
		logistic_detail.getupdate_finance_info_button().click();
		String Invoice_Number=filclass.readData_TestData("IB_Data",9,2);
		update_finance_details.getInvoice_Number_Label().sendKeys(Invoice_Number);
		update_finance_details.getInvoice_Date_label().sendKeys(Keys.ENTER);
		String pdf=IAutoconstant.Pdf_dummy;
		update_finance_details.getInvoice_Attachmente_label().sendKeys(pdf);
		String eway_Bill_No=filclass.readData_TestData("IB_Data",10,2);
		update_finance_details.geteway_Bill_No_label().sendKeys(eway_Bill_No);
		update_finance_details.getSave_button().click();
		test.info("Finance Infomation Updated");
		webaction.explicitlywait_elementtobeclickable(update_finance_details.getBack_button());
		update_finance_details.getBack_button().click();
		logistic_detail.getOrder_Status_dd().click();
		webaction.select_dd_by_value(logistic_detail.getOrder_Status_dd(),"1");
		String Comment2=filclass.readData_TestData("IB_Data",11,2);
		logistic_detail.getComment_Label().sendKeys(Comment2);
		webaction.explicitlywait_elementtobeclickable(logistic_detail.getLogistic_submit_button());
		logistic_detail.getLogistic_submit_button().click();
		logistic_detail.getOrder_Status_dd().click();
		webaction.select_dd_by_value(logistic_detail.getOrder_Status_dd(),"2");
		String Comment3=filclass.readData_TestData("IB_Data",12,2);
		logistic_detail.getComment_Label().sendKeys(Comment3);
		webaction.explicitlywait_elementtobeclickable(logistic_detail.getLogistic_submit_button());
		logistic_detail.getLogistic_submit_button().click();
		String Comment4=filclass.readData_TestData("IB_Data",13,2);
		logistic_detail.getComment_Label().sendKeys(Comment4);
		Thread.sleep(5000);
		webaction.scrollBy_bottom();
		Thread.sleep(3000);
		webaction.explicitlywait_elementtobeclickable(logistic_detail.getSelect_All());
		logistic_detail.getSelect_All().click();
		Thread.sleep(3000);
		webaction.explicitlywait_elementtobeclickable(logistic_detail.getInward_Stock_button());
		logistic_detail.getInward_Stock_button().click();
		webaction.scrollBy_bottom();
		
		webaction.explicitlywait_visibilityofallelements(logistic_detail.getStock_inwarded_text());
		if(logistic_detail.getStock_inwarded_text().isDisplayed())
		{
			String path=concatenate+webaction.screenshot_IB("Inter Branch Order Stock Inwarded");
			test.info("Inter Branch Order Stock Inwarded Successfully",MediaEntityBuilder.createScreenCaptureFromPath(path).build());
			Reporter.log("Step:3 Inter Branch Order Stock Inwarded Successfully and Verification Passed",true);
		}
		else
		{
			String path=concatenate+webaction.screenshot_IB("Inter Branch Order Stock is not Inwarded");
			test.info("Inter Branch Order Stock is not Inwarded",MediaEntityBuilder.createScreenCaptureFromPath(path).build());
			Reporter.log("Step:3 Inter Branch Order Not Inwarded and Verification Failed",true);
		}
		test.info("After Inwarded GRN No: "+logistic_detail.getGRN_Number_text().getText());
		Reporter.log("After Inwarded GRN No: "+logistic_detail.getGRN_Number_text().getText(),true);
		String finalAhmedabadStock = CommonActionClass.getCDCStockCount(Product_SKU, "17",driver);
		int finalDestinationStock = Integer.parseInt(finalAhmedabadStock);
		boolean success;
		if (initialDestinationCount+1 == finalDestinationStock)
		{
			Reporter.log("Ahmedabad Stock after Stock inward "+finalAhmedabadStock,true);
			test.info("Ahmedabad Stock after Stock inward "+finalAhmedabadStock);
			success = true;
		}
		else
		{
			Reporter.log("Ahmedabad Stock is not updated after inward "+finalAhmedabadStock,true);
			test.info("Ahmedabad Stock is not updated after inward "+finalAhmedabadStock);
			success = false;
		}
		
		String finalMumbaiStock = CommonActionClass.getCDCStockCount(Product_SKU, "32",driver);
		int finalFromStock = Integer.parseInt(finalMumbaiStock);
		if(initialFromCount-1 == finalFromStock)
		{
			Reporter.log("Mumbai Stock after Interbranch "+finalMumbaiStock,true);
			test.info("Mumbai Stock after Stock after Interbranch "+finalMumbaiStock);
			success = true;
		}
		else
		{
			Reporter.log("Mumbai Stock after Interbranch is not updated "+finalMumbaiStock,true);
			test.info("Mumbai Stock after Interbranch is not updated "+finalMumbaiStock);
			success=false;
		}
		softassert.assertEquals(success, true,"The Interbranch Test is successful");
	}
	
}
