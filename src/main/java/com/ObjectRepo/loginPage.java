package com.ObjectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class loginPage {

	@FindBy (xpath="//input[@id=\"email\"]") private WebElement Username_textfield;
	@FindBy (xpath="//input[@id=\"password\"]") private WebElement Password_textfield;
	@FindBy (xpath="//button[text()='Login']") private WebElement Login_button;
	@FindBy(xpath="//label[contains(text(),'Remember Me')]") private WebElement Remember_me;
	@FindBy(xpath="//a[contains(text(),'Forgot Your Password?')]") private WebElement Forgot_Password;

 
	public loginPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	public WebElement getUsername_textfield() {
		return Username_textfield;
	}

	public WebElement getPassword_textfield() {
		return Password_textfield;
	}

	public WebElement getLogin_button() {
		return Login_button;
	}

	public WebElement getRemember_me() {
		return Remember_me;
	}

	public WebElement getForgot_Password() {
		return Forgot_Password;
	}

	public void getLogin_app(String Username,String Password )
	{
		Username_textfield.sendKeys(Username);
		Password_textfield.sendKeys(Password);
		Login_button.click();
	}
      
}
