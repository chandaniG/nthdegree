package onsightPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Webapp.OnsightApp.OnsightTestCase;

public class LoginPage {
	WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		}
	
	@FindBy(xpath = "//input[@name='Username' and @type='text']")
     WebElement username;
	
	@FindBy(xpath = ".//input[@name='Password']")
	WebElement password;
	

	@FindBy(xpath = "//*[@type='submit']")
	public WebElement loginbutton;
	
	@FindBy(xpath = "//*[contains(text(),'Manage users')]")
	public WebElement managebutton;
	
	public void login()
	{
		username.click();
		username.sendKeys("TestOnsightWA-Admin");
		password.sendKeys("TAwa1234!");
		
	}
	public void buttonclick()
	{
		loginbutton.click();
	}

}
