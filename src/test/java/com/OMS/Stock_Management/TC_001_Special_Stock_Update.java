package com.OMS.Stock_Management;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ObjectRepo.Edit_Stock_OMS_Medikabazaar;
import com.ObjectRepo.Getting_Started_OMS_MedikabazaarPage;
import com.ObjectRepo.Sales_Order_Shipment_OMS_Medikabazaar_Page;
import com.ObjectRepo.Stock_Management_OMS_Medikabazaar_Page;
import com.oms.GenericLib.BaseUtilityClass;
import com.oms.GenericLib.IAutoconstant;
import com.oms.GenericLib.RetryanalyserClass;

@Listeners(com.oms.GenericLib.MylistenerClass.class)
public class TC_001_Special_Stock_Update extends BaseUtilityClass {

	String testCaseDescription = "Special Stock update.";

	@Test//(retryAnalyzer = RetryanalyserClass.class)
	public void Tc_001_special_stock_update_ManualEntry() {

		String currentClassName = this.getClass().getSimpleName();
		setTestCaseAndDescriptionInReport("Stock Management_special stock_update", currentClassName, testCaseDescription);
		String CDC_Name = getFileLibClass().getDataFromExcell("Stock_management_special_stock", "CDC", IAutoconstant.EXCEL_TestData);
		String SKU_Name = getFileLibClass().getDataFromExcell("Stock_management_special_stock", "SKU_Name", IAutoconstant.EXCEL_TestData);

//Step 1: Initial SKU Count
	
		String skuCount =getCommonAction().getCDStockCount(SKU_Name,CDC_Name);
		int initialCDCcount = Integer.parseInt(skuCount);
		System.out.println(initialCDCcount);
		test.info("Before Special stock updated SKU count "+getCommonAction().product_sku+" is "+ getStrongText(skuCount));
		
//Step 2: Special stock update
		int added_sku_Qty=SpecialStockUpdate_manual(CDC_Name);

//Step 3: Final count_On Special Stock Update Page
		String skuCount_afteradded =getCommonAction().getCDStockCount(SKU_Name,CDC_Name);
		int skuCDCcount_afteradded = Integer.parseInt(skuCount_afteradded);
		test.info("After Special stock updated SKU FinalCount is : "+ getStrongText(skuCount_afteradded));
		
//Step 4: First_Validation_On Special Stock Update Page
		if((initialCDCcount +added_sku_Qty) == skuCDCcount_afteradded ) {
			isSuccess = true ;
			softassert.assertEquals(isSuccess, true, "Validation on Special Stock Update Page " +" is Passed");	
			String msgStatus ="Assertion of "+getStrongText("Validation on Special Stock Update Page ")+" is Passed ,";
			test.info("<span style='background-color:#32CD32; color:white'>"+msgStatus+"</span>");
		}
		else {
			isSuccess = false ;
			softassert.assertEquals(isSuccess, true, "Validation on Special Stock Update Page " +" is Failed");
			String msgStatus = "Assertion of "+getStrongText("Validation on Special Stock Update Page ")+" is Failed ,";
			test.info("<span style='background-color:red; color:white'>"+msgStatus+"</span>");
		}
	
		
//Step 5: Second_Validation_On Stock Logger Page
		String transaction_type_name="Stock Update";
		HashMap<String, Integer> SKU_Details = getCommonAction().stockLogger_Details(CDC_Name, SKU_Name, transaction_type_name);

		System.out.println("Stock Logger SKU details : "+SKU_Details);
		Integer current_total_stock = SKU_Details.get("current_total_stock");
		Integer updated_Qty = SKU_Details.get("updated_Qty");
		Integer previous_stock=SKU_Details.get("previous_stock");	
		test.info(getStrongText("On Stock Logger Details ")+" Previous_stock : "+previous_stock+" "+ " Updated_Qty : "+updated_Qty+" Current_total_Stock : "+current_total_stock);
		getWebdriverAction().thread_sleep(700);
		takeScreenShort(getStrongText("Details On Stock Logger Page ")," Details On Stock Logger Page ");

		if((previous_stock+updated_Qty) == current_total_stock && current_total_stock == skuCDCcount_afteradded && initialCDCcount== previous_stock) {
			isSuccess = true ;	
			softassert.assertEquals(isSuccess, true, "Validation On Stock Logger Page " +" is Passed");	
			String msgStatus ="Assertion of "+getStrongText("Validation on Special Stock Update Page ")+" is Passed ,";
			test.info("<span style='background-color:#32CD32; color:white'>"+msgStatus+"</span>");
		}
		else {
			isSuccess = false ;
			softassert.assertEquals(isSuccess, true, "Validation On Stock Logger Page " +" is Failed");
			String msgStatus = "Assertion of "+getStrongText("Validation on Special Stock Update Page ")+" is Failed ,";
			test.info("<span style='background-color:red; color:white'>"+msgStatus+"</span>");	
		}
		
//Step 6: Third Validation On SO Pick& Pack Page
		String Batch_number = getGloabalData("Batch_number");
		System.out.println(Batch_number);
		String Shipping_price="0";
		String SKU_QTY=Integer.toString(added_sku_Qty);
		String Customer_Email_ID=getFileLibClass().getDataFromExcell("Stock_management_special_stock", "Customer_Email_ID", IAutoconstant.EXCEL_TestData);

		//Step 6.1 Create SO
		getCommonAction().get_Create_SO(Customer_Email_ID, SKU_Name, SKU_QTY,Shipping_price,false );
		getCommonAction().userLogOut();

		//Step 6.2 Procure Approval
		getCommonAction().procureApproveProcess("SO", "approve"); 
		getCommonAction().userLogOut();

		//Step 6.3 Finance Approval
		getCommonAction().financeApproveProcess("SO", "approve");
		getCommonAction().userLogOut();

		//Step 4: Validation on Pick and Pack Page 
		getCommonAction().userLoginPage("user", "User Login For to do Pick and Pack");
		getCommonAction().getSearch_SO(getGloabalData("SO_ID"));

		batch_Qty_validation_on_pick_and_pack_page(CDC_Name,Batch_number,SKU_Name);
		String batch_Qty_on_pap_page=getGloabalData("Batch_Qty_on_pap_page");
		int Batch_Qty_on_pap_page=Integer.parseInt(batch_Qty_on_pap_page);
		getCommonAction().checkAssertint(Batch_Qty_on_pap_page,added_sku_Qty ,"Validation of Tc_001_special_stock_update_ManualEntry Test");			
	}
  
//*************************************Test script is Over Here**************************************************

