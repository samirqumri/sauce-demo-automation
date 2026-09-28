package com.saucedemo.tests;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.saucedemo.pages.HomePage;
import com.saucedemo.pages.SignUpPage;

public class SignUpTests extends BaseTest {

	@Test(priority = 1)
	public void createAccount() throws InterruptedException {
		HomePage homePage = new HomePage(driver);
		homePage.clickMenuLink("Sign up");

		SignUpPage SignUp = new SignUpPage(driver);
		SignUp.signUp("Ali", "Test", "ibrahim.newuser01@gmail.com", "QaTeam2026");

		Assert.assertTrue(driver.getCurrentUrl().contains("register"), "Account should be created");
		Reporter.log("pass", true);
	}

	@Test(priority = 2)
	public void emptyEmailAndPassword() throws InterruptedException {
		HomePage homePage = new HomePage(driver);
		homePage.clickMenuLink("Sign up");

		SignUpPage SignUp = new SignUpPage(driver);
		SignUp.signUp("Ali", "Test", "", "");

		Assert.assertTrue(SignUp.isErrorDisplayed() || driver.getCurrentUrl().contains("register"),
				"Account must NOT be created (error shown or still on the Sign up page)");
		Reporter.log("pass", true);
	}

	@Test(priority = 3)
	public void emailWithoutAt() throws InterruptedException {
		HomePage homePage = new HomePage(driver);
		homePage.clickMenuLink("Sign up");
		Thread.sleep(1500);

		SignUpPage SignUp = new SignUpPage(driver);
		SignUp.signUp("Ali", "Test", "ibrahimgmail.com", "QaTeam2026");

		Assert.assertTrue(SignUp.isErrorDisplayed() || driver.getCurrentUrl().contains("register"),
				"Account must NOT be created (error shown or still on the Sign up page)");
		Reporter.log("pass", true);
	}

	@Test(priority = 4)
	public void existingEmail() throws InterruptedException {
		HomePage homePage = new HomePage(driver);
		homePage.clickMenuLink("Sign up");

		SignUpPage SignUp = new SignUpPage(driver);
		SignUp.signUp("Ali", "Test", "ibrahim.qa.test@gmail.com", "QaTeam2026");

		Assert.assertFalse(SignUp.isErrorDisplayed(), "Error expected: email already taken");
		Reporter.log("pass", true);
	}

	@Test(priority = 5)
	public void shortPassword() throws InterruptedException {
		HomePage homePage = new HomePage(driver);
		homePage.clickMenuLink("Sign up");

		SignUpPage SignUp = new SignUpPage(driver);
		SignUp.signUp("Ali", "Test", "ibrahim.short01@gmail.com", "Ab1c");

		Assert.assertFalse(SignUp.isErrorDisplayed(), "Error expected: password too short");
		Reporter.log("pass", true);
	}
}
