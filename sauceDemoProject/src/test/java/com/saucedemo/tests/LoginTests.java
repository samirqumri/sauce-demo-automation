package com.saucedemo.tests;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.saucedemo.pages.HomePage;
import com.saucedemo.pages.LoginPage;

public class LoginTests extends BaseTest {

	@DataProvider(name = "loginData")
	public Object[][] loginData(Method method) {
		Object[][] allData = new Object[][] {
			// test name               email                          password
			{ "validLogin",            "ibrahim.newuser01@gmail.com", "QaTeam2026" },   // correct email + correct password
			{ "logout",                "ibrahim.newuser01@gmail.com", "QaTeam2026" },   // correct email + correct password
			{ "wrongPassword",         "ibrahim.newuser01@gmail.com", "Wrong123"   },   // correct email + wrong password
			{ "bothEmpty",             "",                            ""           },   // email and password empty
			{ "emptyPassword",         "ibrahim.newuser01@gmail.com", ""           },   // correct email + empty password
			{ "emailWithoutAt",        "ibrahimgmail.com",            "QaTeam2026" },   // email without @
			{ "emailInCapitals",       "IBRAHIM.NEWUSER01@GMAIL.COM", "QaTeam2026" },   // email in capital letters
			{ "passwordInCapitals",    "ibrahim.newuser01@gmail.com", "QATEAM2026" },   // password in capital letters
			{ "backButtonAfterLogout", "ibrahim.newuser01@gmail.com", "QaTeam2026" },   // correct email + correct password
		};

		// return only the row that has the same name as the test method
		for (Object[] row : allData) {
			if (row[0].equals(method.getName())) {
				return new Object[][] { { row[1], row[2] } };
			}
		}
		return new Object[0][0];
	}

	@Test(priority = 1, dataProvider = "loginData")
	public void validLogin(String email, String password) {
		HomePage homePage = new HomePage(driver);
		homePage.clickMenuLink("Log In");

		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(email, password);
		loginPage.waitForMyAccount();

		Assert.assertFalse(driver.getCurrentUrl().contains("login"), "My Account page should open");
		Reporter.log("pass", true);
	}

	@Test(priority = 2)
	public void loginLinkOpensLoginPage() {
		HomePage homePage = new HomePage(driver);
		homePage.clickMenuLink("Log In");

		Assert.assertTrue(driver.getCurrentUrl().contains("login"), "Login page should open");
		Reporter.log("pass", true);
	}

	@Test(priority = 3)
	public void passwordIsHidden() {
		HomePage homePage = new HomePage(driver);
		homePage.clickMenuLink("Log In");

		LoginPage loginPage = new LoginPage(driver);
		Assert.assertEquals(loginPage.getPasswordInputType(), "password");
		Reporter.log("pass", true);
	}

	@Test(priority = 4)
	public void forgotPassword() {
		HomePage homePage = new HomePage(driver);
		homePage.clickMenuLink("Log In");

		LoginPage loginPage = new LoginPage(driver);
		loginPage.clickForgotPassword();

		Assert.assertTrue(loginPage.isRecoveryFormVisible(), "Reset Password form should show");
		Reporter.log("pass", true);
	}

	@Test(priority = 5, dataProvider = "loginData")
	public void logout(String email, String password) {
		HomePage homePage = new HomePage(driver);
		homePage.clickMenuLink("Log In");

		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(email, password);

		loginPage.logout();

		loginPage.openMyAccount();
		Assert.assertTrue(driver.getCurrentUrl().contains("login"),
				"After Log Out, My Account must ask for login again");
		Reporter.log("pass", true);
	}

	@Test(priority = 6, dataProvider = "loginData")
	public void wrongPassword(String email, String password) {
		HomePage homePage = new HomePage(driver);
		homePage.clickMenuLink("Log In");

		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(email, password);

		Assert.assertTrue(driver.getCurrentUrl().contains("login"), "Must NOT log in");
		Reporter.log("pass", true);
	}

	@Test(priority = 7, dataProvider = "loginData")
	public void bothEmpty(String email, String password) {
		HomePage homePage = new HomePage(driver);
		homePage.clickMenuLink("Log In");

		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(email, password);

		Assert.assertTrue(driver.getCurrentUrl().contains("login"), "Must NOT log in");
		Reporter.log("pass", true);
	}

	@Test(priority = 8, dataProvider = "loginData")
	public void emptyPassword(String email, String password) {
		HomePage homePage = new HomePage(driver);
		homePage.clickMenuLink("Log In");

		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(email, password);

		Assert.assertTrue(driver.getCurrentUrl().contains("login"), "Must NOT log in");
		Reporter.log("pass", true);
	}

	@Test(priority = 9, dataProvider = "loginData")
	public void emailWithoutAt(String email, String password) {
		HomePage homePage = new HomePage(driver);
		homePage.clickMenuLink("Log In");

		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(email, password);

		Assert.assertTrue(driver.getCurrentUrl().contains("login"), "Must NOT log in");
		Reporter.log("pass", true);
	}

	@Test(priority = 10)
	public void accountNeedsLogin() {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.openMyAccount();

		Assert.assertTrue(driver.getCurrentUrl().contains("login"), "Website should ask for login");
		Reporter.log("pass", true);
	}

	@Test(priority = 11, dataProvider = "loginData")
	public void emailInCapitals(String email, String password) {
		HomePage homePage = new HomePage(driver);
		homePage.clickMenuLink("Log In");

		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(email, password);
		loginPage.waitForMyAccount();

		Assert.assertFalse(driver.getCurrentUrl().contains("login"), "Email is not case-sensitive - should log in");
		Reporter.log("pass", true);
	}

	@Test(priority = 12, dataProvider = "loginData")
	public void passwordInCapitals(String email, String password) {
		HomePage homePage = new HomePage(driver);
		homePage.clickMenuLink("Log In");

		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(email, password);

		Assert.assertTrue(driver.getCurrentUrl().contains("login"), "Password is case-sensitive - must NOT log in");
		Reporter.log("pass", true);
	}

	@Test(priority = 13, dataProvider = "loginData")
	public void backButtonAfterLogout(String email, String password) {
		HomePage homePage = new HomePage(driver);
		homePage.clickMenuLink("Log In");

		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(email, password);

		loginPage.logout();

		driver.navigate().back();
		driver.navigate().refresh();

		Assert.assertTrue(driver.getCurrentUrl().contains("login") || !driver.getCurrentUrl().contains("/account"),
				"Must stay logged out");
		Reporter.log("pass", true);
	}
}