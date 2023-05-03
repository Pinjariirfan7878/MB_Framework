package com.OMS.MIB_Order;

import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ObjectRepo.Create_MIB_Quote_and_Order_OMS_Medikabazaar_Page;
import com.ObjectRepo.Getting_Started_OMS_MedikabazaarPage;
import com.ObjectRepo.MIB_Quote_Approval_OMS_Medikabazaar_Page;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.oms.GenericLib.BaseUtilityClass;
import com.oms.GenericLib.FileLibClass;
import com.oms.GenericLib.RetryanalyserClass;
import com.oms.GenericLib.WebdriverActionclass;

//@Listeners(com.oms.GenericLib.MylistenerClass.class)
public class TC_001_MIB_BudgetApprove_Test  extends BaseUtilityClass
        {
		@Test (retryAnalyzer = RetryanalyserClass.class)	
		public void MIB_budgetApprove_test() throws Exception
		{
			Getting_Started_OMS_MedikabazaarPage getting_started = new Getting_Started_OMS_MedikabazaarPage(driver);
			WebdriverActionclass webaction = new WebdriverActionclass(driver);
			FileLibClass filclass = new FileLibClass();
			MIB_Quote_Approval_OMS_Medikabazaar_Page MIB_Quote_Approval = new MIB_Quote_Approval_OMS_Medikabazaar_Page(driver);
			Create_MIB_Quote_and_Order_OMS_Medikabazaar_Page Create_MIB_Quote_and_Order = new Create_MIB_Quote_and_Order_OMS_Medikabazaar_Page(driver);
			

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
		}
}
