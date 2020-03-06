package com.wipro.telestra_assignment;

import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.wipro.telestra_assignment.base.BasePage;
import com.wipro.telestra_assignment.pages.HomePage;
import com.wipro.telestra_assignment.pages.LoginPage;
import com.wipro.telestra_assignment.pages.PurchasePage;

public class FlipkartTest extends BasePage {

	BasePage basePage;
	Properties prop;
	LoginPage loginPage;
	HomePage homePage;
	PurchasePage purchasePage;

	@Test(priority = 1, description = "verify Sign up link Test....")
	public void verifySignUpLinkTest() {
		
		loginPage = new LoginPage(driver1);
		Assert.assertTrue(loginPage.checkSignUpLink());
	}

	@Test(priority = 2, description = "verify Login Test....")
	public void loginSearchPurchaseTest() throws InterruptedException {
		loginPage = new LoginPage(driver1);
		homePage = new HomePage(driver1);
		purchasePage = new PurchasePage(driver1);
		loginPage.doLogin();
		homePage.verifySearchItem();
		purchasePage.verifyAddtoCartItem();
	}
		

	@AfterTest(alwaysRun=true)
	public void tearDown() {
		driver1.quit();
	}

}
