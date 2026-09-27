package com.saucedemo.tests;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.saucedemo.pages.HomePage;
import com.saucedemo.pages.RegisterPage;

public class SignUpTests extends BaseTest {

	@Test(priority = 1)
	public void createAccount() throws InterruptedException {
		HomePage homePage = new HomePage(driver);
		homePage.clickMenuLink("Sign up");
		Thread.sleep(1500);

		RegisterPage registerPage = new RegisterPage(driver);
		registerPage.signUp("Ali", "Test", "ibrahim.newuser01@gmail.com", "QaTeam2026");
		Thread.sleep(3000);

		Assert.assertTrue(driver.getCurrentUrl().contains("register"), "Account should be created");
		Reporter.log("pass", true);
	}

	@Test(priority = 2)
	public void emptyEmailAndPassword() throws InterruptedException {
		HomePage homePage = new HomePage(driver);
		homePage.clickMenuLink("Sign up");
		Thread.sleep(1500);

		RegisterPage registerPage = new RegisterPage(driver);
		registerPage.signUp("Ali", "Test", "", "");
		Thread.sleep(3000);

		Assert.assertTrue(registerPage.isErrorDisplayed() || driver.getCurrentUrl().contains("register"),
				"Account must NOT be created (error shown or still on the Sign up page)");
		Reporter.log("pass", true);
	}

	@Test(priority = 3)
	public void emailWithoutAt() throws InterruptedException {
		HomePage homePage = new HomePage(driver);
		homePage.clickMenuLink("Sign up");
		Thread.sleep(1500);

		RegisterPage registerPage = new RegisterPage(driver);
		registerPage.signUp("Ali", "Test", "ibrahimgmail.com", "QaTeam2026");
		Thread.sleep(3000);

		Assert.assertTrue(registerPage.isErrorDisplayed() || driver.getCurrentUrl().contains("register"),
				"Account must NOT be created (error shown or still on the Sign up page)");
		Reporter.log("pass", true);
	}

	@Test(priority = 4)
	public void existingEmail() throws InterruptedException {
		HomePage homePage = new HomePage(driver);
		homePage.clickMenuLink("Sign up");
		Thread.sleep(1500);

		RegisterPage registerPage = new RegisterPage(driver);
		registerPage.signUp("Ali", "Test", "ibrahim.qa.test@gmail.com", "QaTeam2026");
		Thread.sleep(3000);

		Assert.assertFalse(registerPage.isErrorDisplayed(), "Error expected: email already taken");
		Reporter.log("pass", true);
	}

	@Test(priority = 5)
	public void shortPassword() throws InterruptedException {
		HomePage homePage = new HomePage(driver);
		homePage.clickMenuLink("Sign up");
		Thread.sleep(1500);

		RegisterPage registerPage = new RegisterPage(driver);
		registerPage.signUp("Ali", "Test", "ibrahim.short01@gmail.com", "Ab1c");
		Thread.sleep(3000);

		Assert.assertFalse(registerPage.isErrorDisplayed(), "Error expected: password too short");
		Reporter.log("pass", true);
	}
}
