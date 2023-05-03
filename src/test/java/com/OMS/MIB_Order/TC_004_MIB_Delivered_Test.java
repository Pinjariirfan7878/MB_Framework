package com.OMS.MIB_Order;

import org.openqa.selenium.Keys;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ObjectRepo.Create_MIB_Quote_and_Order_OMS_Medikabazaar_Page;
import com.ObjectRepo.Customer_Orders_OMS_Medikabazaar_Page;
import com.ObjectRepo.Getting_Started_OMS_MedikabazaarPage;
import com.ObjectRepo.MIB_Quote_Approval_OMS_Medikabazaar_Page;
import com.ObjectRepo.Pick_And_Pack_OMS_Medikabazaar_Page;
import com.ObjectRepo.Pick_And_Pack_View_OMS_Medikabazaar_Page;
import com.ObjectRepo.Sales_Order_Shipment_OMS_Medikabazaar_Page;
import com.ObjectRepo.Shipment_Status_OMS_Medikabazaar_Page;
import com.ObjectRepo.Success_OMS_Medikabazaar_Page;
import com.ObjectRepo.Update_Finance_Details_OMS_Medikabazaar_Page;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.oms.GenericLib.BaseUtilityClass;
import com.oms.GenericLib.FileLibClass;
import com.oms.GenericLib.IAutoconstant;
import com.oms.GenericLib.RetryanalyserClass;
import com.oms.GenericLib.WebdriverActionclass;

//@Listeners(com.oms.GenericLib.MylistenerClass.class)
public class TC_004_MIB_Delivered_Test extends BaseUtilityClass {
	
