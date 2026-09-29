package com.saucedemo.tests;
 
import java.lang.reflect.Method;
 
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
 
import com.saucedemo.pages.HomePage;
import com.saucedemo.pages.SignUpPage;
 
public class SignUpTests extends BaseTest {
 
	@DataProvider(name = "signUpData")
	public Object[][] signUpData(Method method) {
		String testName = method.getName();
 
		if (testName.equals("createAccount")) {
			return new Object[][] { { "Ali", "Test", "ibrahim.newuser04@gmail.com", "QaTeam2026" } };
		} else if (testName.equals("emptyEmailAndPassword")) {
			return new Object[][] { { "Ali", "Test", "", "" } };
		} else if (testName.equals("emailWithoutAt")) {
			return new Object[][] { { "Ali", "Test", "ibrahimgmail.com", "QaTeam2026" } };
		} else if (testName.equals("existingEmail")) {
			return new Object[][] { { "Ali", "Test", "irandas@gmail\\.com", "QaTeam2026" } };
		} else if (testName.equals("shortPassword")) {
			return new Object[][] { { "Ali", "Test", "ibrahim.short01@gmail.com", "Ab1c" } };
		} else if (testName.equals("emptyFirstName")) {
			return new Object[][] { { "", "Test", "ibrahim.valid01@gmail.com", "QaTeam2026" } };
		} else if (testName.equals("emptyLastName")) {
			return new Object[][] { { "Ali", "", "ibrahim.valid01@gmail.com", "QaTeam2026" } };
		} else if (testName.equals("passwordInCapitals")) {
			return new Object[][] { { "Ali", "Test", "ibrahim.valid01@gmail.com", "QATEAM2026" } };
		}
 
		return new Object[][] { { "Ali", "Test", "ibrahim.newuser04@gmail.com", "QaTeam2026" } };
	}
 
	@Test(priority = 1, dataProvider = "signUpData")
	public void createAccount(String firstName, String lastName, String email, String password) {
		HomePage homePage = new HomePage(driver);
		homePage.clickMenuLink("Sign up");
 
		SignUpPage signUpPage = new SignUpPage(driver);
		signUpPage.signUp(firstName, lastName, email, password);
 
		Assert.assertFalse(signUpPage.isErrorDisplayed(), "Account should be created");
		Reporter.log("pass", true);
	}
 
	@Test(priority = 2, dataProvider = "signUpData")
	public void emptyEmailAndPassword(String firstName, String lastName, String email, String password) {
		HomePage homePage = new HomePage(driver);
		homePage.clickMenuLink("Sign up");
 
		SignUpPage signUpPage = new SignUpPage(driver);
		signUpPage.signUp(firstName, lastName, email, password);
 
		Assert.assertTrue(signUpPage.isErrorDisplayed() || driver.getCurrentUrl().contains("register"),
				"Account must NOT be created (error shown or still on the Sign up page)");
		Reporter.log("pass", true);
	}
 
	@Test(priority = 3, dataProvider = "signUpData")
	public void emailWithoutAt(String firstName, String lastName, String email, String password) {
		HomePage homePage = new HomePage(driver);
		homePage.clickMenuLink("Sign up");
 
		SignUpPage signUpPage = new SignUpPage(driver);
		signUpPage.signUp(firstName, lastName, email, password);
 
		Assert.assertTrue(signUpPage.isErrorDisplayed() || driver.getCurrentUrl().contains("register"),
				"Account must NOT be created (error shown or still on the Sign up page)");
		Reporter.log("pass", true);
	}
 
	@Test(priority = 4, dataProvider = "signUpData")
	public void existingEmail(String firstName, String lastName, String email, String password)
			throws InterruptedException {
		HomePage homePage = new HomePage(driver);
		homePage.clickMenuLink("Sign up");
 
		SignUpPage signUpPage = new SignUpPage(driver);
		signUpPage.signUp(firstName, lastName, email, password);
 
		Thread.sleep(3000);
		String currentUrl = driver.getCurrentUrl();
		String pageContent = driver.getPageSource().toLowerCase();
 
		boolean isBlockedOrStuck = currentUrl.contains("register") || pageContent.contains("challenge")
				|| pageContent.contains("captcha");
 
		Assert.assertTrue(isBlockedOrStuck);
		Reporter.log("pass", true);
	}
 
	@Test(priority = 5, dataProvider = "signUpData")
	public void shortPassword(String firstName, String lastName, String email, String password)
			throws InterruptedException {
		HomePage homePage = new HomePage(driver);
		homePage.clickMenuLink("Sign up");
 
		SignUpPage signUpPage = new SignUpPage(driver);
		signUpPage.signUp(firstName, lastName, email, password);
 
		Thread.sleep(3000);
		String currentUrl = driver.getCurrentUrl();
		String pageContent = driver.getPageSource().toLowerCase();
 
		boolean isBlockedOrStuck = currentUrl.contains("register") || pageContent.contains("challenge")
				|| pageContent.contains("captcha");
 
		Assert.assertTrue(isBlockedOrStuck);
		Reporter.log("pass", true);
	}
 
	@Test(priority = 6, dataProvider = "signUpData")
	public void emptyFirstName(String firstName, String lastName, String email, String password) {
		HomePage homePage = new HomePage(driver);
		homePage.clickMenuLink("Sign up");
 
		SignUpPage signUpPage = new SignUpPage(driver);
		signUpPage.signUp(firstName, lastName, email, password);
 
		Assert.assertTrue(signUpPage.isErrorDisplayed() || driver.getCurrentUrl().contains("register"));
		Reporter.log("pass", true);
	}
 
	@Test(priority = 7, dataProvider = "signUpData")
	public void emptyLastName(String firstName, String lastName, String email, String password) {
		HomePage homePage = new HomePage(driver);
		homePage.clickMenuLink("Sign up");
 
		SignUpPage signUpPage = new SignUpPage(driver);
		signUpPage.signUp(firstName, lastName, email, password);
 
		Assert.assertTrue(signUpPage.isErrorDisplayed() || driver.getCurrentUrl().contains("register"));
		Reporter.log("pass", true);
	}
 
	@Test(priority = 8, dataProvider = "signUpData")
	public void passwordInCapitals(String firstName, String lastName, String email, String password) {
		HomePage homePage = new HomePage(driver);
		homePage.clickMenuLink("Sign up");
 
		SignUpPage signUpPage = new SignUpPage(driver);
		signUpPage.signUp(firstName, lastName, email, password);
 
		Assert.assertTrue(signUpPage.isErrorDisplayed() || driver.getCurrentUrl().contains("register"));
		Reporter.log("pass", true);
	}
 
	@Test(priority = 9)
	public void signUpLinkOpensSignUpPage() {
		HomePage homePage = new HomePage(driver);
		homePage.clickMenuLink("Sign up");
 
		Assert.assertTrue(driver.getCurrentUrl().contains("register") || driver.getCurrentUrl().contains("signup"));
		Reporter.log("pass", true);
	}
}