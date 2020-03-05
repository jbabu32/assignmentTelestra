package com.wipro.telestra_assignment.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.wipro.telestra_assignment.base.BasePage;


public class JavaScriptUtil extends BasePage{

	//WebDriver driver;

	public JavaScriptUtil(WebDriver driver) {
		driver1 = driver;
	}

	public void clickElementByJS(WebElement element) {
		JavascriptExecutor js = ((JavascriptExecutor) driver1);
		js.executeScript("arguments[0].click();", element);

	}

	public void refreshBrowserByJS() {
		JavascriptExecutor js = ((JavascriptExecutor) driver1);
		js.executeScript("history.go(0)");
	}

	public String getTitleByJS() {
		JavascriptExecutor js = ((JavascriptExecutor) driver1);
		String title = js.executeScript("return document.title;").toString();
		return title;
	}

	public void scrollPageDown() {
		JavascriptExecutor js = ((JavascriptExecutor) driver1);
		js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
	}

	public void scrollIntoView(WebElement element) {
		JavascriptExecutor js = ((JavascriptExecutor) driver1);
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public void checkPageIsReady() {
		JavascriptExecutor js = (JavascriptExecutor) driver1;
		// Initially bellow given if condition will check ready state of page.
		if (js.executeScript("return document.readyState").toString().equals("complete")) {
			System.out.println("Page Is loaded.");
			return;
		}

	}

}
