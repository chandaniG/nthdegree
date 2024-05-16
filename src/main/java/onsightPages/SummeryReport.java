package onsightPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SummeryReport {
WebDriver driver;
	
	public SummeryReport(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		}
	
	@FindBy(xpath = "//*[contains(text(),'Summary Report')]")
	public WebElement summeryReport;
	
	@FindBy(xpath = "//*[contains(text(),'On-Sight Master Summary')]")
	public WebElement mastersummeryReport;
	
	@FindBy(xpath = "//input[@id='datepicker-1']")
	public WebElement startdate;
	//*[@id="datepicker-1"]
	
	@FindBy(xpath = "//input[@id='datepicker-2']")
	public WebElement enddate;
	//*[@id="datepicker-2"]
	@FindBy(xpath="/html/body/app-root/app-on-sight-master/div/div/div/div/div[1]/form/div/div[2]/div/div/kendo-datepicker/button/kendo-icon-wrapper/kendo-svg-icon")
	public WebElement calendarbutton;
	
	@FindBy(xpath = "//button[normalize-space()='Submit']")
	public WebElement submit;
	
}

