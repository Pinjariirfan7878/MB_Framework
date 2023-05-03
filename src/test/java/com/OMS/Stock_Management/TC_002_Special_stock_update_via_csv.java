package com.OMS.Stock_Management;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ObjectRepo.Sales_Order_Shipment_OMS_Medikabazaar_Page;
import com.ObjectRepo.Stock_Management_OMS_Medikabazaar_Page;
import com.oms.GenericLib.BaseUtilityClass;
import com.oms.GenericLib.FileLibClass;
import com.oms.GenericLib.IAutoconstant;
import com.oms.GenericLib.RetryanalyserClass;

@Listeners(com.oms.GenericLib.MylistenerClass.class)
public class TC_002_Special_stock_update_via_csv extends BaseUtilityClass{

	String testCaseDescription = "Special Stock update_via_CVS.";

	@Test(retryAnalyzer = RetryanalyserClass.class)
	public void TC_002_special_stock_update_via_csv() throws Exception {

		String currentClassName = this.getClass().getSimpleName();
		setTestCaseAndDescriptionInReport("Stock Management_special stock_update_via_CVS", currentClassName, testCaseDescription);
		Stock_Management_OMS_Medikabazaar_Page stock_mgmt = new Stock_Management_OMS_Medikabazaar_Page(getWebDriver());
		FileLibClass fil=new FileLibClass();

//Step 1: TestData Reading From Excel sheet
		String Warehouse_Name=getFileLibClass().getDataFromExcell("Special_stock_via_CSV", "warehouse_name", IAutoconstant.EXCEL_TestData);
		String warehouse_id=getFileLibClass().getDataFromExcell("Special_stock_via_CSV", "warehouse_id", IAutoconstant.EXCEL_TestData);;
	    String warehouse_code=getFileLibClass().getDataFromExcell("Special_stock_via_CSV", "warehouse_code", IAutoconstant.EXCEL_TestData);
	    String Sku_Name=getFileLibClass().getDataFromExcell("Special_stock_via_CSV", "sku", IAutoconstant.EXCEL_TestData);;
	    String Inward_Qty=getFileLibClass().getDataFromExcell("Special_stock_via_CSV", "inward_quantity", IAutoconstant.EXCEL_TestData);;
	    int inward_qty=Integer.parseInt(Inward_Qty);
		
//Step 2: Updating TestData into CSV File	    
		
	    String csvFile = IAutoconstant.CSV_File_SSU; 
	    
	    //for warehouse ID
	    String Warehouse_id="warehouse_id";
	    fil.editCSV(csvFile,1,Warehouse_id,warehouse_id);
		
		//for warehouse code
	    String Warehouse_code="warehouse_code";
	    fil.editCSV(csvFile,1 , Warehouse_code, warehouse_code);
		
		//for SKU Name
	    String SKU_NAme="sku";
	    fil.editCSV(csvFile, 1, SKU_NAme, Sku_Name);
	    
	    //Inward Quantity 
	    String inward_Qty="inward_quantity";		
	    fil.editCSV(csvFile, 1, inward_Qty, Inward_Qty);
	    
	    //Batch Number 
	    String Batch_number=fake.letterify("??12??3");
	    String batch_number="batch_no";
	    fil.editCSV(csvFile, 1, batch_number,Batch_number);

//Step 3: Initial SKU Count
	    String skuCount = getCommonAction().getCDStockCount(Sku_Name,Warehouse_Name);
		int initialSKUcount = Integer.parseInt(skuCount);
		System.out.println("Initial Count = "+initialSKUcount);
		test.info("Before Special stock update Initial SKU count = "+ getStrongText(skuCount));
		
//Step 4: Uploading CSV file
		customClick(stock_mgmt.getUploadcsv_button());
		customClick(stock_mgmt.getStock_correction_tab());
		stock_mgmt.getUpload_file_field().sendKeys(csvFile);
		customClick(stock_mgmt.getImport_button());
		test.info("CSV File Details "+" Warehouse Name : "+getStrongText(Warehouse_Name)+" "+ " Warehouse ID : "+getStrongText(warehouse_id)+" Warehouse Code : "+getStrongText(warehouse_code));
		test.info("SKU_Name : "+getStrongText(Sku_Name)+" ,"+"Inward Quantity : "+getStrongText(Inward_Qty)+" ,"+"Batch Number : "+getStrongText(Batch_number));
		takeScreenShort(getStrongText("CSV file uploaded ")," CSV file Uploaded");		
		
//Step 5: Final count_On Special Stock Update Page
		String skuCount_afterupdate = getCommonAction().getCDStockCount(Sku_Name,Warehouse_Name);
		int finalSKUcount = Integer.parseInt(skuCount_afterupdate);
		System.out.println(finalSKUcount);
		test.info("After Special stock updated SKU count = "+ getStrongText(skuCount_afterupdate));		

//Step 6: First_Validation_On Special Stock Update Page
		int previous_SKU_Qty=initialSKUcount+inward_qty;
		getCommonAction().checkAssertint(finalSKUcount,previous_SKU_Qty," Validation On Special Stock Update Page");
		
//Step 7: Second_Validation_On Stock Logger Page
		String transaction_type_name="Stock Update";
		HashMap<String, Integer> SKU_Details = getCommonAction().stockLogger_Details(Warehouse_Name, Sku_Name, transaction_type_name);

		System.out.println("Stock Logger SKU details : "+SKU_Details);
		Integer current_total_stock = SKU_Details.get("current_total_stock");
		Integer updated_Qty = SKU_Details.get("updated_Qty");
		Integer previous_stock=SKU_Details.get("previous_stock");	
		test.info(getStrongText("On Stock Logger Details Page ")+" Previous_stock : "+previous_stock+" "+ " Updated_Qty : "+updated_Qty+" Current_total_Stock : "+current_total_stock);
		getWebdriverAction().thread_sleep(700);
		takeScreenShort(getStrongText("Details On Stock Logger Page ")," Details On Stock Logger Page ");
		
		if((previous_stock+updated_Qty) == current_total_stock && current_total_stock == finalSKUcount && initialSKUcount== previous_stock) {
			isSuccess = true ;	
			softassert.assertEquals(isSuccess, true, "Validation On Stock Logger Page" +" is Passed");	
			String msgStatus ="Assertion of "+getStrongText("Validation On Stock Logger Page")+" is pass ,";
			test.info("<span style='background-color:#32CD32; color:white'>"+msgStatus+"</span>");
		}
		else {
			isSuccess = false ;
			softassert.assertEquals(isSuccess, true, "Validation On Stock Logger Page" +" is Failed");
			String msgStatus = "Assertion of "+getStrongText("Validation On Stock Logger Page")+" is fail , ";
			test.info("<span style='background-color:red; color:white'>"+msgStatus+"</span>");
		}
		
//Step 8: Third Validation On SO Pick& Pack Page
		String Shipping_price="0";
		String SKU_QTY=Integer.toString(inward_qty);
		String Customer_Email_ID=getFileLibClass().getDataFromExcell("Special_stock_via_CSV", "Customer_Email_ID", IAutoconstant.EXCEL_TestData);
		
		//Step 8.1 Create SO
		getCommonAction().get_Create_SO(Customer_Email_ID, Sku_Name, SKU_QTY,Shipping_price,false );
		getCommonAction().userLogOut();

		//Step 8.2 Procure Approval
		getCommonAction().procureApproveProcess("SO", "approve"); 
		getCommonAction().userLogOut();

		//Step 8.3 Finance Approval
		getCommonAction().financeApproveProcess("SO", "approve");
		getCommonAction().userLogOut();

		//Step 4: Validation on Pick and Pack Page 
		getCommonAction().userLoginPage("user", "User Login For to do Pick and Pack");
		getCommonAction().getSearch_SO(getGloabalData("SO_ID"));

		batch_Qty_validation_on_pick_and_pack_page(Warehouse_Name,Batch_number,Sku_Name);
		String batch_Qty_on_pap_page=getGloabalData("Batch_Qty_on_pap_page");
		int Batch_Qty_on_pap_page=Integer.parseInt(batch_Qty_on_pap_page);
		getCommonAction().checkAssertint(Batch_Qty_on_pap_page,inward_qty ,"Validation of TC_002_Special_stock_update_via_csv Test");

	}
		//*************************************Test script is Over Here**************************************************
		
