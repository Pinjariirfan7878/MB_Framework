package com.ObjectRepo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.oms.GenericLib.FileLibClass;
import com.oms.GenericLib.IAutoconstant;

public class PO_MasterModule_OMS_MedikabazaarPage {

	@FindBy(xpath="//select[@id=\"verification_status\"]") private WebElement verification_status_dd;
	@FindBy(xpath="//input[@name=\"comment\"]") private WebElement comment_textfield;
	
	@FindBy(xpath="(//button[contains(text(),'Submit')])[2]") private WebElement sumbit_button;
	//@FindBy(xpath="//input[@id='comment']/../../following-sibling::div//button[contains(text(),'Submit')]") private WebElement sumbit_button;
	@FindBy(xpath="//button[contains(text(),'PO Status And Comment')]") private WebElement PO_status_and_comment_bucket;
	@FindBy(xpath="//td[text()='Approved By Finance']") private WebElement finance_approve_text;
	@FindBy(xpath="//td[text()='approved By Irfan']") private WebElement procure_approve_text;
	@FindBy(xpath="//input[@id=\"qa_check\"]") private WebElement qa_check_box;
	@FindBy(xpath="//input[@id=\"invoice_id\"]") private WebElement invoice_number_textfield;
	@FindBy(xpath="//input[@id=\"invoice_date\"]") private WebElement invoice_date_textfield;
	@FindBy(xpath="//input[@id=\"invoice_amount\"]") private WebElement invoice_amount_textfield;
	@FindBy(xpath="//input[@id=\"invoice_attachment\"]") private WebElement invoice_attachment_textfield;
	@FindBy(xpath="//input[@id=\"full\"]") private WebElement select_all_checkbox;
	@FindBy(xpath="//button[contains(text(),'PO Inward History')]") private WebElement PO_inward_history_bucket;
	@FindBy(xpath="//button[text()='Accepted Order']") private WebElement accepet_order_text;
	@FindBy(xpath="//table[@id=\"DataTables_Table_0\"]//td[contains(text(),'PO')]") private WebElement PO_number;
	@FindBy(xpath="//table[@id=\"DataTables_Table_0\"]//a[contains(text(),'GRN')]") private WebElement GRN_number;
	@FindBy(xpath="//td[text()='Approved By PO Supervisor']") private WebElement procure_approved_text;
	@FindBy(xpath="//td[text()='Rejected By PO Supervisor']") private WebElement Rejected_By_PO_Supervisor_text;
	@FindBy(xpath="//td[text()='Rejected By Procurement']") private WebElement Rejected_By_Procurement_text;
	@FindBy(xpath="//td[text()='Rejected By Finance']") private WebElement Rejected_By_Finance_text;
	@FindBy(xpath="//th[contains(text(),'Status while commented')]") private WebElement StatusWhilCommentedHeader;
	@FindBy(xpath="//button[text()='View PO Details']") private WebElement view_purchase_order_button;
	@FindBy(xpath="//button[contains(text(),'Cancel PO')]") private WebElement cancel_po_button;
	@FindBy(xpath="(//input[@id='full'])[2]") private WebElement po_qty_selectAll;
	@FindBy(xpath="//input[@id=\"comment\"]") private WebElement po_cancel_comment;
	@FindBy(xpath="//td[contains(text(),'Canceled')]") private WebElement canceled_text;
	@FindBy(xpath="//button[contains(text(),'Activity Summary')]") private WebElement activitySummary;

	public WebElement getActivitySummary() {
		return activitySummary;
	}
	public WebElement getRejected_By_Procurement_text() {
		return Rejected_By_Procurement_text;
	}
	public WebElement getCancel_po_button() {
		return cancel_po_button;
	}
	public WebElement getCanceled_text() {
		return canceled_text;
	}

	public WebElement getPo_cancel_comment() {
		return po_cancel_comment;
	}


	public WebElement getView_purchase_order_button() {
		return view_purchase_order_button;
	}

	public WebElement getPo_qty_selectAll() {
		return po_qty_selectAll;
	}

	public WebElement getRejected_By_Finance_text() {
		return Rejected_By_Finance_text;
	}

	public WebElement getStatusWhilCommentedHeader() {
		return StatusWhilCommentedHeader;
	}

	public WebElement getRejected_By_PO_Supervisor_text() {
		return Rejected_By_PO_Supervisor_text;
	}

	public WebElement getProcure_approved_text() {
		return procure_approved_text;
	}

	public WebElement getPO_number() {
		return PO_number;
	}

	public WebElement getGRN_number() {
		return GRN_number;
	}

	public PO_MasterModule_OMS_MedikabazaarPage(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}

	public WebElement getVerification_status_dd() {
		return verification_status_dd;
	}
	public WebElement getComment_textfield() {
		return comment_textfield;
	}
	public WebElement getSumbit_button() {
		return sumbit_button;
	}
	public WebElement getPO_status_and_comment_bucket() {
		return PO_status_and_comment_bucket;
	}
	public WebElement getFinance_approve_text() {
		return finance_approve_text;
	}
	public WebElement getProcure_approve_text() {
		return procure_approve_text;
	}
	public WebElement getQa_check_box() {
		return qa_check_box;
	}
	public WebElement getInvoice_number_textfield() {
		return invoice_number_textfield;
	}
	public WebElement getInvoice_date_textfield() {
		return invoice_date_textfield;
	}
	public WebElement getInvoice_amount_textfield() {
		return invoice_amount_textfield;
	}
	public WebElement getInvoice_attachment_textfield() {
		return invoice_attachment_textfield;
	}
	public WebElement getSelect_all_checkbox() {
		return select_all_checkbox;
	}
	public WebElement getPO_inward_history_bucket() {
		return PO_inward_history_bucket;
	}
	public WebElement getAccepet_order_text() {
		return accepet_order_text;
	}



	public void executeRollbackGRN(WebDriver driver) {
		WebElement RollbackGRNButton = driver.findElement(By.xpath("//button[contains(text(),'Rollback GRN')]"));
		WebElement CommentTextField = driver.findElement(By.xpath("//input[@id='po_grnno']/../textarea"));
		WebElement SubmitButton = driver.findElement(By.xpath("//input[@id='po_grnno']/../following-sibling::div/button"));

		FileLibClass filclass = new FileLibClass();

		RollbackGRNButton.click();
		String rollbackGRNComment = filclass.getDataFromExcell("PO_Data", "rollbackGRNComment",
				IAutoconstant.EXCEL_TestData);
		CommentTextField.sendKeys(rollbackGRNComment);
		SubmitButton.click();
	}
}
