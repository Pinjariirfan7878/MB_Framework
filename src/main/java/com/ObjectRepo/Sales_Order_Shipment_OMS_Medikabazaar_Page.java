package com.ObjectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Sales_Order_Shipment_OMS_Medikabazaar_Page {

	@FindBy(xpath = "//button[@id='logisticDetailNavId']")
	private WebElement Logistic_details;
	@FindBy(id = "verification_status")
	private WebElement Verification_status;
	@FindBy(xpath = "//button[@id='logistic_form_submit']")
	private WebElement Approval_submit;
	@FindBy(xpath = "//img[@class='rounded-circle']")
	private WebElement Fin_User_logo;
	@FindBy(xpath = "/html/body/header/div[1]/div[2]/div[4]/div/ul/li/div/button")
	private WebElement Fin_logout;
	@FindBy(xpath = "//input[@id='pickAndPack']")
	private WebElement pick_and_pack;
	@FindBy(xpath = "//select[@id='fulfillmentcenter']")
	private WebElement wh_select;
	@FindBy(xpath = "//input[@id='full']")
	private WebElement check_box;
	@FindBy(xpath = "//strong[contains(text(),'Pick And Pack created successfully!')]")
	private WebElement pap_suc_msg;
	@FindBy(xpath = "//a[contains(text(),'View')]")
	private WebElement view;
	@FindBy(xpath = "//*[@id=\"DataTables_Table_1\"]/tbody/tr/td[10]/a")
	private WebElement view2;
	@FindBy(xpath = "//input[@id='orderstatus']")
	private WebElement status_message1;
	@FindBy(xpath = "//button[@id='activitySummaryNavId']")
	private WebElement activity_summary;
	@FindBy(xpath = "//td[contains(text(),'Approved By Procurement')]")
	private WebElement Approved_by_proc_text;
	@FindBy(xpath = "//td[contains(text(),'Approved By Finance')]")
	private WebElement Approved_by_finance_text;
	@FindBy(xpath = "//td[contains(text(),'Dispatched                                                        ')]")
	private WebElement Dispatched_text;
	@FindBy(xpath = "//input[@id='comment']")
	private WebElement Reject_Comment;
	@FindBy(xpath = "//td[contains(text(),'Rejected By Procurement')]")
	private WebElement Reject_Status;
	@FindBy(xpath = "//select[@id='comment_rejected']")
	private WebElement Reject_Comment_Fin;
	@FindBy(xpath = "//td[contains(text(),'Rejected By Finance')]")
	private WebElement Reject_Status_Fin_text;
	@FindBy(xpath = "//button[contains(text(),'Activity Summary')]")
	private WebElement activitySummaryHeader;
	@FindBy(xpath = "//label[contains(text(),'Pending Shipment')]")
	private WebElement pending_shipment_radio_button;
	@FindBy(xpath = "//select[@id=\"pending_shipment_reason\"]")
	private WebElement pending_shipment_reason_dd;
	@FindBy(xpath = "//option[contains(text(),'Pending for Procurement')]")
	private WebElement pending_shipment_reason_text;
	@FindBy(xpath = "(//option[contains(text(),'Irfan Pinjari')])[1]")
	private WebElement procurement_team_member;

	@FindBy(xpath = "//input[@id='additional_procurement_email_text_box']")
	private WebElement email_to_notify;
	@FindBy(xpath = "//input[@id=\"full\"]")
	private WebElement check_box_all; // For pending shipment
	@FindBy(xpath = "//button[@id='logistic_form_submit']")
	private WebElement submit_button;
	@FindBy(xpath = "//td[contains(text(),'Pending for Procurement')]")
	private WebElement pending_procurement_text_Activity_Summary;
	@FindBy(xpath = "(//td[contains(text(),'MedO742')]/following-sibling::td[2])[2]")
	private WebElement Qty_to_processed_Activity_Summary;

	@FindBy(xpath = "//h4[contains(text(),'Pending Shipments with the Reason')]")
	private WebElement Pending_Shipments_heading;
	@FindBy(xpath = "//label[contains(text(),'Procurement Completed')]")
	private WebElement procurement_completed_radio_button;
	@FindBy(xpath = "//a[contains(text(),'Submit Procurement')]")
	private WebElement submit_procurement_button;
	@FindBy(xpath = "//td[contains(text(),'Procurement Complete')]")
	private WebElement procurement_completed_text_Activity_Summary;
	@FindBy(xpath = "//input[@class='form-control text-right qtyprocess']")
	private WebElement Qty_to_process_field_pending_Shipment;

	@FindBy(xpath = "//label[contains(text(),'Procurement Expected')]")
	private WebElement procurement_expected_radio_button;
	@FindBy(xpath = "//input[@id='est_procurement_date']")
	private WebElement expected_delivery_date;
	@FindBy(xpath = "//button[contains(text(),'New Test')]")
	private WebElement operations_team_member_to_notify;
	@FindBy(xpath = "//input[@id='additional_operations_email_text_box']")
	private WebElement pro_expected_email_to_notify;
	@FindBy(xpath = "//td[@class='today active start-date active end-date in-range available']")
	private WebElement today_date;

	@FindBy(xpath = "//button[contains(text(),'Select Operations Team Member')]")
	private WebElement operation_team_member_label;
	@FindBy(xpath = "//div[@class='ms-search']")
	private WebElement operation_team_member_search;
	@FindBy(xpath = "//input[@class=\"form-control text-right qtyprocess\"]")
	private WebElement Qty_to_process_Pro_Expected;
	@FindBy(xpath = "//td[contains(text(),'Procurement Expected')]")
	private WebElement procurement_expected_text_Activity_Summary;
	@FindBy(xpath = "//button[contains(text(),'Order Info')]")
	private WebElement order_info_tab;

	@FindBy(xpath = "(//th[@class='next available'])[1]")
	private WebElement next_month;
	@FindBy(xpath = "(//td[contains(text(),'10')])[1]")
	private WebElement Day_selection;
	@FindBy(xpath = "//label[contains(text(),'Expected Delivery Date')]/following-sibling::div//input")
	private WebElement expectedDeliveryDate;

	public Sales_Order_Shipment_OMS_Medikabazaar_Page(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public WebElement getExpectedDeliveryDate() {
		return expectedDeliveryDate;
	}

	public WebElement getLogistic_details() {
		return Logistic_details;
	}

	public WebElement getVerification_status() {
		return Verification_status;
	}

	public WebElement getApproval_submit() {
		return Approval_submit;
	}

	public WebElement getFin_User_logo() {
		return Fin_User_logo;
	}

	public WebElement getFin_logout() {
		return Fin_logout;
	}

	public WebElement getpick_and_pack_button() {
		return pick_and_pack;
	}

	public WebElement getwh_select_dd() {
		return wh_select;
	}

	public WebElement getcheck_box() {
		return check_box;
	}

	public WebElement getpap_suc_msg() {
		return pap_suc_msg;
	}

	public WebElement getview() {
		return view;
	}

	public WebElement getview2() {
		return view2;
	}

	public WebElement gestatus_message1() {
		return status_message1;
	}

	public WebElement getactivity_summary_tab() {
		return activity_summary;
	}

	public WebElement getApproved_by_proc_text() {
		return Approved_by_proc_text;
	}

	public WebElement getApproved_by_finance_text() {
		return Approved_by_finance_text;
	}

	public WebElement getDispatched_text() {
		return Dispatched_text;
	}

	public WebElement getReject_Comment_Label() {
		return Reject_Comment;
	}

	public WebElement getReject_Status_text() {
		return Reject_Status;
	}

	public WebElement getReject_Comment_Fin() {
		return Reject_Comment_Fin;
	}

	public WebElement getReject_Status_Fin_text() {
		return Reject_Status_Fin_text;
	}

	public WebElement getActivitySummaryHeader() {
		return activitySummaryHeader;
	}

	public WebElement getpending_shipment_radio_button() {
		return pending_shipment_radio_button;
	}

	public WebElement getpending_shipment_reason_dd() {
		return pending_shipment_reason_dd;
	}

	public WebElement getpending_shipment_reason_text() {
		return pending_shipment_reason_text;
	}

	public WebElement getprocurement_team_member() {
		return procurement_team_member;
	}

	public WebElement getemail_to_notify() {
		return email_to_notify;
	}

	public WebElement getcheck_box_all() {
		return check_box_all;
	}

	public WebElement getsubmit_button() {
		return submit_button;
	}

	public WebElement getpending_procurement_text_Activity_Summary() {
		return pending_procurement_text_Activity_Summary;
	}

	public WebElement getPending_Shipments_heading() {
		return Pending_Shipments_heading;
	}

	public WebElement getprocurement_completed_radio_button() {
		return procurement_completed_radio_button;
	}

	public WebElement getsubmit_procurement_button() {
		return submit_procurement_button;
	}

	public WebElement getprocurement_completed_text_Activity_Summary() {
		return procurement_completed_text_Activity_Summary;
	}

	public WebElement getQty_to_process_field_pending_Shipment() {
		return Qty_to_process_field_pending_Shipment;
	}

	public WebElement getQty_to_processed_Activity_Summary() {
		return Qty_to_processed_Activity_Summary;
	}

	public WebElement getprocurement_expected_radio_button() {
		return procurement_expected_radio_button;
	}

	public WebElement getexpected_delivery_date() {
		return expected_delivery_date;
	}

	public WebElement getoperations_team_member_to_notify() {
		return operations_team_member_to_notify;
	}

	public WebElement getpro_expected_email_to_notify() {
		return pro_expected_email_to_notify;
	}

	public WebElement gettoday_date() {
		return today_date;
	}

	public WebElement getoperation_team_member_label() {
		return operation_team_member_label;
	}

	public WebElement getoperation_team_member_search() {
		return operation_team_member_search;
	}

	public WebElement getQty_to_process_Pro_Expected() {
		return Qty_to_process_Pro_Expected;
	}

	public WebElement getprocurement_expected_text_Activity_Summary() {
		return procurement_expected_text_Activity_Summary;
	}

	public WebElement getorder_info_tab() {
		return order_info_tab;
	}

	public WebElement getnext_month_arrow() {
		return next_month;
	}

	public WebElement getDay_selection() {
		return Day_selection;
	}
}
