package com.OMS.Return_To_Supplier;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ObjectRepo.Getting_Started_OMS_MedikabazaarPage;
import com.ObjectRepo.Product_List_OMS_Medikabazaar_RTS_Page;
import com.ObjectRepo.Return_to_Supplier_List_OMS_Medikabazaar_Page;
import com.ObjectRepo.Return_to_Supplier_OMS_Medikabazaar_Page;
import com.ObjectRepo.Update_Finance_Details_OMS_Medikabazaar_Page;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.oms.GenericLib.BaseUtilityClass;
import com.oms.GenericLib.CommonActionClass;
import com.oms.GenericLib.FileLibClass;
import com.oms.GenericLib.IAutoconstant;
import com.oms.GenericLib.RetryanalyserClass;
import com.oms.GenericLib.WebdriverActionclass;

//@Listeners(com.oms.GenericLib.MylistenerClass.class)	
public class TC_001_RTS_Ent_To_EndTest extends BaseUtilityClass  {
	
	@Test(retryAnalyzer = RetryanalyserClass.class)
	public void RTS_End_to_End_Test() throws Throwable
	{
		Getting_Started_OMS_MedikabazaarPage getting_started = new Getting_Started_OMS_MedikabazaarPage(driver);
		Return_to_Supplier_List_OMS_Medikabazaar_Page return_to_supplier_list = new Return_to_Supplier_List_OMS_Medikabazaar_Page(driver);
		Return_to_Supplier_OMS_Medikabazaar_Page return_to_supplier_oms = new Return_to_Supplier_OMS_Medikabazaar_Page(driver);
		WebdriverActionclass webaction=new WebdriverActionclass(driver);
		Product_List_OMS_Medikabazaar_RTS_Page product_list = new Product_List_OMS_Medikabazaar_RTS_Page(driver);
		Update_Finance_Details_OMS_Medikabazaar_Page update_finance = new Update_Finance_Details_OMS_Medikabazaar_Page(driver);
		FileLibClass filclass=new FileLibClass();

//Initially stock count at CDC
		String product_sku=filclass.readData_TestData("RTS_Data",16,2);
		String CDCId=filclass.readData_TestData("RTS_Data",15,2);
		String InitialCount=CommonActionClass.getCDCStockCount(product_sku,CDCId, driver);
	    int initialCDCcount = Integer.parseInt(InitialCount);
		Reporter.log("CDC initially stock count is "+initialCDCcount,true);
		test.info("CDC initially stock count is "+initialCDCcount);
		
		getting_started.getPurchase_tab().click();
		test.info("Home Page is opened");
		getting_started.getReturn_to_supplier_button().click();
		return_to_supplier_list.getCreate_RTS_button().click();
		test.info("Create Return to supplier page is opened");
		return_to_supplier_oms.getWarehouse_dd().click();
		webaction.select_dd_by_value(return_to_supplier_oms.getWarehouse_dd(),"32");
		return_to_supplier_oms.getSupplier_dd().click();
		Thread.sleep(2000);
		String Supplier_Email_ID = filclass.readData_TestData("RTS_Data",1,2);
		return_to_supplier_oms.getsupplier_search().sendKeys(Supplier_Email_ID);
		Thread.sleep(2000);
		return_to_supplier_oms.getsupplier_search().sendKeys(Keys.ENTER);
		return_to_supplier_oms.getCourier_type_dd().click();
		webaction.select_dd_by_value(return_to_supplier_oms.getCourier_type_dd(),"2");
		return_to_supplier_oms.getEst_tat().clear();
		String Estimated_TAT_In_Days=filclass.readData_TestData("RTS_Data",2,2);
		return_to_supplier_oms.getEst_tat().sendKeys(Estimated_TAT_In_Days);
		return_to_supplier_oms.getgst_Label().click();
		webaction.select_dd_by_value(return_to_supplier_oms.getgst_Label(),"Yes");
		return_to_supplier_oms.getDistance_Label().clear();
		String Distance_In_KM=filclass.readData_TestData("RTS_Data",3,2);
		return_to_supplier_oms.getDistance_Label().sendKeys(Distance_In_KM);
		return_to_supplier_oms.getTransporter_name().clear();
		String Transporter_Name=filclass.readData_TestData("RTS_Data",4,2);
		return_to_supplier_oms.getTransporter_name().sendKeys(Transporter_Name);
		return_to_supplier_oms.getGST_num().clear();
		String Transporter_GST_No=filclass.readData_TestData("RTS_Data",5,2);
		return_to_supplier_oms.getGST_num().sendKeys(Transporter_GST_No);
		return_to_supplier_oms.getComment().clear();
		String Comment1=filclass.readData_TestData("RTS_Data",6,2);
		return_to_supplier_oms.getComment().sendKeys(Comment1);
		return_to_supplier_oms.getUpdate_button().click();
		Thread.sleep(5000);
		webaction.scrollBy_bottom();
		Thread.sleep(3000);
		test.info("Customer Details added");
		webaction.explicitlywait_elementtobeclickable(return_to_supplier_oms.getAdd_Product_button());
		return_to_supplier_oms.getAdd_Product_button().click();
		Thread.sleep(2000);
		webaction.scrollBy_bottom();
		Thread.sleep(2000);
		product_list.getSelect_Product_Checkbox().click();
		product_list.getpro_qty_Field().clear();
		
		String SKU_Qty=filclass.readData_TestData("RTS_Data",7,2);
		int sku_qty=Integer.parseInt(SKU_Qty);
		test.info("Return Quantity is "+sku_qty);
		Reporter.log("Return Quantity is "+sku_qty,true);
		
		product_list.getpro_qty_Field().sendKeys(SKU_Qty);
		Thread.sleep(1000);
		webaction.scrollBy_top();
		Thread.sleep(1000);
		product_list.getadd_to_RTS_cart().click();
		Thread.sleep(5000);
		test.info("Product added to Return ");
		webaction.scrollBy_bottom();
		Thread.sleep(3000);
		String product_price=filclass.readData_TestData("RTS_Data",14,2);
		return_to_supplier_oms.getProduct_price().clear();
		return_to_supplier_oms.getProduct_price().sendKeys(product_price);
		String Invoice_number=filclass.readData_TestData("RTS_Data",12,2);
		return_to_supplier_oms.getInvoice_number().sendKeys(Invoice_number);
		return_to_supplier_oms.getInvoice_date().click();
		return_to_supplier_oms.getToday_date().click();
		return_to_supplier_oms.getProduct_table().click();
		WebElement action_button = return_to_supplier_oms.getUpdate_action_button();
		webaction.scrollBy_horizontally(action_button);
		Thread.sleep(200);
		return_to_supplier_oms.getUpdate_action_button().click();
		Thread.sleep(2000);
		webaction.scrollBy_bottom();
		Thread.sleep(1000);
		webaction.explicitlywait_elementtobeclickable(return_to_supplier_oms.getCreate_RTS_button());
		return_to_supplier_oms.getCreate_RTS_button().click();
		Reporter.log(return_to_supplier_list.getRTS_created_suc_message().getText(),true);
		if(return_to_supplier_list.getRTS_created_suc_message().isDisplayed())
		{
			String path=concatenate+webaction.screenshot_RTS("Return to Supplier created");
			test.info("Return To Supplier Created Successfully",MediaEntityBuilder.createScreenCaptureFromPath(path).build());
			Reporter.log("Step:1 Return To Supplier Created Successfully and Verification Passed ",true);
		}
		else
		{
			String path=concatenate+webaction.screenshot_RTS("Return to Supplier is not created");
			test.info("Return To Supplier is not Created",MediaEntityBuilder.createScreenCaptureFromPath(path).build());
			Reporter.log("Step:1 Return To Supplier Not Created and Verification Failed",true);
		}
		webaction.explicitlywait_elementtobeclickable(return_to_supplier_list.getSearch_RTS_ID_button());
		return_to_supplier_list.getSearch_RTS_ID_button().click();
		Thread.sleep(2000);
		test.info("Return to supplier ID: "+return_to_supplier_list.getselect_RTS_ID().getText());
		Reporter.log("Return to supplier ID: "+return_to_supplier_list.getselect_RTS_ID().getText());
		return_to_supplier_list.getselect_RTS_ID().click();
		String Debit_Note_Number=filclass.readData_TestData("RTS_Data",8,2);
		return_to_supplier_oms.getDebit_Note_Num().sendKeys(Debit_Note_Number);
		return_to_supplier_oms.getDebit_Note_Date().sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		String pdf=IAutoconstant.Pdf_dummy;
		return_to_supplier_oms.getDebit_Note_Attachment().sendKeys(pdf);
		return_to_supplier_oms.getApproval_Status().click();
		webaction.select_dd_by_value(return_to_supplier_oms.getApproval_Status(),"approved");
		String Comment2=filclass.readData_TestData("RTS_Data",9,2);
		return_to_supplier_oms.getApproval_Comment().sendKeys(Comment2);
		Thread.sleep(2000);
		webaction.scrollBy_bottom();
		webaction.explicitlywait_elementtobeclickable(return_to_supplier_oms.getUpdate_button());
		Thread.sleep(2000);
		return_to_supplier_oms.getUpdate_button().click();
		//Thread.sleep(3000);
		if(return_to_supplier_oms.getApprove_Green().isDisplayed())
		{
			String path=concatenate+webaction.screenshot_RTS("Return to Supplier is not created");
			test.info("Return To Supplier Is Updated Successfully",MediaEntityBuilder.createScreenCaptureFromPath(path).build());
			Reporter.log("Step:2 Return To Supplier Is Updated Successfully and Verification Passed",true);
		}
		else
		{
			String path=concatenate+webaction.screenshot_RTS("Return to Supplier is not created");
			test.info("Return To Supplier Is Not Updated",MediaEntityBuilder.createScreenCaptureFromPath(path).build());
			Reporter.log("Step:2 Return To Supplier Is Not Updated and Verification Failed",true);
		}
		webaction.scrollBy_bottom();
		webaction.explicitlywait_elementtobeclickable(return_to_supplier_oms.getUdate_Tally_Info());
		Thread.sleep(2000);
		return_to_supplier_oms.getUdate_Tally_Info().click();
		String Invoice_Number=filclass.readData_TestData("RTS_Data",10,2);
		update_finance.getInvoice_Number_Label().sendKeys(Invoice_Number);
		update_finance.getInvoice_Date_label().sendKeys(Keys.ENTER);
		update_finance.getInvoice_Attachmente_label().sendKeys(pdf);
		String eway_Bill_Number=filclass.readData_TestData("RTS_Data",11,2);
		update_finance.geteway_Bill_No_label().sendKeys(eway_Bill_Number);
		update_finance.getSave_button().click();
		update_finance.getBack_button().click();
		test.info("Tally Information Updated");
		if(return_to_supplier_oms.getApprove_Green().isDisplayed())
		{
			String path=concatenate+webaction.screenshot_RTS("Return to Supplier is not created");
			test.info("Return To Supplier Is Approved",MediaEntityBuilder.createScreenCaptureFromPath(path).build());
			Reporter.log("Step:3 Return To Supplier Is Approved and Verification Passed",true);
		}
		else
		{
			String path=concatenate+webaction.screenshot_RTS("Return to Supplier is not created");
			test.info("Return To Supplier Is Not Approved",MediaEntityBuilder.createScreenCaptureFromPath(path).build());
			Reporter.log("Step:3 Return To Supplier Is Not Approved and Verification Failed",true);
		}
		String FinalCount=CommonActionClass.getCDCStockCount(product_sku,CDCId, driver);
		int finalCount = Integer.parseInt(FinalCount);
		
		boolean success;
		if (initialCDCcount-sku_qty== finalCount)
		{
			Reporter.log("CDC Stock after Returned "+finalCount,true);
			test.info("CDC Stock after Returned "+finalCount);
			success = true;
		}
		else
		{
			Reporter.log("CDC Stock is not updated after Returned ",true);
			test.info("CDC Stock is not updated after Returned ");
			success = false;
		}
		softassert.assertEquals(success, true,"The TC_001_RTS_Ent_To_EndTest is successful");

	}
}