	//common Funtions 	
	public String batch_Qty_validation_on_pick_and_pack_page(String CDC_Name,String Batch_number,String SKU_Name) {
		Sales_Order_Shipment_OMS_Medikabazaar_Page Sales_Order_Shipment = new Sales_Order_Shipment_OMS_Medikabazaar_Page(
				getWebDriver());
		customClick(Sales_Order_Shipment.getLogistic_details());

		customClick(Sales_Order_Shipment.getpick_and_pack_button());
		getWebdriverAction().select_dd_by_visibletext(CDC_Name, Sales_Order_Shipment.getwh_select_dd());

		scrollIntoView(Sales_Order_Shipment.getcheck_box());
		customClick(Sales_Order_Shipment.getcheck_box());
		customClick(Sales_Order_Shipment.getApproval_submit());

		WebElement pickAndPackQnt = getWebDriver().findElement(
				By.xpath("((//td[contains(text(),'" + SKU_Name + "')])[1]/following-sibling::td)[2]//label"));
		String pickAndPickQntNumber = pickAndPackQnt.getText();
		System.out.println("pickAndPickQntNumber =" + pickAndPickQntNumber);

		WebElement qty = null;
		WebElement batch_number=null;
		String QTY="";
		List<WebElement> listElements = getWebDriver().findElements(By.xpath("//table[@class=\"table datatable\"]//tr[*]//td[2]"));

		for (WebElement batch : listElements) {

			if(batch.getText().equalsIgnoreCase(Batch_number)) {
				batch_number=getWebDriver().findElement(By.xpath("//td//label[@class=\"form-label \" and text()='"+Batch_number+" ']"));
				qty = getWebDriver().findElement(By.xpath("(//td//label[@class=\"form-label \"and text()='"+Batch_number+" "+"']/preceding::span[@class=\"availablestock\"])["+"last()-1"+"]"));
				highLight(qty);
				highLight(batch_number);				
				customClick(batch_number);
				getWebdriverAction().thread_sleep(1000);
				takeScreenShort(getStrongText("Batch_Quantity_Validation_on_Pick_and_Pack_Page"),"batch_Qty_validtaion_on_pick_and_pack_page");
				System.out.println(batch.getText()+"<=========>"+Batch_number);
				System.out.println("Available QTY is "+qty.getText());
				QTY=qty.getText();
				addGlobalData("Batch_Qty_on_pap_page", QTY);
				test.info(getStrongText("Added Batch is Available on SO Pick and pack Page, Batch Number : "+Batch_number+" with Qauntity : "+QTY));
			}
		}

		return QTY;
	}
}


