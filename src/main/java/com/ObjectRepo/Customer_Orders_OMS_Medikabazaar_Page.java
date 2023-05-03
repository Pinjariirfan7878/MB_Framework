package com.ObjectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Customer_Orders_OMS_Medikabazaar_Page {
	
	//@FindBy(xpath="//a[text()=' Orders ']") private WebElement Order_tab;
		@FindBy(xpath="//a[text()=' Sales Orders ']") private WebElement Sales_Order_button;
		@FindBy(xpath="//*[@id=\"add_to_cart\"]/button") private WebElement Create_Sales_Order;
		@FindBy(name="c_increment_id") private WebElement so_order_id_label;
		@FindBy(id="btnFiterSubmitSearch") private WebElement so_search_button;
		@FindBy(xpath="//*[@id=\"example1\"]/tbody/tr[1]/td[2]") private WebElement first_so_id;

		

		


		public Customer_Orders_OMS_Medikabazaar_Page(WebDriver driver)
		{
			PageFactory.initElements(driver,this);
		}
		
		
		public WebElement getcreate_sales_order_button() {
			return Create_Sales_Order;
		}
		public WebElement getso_search_button() {
			return so_search_button;
		}
		public WebElement getso_order_id() {
			return so_order_id_label;
		}
		public WebElement getfirst_so_id() {
			return first_so_id;
		}
		public WebElement getSales_Order_button() {
			return Sales_Order_button;
		}
		/*public WebElement getOrder_tab() {
			return Order_tab;
		}*/
		

}
