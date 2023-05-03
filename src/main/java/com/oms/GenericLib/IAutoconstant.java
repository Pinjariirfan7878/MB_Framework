package com.oms.GenericLib;

public interface IAutoconstant {
	
	String PROP_PATH= "./Data/config.properties";
	String EXCEL_URL="./Data/URL.xlsx";
	String EXCEL_TestData ="./Data/TestData.xlsx";
	
	String Screenshot_PATH="./Reports/ScreenShot/" ; 
	String Screenshot_PATH_PO="./Reports/ScreenShot/PO/";
	String Screenshot_PATH_IB="./Reports/ScreenShot/IB/";
	String Screenshot_PATH_RTS="./Reports/ScreenShot/RTS/";
	String Screenshot_PATH_SO="./Reports/ScreenShot/SO/";
	String Screenshot_PATH_MIB="./Reports/ScreenShot/MIB/";

	String Screenshot_Failed_PATH="./Reports/ScreenShot/Failed_Screenshot/";
	String Extendreport_PATH="./Reports/";
	String Screenshot_URL_PATH="/Reports/ScreenShot/URL/";
	
	String Pdf_dummy=System.getProperty("user.dir")+"./Data/dummy.pdf";
	String CSV_File_SSU=System.getProperty("user.dir")+"./Data/stockupdate.csv";
	String CSV_File_Bulk_GRN_Upload=System.getProperty("user.dir")+"./Data/inward_inventory_qty.csv";
	String Screenshot_PATH_Supplier_Master="./Reports/ScreenShot/Supplier_Master/";
	
	String PO_bulk_sku=System.getProperty("user.dir")+"./Data/Bulk_PO_442.csv";
	String SO_bulk_sku=System.getProperty("user.dir")+"./Data/Bulk_SO_442.csv";
	String SO_bulk_sku_553=System.getProperty("user.dir")+"./Data/SOBULK_553.csv";
	String PO_bulk_sku_553=System.getProperty("user.dir")+"./Data/POBULK_553.csv";
	String SO_bulk_sku_553_modify=System.getProperty("user.dir")+"./Data/SOBULK_553_modify.csv";
	String SO_bulk_sku_modify=System.getProperty("user.dir")+"./Data/Bulk_SO_442_modify.csv";
	
}
