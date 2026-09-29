package com.saucedemo.tests;
 
import java.time.Duration;
 
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
 
public class BaseTest {
	protected WebDriver driver;
	private static final String url = "https://sauce-demo.myshopify.com";
 
	@BeforeMethod
	public void setUp() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
 
		driver.get(url);
		driver.manage().deleteAllCookies();
		driver.navigate().refresh();
	}
 
	@AfterMethod
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
}