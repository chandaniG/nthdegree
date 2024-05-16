package utilis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class InputUtilis {	
	private static int DATE_LEFT_COUNT = 3;
	
	public static void inputDate(WebDriver driver, WebElement element, String month, String day, String year) throws InterruptedException {
		element.click();
		
		for(int i=0; i<DATE_LEFT_COUNT; i++){
			element.sendKeys(Keys.LEFT);
		}
		
		element.sendKeys(month);
		element.sendKeys(day);
		element.sendKeys(year);
	}
	
	public static HashMap<String, List<Double>> filterPercentage(List<List<String>> list) {

	    HashMap<String, List<Double>> records = new HashMap<>();

	    for(List<String> item : list) {
	        if(item.size() > 2 && "%".equals(item.get(item.size() - 1))) {
	            String country = item.stream().findFirst().get().replaceAll("%", "").trim();
	            List<Double> items = new ArrayList<>();

	            for(String value : item.subList(1, item.size() - 1)) {
	                items.add(Double.parseDouble(value.replace("%", "")));
	            }
	            records.put(country, items);

	        } else if(item.size() > 2 && "QTY".equals(item.get(item.size() - 1))) {
	            //System.out.println("QTY Item");

	        } else if(item.size() > 2 && "Total".equals(item.get(item.size() - 1))) {
	            //System.out.println("Total Item");
	        }
	    }
	    return records;
	}
		
	public static HashMap<String, List<Double>> filterGrandPercentage(List<List<String>> list) {

	    HashMap<String, List<Double>> records = new HashMap<>();

	    for(List<String> item : list) {
	        if(item.size() > 1 && "Grand Total %".equals(item.get(0))) {
	            String country = item.stream().findFirst().get().replaceAll("%", "").trim();
	            List<Double> items = new ArrayList<>();

	            for(String value : item.subList(1, item.size())) {
	                items.add(Double.parseDouble(value.replace("%", "")));
	            }
	            records.put(country, items);

	        } else if(item.size() > 1 && "Grand Total Quantity".equals(item.get(0))) {
	            //System.out.println("QTY Item");

	        } else if(item.size() > 1 && "Grand Total".equals(item.get(0))) {
	            //System.out.println("Total Item");
	        }
	    }
	    return records;
	}

}
