package com.wipro.telestra_assignment.pages;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.wipro.telestra_assignment.base.BasePage;
import com.wipro.telestra_assignment.utils.ElementUtil;
import com.wipro.telestra_assignment.utils.ExcelReader;
import com.wipro.telestra_assignment.utils.JavaScriptUtil;


public class PurchasePage extends BasePage {

	ElementUtil elementUtil;
	JavaScriptUtil jsUtil;
	ExcelReader excelread; 

	// locators - By
	By add_to_cart_button = By.xpath("//button[normalize-space()='ADD TO CART']");
	By title_info = By.xpath("//h1[@class='_9E25nV']//span");
	By price_info = By.xpath("//div[@class='_1vC4OE _3qQ9m1']");
	By title_info_cart = By.xpath("//div//a[@class='_325-ji _3ROAwx']");
	By price_info_cart = By.xpath("//span[@class='pMSy0p XU9vZa']");
	By remove_from_cart = By.xpath("//div[text()='Remove']");
	By go_to_cart_button = By.xpath("//button[normalize-space()='GO TO CART']");
	

	public PurchasePage(WebDriver driver) {
		driver1 = driver;
		elementUtil = new ElementUtil(driver1);
		jsUtil = new JavaScriptUtil(driver1);
		excelread = new ExcelReader(System.getProperty("user.dir") + "\\src\\test\\resources\\excel\\testdata.xlsx");
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
