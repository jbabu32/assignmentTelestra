package com.wipro.telestra_assignment;

import java.text.ParseException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.wipro.telestra_assignment.base.BasePage;
import com.wipro.telestra_assignment.pages.HomePage;
import com.wipro.telestra_assignment.pages.LoginPage;

public class FlipkartTest extends BasePage {

	BasePage basePage;
	Properties prop;
	LoginPage loginPage;
	HomePage homePage;

	@Test(priority = 1, description = "verify Sign up link Test....")
	public void verifySignUpLinkTest() {
		
		loginPage = new LoginPage(driver1);
		Assert.assertTrue(loginPage.checkSignUpLink());
	}

	@Test(priority = 2, description = "verify Login Test....")
	public void loginSearchPurchaseTest() throws InterruptedException, ParseException {
		loginPage = new LoginPage(driver1);
		homePage = new HomePage(driver1);
		loginPage.doLogin();
		homePage.verifySearchItem();
		homePage.verifyAddtoCartItem();
	}
		

	@AfterTest(alwaysRun=true)
	public void tearDown() {
		driver1.quit();
	}

}