	@Test(retryAnalyzer = RetryanalyserClass.class)	
	public void MIB_delivered_test() throws Exception
	{
		Getting_Started_OMS_MedikabazaarPage getting_started = new Getting_Started_OMS_MedikabazaarPage(driver);
		WebdriverActionclass webaction = new WebdriverActionclass(driver);
		FileLibClass filclass = new FileLibClass();
		MIB_Quote_Approval_OMS_Medikabazaar_Page MIB_Quote_Approval = new MIB_Quote_Approval_OMS_Medikabazaar_Page(driver);
		Create_MIB_Quote_and_Order_OMS_Medikabazaar_Page Create_MIB_Quote_and_Order = new Create_MIB_Quote_and_Order_OMS_Medikabazaar_Page(driver);
		Success_OMS_Medikabazaar_Page Success = new Success_OMS_Medikabazaar_Page(driver);
		Customer_Orders_OMS_Medikabazaar_Page Customer_Orders = new Customer_Orders_OMS_Medikabazaar_Page(driver);
		Sales_Order_Shipment_OMS_Medikabazaar_Page Sales_Order_Shipment = new Sales_Order_Shipment_OMS_Medikabazaar_Page(driver);
		Pick_And_Pack_OMS_Medikabazaar_Page Pick_And_Pack = new Pick_And_Pack_OMS_Medikabazaar_Page(driver);
		Pick_And_Pack_View_OMS_Medikabazaar_Page Pick_And_Pack_View = new Pick_And_Pack_View_OMS_Medikabazaar_Page(driver);
		Shipment_Status_OMS_Medikabazaar_Page Shipment_Status = new Shipment_Status_OMS_Medikabazaar_Page(driver);
		Update_Finance_Details_OMS_Medikabazaar_Page Update_Finance_Details = new Update_Finance_Details_OMS_Medikabazaar_Page(driver);

		webaction.explicitlywait_elementtobeclickable(getting_started.getOrder_button());
		getting_started.getOrder_button().click();
		getting_started.getMIB_Order_button().click();
		Thread.sleep(2000);
		webaction.explicitlywait_elementtobeclickable(MIB_Quote_Approval.getCreate_MIB_Order_button());
		MIB_Quote_Approval.getCreate_MIB_Order_button().click();
		String Customer_Email_ID = filclass.readData_TestData("MIB_Data",1,2);
		Create_MIB_Quote_and_Order.getCustomer_Email_Label().sendKeys(Customer_Email_ID);
		Create_MIB_Quote_and_Order.getEmail_submit_button().click();
		Thread.sleep(2000);

		webaction.scrollBy_value("0","400");
		Create_MIB_Quote_and_Order.getSKU_Name_Label().clear();
		Thread.sleep(4000);
		String SKU_Name = filclass.readData_TestData("MIB_Data",2,2);
		Create_MIB_Quote_and_Order.getSKU_Name_Label().sendKeys(SKU_Name);
		Create_MIB_Quote_and_Order.getSKU_Qty_Label().clear();
		Thread.sleep(2000);
		String SKU_Qty = filclass.readData_TestData("MIB_Data",3,2);
		Create_MIB_Quote_and_Order.getSKU_Qty_Label().sendKeys(SKU_Qty);
		webaction.explicitlywait_elementtobeclickable(Create_MIB_Quote_and_Order.getSKU_submit_button());
		Create_MIB_Quote_and_Order.getSKU_submit_button().click();
		Thread.sleep(8000);
		test.info("MIB Order details updated");
		webaction.scrollBy_value("0","900");
		Thread.sleep(1500);
		Create_MIB_Quote_and_Order.getwarranty_period_Label().clear();
		String warranty_period = filclass.readData_TestData("MIB_Data",4,2);
		Create_MIB_Quote_and_Order.getwarranty_period_Label().sendKeys(warranty_period);
		//webaction.explicitlywait_elementtobeclickable(Create_MIB_Quote_and_Order.getSave_button());
		Thread.sleep(2000);
		Create_MIB_Quote_and_Order.getSave_button().click();
		//webaction.scrollBy_bottom();
		webaction.scrollBy_value("0","1000");
		Thread.sleep(2000);
		Create_MIB_Quote_and_Order.getRequest_to_verify_budget_button().click();
		if(Create_MIB_Quote_and_Order.getBudget_approval_status_message().isDisplayed())
		{
			String path=concatenate+webaction.screenshot_MIB("MIB send for approval");
			test.info("MIB order Quote Budget Sent for approval",MediaEntityBuilder.createScreenCaptureFromPath(path).build());
			Reporter.log("Step:1 MIB order Quote Budget Sent Successfully! and Verification Passed",true);
		}
		else
		{
			String path=concatenate+webaction.screenshot_MIB("MIB is not send for approval");
			test.info("MIB order Quote Budget is not Send for approval",MediaEntityBuilder.createScreenCaptureFromPath(path).build());
			Reporter.log("Step:1 MIB order Quote Budget NOT send for approval and Verification Failed",true);
		}
		webaction.explicitlywait_elementtobeclickable(getting_started.getOrder_button());
		getting_started.getOrder_button().click();
		getting_started.getMIB_Order_button().click();
		MIB_Quote_Approval.getCustomer_Email_Label().sendKeys(Customer_Email_ID);
		MIB_Quote_Approval.getEmail_search_button().click();
		Thread.sleep(2000);
		String quote_id = MIB_Quote_Approval.getMIB_quote_ID().getText();
		System.out.println("MIB Quote ID: "+quote_id);
		test.info("MIB Quote ID: "+quote_id);
		webaction.explicitlywait_elementtobeclickable(MIB_Quote_Approval.getMIB_quote_ID());
		MIB_Quote_Approval.getMIB_quote_ID().click();
		MIB_Quote_Approval.getBudget_Approval_dd().click();
		webaction.select_dd_by_value(MIB_Quote_Approval.getBudget_Approval_dd(), "2");
		String comment1 = filclass.readData_TestData("MIB_Data",5,2);
		MIB_Quote_Approval.getBudget_Approval_comment().sendKeys(comment1);
		MIB_Quote_Approval.getBudget_Approval_submit_button().click();
		if(MIB_Quote_Approval.getApproved_Green_Label().isDisplayed())
		{
			String path=concatenate+webaction.screenshot_MIB("MIB approved");
			test.info("MIB order Quote Budget Approved",MediaEntityBuilder.createScreenCaptureFromPath(path).build());
			Reporter.log("Step:2 MIB order Quote Budget Approved Successfully! and Verification Passed",true);
		}
		else
		{
			String path=concatenate+webaction.screenshot_MIB("MIB is not approved");
			test.info("MIB order Quote Budget is not Approve",MediaEntityBuilder.createScreenCaptureFromPath(path).build());
			Reporter.log("Step:2 MIB order Quote Budget NOT Approved and Verification Failed",true);
		}
		webaction.explicitlywait_elementtobeclickable(getting_started.getOrder_button());
		getting_started.getOrder_button().click();
		getting_started.getMIB_Order_button().click();
		MIB_Quote_Approval.getCreate_MIB_Order_button().click();
		Create_MIB_Quote_and_Order.getCustomer_Email_Label().sendKeys(Customer_Email_ID);
		Create_MIB_Quote_and_Order.getEmail_submit_button().click();
		webaction.scrollBy_value("0","1000");
		Thread.sleep(2000);
		Create_MIB_Quote_and_Order.getPlace_Order_button().click();
		Thread.sleep(5000);
		String suc_message = Success.getSuccess_message().getText();
		String MIB_Order_ID = suc_message.replaceAll("[^0-9]", "");
		System.out.println("MIB Order ID is "+MIB_Order_ID);
		test.info("MIB Order ID is "+MIB_Order_ID);
		Reporter.log(suc_message,true);
		if(Success.getSuccess_message().isDisplayed())
		{
			String path=concatenate+webaction.screenshot_MIB("MIB Created");
			test.info("MIB Order Created : "+MIB_Order_ID,MediaEntityBuilder.createScreenCaptureFromPath(path).build());
			Reporter.log("Step:3 MIB Order Created Successfully and Verification Passed",true);
		}
		else
		{
			String path=concatenate+webaction.screenshot_MIB("MIB Not Created");
			test.info("MIB Order is not Created",MediaEntityBuilder.createScreenCaptureFromPath(path).build());
			Reporter.log("Step:3 MIB Order is UNSUCCESSFUL and Verification Failed",true);
		}
		webaction.moveToElement(getting_started.getOrder_button());
		webaction.moveToElement(getting_started.getSalesorder_button());
		getting_started.getSalesorder_button().click();
		Thread.sleep(3000);
		Customer_Orders.getso_order_id().sendKeys(MIB_Order_ID);
		Customer_Orders.getso_search_button().click();
		webaction.explicitlywait_elementtobeclickable(Customer_Orders.getfirst_so_id());
		Customer_Orders.getfirst_so_id().click();
		webaction.explicitlywait_elementtobeclickable(Sales_Order_Shipment.getLogistic_details());
		Sales_Order_Shipment.getLogistic_details().click();
		Thread.sleep(2000);
		test.info("Logistic details Updated ");
		Sales_Order_Shipment.getpick_and_pack_button().click();
		webaction.select_dd_by_value(Sales_Order_Shipment.getwh_select_dd(), "32");
		webaction.scrollBy_bottom();
		Thread.sleep(2000);
		Sales_Order_Shipment.getcheck_box().click();
		Sales_Order_Shipment.getApproval_submit().click();
		webaction.scrollBy_value("0","300");
		Thread.sleep(2000);
		Pick_And_Pack.getMIB_qty_for_outward().sendKeys(SKU_Qty);
		webaction.scrollBy_bottom();
		Thread.sleep(2000);
		Pick_And_Pack.getconfirm_button().click();
		Thread.sleep(2000);
		webaction.alert_accept();
		Thread.sleep(3000);
		if(Sales_Order_Shipment.getpap_suc_msg().isDisplayed())
		{
			String path=concatenate+webaction.screenshot_MIB("Pick and Pack Created");
			test.info("Pick and Pack Created",MediaEntityBuilder.createScreenCaptureFromPath(path).build());
			Reporter.log("Step:4 Pick and Pack Created Successfully and Verification Passed",true);
		}
		else
		{
			String path=concatenate+webaction.screenshot_MIB("Pick and Pack is not Created");
			test.info("Pick and Pack is Not Created",MediaEntityBuilder.createScreenCaptureFromPath(path).build());
			Reporter.log("Step:4 Pick and Pack NOT Created and Verification Failed",true);
		}
		webaction.scrollBy_bottom();
		Thread.sleep(2000);
		Sales_Order_Shipment.getview().click();
		//Pick and Pack Details
		webaction.explicitlywait_elementtobeclickable(Pick_And_Pack_View.getcourier_type_dd());
		Pick_And_Pack_View.getcourier_type_dd().click();
		Pick_And_Pack_View.getcourier_type_search().sendKeys("Bluedart", Keys.ENTER);
		webaction.explicitlywait_elementtobeclickable(Pick_And_Pack_View.getgst_available_dd());
		Pick_And_Pack_View.getgst_available_dd().click();
		Pick_And_Pack_View.getgst_search().sendKeys("Yes", Keys.ENTER);
		String Insurance_Reference_Number = filclass.readData_TestData("SO_Data",6,2);
		Pick_And_Pack_View.getInsurance_Ref_Num().sendKeys(Insurance_Reference_Number);
		String Estimated_TAT = filclass.readData_TestData("SO_Data",7,2);
		Pick_And_Pack_View.getEst_TAT().sendKeys(Estimated_TAT);
		String Distance = filclass.readData_TestData("SO_Data",8,2);
		Pick_And_Pack_View.getdistance().sendKeys(Distance);
		String Transporter_Name = filclass.readData_TestData("SO_Data",13,2);
		Pick_And_Pack_View.gettransporter_name().sendKeys(Transporter_Name);
		String GST_Number = filclass.readData_TestData("SO_Data",14,2);
		Pick_And_Pack_View.getgst_number().sendKeys(GST_Number);
		String Comment1 = filclass.readData_TestData("SO_Data",9,2);
		Pick_And_Pack_View.getcomment().sendKeys(Comment1);
		webaction.scrollBy_bottom();
		Thread.sleep(2000);
		Pick_And_Pack_View.getsave().click();
		webaction.scrollBy_bottom();
		Thread.sleep(2000);
		Pick_And_Pack_View.getGenerate_Invoice().click();
		Thread.sleep(2000);
		test.info("Invoice Generated");
		webaction.scrollBy_bottom();
		Thread.sleep(2000);
		Pick_And_Pack_View.getUpdate_Finance_Info().click();
		Thread.sleep(1000);
		String Invoice_Number = filclass.readData_TestData("SO_Data",10,2);
		Update_Finance_Details.getInvoice_Number_Label().sendKeys(Invoice_Number);
		Update_Finance_Details.getInvoice_Date_label().sendKeys(Keys.ENTER);
		String pdf=IAutoconstant.Pdf_dummy;
		Update_Finance_Details.getInvoice_Attachmente_label().sendKeys(pdf);
		String eway_Bill_No = filclass.readData_TestData("SO_Data",11,2);
		Update_Finance_Details.geteway_Bill_No_label().sendKeys(eway_Bill_No);
		Update_Finance_Details.getSave_button().click();
		Update_Finance_Details.getBack_button().click();
		webaction.scrollBy_bottom();
		Thread.sleep(1000);
		test.info("Finance Info updated");
		Pick_And_Pack_View.getFinal_Dispatch().click();
		Thread.sleep(3000);
		if(Sales_Order_Shipment.getDispatched_text().isDisplayed())
		{
			String path=concatenate+webaction.screenshot_MIB("MIB Order Dispatched");
			test.info("MIB Order Dispatched",MediaEntityBuilder.createScreenCaptureFromPath(path).build());
			Reporter.log("Step:5 Dispatched Successfully and Verification Passed",true);
		}
		else
		{
			String path=concatenate+webaction.screenshot_MIB("MIB Order Not Dispatched");
			test.info("MIB Order is Not Dispatched",MediaEntityBuilder.createScreenCaptureFromPath(path).build());
			Reporter.log("Step:5 Dispatch NOT done and Verification Failed",true);
		}
		Sales_Order_Shipment.getview2().click();
		Thread.sleep(2000);
		webaction.scrollBy_bottom();
		Thread.sleep(1000);
		webaction.select_dd_by_value(Shipment_Status.getShipping_Status_Label(), "2");
		Shipment_Status.getDate_Time_Picker_Label().click();
		Shipment_Status.getDate_Time_Picker_Label().sendKeys(Keys.ENTER);
		String comment2 = filclass.readData_TestData("SO_Data",12,2);
		Shipment_Status.getshipping_comment1_Label().sendKeys(comment2);
		Shipment_Status.getsubmit().click();
		webaction.scrollBy_bottom();
		Thread.sleep(1000);
		if(Shipment_Status.getDelivered_text().isDisplayed())
		{
			String path=concatenate+webaction.screenshot_MIB("MIB Order Delivered");
			test.info("MIB Order Delivered",MediaEntityBuilder.createScreenCaptureFromPath(path).build());
			Reporter.log("Step:6 MIB Order Delivery Done Successfully and Verification Passed",true);
		}
		else
		{
			String path=concatenate+webaction.screenshot_MIB("MIB Order Not Delivered");
			test.info("MIB Order is Not Delivered",MediaEntityBuilder.createScreenCaptureFromPath(path).build());
			Reporter.log("Step:6 MIB Order Delivery NOT Done and Verification Failed",true);
		}
	}
}
