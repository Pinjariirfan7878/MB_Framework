package com.OMS.PO;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ObjectRepo.Getting_Started_OMS_MedikabazaarPage;
import com.ObjectRepo.Inward_Inventory_Quantity_Bulk_Upload_OMS_Medikabazaar_Page;
import com.oms.GenericLib.BaseUtilityClass;
import com.oms.GenericLib.FileLibClass;
import com.oms.GenericLib.IAutoconstant;
@Listeners(com.oms.GenericLib.MylistenerClass.class)
public class TC_007_PO_mulitple_po_GRN_via_bulk extends BaseUtilityClass {

	String testCaseDescription = "Testing flow of Mulitple_po_GRN_via_Bulk";

	@Test
	public void multiple_po_GRN_via_bulk() {

		
//Step 1: Create multiple PO 
		String supplier_email=getFileLibClass().getDataFromExcell("Multiple_po_GRN_via_bulk", "Supplier Email", IAutoconstant.EXCEL_TestData);
		String Sku_1=getFileLibClass().getDataFromExcell("Multiple_po_GRN_via_bulk", "Sku_1", IAutoconstant.EXCEL_TestData);
		String PO_Qty=getFileLibClass().getDataFromExcell("Multiple_po_GRN_via_bulk", "PO_Qty", IAutoconstant.EXCEL_TestData);
	    String Sku_2=getFileLibClass().getDataFromExcell("Multiple_po_GRN_via_bulk", "Sku_2", IAutoconstant.EXCEL_TestData);
		String Sku_3=getFileLibClass().getDataFromExcell("Multiple_po_GRN_via_bulk", "Sku_3", IAutoconstant.EXCEL_TestData);
		String Sku_Price=getFileLibClass().getDataFromExcell("Multiple_po_GRN_via_bulk", "Sku Price", IAutoconstant.EXCEL_TestData);
        String Warehouse_code=getFileLibClass().getDataFromExcell("Multiple_po_GRN_via_bulk", "Warehouse code", IAutoconstant.EXCEL_TestData);
		
		//Step 1.11 : PO One Create with L1 and L2 approval
		getCommonAction().createPurchaseOrder_(supplier_email, Sku_1, PO_Qty,Sku_Price, false);
		String Po_1=getGloabalData("PO_ID");
		System.out.println("****--"+Po_1+"--****");
		getCommonAction().userLogOut();
		getCommonAction().procureApproveProcess("PO", "approve");
		getCommonAction().userLogOut();
		getCommonAction().financeApproveProcess("PO", "approve");
		getCommonAction().userLogOut();
		
		//Step 1.21 : PO Two Create with L1 and L2 approval
		getCommonAction().userLoginPage("user", "User Login");
		getCommonAction().createPurchaseOrder_(supplier_email, Sku_2, PO_Qty,Sku_Price, false);
		String Po_2=getGloabalData("PO_ID");
		System.out.println("****--"+Po_2+"--****");
		getCommonAction().userLogOut();
		getCommonAction().procureApproveProcess("PO", "approve");
		getCommonAction().userLogOut();
		getCommonAction().financeApproveProcess("PO", "approve");
		getCommonAction().userLogOut();
		
		//Step 1.31 : PO three Create with L1 and L2 approval
		getCommonAction().userLoginPage("user", "User Login");
		getCommonAction().createPurchaseOrder_(supplier_email, Sku_3, PO_Qty,Sku_Price, false);
		String Po_3=getGloabalData("PO_ID");
		getCommonAction().userLogOut();
		getCommonAction().procureApproveProcess("PO", "approve");
		getCommonAction().userLogOut();
		getCommonAction().financeApproveProcess("PO", "approve");
		getCommonAction().userLogOut();
		System.out.println("****--"+Po_3+"--****--"+Po_1+"--****--"+Po_2+"--****");
		getCommonAction().userLoginPage("user", "User Login");
		
//Updating TestData into CSV File
		FileLibClass fil =new FileLibClass();
		String inward_csv_file = IAutoconstant.CSV_File_Bulk_GRN_Upload; 
		
		//for PO First
	    String Po_No="Po No";
	    fil.editCSV(inward_csv_file,1,Po_No,Po_1);
	    
	    String Warehouse_Code="Warehouse Code";
	    fil.editCSV(inward_csv_file,1,Warehouse_Code,Warehouse_code);

	    String SKU="SKU";
	    fil.editCSV(inward_csv_file,1,SKU,Sku_1);
	    
	    String Po_Qty_To_Inward="Po Qty To Inward";
	    fil.editCSV(inward_csv_file,1,Po_Qty_To_Inward,PO_Qty);


	  //for PO Second
	    fil.editCSV(inward_csv_file,2,Po_No,Po_2);
	    fil.editCSV(inward_csv_file,2,Warehouse_Code,Warehouse_code);
	    fil.editCSV(inward_csv_file,2,SKU,Sku_2);
	    fil.editCSV(inward_csv_file,2,Po_Qty_To_Inward,PO_Qty);

	  //for PO Third
	    fil.editCSV(inward_csv_file,3,Po_No,Po_3);
	    fil.editCSV(inward_csv_file,3,Warehouse_Code,Warehouse_code);
	    fil.editCSV(inward_csv_file,3,SKU,Sku_3);
	    fil.editCSV(inward_csv_file,3,Po_Qty_To_Inward,PO_Qty);
	    
		//Method to upload CSV 
		Getting_Started_OMS_MedikabazaarPage getting_started = new Getting_Started_OMS_MedikabazaarPage(getWebDriver());
		Inward_Inventory_Quantity_Bulk_Upload_OMS_Medikabazaar_Page inwar_bulk_page=new Inward_Inventory_Quantity_Bulk_Upload_OMS_Medikabazaar_Page(getWebDriver()) ;
		
		customClick(getting_started.get_Operations_button());
		customClick(getting_started.getBulk_GRN_creation_menu());
		customClick(inwar_bulk_page.getUpload_csv_field());
		String invoice_file=IAutoconstant.Pdf_dummy;
		inwar_bulk_page.getUpload_csv_field().sendKeys(inward_csv_file);
		inwar_bulk_page.getUpload_invoice_field().sendKeys(invoice_file);
		inwar_bulk_page.getUpload_button().click();
		customClick(inwar_bulk_page.getQa_check_box());
		customSendText(inwar_bulk_page.getInvoice_text_field(), "123456");
		customSendText(inwar_bulk_page.getInvoice_amount_field(), "10");
		customClick(inwar_bulk_page.getConfirm_button());
	    String GRN_Generate_test=inwar_bulk_page.getGrn_generated_Message().getText();
	    String GRN_Number = getCommonAction().getOnlyId(GRN_Generate_test, "GRN");
	    System.out.println("GRN_Number : "+GRN_Number);
		
		
		
	}

}
