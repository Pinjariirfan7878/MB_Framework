package com.ObjectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Edit_Stock_OMS_Medikabazaar {

	@FindBy(xpath="//buttton[@class=\"btn btn-primary addmore\"]") private WebElement add_more_button; ;
	@FindBy(id="comment") private WebElement comment_test_filed;
	@FindBy(xpath="//button[@type=\"submit\"]") private WebElement submit_button;
	@FindBy(xpath="(//table[@class=\"table datatable dataTable no-footer\"]//tr[*]//td[last()-13])[last()]//input") private WebElement batch_no_textfield;
	@FindBy(xpath="(//table[@class=\"table datatable dataTable no-footer\"]//tr[*]//td[last()-12])[last()]//input") private WebElement exp_date_textfiled;
	@FindBy(xpath="//td[@class=\"today day\"]") private WebElement date_picker_testfield;
	@FindBy(xpath="(//table[@class=\"table datatable dataTable no-footer\"]//tr[*]//td[last()-1])[last()]//select") private WebElement rack_shelf_floor_dd;
	//@FindBy(xpath="(//table[@class=\"table datatable dataTable no-footer\"]//tr[*]//td[last()-2])[last()]//input") private WebElement po_Qty_textfield;
	@FindBy(xpath="//table[@class=\"table datatable dataTable no-footer\"]//tr[last()]//td[last()-3]//input") private WebElement po_Qty_textfield;
	@FindBy(xpath="//button[@class=\"btn btn-danger removerow trash\"]") private WebElement delete_button;
	
	
	public Edit_Stock_OMS_Medikabazaar(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}
	public WebElement getAdd_more_button() {
		return add_more_button;
	}
	public WebElement getComment_test_filed() {
		return comment_test_filed;
	}
	public WebElement getSubmit_button() {
		return submit_button;
	}
	public WebElement getBatch_no_textfield() {
		return batch_no_textfield;
	}
	public WebElement getExp_date_textfiled() {
		return exp_date_textfiled;
	}
	public WebElement getDate_picker_testfield() {
		return date_picker_testfield;
	}
	public WebElement getRack_shelf_floor_dd() {
		return rack_shelf_floor_dd;
	}
	public WebElement getPo_Qty_textfield() {
		return po_Qty_textfield;
	}
	public WebElement getDelete_button() {
		return delete_button;
	}
	



	
	
	

}
