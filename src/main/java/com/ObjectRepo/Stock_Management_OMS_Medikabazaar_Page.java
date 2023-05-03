package com.ObjectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Stock_Management_OMS_Medikabazaar_Page {

	
	@FindBy(xpath="(//select[@id='warehouse_name'])[1]") private WebElement Warehouse_Name ;
	//@FindBy(xpath="//span[@id=\"select2-warehouse_name-container\"]") private WebElement Warehouse_Name;
	@FindBy(xpath="(//input[@id='sku'])[1]") private WebElement sku ;
	@FindBy(xpath="//button[@id='btnFiterSubmitSearch']") private WebElement search_btn ;
	@FindBy(xpath = "((//table//tr)[3]//td)[3]")private WebElement stockCount;
	//@FindBy(xpath="//td[@class=\"dtfc-fixed-left\"]//a") private WebElement Sku_column;
	@FindBy(xpath="//td[@class=\"sorting_1 dtfc-fixed-left\"]//a") private WebElement Sku_column;
	
	@FindBy(xpath="//button[@data-bs-target=\"#uploadCSV\"]") private WebElement uploadcsv_button;
	@FindBy(xpath="//button[text()=\"Stock Correction\"]") private WebElement stock_correction_tab;
	@FindBy(xpath="//input[@id=\"special_stock_update_csv\"]") private WebElement upload_file_field;
    @FindBy(xpath="//button[@class=\"btn btn-primary importCSV2\"]") private WebElement import_button;
    
    @FindBy(xpath="//button[text()='Stock Loggers']") private WebElement Stock_logger_tab;
   // @FindBy(xpath="(//select[@id=\"warehouse_name\"])[2]") private WebElement warehouse_dd;
  
    @FindBy(xpath="//select[@id=\"warehouse_name2\"]") private WebElement warehouse_dd;
    
   // @FindBy(xpath="(//input[@id=\"sku\"])[2]") private WebElement sku_textfield;
    @FindBy(xpath="//input[@id=\"sku2\"]") private WebElement sku_textfield;
  
    @FindBy(id="transaction_type") private WebElement Transaction_Sub_Type_dd;
	@FindBy(id="btnFiterSubmitSearch3") private WebElement search_button;

	@FindBy(xpath="//th[text()='Previous Total Stock']") private WebElement Previous_Total_Stock_th;
	@FindBy(xpath="//table[@id=\"visitorslogid2\"]//tr[1]//td[13]")private WebElement QTY;
	@FindBy(xpath="//table[@id=\"visitorslogid2\"]//tr[1]//td[12]") private WebElement scheme_Qty;
	@FindBy(xpath="//table[@id=\"visitorslogid2\"]//tr[1]//td[14]")private WebElement decrease_stock;
	@FindBy(xpath="//table[@id=\"visitorslogid2\"]//tr[1]//td[15]")private WebElement total_Qty_back_to_stock;
	@FindBy(xpath="//table[@id=\"visitorslogid2\"]//tr[1]//td[16]")private WebElement current_total_stock;
	@FindBy(xpath="//table[@id=\"visitorslogid2\"]//tr[1]//td[17]") private WebElement Previous_Total_Stock_th_qty;
	
	
	public WebElement getPrevious_Total_Stock_th_qty() {
		return Previous_Total_Stock_th_qty;
	}
	public Stock_Management_OMS_Medikabazaar_Page(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}
	
	public WebElement getPrevious_Total_Stock_th() {
		return Previous_Total_Stock_th;
	}

	public WebElement getQTY() {
		return QTY;
	}

	public WebElement getScheme_Qty() {
		return scheme_Qty;
	}

	public WebElement getDecrease_stock() {
		return decrease_stock;
	}

	public WebElement getTotal_Qty_back_to_stock() {
		return total_Qty_back_to_stock;
	}

	public WebElement getCurrent_total_stock() {
		return current_total_stock;
	}

	public WebElement getStock_logger_tab() {
		return Stock_logger_tab;
	}

	public WebElement getWarehouse_dd() {
		return warehouse_dd;
	}

	public WebElement getSku_textfield() {
		return sku_textfield;
	}

	public WebElement getTransaction_Sub_Type_dd() {
		return Transaction_Sub_Type_dd;
	}

	public WebElement getSearch_button() {
		return search_button;
	}

	public WebElement get_Warehouse_Name() {
		return Warehouse_Name;
	}
	public WebElement get_sku_field()
	{
		return sku;
	}
	public WebElement get_search_button() {
		return search_btn;
	}
	public WebElement get_stock_count_ib() {
		return stockCount;
	}
	public WebElement getSku_column() {
		return Sku_column;
	}	
	public WebElement getUploadcsv_button() {
		return uploadcsv_button;
	}

	public WebElement getStock_correction_tab() {
		return stock_correction_tab;
	}

	public WebElement getUpload_file_field() {
		return upload_file_field;
	}

	public WebElement getImport_button() {
		return import_button;
	}
}