	//1> Step 6 common function Method for validation batch number Qty on SO Pick and pack Page under stock update flow

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
	
	//2> For Step 2 common function Method
	public int SpecialStockUpdate_manual(String cdc_Name) {

		Stock_Management_OMS_Medikabazaar_Page stock_mgmt = new Stock_Management_OMS_Medikabazaar_Page(getWebDriver());
		Getting_Started_OMS_MedikabazaarPage getting_started = new Getting_Started_OMS_MedikabazaarPage(getWebDriver());
		Edit_Stock_OMS_Medikabazaar edit_stock=new Edit_Stock_OMS_Medikabazaar(getWebDriver());
		String CDC_Name = getFileLibClass().getDataFromExcell("Stock_management_special_stock", "CDC", IAutoconstant.EXCEL_TestData);
		String SKU_Name = getFileLibClass().getDataFromExcell("Stock_management_special_stock", "SKU_Name", IAutoconstant.EXCEL_TestData);
		String SKU_Qty = getFileLibClass().getDataFromExcell("Stock_management_special_stock", "SKU_Qty", IAutoconstant.EXCEL_TestData);
		int sku_Qty = Integer.parseInt(SKU_Qty);
		String comment_text=getFileLibClass().getDataFromExcell("Stock_management_special_stock", "comment text", IAutoconstant.EXCEL_TestData);


		String Batch_number=fake.letterify("??12???3");
		addGlobalData("Batch_number",Batch_number);
		System.out.println("Batch number : "+Batch_number);
		customClick(getting_started.get_Operations_button());
		clickOnSubMenu(getting_started.get_Operations_button(), getting_started.get_Stock_Management_button(), 2);
//		try {
//			customClick(getting_started.get_Stock_Management_button());
//		} catch (Exception e) {
//			customClick(getting_started.get_Operations_button());
//			customClick(getting_started.get_Stock_Management_button());
//		}
//		customClick(stock_mgmt.get_Warehouse_Name());
//		getWebDriver().findElement(By.xpath("//input[@class=\"select2-search__field\"]")).sendKeys(cdc_Name,Keys.ENTER);
//		getWebdriverAction().thread_sleep(500);
		//customSendText(stock_mgmt.get_Warehouse_Name(), "Mumbai (CDC)");
		customClick(stock_mgmt.get_Warehouse_Name());
//		getWebDriver().findElement(By.xpath("//input[@class=\"select2-search__field\"]")).sendKeys("Mumbai (CDC)",Keys.ENTER);
//		getWebdriverAction().thread_sleep(500);
		getWebdriverAction().select_dd_by_visibletext(CDC_Name, stock_mgmt.get_Warehouse_Name());
		customSendText(stock_mgmt.get_sku_field(), SKU_Name);
		customClick(stock_mgmt.get_search_button());
		getWebdriverAction().thread_sleep(1000);
		customClick(stock_mgmt.getSku_column());
		getWebdriverAction().scrollBy_bottom();
		customClick(edit_stock.getAdd_more_button());
		customSendText(edit_stock.getBatch_no_textfield(),Batch_number);
		customClick(edit_stock.getExp_date_textfiled());
		customClick(edit_stock.getDate_picker_testfield());
		getWebdriverAction().scrollBy_horizontally(edit_stock.getDelete_button());
		customSendText(edit_stock.getPo_Qty_textfield(),SKU_Qty);

		try {
			edit_stock.getRack_shelf_floor_dd().click();
			getWebdriverAction().select_dd_by_index(edit_stock.getRack_shelf_floor_dd(),1);
		} catch (Exception e) {
			customSendText(edit_stock.getComment_test_filed(),comment_text);
		}
		test.info("Warehouse Name : " +getStrongText(CDC_Name) + " , SKU_Name : " +getStrongText(SKU_Name)+" , SKU_Qty : "+getStrongText(SKU_Qty)+" , Batch_Number : "+getStrongText(Batch_number));
		customClick(edit_stock.getSubmit_button());
		takeScreenShort(getStrongText("Special stock updated ")," Special Stock Updated");
		getWebdriverAction().thread_sleep(200);
		return sku_Qty;
	}
	//*************for ProtaIndia****************
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
		//stock_mgmt.get_Warehouse_Name().sendKeys("Mumbai (CDC)");
		
	//	customSendText(stock_mgmt.get_Warehouse_Name(), "Mumbai (CDC)");
		customClick(stock_mgmt.get_Warehouse_Name());
//		getWebDriver().findElement(By.xpath("//input[@class=\"select2-search__field\"]")).sendKeys(cdcName,Keys.ENTER);
//		getWebdriverAction().thread_sleep(500);
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
}




