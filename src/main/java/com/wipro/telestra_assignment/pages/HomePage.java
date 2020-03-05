package com.wipro.telestra_assignment.pages;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.wipro.telestra_assignment.base.BasePage;
import com.wipro.telestra_assignment.utils.ElementUtil;
import com.wipro.telestra_assignment.utils.ExcelReader;
import com.wipro.telestra_assignment.utils.JavaScriptUtil;

public class HomePage extends BasePage {

	ElementUtil elementUtil;
	JavaScriptUtil jsUtil;
	ExcelReader excelread; 
	
	By SearchBar = By.xpath("//input[@class='LM6RPg']");
	//By SItemImg = By.xpath("(//img[contains(@class,'_1Nyybr')])[7]");
	By ItemImg = By.xpath("(//img[contains(@class,'_1Nyybr')])[6]");
	By ItemTitle = By.xpath("(//div[@class='_3wU53n'])[7]");
	
	By add_to_cart_button = By.xpath("//button[normalize-space()='ADD TO CART']");
	By title_info = By.xpath("//h1[@class='_9E25nV']//span");
	By price_info = By.xpath("//div[@class='_1vC4OE _3qQ9m1']");
	By title_info_cart = By.xpath("//div//a[@class='_325-ji _3ROAwx']");
	By price_info_cart = By.xpath("//span[@class='pMSy0p XU9vZa']");
	By remove_from_cart = By.xpath("//div[text()='Remove']");
	By go_to_cart_button = By.xpath("//button[normalize-space()='GO TO CART']");

	public HomePage(WebDriver driver) {
		driver1 = driver;
		elementUtil = new ElementUtil(driver);
		jsUtil = new JavaScriptUtil(driver);
		excelread = new ExcelReader(System.getProperty("user.dir") + "\\src\\test\\resources\\excel\\testdata.xlsx");
	}
	
	public void verifySearchItem() throws InterruptedException {
		
		elementUtil.waitForElementPresent(SearchBar);
		String camera = excelread.getCellData("Home", "Item", 2);
		System.out.println("Cameraname = " + camera);
		elementUtil.doClick(SearchBar);
		elementUtil.doSendKeys(SearchBar, camera);
		Thread.sleep(5000);
		driver1.findElement(SearchBar).sendKeys(Keys.ENTER);
		Thread.sleep(5000);
		jsUtil.scrollIntoView(driver1.findElement(ItemImg));
		Thread.sleep(5000);
		elementUtil.doClick(ItemTitle);
	}
	
	public void verifyAddtoCartItem() {
		System.out.println("Entering new Window handles");
		String mainWindow=driver1.getWindowHandle();
		 Set<String> set =driver1.getWindowHandles();
		 Iterator<String> itr= set.iterator();
		 while(itr.hasNext()){
		 String childWindow=itr.next();
		 if(!mainWindow.equals(childWindow))
		 	{
			 driver1.switchTo().window(childWindow);
			 System.out.println(driver1.switchTo().window(childWindow).getTitle());
			 elementUtil.waitForElementPresent(title_info);
			 String item_name = elementUtil.doGetText(title_info);
			 System.out.println("item_name = " + item_name);
			 String item_name_before_cart = item_name.substring(0, item_name.indexOf("("));
			 item_name_before_cart = item_name_before_cart.trim();
			 System.out.println("item_name_before_cart = " + item_name_before_cart);
			 elementUtil.waitForElementPresent(price_info);
			 String price_information = elementUtil.doGetText(price_info);
			 String price_info_before_cart = price_information.substring(1);
			 //String price_info_before_cart1 = price_info_before_cart.substring(price_info_before_cart.indexOf("?")-1,price_info_before_cart.length());
			 System.out.println("price_info_before_cart = " + price_info_before_cart);
			 
			 if (driver1.findElements(add_to_cart_button).size() == 1)
			 {
				 elementUtil.waitForElementPresent(add_to_cart_button);
			 	 elementUtil.doClick(add_to_cart_button);
			 }
			 else if (driver1.findElements(go_to_cart_button).size() == 1)
			 {
				 elementUtil.waitForElementPresent(go_to_cart_button);
			 	 elementUtil.doClick(go_to_cart_button);
			 }
			 else
			 {
				 System.out.println("Add or Go to Cart not visible");
			 }
			 elementUtil.waitForElementPresent(title_info_cart);
			 String item_name_after_cart = elementUtil.doGetText(title_info_cart);
			 System.out.println("item_name_after_cart = " + item_name_after_cart);
			 Assert.assertEquals(item_name_before_cart, item_name_after_cart);
			 
			 elementUtil.waitForElementPresent(price_info_cart);
			 String price_info = elementUtil.doGetText(price_info_cart);
			 String price_info_after_cart = price_info.substring(1);
			 System.out.println("price_info_after_cart = " + price_info_after_cart);
			 Assert.assertEquals(price_info_before_cart, price_info_after_cart);
			 
			 elementUtil.waitForElementPresent(remove_from_cart);
			 elementUtil.doClick(remove_from_cart);
			 driver1.close();
			 driver1.switchTo().window(mainWindow);
		 	}
		
		 }
	}
}
		
		 


