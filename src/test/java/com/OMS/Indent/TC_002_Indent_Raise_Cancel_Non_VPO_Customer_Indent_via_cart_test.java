package com.OMS.Indent;

import org.openqa.selenium.Keys;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ObjectRepo.Edit_Indent_PROTA_Medikabazaar_Page;
import com.ObjectRepo.Getting_Started_OMS_MedikabazaarPage;
import com.ObjectRepo.Indent_Configuration_PROTA_Medikabazaar_Page;
import com.ObjectRepo.Indent_List_PROTA_Medikabazaar_Page;
import com.ObjectRepo.Indent_PROTA_Medikabazaar_Page;
import com.ObjectRepo.Non_VPO_Cart_PROTA_Medikabazaar_Page;
import com.oms.GenericLib.BaseUtilityClass;
import com.oms.GenericLib.IAutoconstant;
@Listeners(com.oms.GenericLib.MylistenerClass.class)
public class TC_002_Indent_Raise_Cancel_Non_VPO_Customer_Indent_via_cart_test extends BaseUtilityClass{
	
	String testCaseDescription = "Testing of Cancel Indent Flow";	
	@Test
	public void Indent_Raise_Cancel_Non_VPO_Customer_Indent_via_via_cart_test() {
		//FlowNAME = "Indent";
		String currentClassName = this.getClass().getSimpleName();
		setTestCaseAndDescriptionInReport("Indent", currentClassName, testCaseDescription);
		String SKU_Name = getFileLibClass().getDataFromExcell("Indent_Raise_indent", "SKU_Name",
				IAutoconstant.EXCEL_TestData);
		String SKU_QTY  = getFileLibClass().getDataFromExcell("Indent_Raise_indent", "SKU_QTY",
				IAutoconstant.EXCEL_TestData);
		String Warehouse_Name = getFileLibClass().getDataFromExcell("Indent_Raise_indent", "Warehouse_Name",
				IAutoconstant.EXCEL_TestData);
		String SRM_Name  = getFileLibClass().getDataFromExcell("Indent_Raise_indent", "SRM_Name",
				IAutoconstant.EXCEL_TestData);
		Edit_Indent_PROTA_Medikabazaar_Page edit_indent_page=new Edit_Indent_PROTA_Medikabazaar_Page(getWebDriver());
		Indent_List_PROTA_Medikabazaar_Page inden_list_page=new Indent_List_PROTA_Medikabazaar_Page(getWebDriver());

//Step 1 : Raise Indent 
		raise_indent_for_non_vpo_customer(SKU_Name, SKU_QTY, Warehouse_Name, SRM_Name);
		String indent_id = getGloabalData("Indent_ID");
		
//Step 2 :Cancel Indent 
		searchByIndentId(indent_id);
		customClick(edit_indent_page.getDelete_button());
		
//Step 4: Validation of test Case 
		searchByIndentId(indent_id);
		customClick(edit_indent_page.getBack_button());
		getWebdriverAction().scrollBy_horizontally(inden_list_page.getRaised_by());
		highLight(inden_list_page.getIndent_status());
		String indent_status_after_cancel = inden_list_page.getIndent_status().getText();
		takeScreenShort("Indent cancelled ","Indent cancelled ");   
		String expected_status="Cancelled";
		getCommonAction().checkAssertString(indent_status_after_cancel, expected_status, "Indent status Verify ");
				
	}
	
	
	
	
	
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
}
