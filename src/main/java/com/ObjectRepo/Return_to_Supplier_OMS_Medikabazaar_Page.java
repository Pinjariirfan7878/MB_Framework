package com.ObjectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Return_to_Supplier_OMS_Medikabazaar_Page {

	@FindBy(id="warehouse_id") private WebElement Warehouse;
	@FindBy(id="select2-seller_id-container") private WebElement supplier;
	@FindBy(xpath="//input[@class='select2-search__field']") private WebElement supplier_dd;
	@FindBy(xpath="//input[@class='select2-search__field']") private WebElement supplier_search;
	@FindBy(id="courier_type_id") private WebElement Courier_type;
	@FindBy(id="es_tat") private WebElement Est_tat;
	@FindBy(id="gst") private WebElement gst;
	@FindBy(id="distance") private WebElement Distance;
	@FindBy(id="transporter_name") private WebElement Transporter_name;
	@FindBy(id="gstno") private WebElement GST_num;
	@FindBy(id="comment") private WebElement Comment;
	@FindBy(id="update_return_to_vendor_btn") private WebElement Update;
	@FindBy(xpath="//button[contains(text(),'+ Add Product')]") private WebElement Add_Product;
	@FindBy(id="create_order") private WebElement Create_RTS;
	@FindBy(xpath="//input[@name='debit_note_number']") private WebElement Debit_Note_Num;
	@FindBy(xpath="//input[@name='debit_note_date']") private WebElement Debit_Note_Date;
	@FindBy(xpath="//input[@name='debit_note_attachment']") private WebElement Debit_Note_Attachment;
	@FindBy(xpath="//select[@name='verification_status']") private WebElement Approval_Status;
	@FindBy(xpath="//textarea[@name='approval_comment']") private WebElement Approval_Comment;
	@FindBy(xpath="//button[contains(text(),'Pending')]") private WebElement Pending_Button;
	@FindBy(xpath="//*[@id=\"view_update_return_to_vendor_log\"]/div[1]/div[3]/a") private WebElement Udate_Tally_Info;
	@FindBy(xpath="/html/body/div[2]/div/div[3]/div/div/div[3]/div/div[3]/button") private WebElement Approve_Green;
	@FindBy (xpath="//input[@name=\"product_data[86731][invoice_number]\"]") private WebElement invoice_number;
	@FindBy(xpath="//input[@name=\"product_data[86731][invoice_date]\"]") private WebElement invoice_date_picker;
	@FindBy(xpath="//td[@class=\"today day\"]") private WebElement today_date;
    @FindBy(xpath="//input[@name=\"product_data[86731][price]\"]") private WebElement product_price;
	@FindBy(xpath="//th[text()='Product']") private WebElement product_table;
	@FindBy(xpath="//i[@class=\"fa fa-save\"]") private WebElement update_action_button;
	

	

	public Return_to_Supplier_OMS_Medikabazaar_Page(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}

	public WebElement getProduct_table() {
		return product_table;
	}

	public WebElement getWarehouse_dd() {
		return Warehouse;
	}
	public WebElement getToday_date() {
		return today_date;
	}

	public WebElement getSupplier_dd() {
		return supplier;
	}
	public WebElement getsupplier_search() {
		return supplier_search;
	}
	public WebElement getCourier_type_dd() {
		return Courier_type;
	}
	public WebElement getEst_tat() {
		return Est_tat;
	}
	public WebElement getgst_Label() {
		return gst;
	}
	public WebElement getDistance_Label() {
		return Distance;
	}
	public WebElement getTransporter_name() {
		return Transporter_name;
	}
	public WebElement getGST_num() {
		return GST_num;
	}
	public WebElement getComment() {
		return Comment;
	}
	public WebElement getUpdate_button() {
		return Update;
	}
	public WebElement getAdd_Product_button() {
		return Add_Product;
	}
	public WebElement getCreate_RTS_button() {
		return Create_RTS;
	}
	public WebElement getDebit_Note_Num() {
		return Debit_Note_Num;
	}
	public WebElement getDebit_Note_Date() {
		return Debit_Note_Date;
	}
	public WebElement getDebit_Note_Attachment() {
		return Debit_Note_Attachment;
	}
	public WebElement getApproval_Status() {
		return Approval_Status;
	}
	public WebElement getApproval_Comment() {
		return Approval_Comment;
	}

	public WebElement getUdate_Tally_Info() {
		return Udate_Tally_Info;
	}
	public WebElement getApprove_Green() {
		return Approve_Green;
	}
	public WebElement getPending_Button() {
		return Pending_Button;
	}
	public WebElement getInvoice_number() {
		return invoice_number;
	}

	public WebElement getInvoice_date() {
		return invoice_date_picker;
	}
	public WebElement getProduct_price() {
		return product_price;
	}
	public WebElement getUpdate_action_button() {
		return update_action_button;
	}

}
