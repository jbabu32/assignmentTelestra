package com.wipro.telestra_assignment.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.wipro.telestra_assignment.utils.ExcelReader;

import io.github.bonigarcia.wdm.WebDriverManager;


public class BasePage {
	
	public static WebDriver driver1;
	public static Properties config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream fis;

	public static WebDriverWait wait;

	public static String browser;
	

	@BeforeMethod
	public WebDriver init_driver() {

		if (driver1 == null) {

			try {
				fis = new FileInputStream(
						System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				config.load(fis);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
					
				browser = config.getProperty("browser");
				
			}
			
			config.setProperty("browser", browser);

			if (config.getProperty("browser").equals("firefox")) {

				//WebDriverManager.firefoxdriver().setup();
				System.setProperty("webdriver.gecko.driver",
						System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\geckodriver.exe");
				
				// Initialize Firefox driver     
				driver1 = new FirefoxDriver();

			} else if (config.getProperty("browser").equals("chrome")) {
				
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\chromedriver.exe");

			//	WebDriverManager.chromedriver().setup();
				driver1 = new ChromeDriver();
				
			} else if (config.getProperty("browser").equals("ie")) {

				System.setProperty("webdriver.ie.driver",
						System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\IEDriverServer.exe");
				driver1 = new InternetExplorerDriver();

			}
			

			driver1.get(config.getProperty("url"));
			driver1.manage().window().maximize();
			driver1.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")),
					TimeUnit.SECONDS);
			wait = new WebDriverWait(driver1, 50);
			
			return driver1;
		}
	
}


