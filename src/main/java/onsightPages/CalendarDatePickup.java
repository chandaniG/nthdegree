package onsightPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CalendarDatePickup {
WebDriver driver;
	
	public CalendarDatePickup(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		}
	
	@FindBy(xpath = "//*[contains(text(),'Summary Report')]")
	public WebElement summeryReport;

}
