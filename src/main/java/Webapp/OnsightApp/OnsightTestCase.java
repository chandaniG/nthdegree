package Webapp.OnsightApp;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import dev.failsafe.Timeout;
import onsightPages.LoginPage;
import onsightPages.ManageUser;
import onsightPages.SummeryReport;
import utilis.InputUtilis;

public class OnsightTestCase {
	WebDriver driver;
	LoginPage path;

	@BeforeTest
	public void launchapp() throws InterruptedException{
		//System.setProperty("webdriver.chrome.driver","C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe");
		driver= new ChromeDriver();
		driver.manage().window().setSize(new Dimension(824,768));
		driver.manage().window().maximize();
		//JavascriptExecutor js = (JavascriptExecutor)driver;
		//js.executeScript("document.body.style.zoom='40%'");
		//((JavascriptExecutor)driver).executeScript("document.body.style.transform='scale(0.8)'");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://onsight-stage.nthdegree.com/#/login");	
	}

	@Test(priority = 1)
	public void loginapp() throws InterruptedException{
		path = new LoginPage(driver);
		//Thread.sleep(5000);
		path.login();
		path.buttonclick();	
	}

	@Test(priority = 2,enabled = false)
	public void summery() throws InterruptedException{
		SummeryReport user = new SummeryReport(driver);
		user.summeryReport.click();
		user.mastersummeryReport.click();

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='datepicker-1']")));

		WebElement element1 = driver.findElement(By.xpath("//input[@id='datepicker-1']")); 
		InputUtilis.inputDate(driver, element1, "03", "01", "2023");

		WebElement element2 = driver.findElement(By.xpath("//input[@id='datepicker-2']"));
		InputUtilis.inputDate(driver, element2, "12", "31", "2024");

		user.submit.click();
	}

	@Test(priority = 3,enabled = false)
	public void verifyMasterSummery() throws InterruptedException{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("grid-master-wrapper")));

		WebElement table = driver.findElement(By.className("grid-master-wrapper"));

		List<WebElement> headersList = table.findElements(By.tagName("th"));
		List<String> headers = findTableItems(headersList);

		WebElement content = table.findElement(By.className("k-grid-container"));
		List<WebElement> trList = null; 

		int retry = 0;
		while(retry < 5 && (trList == null || trList.size() <= 1)) {
			trList = content.findElements(By.tagName("tr"));

			Thread.sleep(5000);
			retry++; 
		}
		System.out.println(trList.size());

		List<List<String>> records = new ArrayList();
		for(WebElement row : trList) {
			records.add(findTableTrItems(row));	
		}
		HashMap<String, List<Double>> percentage = InputUtilis.filterPercentage(records);
		System.out.println(percentage.size());	

		for(Map.Entry<String, List<Double>> entry : percentage.entrySet()) {
			for(Double sub : entry.getValue()) {
				Assert.assertTrue(sub <= 100,  String.format("Error, Percentage is too high : %s in %s", sub, entry.getKey()));
				Assert.assertTrue(sub >= 0, String.format("Error, Percentage is too low : %s in %s", sub, entry.getKey()));
			}
		}
	}

	@Test(priority = 4,enabled = false)
	public void verifyMasterGrandSummery() throws InterruptedException{

		WebElement table = driver.findElement(By.className("grid-footer-wrapper"));

		List<WebElement> headersList = table.findElements(By.tagName("th"));
		List<String> headers = findTableItems(headersList);

		WebElement content = table.findElement(By.className("k-grid-container"));
		List<WebElement> trList = content.findElements(By.tagName("tr")); 


		System.out.println(trList.size());

		List<List<String>> records = new ArrayList();
		for(WebElement row : trList) {
			records.add(findTableTrItems(row));	
		}
		HashMap<String, List<Double>> percentage = InputUtilis.filterGrandPercentage(records);
		System.out.println(percentage.size());	

		for(Map.Entry<String, List<Double>> entry : percentage.entrySet()) {
			for(Double sub : entry.getValue()) {
				Assert.assertTrue(sub <= 100,  String.format("Error, Percentage is too high : %s in %s", sub, entry.getKey()));
				Assert.assertTrue(sub >= 0, String.format("Error, Percentage is too low : %s in %s", sub, entry.getKey()));
			}
		}	
	}

	@Test(priority = 5,enabled = true)
	public void verifyManageUsers() throws InterruptedException {
		ManageUser users = new ManageUser(driver);
		users.manageUser.click();

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("k-grid-aria-root")));

		WebElement table = driver.findElement(By.className("k-grid-aria-root"));

		List<WebElement> headersList = table.findElements(By.tagName("th"));
		List<String> headers = findTableItems(headersList);
		System.out.println(headers.size());

		WebElement content = table.findElement(By.className("k-grid-container"));
		List<WebElement> trList = content.findElements(By.tagName("tr")); 

		System.out.println(trList.size());

		List<List<String>> records = new ArrayList();
		for(WebElement row : trList) {
			records.add(findTableTrItems(row));	
		}		
	}

	@Test(priority = 6,enabled = true)
	public void filterManageUsers() throws InterruptedException {
		ManageUser users = new ManageUser(driver);
		Thread.sleep(5000);
		users.emailFilter.click();

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("kendo-popup")));

		WebElement element = driver.findElement(By.tagName("kendo-popup")).findElement(By.tagName("input"));
		System.out.println(element.getSize());

		element.click();
		element.sendKeys("chandani.kumari@dreamorbit.com");
		users.filterButtonClick.click();

		System.out.println("Email testing");	
	}

	@AfterTest
	public void driverClose(){

		//driver.close();
	}

	/**************************************
	 * Test Case Local Helper Functions - No Direct Execution
	 */

	public List<String> findTableTrItems(WebElement row) {
		List<String> items = new ArrayList<>();
		List<WebElement> tdList = row.findElements(By.tagName("td"));

		for(WebElement element : tdList) {
			if(element != null) {
				items.add(element.getAttribute("innerHTML").replaceAll("<!---->", "").trim());
			} else { items.add(null); }
		}
		System.out.println("---------------------------------------------------------------------------------------------\n");
		System.out.println(items);
		return items;
	}

	public List<String> findTableItems(List<WebElement> itemList) {
		List<String> items = new ArrayList<>();
		for(WebElement item : itemList) {
			WebElement element = item.findElement(By.className("k-link"));
			if(element != null) {
				WebElement sub = element.findElement(By.className("ng-star-inserted"));
				if(sub != null) {
					items.add(sub.getAttribute("innerHTML"));

				} else { items.add(null); }
			} else { items.add(null); }
		}
		//System.out.println(items.size());
		System.out.println(items);
		return items;
	}
}
