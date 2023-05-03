package com.OMS.Indent;

import java.time.Duration;

import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ObjectRepo.Create_PO_OMS_MedikabazaarPage;
import com.ObjectRepo.Edit_Indent_PROTA_Medikabazaar_Page;
import com.ObjectRepo.Getting_Started_OMS_MedikabazaarPage;
import com.ObjectRepo.Indent_Configuration_PROTA_Medikabazaar_Page;
import com.ObjectRepo.Indent_List_PROTA_Medikabazaar_Page;
import com.ObjectRepo.Indent_PROTA_Medikabazaar_Page;
import com.ObjectRepo.Non_VPO_Cart_PROTA_Medikabazaar_Page;
import com.ObjectRepo.PO_OMS_MedikabazaarPage;
import com.oms.GenericLib.BaseUtilityClass;
import com.oms.GenericLib.IAutoconstant;
import com.oms.GenericLib.RetryanalyserClass;
@Listeners(com.oms.GenericLib.MylistenerClass.class)
public class TC_001_Indent_Raise_Non_VPO_Customer_Indent_via_cart_test extends BaseUtilityClass{
	
	String testCaseDescription = "Testing of Raise Indent Flow";	

	@Test//(retryAnalyzer = RetryanalyserClass.class)
	public void Indent_Raise_indent_via_cart_test() {

		FlowNAME = "Indent";
		String currentClassName = this.getClass().getSimpleName();
		setTestCaseAndDescriptionInReport("Indent", currentClassName, testCaseDescription);
		Edit_Indent_PROTA_Medikabazaar_Page edit_indent_page=new Edit_Indent_PROTA_Medikabazaar_Page(getWebDriver());
		Indent_List_PROTA_Medikabazaar_Page inden_list_page=new Indent_List_PROTA_Medikabazaar_Page(getWebDriver());
		
		String SKU_Name = getFileLibClass().getDataFromExcell("Indent_Raise_indent", "SKU_Name",
				IAutoconstant.EXCEL_TestData);
		String SKU_QTY  = getFileLibClass().getDataFromExcell("Indent_Raise_indent", "SKU_QTY",
				IAutoconstant.EXCEL_TestData);
		String Warehouse_Name = getFileLibClass().getDataFromExcell("Indent_Raise_indent", "Warehouse_Name",
				IAutoconstant.EXCEL_TestData);
		String SRM_Name  = getFileLibClass().getDataFromExcell("Indent_Raise_indent", "SRM_Name",
				IAutoconstant.EXCEL_TestData);
		String supplier_email  = getFileLibClass().getDataFromExcell("Indent_Raise_indent", "supplier_email",
				IAutoconstant.EXCEL_TestData);
		
//Step 1 : Raise Indent 
		raise_indent_for_non_vpo_customer(SKU_Name, SKU_QTY, Warehouse_Name, SRM_Name);
		String indent_id = getGloabalData("Indent_ID");
		
//Step 2 : Search By Indent ID 
		searchByIndentId(indent_id);

		getWebdriverAction().scrollBy_horizontally(edit_indent_page.getIndent_Qty());
		takeScreenShort("Details of Indent  ","Details of Indent  ");
		String primary_stock = edit_indent_page.getPrimary_stock().getText();
		String safety_stock = edit_indent_page.getSafety_stock().getText();
		String reorder_level_count = edit_indent_page.getReorder_level().getText();	
		String EOQ = edit_indent_page.getEOQ().getText();

		test.info("Primary_stock = "+getStrongText(primary_stock)+", Safety_stock = "+
				getStrongText(safety_stock)+", Reorder_level_count = "+getStrongText(reorder_level_count)+", EOQ = "+getStrongText(EOQ));
		customClick(edit_indent_page.getSelectAll_checkbox());
		customClick(edit_indent_page.getCreate_po_button());

// Step 3 : Creating Purchase Order.
		create_po_via_indent(supplier_email,indent_id);
		String created_PO_number = getGloabalData("PO_ID");
		
//Step 4 : Validation on Indent Master Item
		searchByIndentId(indent_id);
		customClick(edit_indent_page.getBack_button());
		getWebdriverAction().scrollBy_horizontally(inden_list_page.getRaised_by());
		
		//PO Number Validation
		highLight(inden_list_page.getPO_Number());
		String Po_Number_with_indent= inden_list_page.getPO_Number().getText();
	    getCommonAction().checkAssertString(Po_Number_with_indent, created_PO_number, "Attached PO same as created PO ");
		
	    //Indent Status Validation 
		highLight(inden_list_page.getIndent_status());
		String Indent_status = inden_list_page.getIndent_status().getText();
		String expected_indent_status="Complete";
	    getCommonAction().checkAssertString(Indent_status, expected_indent_status, "Indent Status verify ");
	   
		//Indent Quantity Validation with created PO
	    highLight(inden_list_page.getIndent_Qty());
	    String Indent_Qty = inden_list_page.getIndent_Qty().getText();
	    String expected_Qty=SKU_QTY;
        getCommonAction().checkAssertString(Indent_Qty, expected_Qty, "PO Qty and Indent Quantity is same ");
        getWebdriverAction().thread_sleep(3000);
        takeScreenShort("Indent details After create PO ","Indent details After create PO ");  
	}
	
	
//*****************************************************************************************************************
	public void raise_indent_for_non_vpo_customer(String SKU_Name,String SKU_QTY,String Warehouse_Name,String SRM_Name) {

		Getting_Started_OMS_MedikabazaarPage getting_started = new Getting_Started_OMS_MedikabazaarPage(getWebDriver());
		Indent_Configuration_PROTA_Medikabazaar_Page indent_config =new Indent_Configuration_PROTA_Medikabazaar_Page(getWebDriver());
		Non_VPO_Cart_PROTA_Medikabazaar_Page non_Vpo_cart_page=new Non_VPO_Cart_PROTA_Medikabazaar_Page(getWebDriver());
		Indent_PROTA_Medikabazaar_Page indent_page=new Indent_PROTA_Medikabazaar_Page(getWebDriver());

		customClick(getting_started.getCustomers_button());
		clickOnSubMenu(getting_started.getCustomers_button(),getting_started.getIndent_configuration_tab() , globalTime);
		customClick(indent_config.getSku_level_tab());
		customClick(indent_config.getRaise_indent_button_for_non_vpo());

		customSendText(non_Vpo_cart_page.getProduct_sku_text_field(),SKU_Name );
		customSendText(non_Vpo_cart_page.getSku_qty_text_field(),SKU_QTY );
		customClick(non_Vpo_cart_page.getWarehouse_dd());
		non_Vpo_cart_page.getSearch_bar_warehouse_dd().sendKeys(Warehouse_Name,Keys.ENTER);

		customClick(non_Vpo_cart_page.getSRM_dd());
		non_Vpo_cart_page.getSearch_bar_SRM_dd().sendKeys(SRM_Name,Keys.ENTER);
		customClick(non_Vpo_cart_page.getAdd_product_button());
		takeScreenShort("Product Added into cart ","Product Added into cart ");
		customClick(non_Vpo_cart_page.getSelectAll_checkbox());
		customClick(non_Vpo_cart_page.getRaise_indent_button());
		String Indent_id = indent_page.getIndent_id().getText();
		System.out.println("Indent_Id = " + getStrongText(getOnlyId(Indent_id, "MBIND-")));

		if (indent_page.getIndent_id().isDisplayed()) {
			test.info("Indent_ID = " + getStrongText(getOnlyId(Indent_id, "MBIND-")));
			takeScreenShort("Indent is raised with Indent_ID = " + getStrongText(getOnlyId(Indent_id, "MBIND-")),
					"Step:1 Indent Raise Successfully And Verification Passed ");
			addGlobalData("Indent_ID", getOnlyId(Indent_id, "MBIND-"));
			test.info("<span style='background-color:#32CD32; color:white'>" + "Indent Raised and verification passed" + "</span>");
		}
		else {
			takeScreenShort(getStrongText("Indent is not Raised"),
					"Step:1 Raise Indent Verified is failed ");
			test.info("<span style='background-color:red; color:white'>" + "Raise Indent is failed " + "</span>");
		}
	}
	public void searchByIndentId(String Indent_Id) {
		Getting_Started_OMS_MedikabazaarPage getting_started = new Getting_Started_OMS_MedikabazaarPage(getWebDriver());
		Indent_List_PROTA_Medikabazaar_Page inden_list_page=new Indent_List_PROTA_Medikabazaar_Page(getWebDriver());

		customClick(getting_started.get_Operations_button());
		clickOnSubMenu(getting_started.get_Operations_button(),getting_started.getIndent_module_tab() , globalTime);
		customSendText(inden_list_page.getIndent_id_text_field(), Indent_Id);
		customClick(inden_list_page.getSearch_button());
		customClick(inden_list_page.getIndent_id());

	}
	public void create_po_via_indent(String supplier_email,String Indent_id) {
		
    	Create_PO_OMS_MedikabazaarPage create_PO_page = new Create_PO_OMS_MedikabazaarPage(getWebDriver());
		PO_OMS_MedikabazaarPage PO_created_page = new PO_OMS_MedikabazaarPage(getWebDriver());
		
		customSendText(create_PO_page.getSupplier_email_textfield(), supplier_email);
		customClick(create_PO_page.getSubmit_button());
		String MRP_Price="75";
		customSendText(create_PO_page.getMRP_text_field(), MRP_Price);
		customClick(create_PO_page.getCreate_purchase_order_button());
		
		try {
			WebDriverWait w = new WebDriverWait(getWebDriver(), Duration.ofSeconds(5));
			if (w.until(ExpectedConditions.alertIsPresent()) == null) {
				customClick(create_PO_page.getCreate_purchase_order_button());
				System.out.println("Alert not exists");
			} else {
				getWebdriverAction().alert_accept();
				System.out.println("Alert exists");
			}
		} catch (Exception e) {
			System.out.println("Exception of Alert =" + e);
		}
		 String PO_number = PO_created_page.getPO_ID().getText();
	
	if (PO_created_page.getConfirm_img().isDisplayed()) {
		test.info("Indent ID is "+getStrongText(Indent_id)+" for this Indent PO ID created is "+getStrongText(PO_number));
		takeScreenShort("Purchase order is created with number " + getStrongText(PO_number),
				"Step:1 P.O Created Successfully for indent  And Verification Passed ");
		addGlobalData("PO_ID", getOnlyId(PO_number, "PO"));
	} else {
		takeScreenShort(getStrongText("Purchase order is not created"),
				"Step:1 P.O is not created Verification Failed");
	}
  }
}
