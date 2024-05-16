package onsightPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ManageUser {
WebDriver driver;
	
	public ManageUser(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		}
	
	@FindBy(xpath = "//a[normalize-space()='Manage users']")
	public WebElement manageUser;
	
	@FindBy(xpath = "//a[@title='Email Address Filter Menu']")
	public WebElement emailFilter;
	
	@FindBy(xpath ="//input[@class='k-textbox k-input k-input-md k-rounded-md k-input-solid ng-pristine ng-valid ng-touched']")
	public WebElement emailField;

	// //input[@class='k-textbox k-input k-input-md k-rounded-md k-input-solid ng-pristine ng-valid ng-touched']
	//input[class='k-textbox k-input k-input-md k-rounded-md k-input-solid ng-pristine ng-valid ng-touched']
	//k-textbox k-input k-input-md k-rounded-md k-input-solid ng-pristine ng-valid ng-touched
	
	@FindBy(xpath ="button[type='submit']")
	public WebElement filterButtonClick;
// //kendo-grid-string-filter-menu-input[1]//kendo-grid-filter-menu-input-wrapper[1]//input[1]
	
	//input[@class='k-textbox k-input k-input-md k-rounded-md k-input-solid ng-pristine ng-valid ng-touched']
	
	
	//input[class='k-textbox k-input k-input-md k-rounded-md k-input-solid ng-pristine ng-valid ng-touched']

	
}
