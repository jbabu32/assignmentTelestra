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
	By ItemImg = By.xpath("(//img[contains(@class,'_1Nyybr')])[6]");
	By ItemTitle = By.xpath("(//div[@class='_3wU53n'])[7]");
	

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
}
		
		 


