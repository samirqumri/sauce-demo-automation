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
		Object[][] allData = new Object[][] {
				{ "Ali", "Test", "createAccount", "ibrahim.newuser04@gmail.com", "QaTeam2026" },
				{ "Ali", "Test", "emptyEmailAndPassword", "", "" },
				{ "Ali", "Test", "emailWithoutAt", "ibrahimgmail.com", "QaTeam2026" },
				{ "Ali", "Test", "existingEmail", "ibrahim.newuser001@gmail.com", "QaTeam2026" },
				{ "Ali", "Test", "shortPassword", "ibrahim.short01@gmail.com", "Ab1c" }, };

		for (Object[] row : allData) {
			if (row[2].equals(method.getName())) {
				return new Object[][] { { row[0], row[1], row[3], row[4] } };
			}
		}
		return new Object[0][0];
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
<<<<<<< HEAD

	}

=======
}
>>>>>>> branch 'master' of https://github.com/samirqumri/sauce-demo-automation
