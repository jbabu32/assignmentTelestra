package com.wipro.telestra_assignment.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.wipro.telestra_assignment.base.BasePage;
import com.wipro.telestra_assignment.utils.ElementUtil;
import com.wipro.telestra_assignment.utils.ExcelReader;
import com.wipro.telestra_assignment.utils.JavaScriptUtil;


public class LoginPage extends BasePage {

	ElementUtil elementUtil;
	JavaScriptUtil jsUtil;
	ExcelReader excelread; 

	// locators - By
	By LoginEnableButton = By.xpath("//div//a[text()='Login']");
	By emailId = By.xpath("//input[@class='_2zrpKA _1dBPDZ']");
	By password = By.xpath("//input[@class='_2zrpKA _3v41xv _1dBPDZ']");
	By loginButton = By.xpath("(//span[text()='Login'])[2]/..");
	By signUpLink = By.linkText("New to Flipkart? Create an account");
	By loginErrorText = By.xpath("//div[@class='private-alert__inner']");
	
	

	public LoginPage(WebDriver driver) {
		driver1 = driver;
		elementUtil = new ElementUtil(driver1);
		jsUtil = new JavaScriptUtil(driver1);
		excelread = new ExcelReader(System.getProperty("user.dir") + "\\src\\test\\resources\\excel\\testdata.xlsx");
	}

	public boolean checkSignUpLink() {
		elementUtil.waitForElementPresent(signUpLink);
		return elementUtil.doIsDisplayed(signUpLink);
	}

	public void doLogin() throws InterruptedException {
		elementUtil.waitForElementPresent(emailId);
		String username = excelread.getCellData("Login", "Username", 2);
		String password1 = excelread.getCellData("Login", "Password", 2);
		elementUtil.doSendKeys(emailId, username);
		elementUtil.doSendKeys(password, password1);
		elementUtil.doClick(loginButton);

	}
	

}
