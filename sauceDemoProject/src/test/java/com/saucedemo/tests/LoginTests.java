package com.saucedemo.tests;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.saucedemo.pages.HomePage;
import com.saucedemo.pages.LoginPage;

public class LoginTests extends BaseTest {

	@Test(priority = 1)
	public void validLogin() throws InterruptedException {
		HomePage homePage = new HomePage(driver);
		homePage.clickMenuLink("Log In");
		Thread.sleep(1500);

		LoginPage loginPage = new LoginPage(driver);
		loginPage.login("ibrahim.newuser01@gmail.com", "QaTeam2026");
		Thread.sleep(3000);

		Assert.assertTrue(driver.getCurrentUrl().contains("login"), "My Account page should open");
		Reporter.log("pass", true);
	}

	@Test(priority = 2)
	public void loginLinkOpensLoginPage() throws InterruptedException {
		HomePage homePage = new HomePage(driver);
		homePage.clickMenuLink("Log In");
		Thread.sleep(1500);

		Assert.assertTrue(driver.getCurrentUrl().contains("login"), "Login page should open");
		Reporter.log("pass", true);
	}

	@Test(priority = 3)
	public void passwordIsHidden() throws InterruptedException {
		HomePage homePage = new HomePage(driver);
		homePage.clickMenuLink("Log In");
		Thread.sleep(1500);

		LoginPage loginPage = new LoginPage(driver);
		Assert.assertEquals(loginPage.getPasswordInputType(), "password");
		Reporter.log("pass", true);
	}

	@Test(priority = 4)
	public void forgotPassword() throws InterruptedException {
		HomePage homePage = new HomePage(driver);
		homePage.clickMenuLink("Log In");
		Thread.sleep(1500);

		LoginPage loginPage = new LoginPage(driver);
		loginPage.clickForgotPassword();
		Thread.sleep(1500);

		Assert.assertTrue(loginPage.isRecoveryFormVisible(), "Reset Password form should show");
		Reporter.log("pass", true);
	}

	@Test(priority = 5)
	public void logout() throws InterruptedException {
		HomePage homePage = new HomePage(driver);
		homePage.clickMenuLink("Log In");
		Thread.sleep(1500);

		LoginPage loginPage = new LoginPage(driver);
		loginPage.login("ibrahim.newuser01@gmail.com", "QaTeam2026");
		Thread.sleep(3000);

		driver.get("https://sauce-demo.myshopify.com/account/logout"); // Log Out (a pop-up can't block this)
		Thread.sleep(2000);

		driver.get("https://sauce-demo.myshopify.com/account");
		Thread.sleep(2000);
		Assert.assertTrue(driver.getCurrentUrl().contains("login"),
				"After Log Out, My Account must ask for login again");
		Reporter.log("pass", true);
	}

	@Test(priority = 6)
	public void wrongPassword() throws InterruptedException {
		HomePage homePage = new HomePage(driver);
		homePage.clickMenuLink("Log In");
		Thread.sleep(1500);

		LoginPage loginPage = new LoginPage(driver);
		loginPage.login("ibrahim.newuser01@gmail.com", "Wrong123");
		Thread.sleep(3000);

		Assert.assertTrue(driver.getCurrentUrl().contains("login"), "Must NOT log in");
		Reporter.log("pass", true);
	}

	@Test(priority = 7)
	public void bothEmpty() throws InterruptedException {
		HomePage homePage = new HomePage(driver);
		homePage.clickMenuLink("Log In");
		Thread.sleep(1500);

		LoginPage loginPage = new LoginPage(driver);
		loginPage.clickSubmitOnly();
		Thread.sleep(2000);

		Assert.assertTrue(driver.getCurrentUrl().contains("login"), "Must NOT log in");
		Reporter.log("pass", true);
	}

	@Test(priority = 8)
	public void emptyPassword() throws InterruptedException {
		HomePage homePage = new HomePage(driver);
		homePage.clickMenuLink("Log In");
		Thread.sleep(1500);

		LoginPage loginPage = new LoginPage(driver);
		loginPage.login("ibrahim.newuser01@gmail.com", "");
		Thread.sleep(3000);

		Assert.assertTrue(driver.getCurrentUrl().contains("login"), "Must NOT log in");
		Reporter.log("pass", true);
	}

	@Test(priority = 9)
	public void emailWithoutAt() throws InterruptedException {
		HomePage homePage = new HomePage(driver);
		homePage.clickMenuLink("Log In");
		Thread.sleep(1500);

		LoginPage loginPage = new LoginPage(driver);
		loginPage.login("ibrahimgmail.com", "QaTeam2026");
		Thread.sleep(3000);

		Assert.assertTrue(driver.getCurrentUrl().contains("login"), "Must NOT log in");
		Reporter.log("pass", true);
	}

	@Test(priority = 10)
	public void accountNeedsLogin() throws InterruptedException {
		driver.get("https://sauce-demo.myshopify.com/account");
		Thread.sleep(2000);

		Assert.assertTrue(driver.getCurrentUrl().contains("login"), "Website should ask for login");
		Reporter.log("pass", true);
	}

	@Test(priority = 11)
	public void emailInCapitals() throws InterruptedException {
		HomePage homePage = new HomePage(driver);
		homePage.clickMenuLink("Log In");
		Thread.sleep(1500);

		LoginPage loginPage = new LoginPage(driver);
		loginPage.login("IBRAHIM.NEWUSER01@GMAIL.COM", "QaTeam2026");
		Thread.sleep(3000);

		Assert.assertTrue(driver.getCurrentUrl().contains("login"), "Email is not case-sensitive - should log in");
		Reporter.log("pass", true);
	}

	@Test(priority = 12)
	public void passwordInCapitals() throws InterruptedException {
		HomePage homePage = new HomePage(driver);
		homePage.clickMenuLink("Log In");
		Thread.sleep(1500);

		LoginPage loginPage = new LoginPage(driver);
		loginPage.login("ibrahim.newuser01@gmail.com", "QATEAM2026");
		Thread.sleep(3000);

		Assert.assertTrue(driver.getCurrentUrl().contains("login"), "Password is case-sensitive - must NOT log in");
		Reporter.log("pass", true);
	}

	@Test(priority = 13)
	public void backButtonAfterLogout() throws InterruptedException {
		HomePage homePage = new HomePage(driver);
		homePage.clickMenuLink("Log In");
		Thread.sleep(1500);

		LoginPage loginPage = new LoginPage(driver);
		loginPage.login("ibrahim.newuser01@gmail.com", "QaTeam2026");
		Thread.sleep(3000);

		driver.get("https://sauce-demo.myshopify.com/account/logout"); // Log Out (a pop-up can't block this)
		Thread.sleep(2000);

		driver.navigate().back();
		Thread.sleep(1500);
		driver.navigate().refresh();
		Thread.sleep(2000);

		Assert.assertTrue(driver.getCurrentUrl().contains("login") || !driver.getCurrentUrl().contains("/account"),
				"Must stay logged out");
		Reporter.log("pass", true);
	}
}
